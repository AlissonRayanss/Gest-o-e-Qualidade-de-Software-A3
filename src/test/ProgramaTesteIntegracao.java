package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import aplicacao.Programa;
import entidades.Cliente;
import entidades.GerenciarClientes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class ProgramaTesteIntegracao {

    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream mockOutput;
    private GerenciarClientes gerenciadorClientesMock;
    private Programa programa;

    @BeforeEach
    public void setUp() {
        gerenciadorClientesMock = Mockito.mock(GerenciarClientes.class);
        programa = new Programa();
        programa.sistema = gerenciadorClientesMock;

        mockOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mockOutput));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testIntegracaoCadastroCliente() {
        String input = "1\n12345678900\nJoão\njoao@example.com\nRua A\n123456789\n4\n";
        ByteArrayInputStream mockInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(mockInput);

        programa.main(new String[]{});

        verify(gerenciadorClientesMock).cadastrarCliente(new Cliente("Rua A", "João", "joao@example.com", "12345678900", "123456789"));

        String consoleOutput = mockOutput.toString();
        assertEquals("Cliente cadastrado com sucesso!\nSaindo do programa...\n", consoleOutput);
    }

}