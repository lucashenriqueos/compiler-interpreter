package org.lucashos.analyzer;

import org.lucashos.util.Node;
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
    private Node root;

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
        errorList.add(String.format("Expected %s at position %d:%d", s, token.getLine(), token.getColumn()));
    }

    private void mostraArvore() {
        mostraNo(root, "");
    }

    private void mostraNo(Node no, String espaco) {
        System.out.println(espaco + no);
        for(Node filho: no.getChildren()) {
            mostraNo(filho, espaco+ "   ");
        }
    }


    public Node analyze() {
        if(token.getValue().equals("(")) {
           root = funcs();
        } else log("(", token);

        if(errorList.isEmpty()) {
            mostraArvore();
        } else {
            System.out.println(errorList);
        }
        return root;
    }

    //<funcs> ::= <func> <funcs> |
    private Node funcs() {
        Node funcs = new Node("funcs");
        if(token.getValue().equals("(")) {
            funcs.addChild(func());
            funcs();
        }
        return funcs;
    }

    //<func> ::= '(' 'func' id '(' <params> ')' <tipo> <cmds> ')'
    private Node func() {
        Node func = new Node("func");
        if(token.getValue().equals("(")) {
            func.addChild(new Node(token));
            readToken();
            if(token.getValue().equals("func")) {
            func.addChild(new Node(token));
            readToken();
                if(token.isIdentifier()) {
                    func.addChild(new Node(token));
                    readToken();
                    if(token.getValue().equals("(")) {
                        func.addChild(new Node(token));
                        readToken();
                        func.addChild(params());
                        if(token.getValue().equals(")")) {
                            func.addChild(new Node(token));
                            readToken();
                            if(token.isTipo()) {
                                func.addChild(tipo());
                                func.addChild(cmds());
                                if(!token.getValue().equals(")")) {
                                    func.addChild(new Node(token));
                                    log(")", token);
                                }
                            }
                        } else log("(", token);
                    } else log("(", token);
                } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);
            } else log("func", token);
        } else log ("(", token);
        return func;
    }

    // <params> ::= <param> <params> |
    private Node params() {
        Node params = new Node("params");
        if(token.isTipo()) {
            params.addChild(param());
            params.addChild(params());
        } else if (!token.getValue().equals(")")) {
            log("Params ou )", token);
        }
        return params;
    }

    // <param> ::= <tipo> id
    private Node param() {
        Node param = new Node("param");
        if(token.isTipo()) {
            param.addChild(tipo());
            if(token.isIdentifier()) {
                param.addChild(new Node(token));
                readToken();
            } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);
        }
        return param;
    }

    // <tipo> ::= 'nada' | 'int' | 'real' | 'texto' | 'logico'
    private Node tipo() {
        Node tipo = new Node("tipo");
        if (token.isTipo()) {
            tipo.addChild(new Node(token));
            readToken();
        } else log("tipo", token);
        return tipo;
    }

    // <cmds> ::= <cmd> <cmds> |
    private Node cmds() {
        Node node = new Node("cmds");
        if(token.getValue().equals("(")) {
            node.addChild(cmd());
            if(token.getValue().equals("(")) {
                node.addChild(cmds());
            }
        }

        return node;
    }

    // <cmd> ::= '(' <cmdint> ')'
    private Node cmd() {
        Node cmd = new Node("cmd");
        if(token.getValue().equals("(")) {
            cmd.addChild(new Node(token));
            readToken();
            cmd.addChild(cmdInt());
            if(token.getValue().equals(")")) {
                cmd.addChild(new Node(token));
                readToken();
            } else log("(", token);
        } else log("(", token);
        return cmd;
    }

    // <cmdint> ::= <decl> | <atrib> | <leitura> | <escrita> | <cond> | <laco> | <invoca>
    private Node cmdInt() {
        Node node = new Node("cmdInt");
        if(token.getValue().equals("=")) {
            node.addChild(attrib());
        } else if(token.isTipo()) {
            node.addChild(decl());
        } else if(token.getValue().equals("le")) {
            node.addChild(leitura());
        } else if(token.getValue().equals("escreve")) {
            node.addChild(escrita());
        } else if(token.getValue().equals("se")) {
            node.addChild(cond());
        } else if(token.getValue().equals("enquanto")) {
            node.addChild(laco());
        } else if(token.isIdentifier()) {
            node.addChild(invoca());
        } else {
            log("Decl, Atrib, leitura, escrita, cond, laco, invoca", token);
        }

        return node;
    }


    // <decl> ::= <tipo> <ids>
    private Node decl() {
        Node node = new Node("decl");
        if(token.isTipo()) {
            node.addChild(tipo());
            node.addChild(ids());
        }

        return node;
    }

    // <ids> ::= id <ids> | id
    private Node ids() {
        Node node = new Node("ids");
        if(token.isIdentifier()) {
            node.addChild(new Node(token));
            readToken();
            if(lookAhead().isIdentifier()) {
                node.addChild(ids());
            } else {
                node.addChild(new Node(token));
                readToken();
            }
        } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);
        return node;
    }

    //<atrib> ::= '=' id <exp>
    private Node attrib() {
        Node node = new Node("attrib");
        if(token.getValue().equals("=")) {
            node.addChild(new Node(token));
            readToken();
            if(token.getTokenClass().equals(TokenClass.IDENTIFIER)) {
                node.addChild(new Node(token));
                readToken();
                node.addChild(exp());
            } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);
        } else log("=", token);

        return node;
    }

    //<exp> ::= <operan> | '(' <op> <exp> <exp> ')'
    private Node exp() {
        Node node = new Node("exp");
        Token ahead = lookAhead();
        if(ahead.isOperator() && token.getValue().equals("(")) {
            node.addChild(new Node(token));
            readToken();
            node.addChild(op());
            node.addChild(exp());
            node.addChild(exp());
            if(token.getValue().equals(")")) {
                node.addChild(new Node(token));
                readToken();
            } else log(")", token);
        } else if(token.isTerminal() || token.getValue().equals("(")) {
            node.addChild(operan());
        } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);

        return node;
    }

    //<operan> ::= id | cli | cll | cls | clr | '(' <invoca> ')'
    private Node operan() {
        Node node = new Node("opean");
        if(token.getValue().equals("(")) {
            node.addChild(new Node(token));
            readToken();
            node.addChild(invoca());
            if (token.getValue().equals(")")) {
                node.addChild(new Node(token));
                readToken();
            } else log(")", token);
        } else if(token.isTerminal()) {
            node.addChild(new Node(token));
            readToken();
        } else log("id | cli | cll | cls | clr | '('", token);

        return node;
    }

    //<op> ::= '&' | '|' | '>' | '<' | '>=' | '<=' | '==' | '!=' | '+' | '-' | '*' | '/' | '.'
    private Node op() {
        Node node =  new Node("op");
        if (token.isOperator()) {
            node.addChild(new Node(token));
            readToken();
        } else log(TokenClass.OPERATOR.getFriendlyName(), token);

        return node;
    }

    //<leitura> ::= 'le' id
    private Node leitura() {
        Node node = new Node("leitura");
        if(token.getValue().equals("le")) {
            node.addChild(new Node(token));
            readToken();
            if(token.isIdentifier()) {
                node.addChild(new Node(token));
                readToken();
            } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);
        } else log("le", token);

        return node;
    }

    //<escrita> ::= 'escreve' <exp>
    private Node escrita() {
        Node node = new Node("escrita");
        if(token.getValue().equals("escreve")) {
            node.addChild(new Node(token));
            readToken();
            node.addChild(exp());
        } else log("escreve", token);
        return node;
    }

    //<cond> ::= 'se' <exp> '(' <cmds> ')' <senao>
    private Node cond() {
        Node node = new Node("cond");
        if(token.getValue().equals("se")) {
            node.addChild(new Node(token));
            readToken();
            node.addChild(exp());
            if(token.getValue().equals("(")) {
                node.addChild(new Node(token));
                readToken();
                node.addChild(cmds());
                if(token.getValue().equals(")")) {
                    node.addChild(new Node(token));
                    readToken();
                    senao();
                } else log(")", token);
            } else log("(", token);
        } else log("se", token);
        return node;
    }

    //<senao> ::= '(' <cmds> ')' |
    private Node senao() {
        Node node = new Node("senao");
        if(token.getValue().equals("(")) {
            node.addChild(new Node(token));
            readToken();
            node.addChild(cmds());
            if (token.getValue().equals(")")) {
                node.addChild(new Node(token));
                readToken();
            }
        }
        return node;
    }

    //<laco> ::= 'enquanto' <exp> <cmds>
    private Node laco () {
        Node node = new Node("laco");
        if(token.getValue().equals("enquanto")) {
            node.addChild(new Node(token));
            readToken();
            node.addChild(exp());
            node.addChild(cmds());
        } else log("enquanto", token);

        return node;
    }

    //<invoca> ::= id <args>
    private Node  invoca () {
        Node node = new Node("invoca");
        if(token.isIdentifier()) {
            node.addChild(new Node(token));
            readToken();
            node.addChild(args());
        } else log(TokenClass.IDENTIFIER.getFriendlyName(), token);

        return node;
    }

    //<args> ::= <operan> <args> |
    private Node args() {
        Node node = new Node("args");
        node.addChild(new Node(token));
        Token ahead = lookAhead();
        if ( token.getValue().equals("(") || ahead.isIdentifier() || token.isLiteral()) {
            node.addChild(operan());
            node.addChild(args());
        }

        return node;
    }

}
