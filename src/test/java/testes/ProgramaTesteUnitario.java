package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import aplicacao.Programa;
import entidades.Cliente;
import entidades.GerenciarClientes;

@ExtendWith(MockitoExtension.class)
public class GerenciarClientesTest {

    private GerenciarClientes gerenciador;

    @Mock
    private Cliente clienteMock;

    @BeforeEach
    public void setUp() {
        gerenciador = new GerenciarClientes();
    }

    @Test
    public void testCadastrarCliente() {
        Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");

        gerenciador.cadastrarCliente(cliente);
        verify(gerenciador).cadastrarCliente(cliente);

        assertEquals(1, gerenciador.getClientes().size());
    }

    @Test
public void testEditarCliente() {
    GerenciarClientes gerenciadorMock = mock(ProgramaTeste.class);

    Cliente clienteAntigo = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
    Cliente clienteNovo = new Cliente("123456789", "Beltrano", "Rua B", 987654321, "beltrano@example.com");

    gerenciadorMock.cadastrarCliente(clienteAntigo);

    gerenciadorMock.editarCliente("123456789", clienteNovo);
    verify(gerenciadorMock).editarCliente("123456789", clienteNovo);

    assertEquals("Beltrano", gerenciadorMock.consultarCliente("123456789").getNome());
}

    @Test
public void testExcluirCliente() {
    GerenciarClientes gerenciadorMock = mock(ProgramaTeste.class);
    
    Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");

    gerenciadorMock.cadastrarCliente(cliente);

    gerenciadorMock.excluirCliente("123456789");
    verify(gerenciadorMock).excluirCliente("123456789");

    assertEquals(0, gerenciadorMock.getClientes().size());
}

    @Test
public void testConsultarCliente() {
    GerenciarClientes gerenciadorMock = mock(ProgramaTeste.class);

    Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");

    gerenciadorMock.cadastrarCliente(cliente);

    gerenciadorMock.consultarCliente("123456789");
    verify(gerenciadorMock).consultarCliente("123456789");

    assertEquals(cliente, gerenciadorMock.consultarCliente("123456789"));
}

    @Test
public void testListarClientes() {
    GerenciarClientes gerenciadorMock = mock(ProgramaTeste.class);

    Cliente cliente1 = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
    Cliente cliente2 = new Cliente("987654321", "Beltrano", "Rua B", 987654321, "beltrano@example.com");

    gerenciadorMock.cadastrarCliente(cliente1);
    gerenciadorMock.cadastrarCliente(cliente2);

    gerenciadorMock.listarClientes();
    verify(gerenciadorMock).listarClientes();

    String expected = "CPF: 123456789\n" + "Nome: Fulano\n" + "Telefone: 1234567890\n\n" + "CPF: 987654321\n"
            + "Nome: Beltrano\n" + "Telefone: 987654321\n\n";
    assertEquals(expected, gerenciadorMock.listarClientes());
}

    @Test
public void testConsultarClienteInexistente() {
    GerenciarClientes gerenciadorMock = mock(ProgramaTeste.class);

    gerenciadorMock.consultarCliente("999999999");
    verify(gerenciadorMock).consultarCliente("999999999");

    assertEquals(null, gerenciadorMock.consultarCliente("999999999"));
}

    @Test
public void testEditarClienteInexistente() {
    GerenciarClientes gerenciadorMock = mock(ProgramaTeste.class);

    Cliente clienteMock = mock(Cliente.class);

    gerenciadorMock.editarCliente("999999999", clienteMock);
    verify(gerenciadorMock).editarCliente("999999999", clienteMock);

    assertEquals(0, gerenciadorMock.getClientes().size());
}

    @Test
public void testExcluirClienteInexistente() {
    GerenciarClientes gerenciadorMock = mock(ProgramaTeste.class);

    gerenciadorMock.excluirCliente("999999999");
    verify(gerenciadorMock).excluirCliente("999999999");

    assertEquals(0, gerenciadorMock.getClientes().size());
}

    @Test
public void testListarClientesVazio() {
    GerenciarClientes gerenciadorMock = mock(ProgramaTeste.class);

    gerenciadorMock.listarClientes();
    verify(gerenciadorMock).listarClientes();

    assertEquals("Nenhum cliente cadastrado.", gerenciadorMock.listarClientes());
    }
}
