package org.lucashos.analyzer;

import org.lucashos.gramar.Keywords;
import org.lucashos.util.TokenClass;
import org.lucashos.util.TokenTable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lucas on 28/02/17.
 */
public class LexicalAnalyzer {

    public static TokenTable analize(String sentence){
        TokenTable tokenTable = new TokenTable();
        List<String> letters = Arrays.asList(sentence.split(" "));
        int index = 0;

        for(String letter: letters) {
            tokenTable.addToken(TokenClass.getClass(letter), letter, index);
            index += letter.length() + 1;
        }

        return tokenTable;
    }
}
