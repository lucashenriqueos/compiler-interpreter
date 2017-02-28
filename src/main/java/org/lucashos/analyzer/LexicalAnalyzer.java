package org.lucashos.analyzer;

import org.lucashos.util.TokenClass;
import org.lucashos.util.TokenTable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lucas on 28/02/17.
 */
public class LexicalAnalyzer {

    public static TokenTable createTable(String sentence){
        TokenTable tokenTable = new TokenTable();
        List<String> letters = Arrays.asList(sentence.split(" "));

        int line = 0;
        int col = 0;
        for(String letter: letters) {
            tokenTable.addToken(TokenClass.getClass(letter), letter, line, col);
            line += letter.length() + 1;
        }

        return tokenTable;
    }
}
