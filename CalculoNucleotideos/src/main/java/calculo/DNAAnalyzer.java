/*
 * Classe responsável por analisar uma sequência de DNA lida de um arquivo de texto.
 * @author Willy
 */

package calculo;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class DNAAnalyzer {

    public int[] calculaNucleotideos(String filePath) throws IOException {
        String sequence = new String(Files.readAllBytes(Paths.get(filePath))).toUpperCase();
        int[] counts = new int[5];  // [A, C, G, T, Erros]
        int total = sequence.length();

        for (char nucleotide : sequence.toCharArray()) {
            switch (nucleotide) {
                case 'A': counts[0]++; break; // A
                case 'C': counts[1]++; break; // C
                case 'G': counts[2]++; break; // G
                case 'T': counts[3]++; break; // T
                default: counts[4]++;  // Erros
            }
        }

        // Verifica se mais de 10% da sequência são erros
        if (counts[4] > 0.1 * total) {
            return null;  // Mais de 10% de erros
        }
        return counts;
    }
}
