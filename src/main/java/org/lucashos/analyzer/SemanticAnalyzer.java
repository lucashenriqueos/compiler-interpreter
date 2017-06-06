package org.lucashos.analyzer;

import org.lucashos.util.Node;
import org.lucashos.util.Token;
import org.lucashos.util.TokenTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 28/02/17.
 */
public class SemanticAnalyzer {
    private final TokenTable table;
    private List<Token> tokens;
    private Token token;
    private int pos;
    private Node root;
    private List<String> errorList;

    public SemanticAnalyzer(TokenTable table, Node node) {
        this.table = table;
        this.tokens = table.getTokens();
        this.root = node;
        pos = -1;
        readToken();
        errorList = new ArrayList<>();
    }

    public void run() {
        funcs(root);

        if(!errorList.isEmpty()) {
            System.out.println(errorList);
        }

    }

    private void funcs(Node no) {
        func(no.getChild(0));
    }

    private void func(Node no) {
        params(no.getChild(4));
        tipo(no.getChild(6));
        cmds(no.getChild(7));
    }

    private Object params(Node node) {
        if(node.getChildren().isEmpty())
            return new ArrayList<>();
        else {
            if(node.getChildren().size() == 2) {
                List<Token> params = (List<Token>) params(node.getChild(1));
                params.add(0, (Token) param(node.getChild(0)));
                return params;
            } else {
                List<Token> params = new ArrayList<>();
                params.add(node.getChild(0).getToken());
                return params;
            }
        }
    }

    private Object param(Node node) {
        String tipo = (String) tipo(node.getChild(0));
        String symbolType = table.getSymbolType(node.getChild(1).getToken().getIndex());

        if(symbolType == null) {
            table.setSymbolType(tipo, node.getChild(1).getToken());
        } else log("deu alguma coisa errada ai jovem! O parametro tá repetindo..", node.getChild(1).getToken());

        return node.getChild(1).getToken();
    }

    private Object tipo(Node node) {
        return node.getChild(0).getToken().getValue();
    }

    private Object cmds(Node no) {
        if(no.getChildren().size() == 2) {
            return cmds(no.getChild(1));
        } else {
            return cmd(no.getChild(0));
        }
    }

    private Object cmd(Node no) {
        return cmdInt(no.getChild(1));
    }

    private Object cmdInt(Node no) {
        if(no.getChildType(0).equals("decl")) {
            return decl(no.getChild(0));
        }
        return null;
    }

    private Object decl(Node node) {
        String tipo = (String) tipo(node.getChild(0));
        List<Token> ids = (List<Token>) ids(node.getChild(1));
        ids.forEach(id -> {
            String symbolType = table.getSymbolType(id.getIndex());
            if(null != symbolType) {
                log("id redeclarado", id);
            } else {
                table.setSymbolType(tipo, id);
            }
        });
        return null;
    }

    private Object ids(Node node) {
        if(node.getChildren().size() == 2) {
            Token id = node.getChild(0).getToken();
            List<Token> ids = (List<Token>) ids(node.getChild(1));
            ids.add(0, id);
            return ids;
        } else {
            List<Token> ids = new ArrayList<>();
            ids.add(node.getChild(0).getToken());
            return ids;
        }
    }

    private Object attrib(Node node) {
        return null;
    }

    private Object exp(Node node) {
        return node;
    }

    //<operan> ::= id | cli | cll | cls | clr | '(' <invoca> ')'
    private Object operan(Node node) {
        return node;
    }

    //<op> ::= '&' | '|' | '>' | '<' | '>=' | '<=' | '==' | '!=' | '+' | '-' | '*' | '/' | '.'
    private Object op(Node node) {
        return node;
    }

    //<leitura> ::= 'le' id
    private Object leitura(Node node) {
        return node;
    }

    //<escrita> ::= 'escreve' <exp>
    private Object escrita(Node node) {
        return node;
    }

    //<cond> ::= 'se' <exp> '(' <cmds> ')' <senao>
    private Object cond(Node node) {
        return node;
    }

    //<senao> ::= '(' <cmds> ')' |
    private Object senao(Node node) {
        return node;
    }

    //<laco> ::= 'enquanto' <exp> <cmds>
    private Object laco (Node node) {
        return node;
    }

    //<invoca> ::= id <args>
    private Object  invoca (Node node) {
        return node;
    }

    //<args> ::= <operan> <args> |
    private Object args(Node node) {
       return node;
    }

    private void readToken() {
        token = tokens.get(++pos);
    }

    private Token lookAhead() {
        return tokens.get(pos + 1);
    }

    private void log(String s, Token token) {
        errorList.add(String.format("%s at position %d:%d", s, token.getLine(), token.getColumn()));
    }
}
