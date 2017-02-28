package org.lucashos.gramar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 28/02/17.
 */
public class Rule {
    private String origin;
    private List<String> derivations;

    public Rule(String origin) {
        this.origin = origin;
        this.derivations = new ArrayList<String>();
    }

    public Rule(String origin, List<String> derivations) {
        this.origin = origin;
        this.derivations = derivations;
    }

    public void addDerivation(String derivation) {
        this.derivations.add(derivation);
    }
}
