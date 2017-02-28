package org.lucashos.util;

import org.lucashos.util.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 28/02/17.
 */
public class TokenTable {
    List<Token> tokens;

    public TokenTable() {
        this.tokens = new ArrayList<Token>();
    }

    public TokenTable(List<Token> tokens) {
        this.tokens = tokens;
    }

    public Token addToken(TokenClass tokenClass, String value, int position){
        Token token = findToken(value);

        if(token == null) {
            token = new Token(tokenClass, value, position);
            tokens.add(token);
            return token;
        } else {
            return token;
        }
    }

    public Token findToken(String value) {
        for(Token token: tokens) {
            if(token.getValue().equals(value)) {
                return token;
            }
        }
        return null;
    }
}
