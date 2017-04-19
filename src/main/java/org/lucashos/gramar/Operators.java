package org.lucashos.gramar;

/**
 * Created by lucas on 28/02/17.
 */
public enum Operators {
    //'&' | '|' | '>' | '<' | '>=' | '<=' | '==' | '!=' | '+' | '-' | '*' | '/' | '.'
    BIT_AND("&"),
    BIT_OR("|"),
    GT(">"),
    LT("<"),
    GTE(">="),
    LTE("<="),
    EQ("=="),
    NEQ("!="),
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDED("/"),
    CONCAT("."),
    ATTRIBUTION("=");

    private final String friendlyName;

    Operators(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public static boolean isOperator(String letter) {
        for(Operators key: Operators.values())
            if(key.getFriendlyName().equals(letter)) return true;
        return false;
    }
}
