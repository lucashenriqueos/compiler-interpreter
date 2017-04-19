package org.lucashos.gramar;

import java.util.List;

/**
 * Created by lucas on 28/02/17.
 */
public class Gramar {
    private Alphabet alphabet;
    private List<Rule> rules;

    public Gramar(Alphabet alphabet, List<Rule> rules) {
        this.alphabet = alphabet;
        this.rules = rules;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }
}
