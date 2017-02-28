package org.lucashos.gramar;

import java.util.List;

/**
 * Created by lucas on 28/02/17.
 */
public class Alphabet {
    private List<String> simbols;

    public Alphabet() {
    }

    public Alphabet(List<String> simbols) {
        this.simbols = simbols;
    }

    public List<String> getSimbols() {
        return simbols;
    }

    public void setSimbols(List<String> simbols) {
        this.simbols = simbols;
    }
}
