import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class TesteIntegracao {

    private InputStream originalSystemIn;
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream mockOutput;
    private GerenciarClientes gerenciadorClientes;
    private Programa programa;

    @BeforeEach
    public void setUp() {
        gerenciadorClientes = new GerenciarClientes();
        programa = new Programa();
        programa.sistema = gerenciadorClientes;

        originalSystemIn = System.in;
        originalSystemOut = System.out;
        mockOutput = new ByteArrayOutputStream();

        System.setIn(new ByteArrayInputStream("1\n12345678900\nJoão\njoao@example.com\nRua A\n123456789\n4\n".getBytes()));
        System.setOut(new PrintStream(mockOutput));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testIntegracaoCadastroCliente() {
        programa.main(new String[]{});

        String consoleOutput = mockOutput.toString();
        assertEquals("Cliente cadastrado com sucesso!\nSaindo do programa...\n", consoleOutput);
    }

}
