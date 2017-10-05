package org.lucashos.util;

/**
 * Created by lucas on 06/06/17.
 */
public class Symbol {
    private String value;
    private Integer index;
    private int column;
    private int line;
    private String type;

    public Symbol(String value, Integer index, int column, int line) {
        this.value = value;
        this.index = index;
        this.column = column;
        this.line = line;
    }

    public static Symbol fromToken(Token token) {
        return new Symbol(token.getValue(), token.getIndex(), token.getColumn(), token.getLine());
    }

    public Integer getIndex() {
        return index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
