package org.lucashos.util;

import org.lucashos.gramar.Keywords;
import org.lucashos.gramar.Operators;
import org.lucashos.gramar.Separators;

/**
 * Created by lucas on 28/02/17.
 */
public enum TokenClass {
	INTEGER_LITERAL("Constante literal inteira"),
	FLOAT_LITERAL("Constante literal real"),
	STRING_LITERAL("Constante literal String"),
    IDENTIFIER("Identificador"),
    NUMERIC_CONSTANT("Constante numérica"),
    CHARACTERS("Cadeia de caracteres"),
    KEYWORD("Palavra reservada"),
    OPERATOR("Operador"),
    SEPARATOR("Separador");

    private final String friendlyName;

    TokenClass(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName(){
        return this.friendlyName;
    }

    public static TokenClass getClass(String letter) {
    	if(letter.matches("([0-9]+.[0-9]+)")) {
    		return TokenClass.FLOAT_LITERAL;
    	} else if(letter.matches("([0-9]+)")) {
    		return TokenClass.INTEGER_LITERAL;
    	} else if(letter.matches("\".*\"")) {
    		return TokenClass.STRING_LITERAL;
    	} else if(Keywords.isKeyword(letter)){
            return TokenClass.KEYWORD;
        } else if(Operators.isOperator(letter)){
            return TokenClass.OPERATOR;
        } else if(Separators.isSeparator(letter)) {
            return TokenClass.SEPARATOR;
        } else {
            return TokenClass.IDENTIFIER;
        }
    }
    

}
