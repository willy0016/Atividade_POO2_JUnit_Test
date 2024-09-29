/*
 * Classe que utiliza o JUnit 5 para realização dos testes.
 * @author Willy
 */

import calculo.DNAAnalyzer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

class DNAAnalyzerTest {

    private static final String VALID_FILE = "valid_sequence.txt";
    private static final String INVALID_FILE = "invalid_sequence.txt";
    private static final String NON_EXISTENT_FILE = "non_existent.txt";

    @BeforeAll
    static void setUp() throws IOException {
        Files.writeString(Paths.get(VALID_FILE), "AACATGCTGCATGCTGGTAAAACCACTGGGCACCATTGCACAC");
        Files.writeString(Paths.get(INVALID_FILE), "ABC TEM FALHA");
    }

    @AfterAll
    static void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(VALID_FILE));
        Files.deleteIfExists(Paths.get(INVALID_FILE));
    }

    @Test
    @DisplayName("Verifica se a sequência válida retorna o array correto")
    void testValidSequence() throws IOException {
        DNAAnalyzer analyzer = new DNAAnalyzer();
        int[] expected = {13, 13, 9, 8, 0};  // Número de A, C, G, T, Erros
        assertArrayEquals(expected, analyzer.calculaNucleotideos(VALID_FILE));
    }

    @Test
    @DisplayName("Verifica se uma sequência inválida retorna null quando mais de 10% são inválidos")
    void testInvalidSequence() throws IOException {
        DNAAnalyzer analyzer = new DNAAnalyzer();
        assertNull(analyzer.calculaNucleotideos(INVALID_FILE));
    }

    @Test
    @DisplayName("Verifica se lança exceção quando o arquivo não é encontrado")
    void testFileNotFound() {
        DNAAnalyzer analyzer = new DNAAnalyzer();
        assertThrows(IOException.class, () -> analyzer.calculaNucleotideos(NON_EXISTENT_FILE));
    }
}
