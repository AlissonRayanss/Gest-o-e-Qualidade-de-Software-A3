import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.io.*;
import java.util.Scanner;
import aplicacao.Programa;
import entidades.Cliente;

public class ProgramaIntegrationTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testProgramFlow() {
        
        String simulatedInput = "1\n123456789\nJohn Doe\n123 Main St\n1234567890\njohn@example.com\n6\n";
        testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        
        Programa.main(new String[0]);

        
        String expectedOutput = "-Cadastro de Cliente-\n" +
                                 "CPF: \n" +
                                 "Nome: \n" +
                                 "Endereço: " +
                                 "Telefone: \n" +
                                 "E-mail: \n" +
                                 "Cliente cadastrado com sucesso!\n" +
                                 "Programa finalizado\n";
        assertEquals(expectedOutput, testOut.toString());
    }

    @Test
    public void testConsultarCliente() {
        
        Cliente clienteTeste = new Cliente("987654321", "Alice", "456 Oak St", 9876543210L, "alice@example.com");
        
        
        Programa.sistema.cadastrarCliente(clienteTeste);

       
        String simulatedInput = "4\n987654321\n6\n";
        testIn = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(testIn);

        
        Programa.main(new String[0]);

        
        String expectedOutput = "-Consulta de Cliente-\n" +
                                 "CPF do cliente a ser consultado: " +
                                 "CPF: 987654321\n" +
                                 "Nome: Alice\n" +
                                 "Endereço: 456 Oak St\n" +
                                 "Telefone: 9876543210\n" +
                                 "E-mail: alice@example.com\n" +
                                 "Programa finalizado\n";
        assertEquals(expectedOutput, testOut.toString());
    }
}
