package org.lucashos.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 28/02/17.
 */
public class TokenTable {
    List<Token> tokens;
    List<Symbol> symbols;

    public TokenTable() {
        this.tokens = new ArrayList<Token>();
        this.symbols = new ArrayList<>();
    }

    public Token addToken(TokenClass tokenClass, String value, int line, int col){
        Token token = findToken(value);

        if(token == null) {
            Integer index = tokenClass.compareTo(TokenClass.IDENTIFIER) == 0 ? findNextIndex() + 1 : null;
            token = new Token(tokenClass, value, line, col, index);
            if (index != null) this.addSymbol(token);
        } else {
            token = new Token(tokenClass, value, line, col, token.getIndex());
        }

        tokens.add(token);
        return token;
    }

    private Symbol addSymbol(Token token) {
        Symbol symbol = Symbol.fromToken(token);
        if(!this.symbols.contains(symbol)) {
            this.symbols.add(symbol);
        }
        return symbol;
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
        for(Symbol symbol: symbols) {
            if(symbol.getIndex() != null && symbol.getIndex() > lastIndex) {
                lastIndex = symbol.getIndex();
            }
        }
        return lastIndex;
    }

    public List<Token> getTokens() {
		return tokens;
	}

	public String getSymbolType(int index) {
        return symbols.get(index).getType();
    }

    public void setSymbolType(String tipo, Token token) {
        this.symbols.get(token.getIndex()).setType(tipo);
    }

	@Override
	public String toString() {
		return "TokenTable [nodes=" + tokens + "]";
	}


}
