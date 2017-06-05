package org.lucashos.analyzer;

import org.lucashos.util.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 28/02/17.
 */
public class SemanticAnalyzer {
    private List<Token> tokens;
    private Token token;
    private int pos;
    private List<String> errorList;

    public SemanticAnalyzer(List<Token> code) {
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
        System.out.printf("Expected %s at position %d:%d\n", s, token.getLine(), token.getColumn());
        errorList.add(String.format("Expected %s at position %d:%d", s, token.getLine(), token.getColumn()));
    }
}
