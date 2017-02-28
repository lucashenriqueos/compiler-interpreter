package org.lucashos.util;

/**
 * Created by lucas on 28/02/17.
 */
public class Token {
    private TokenClass tokenClass;
    private String value;
    private int position;
    private int column;
    private int line;

    public Token(TokenClass tokenClass, String value, int position) {
        this.tokenClass = tokenClass;
        this.value = value;
        this.position = position;
    }

    public Token(TokenClass tokenClass, String value) {
        this.tokenClass = tokenClass;
        this.value = value;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
