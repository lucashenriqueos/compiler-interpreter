package org.lucashos.gramar;

/**
 * Created by lucas on 28/02/17.
 */
public enum Operators {
    PLUS("+"),
    MINUS("-"),
    TIMES("*"),
    DIVIDED("/");


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
