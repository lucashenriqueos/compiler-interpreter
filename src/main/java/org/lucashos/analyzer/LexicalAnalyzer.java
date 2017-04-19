package org.lucashos.analyzer;

import org.lucashos.util.TokenClass;
import org.lucashos.util.TokenTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lucas on 28/02/17.
 */
public class LexicalAnalyzer {

    private static TokenTable nextLine(String line, Integer lineNumber){
        TokenTable tokenTable = new TokenTable();

        List<String> list = new ArrayList<String>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(line);
        while (m.find())
            list.add(m.group(1)); // Add .replace("\"", "") to remove surrounding quotes.

        int col = 0;
        for(String letter: list) {
            tokenTable.addToken(TokenClass.getClass(letter), letter, lineNumber, col);
            col += letter.length() + 1;
        }

        return tokenTable;
    }

    public static TokenTable createTable(List<String> lines) {

        TokenTable table = new TokenTable();

        AtomicInteger index = new AtomicInteger(0);
        lines.forEach(l -> {
            table.addAll(LexicalAnalyzer.nextLine(l, index.intValue()).getTokens());
            index.addAndGet(1);
        });

        return table;
    }
}
