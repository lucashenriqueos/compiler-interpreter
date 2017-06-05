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

    public Token addToken(TokenClass tokenClass, String value, int line, int col){
        Token token = findToken(value);

        if(token == null) {
            Integer index = tokenClass.compareTo(TokenClass.IDENTIFIER) == 0 ? findNextIndex() + 1 : null;
            token = new Token(tokenClass, value, line, col, index);
        } else {
            token = new Token(tokenClass, value, line, col, token.getIndex());
        }

        tokens.add(token);
        return token;
    }
    
    public List<Token> addAll(List<Token> tokens) {
    	this.tokens.addAll(tokens);
    	return this.tokens;
    }

    private Token findToken(String value) {
        for(Token token: tokens) {
            if(token.getValue().equals(value)) {
                return token;
            }
        }
        return null;
    }

    private Integer findNextIndex() {
        int lastIndex = -1;
        for(Token token: tokens) {
            if(token.getIndex() != null && token.getIndex() > lastIndex) {
                lastIndex = token.getIndex();
            }
        }

        return lastIndex;
    }

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	@Override
	public String toString() {
		return "TokenTable [nodes=" + tokens + "]";
	}
    
    
}
