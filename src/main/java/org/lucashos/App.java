package org.lucashos;

import org.lucashos.analyzer.LexicalAnalyzer;
import org.lucashos.gramar.Keywords;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String teste = "while i do this, if i want i can drink a coffe while do some maths like 1 + 1, 2 - 2, 3 * 3 $";

        LexicalAnalyzer.createTable(teste);
    }
}
