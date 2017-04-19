package org.lucashos.gramar;

/**
 * Created by lucas on 28/02/17.
 */
public enum Separators {
    END_LINE("$"),
	OPEN_SCOPE("("),
	CLOSE_SCOPE(")");

    private final String friendlyName;

    Separators(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public static boolean isSeparator(String letter) {
        for(Separators key: Separators.values())
            if(key.getFriendlyName().equals(letter)) return true;
        return false;
    }
}
