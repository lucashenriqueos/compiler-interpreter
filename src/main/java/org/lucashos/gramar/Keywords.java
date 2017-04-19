package org.lucashos.gramar;

/**
 * Created by lucas on 28/02/17.
 */
public enum Keywords {
    FUNCTION("func"),
    INTEGER("int"),
    FLOAT("real"),
    TEXT("texto"),
    BOOLEAN("logico"),
    READ("le"),
    PRINT("escreve"),
    VOID("nada");

    private final String friendlyName;

    Keywords(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public static boolean isKeyword(String letter) {
        for(Keywords key: Keywords.values())
            if(key.getFriendlyName().equals(letter)) return true;
        return false;
    }
}
