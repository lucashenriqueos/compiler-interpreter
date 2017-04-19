package org.lucashos.analyzer;

import org.lucashos.util.Token;
import org.lucashos.util.TokenClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 28/02/17.
 */
public class SyntaticAnalyzer {
    private List<Token> tokens;
    private Token token;
    private int pos;
    private List<String> errorList;

    public SyntaticAnalyzer(List<Token> code) {
        this.tokens = code;
        pos = -1;
        readToken();
        errorList = new ArrayList<>();
    }

    private void readToken() {
        token = tokens.get(++pos);
    }

    private Token lookAhead() {
        return tokens.get(pos + 1);
    }

    private void log(String s, Token token) {
        System.out.printf("Expected %s at position %d:%d", s, token.getLine(), token.getColumn());
        errorList.add(String.format("Expected %s at position %d:%d", s, token.getLine(), token.getColumn()));
    }

    public void analyze() {
        if(token.getValue().equals("(")) {
            readToken();
            func();
        } else log("(", token);
    }

    //<funcs> ::= <func> <funcs> |
    private void funcs() {

    }

    //<func> ::= '(' 'func' id '(' <params> ')' <tipo> <cmds> ')'
    private void func() {
        if(token.getValue().equals("(")) {
            readToken();
            if(token.getValue().equals("func")) {
            readToken();
                if(token.isIdentifier()) {
                    readToken();
                    if(token.getValue().equals("(")) {
                        readToken();
                        params();
                    } else log("(", token);
                } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);
            } else log("func", token);
        } else log ("(", token);
    }

    // <params> ::= <param> <params> |
    private void params() {
        if(token.isTipo()) {
            param();
            params();
        }
    }

    // <param> ::= <tipo> id
    private void param() {
        if(token.isTipo()) {
            tipo();
            if(token.isIdentifier()) {
                readToken();
            } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);
        }
    }

    // <tipo> ::= 'nada' | 'int' | 'real' | 'texto' | 'logico'
    private void tipo() {
        if (token.isTipo()) {
            readToken();
        } else log("tipo", token);
    }

    // <cmds> ::= <cmd> <cmds> |
    private void cmds() {
        if(token.getValue().equals("(")) {
            cmd();
            cmds();
        }
    }

    // <cmd> ::= '(' <cmdint> ')'
    private void cmd() {
        if(token.getValue().equals("(")) {
            readToken();
            cmdInt();
            if(token.getValue().equals(")")) {
                readToken();
            } else log("(", token);
        } else log("(", token);
    }

    // <cmdint> ::= <decl> | <atrib> | <leitura> | <escrita> | <cond> | <laco> | <invoca>
    private void cmdInt() {
    }


    // <decl> ::= <tipo> <ids>
    private void decl() {
        if(token.isTipo()) {
            tipo();
            ids();
        }
    }

    // <ids> ::= id <ids> | id
    private void ids() {
        if(token.isIdentifier()) {
            readToken();
            if(lookAhead().isIdentifier()) {
                ids();
            }
        } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);
    }

    //<atrib> ::= '=' id <exp>
    private void attrib() {
        if(token.getValue().equals("=")) {
            readToken();
            if(token.getTokenClass().equals(TokenClass.IDENTIFIER)) {
                readToken();
                exp();
            } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);
        } else log("=", token);
    }

    //<exp> ::= <operan> | '(' <op> <exp> <exp> ')'
    private void exp() {
        Token ahead = lookAhead();
        if(ahead.isOperator()) {
            readToken();
            op();
            exp();
            exp();
        } else if(ahead.isIdentifier()) {
            operan();
        }
    }

    //<operan> ::= id | cli | cll | cls | clr | '(' <invoca> ')'
    private void operan() {
        if(token.getValue().equals("(")) {
            readToken();
            invoca();
            if (token.getValue().equals(")")) {
                readToken();
            } else log(")", token);
        } else if(token.isTerminal()) {
            readToken();
        } else log("id | cli | cll | cls | clr | '('", token);
    }

    //<op> ::= '&' | '|' | '>' | '<' | '>=' | '<=' | '==' | '!=' | '+' | '-' | '*' | '/' | '.'
    private void op() {
        if (token.isOperator()) {
            readToken();
        } else log(TokenClass.OPERATOR.getFriendlyName(), token);
    }

    //<leitura> ::= 'le' id
    private void leitura() {
        if(token.getValue().equals("le")) {
            readToken();
            if(token.isIdentifier()) {
                readToken();
            } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);
        } else log("le", token);
    }

    //<escrita> ::= 'escreve' <exp>
    private void escrita() {
        if(token.getValue().equals("escreve")) {
            readToken();
            exp();
        } else log("escreve", token);
    }

    //<cond> ::= 'se' <exp> '(' <cmds> ')' <senao>
    private void cond() {
        if(token.getValue().equals("se")) {
            readToken();
            exp();
            if(token.getValue().equals("(")) {
                readToken();
                cmds();
                if(token.getValue().equals(")")) {
                    readToken();
                    senao();
                } else log(")", token);
            } else log("(", token);
        } else log("se", token);
    }

    //<senao> ::= '(' <cmds> ')' |
    private void senao() {
        if(token.getValue().equals("(")) {
            readToken();
            cmds();
            if (token.getValue().equals(")")) {
                readToken();
            }
        }
    }

    //<laco> ::= 'enquanto' <exp> <cmds>
    private void laco () {
        if(token.getValue().equals("enquanto")) {
            readToken();
            exp();
            cmds();
        } else log("enquanto", token);
    }

    //<invoca> ::= id <args>
    private void  invoca () {
        if(token.isIdentifier()) {
            readToken();
            args();
        } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);
    }

    //<args> ::= <operan> <args> |
    private void args() {
        Token ahead = lookAhead();
        if ( token.getValue().equals("(") || ahead.isIdentifier() || token.isLiteral()) {
            operan();
            args();
        }
    }

}
