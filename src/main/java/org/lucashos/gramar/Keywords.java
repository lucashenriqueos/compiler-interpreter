package org.lucashos.gramar;

/**
 * Created by lucas on 28/02/17.
 */
public enum Keywords {
    WHILE("while"),
    IF("if"),
    FOR("for"),
    ELSE("else");

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
