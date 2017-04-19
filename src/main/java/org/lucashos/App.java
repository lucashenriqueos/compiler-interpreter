package org.lucashos;

import org.lucashos.analyzer.LexicalAnalyzer;
import org.lucashos.analyzer.SyntaticAnalyzer;
import org.lucashos.util.Token;
import org.lucashos.util.TokenTable;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
	private static List<String> lines;

    public static void main( String[] args ) throws IOException
	{
		App.lines = App.readFile();
		TokenTable table = LexicalAnalyzer.createTable(lines);

		SyntaticAnalyzer syntatic = new SyntaticAnalyzer(table.getTokens());
		syntatic.analyze();
    }

    private static List<String> readFile() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/home/lucas/dev/projetos/compiler-interpreter/src/main/resources/entrada.slp")));

		List<String> lines = new ArrayList<>();
		String line = reader.readLine();
		while(line != null) {
			lines.add(line.trim());
			line = reader.readLine();
		}
		return lines;
	}
}
