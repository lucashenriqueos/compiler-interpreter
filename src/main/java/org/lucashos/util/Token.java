package org.lucashos.util;

/**
 * Created by lucas on 28/02/17.
 */
public class Token {
    private TokenClass tokenClass;
    private String value;
    private Integer index;
    private int column;
    private int line;

    private Token(TokenClass tokenClass, String value) {
        this.tokenClass = tokenClass;
        this.value = value;
    }

    public Token(TokenClass tokenClass, String value, int line, int column) {
        this(tokenClass, value);
        this.line = line;
        this.column = column;
    }

    public Token(TokenClass tokenClass, String value, int line, int column, Integer index) {
        this(tokenClass, value, line, column);
        this.index = index;
    }

    public TokenClass getTokenClass() {
        return tokenClass;
    }

    public void setTokenClass(TokenClass tokenClass) {
        this.tokenClass = tokenClass;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
