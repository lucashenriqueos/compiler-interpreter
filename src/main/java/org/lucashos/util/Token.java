package org.lucashos.util;

import java.util.ArrayList;
import java.util.List;

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

    private Token(TokenClass tokenClass, String value, int line, int column) {
        this(tokenClass, value);
        this.line = line;
        this.column = column;
    }

    Token(TokenClass tokenClass, String value, int line, int column, Integer index) {
        this(tokenClass, value, line, column);
        this.index = index;
    }

    public Boolean isLiteral () {
        return this.tokenClass.equals(TokenClass.FLOAT_LITERAL) ||
                this.tokenClass.equals(TokenClass.INTEGER_LITERAL) ||
                this.tokenClass.equals(TokenClass.STRING_LITERAL);
    }

    public String getLiteral() {
        if(this.tokenClass.equals(TokenClass.FLOAT_LITERAL)){
            return "real";
        } else if(this.tokenClass.equals(TokenClass.INTEGER_LITERAL)) {
            return "int";
        } else if (this.tokenClass.equals(TokenClass.STRING_LITERAL)) {
            return "texto";
        }
        return "";
    }

    public Boolean isTerminal () {
        return isLiteral() || isIdentifier();
    }

    public Boolean isIdentifier() {
        return this.tokenClass.equals(TokenClass.IDENTIFIER);
    }

    public Boolean isOperator() {
        return this.tokenClass.equals(TokenClass.OPERATOR);
    }

    public Boolean isTipo() {
        List<String> tipos = new ArrayList<>();
        tipos.add("nada");
        tipos.add("int");
        tipos.add("real");
        tipos.add("texto");
        tipos.add("logico");

        return tipos.contains(value);
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

	@Override
	public String toString() {
		return value;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (tokenClass != token.tokenClass) return false;
        if (!value.equals(token.value)) return false;
        return index.equals(token.index);
    }

    @Override
    public int hashCode() {
        int result = tokenClass.hashCode();
        result = 31 * result + value.hashCode();
        result = 31 * result + index.hashCode();
        return result;
    }
}
