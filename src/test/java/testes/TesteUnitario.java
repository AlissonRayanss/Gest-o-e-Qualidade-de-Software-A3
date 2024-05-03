package test;

import entidades.Cliente;
import entidades.GerenciarClientes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    GerenciadorDeClientes gerenciadorMock = mock(GerenciadorDeClientes.class);

    Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");

    gerenciadorMock.cadastrarCliente(cliente);
    verify(gerenciadorMock).cadastrarCliente(cliente);

    assertEquals(1, gerenciadorMock.getClientes().size());
}

    @Test
public void testEditarCliente() {
    GerenciadorDeClientes gerenciadorMock = mock(GerenciadorDeClientes.class);

    Cliente clienteAntigo = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
    Cliente clienteNovo = new Cliente("123456789", "Beltrano", "Rua B", 987654321, "beltrano@example.com");

    gerenciadorMock.cadastrarCliente(clienteAntigo);

    gerenciadorMock.editarCliente("123456789", clienteNovo);
    verify(gerenciadorMock).editarCliente("123456789", clienteNovo);

    assertEquals("Beltrano", gerenciadorMock.consultarCliente("123456789").getNome());
}

    @Test
public void testExcluirCliente() {
    GerenciadorDeClientes gerenciadorMock = mock(GerenciadorDeClientes.class);
    
    Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");

    gerenciadorMock.cadastrarCliente(cliente);

    gerenciadorMock.excluirCliente("123456789");
    verify(gerenciadorMock).excluirCliente("123456789");

    assertEquals(0, gerenciadorMock.getClientes().size());
}

    @Test
public void testConsultarCliente() {
    GerenciadorDeClientes gerenciadorMock = mock(GerenciadorDeClientes.class);

    Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");

    gerenciadorMock.cadastrarCliente(cliente);

    gerenciadorMock.consultarCliente("123456789");
    verify(gerenciadorMock).consultarCliente("123456789");

    assertEquals(cliente, gerenciadorMock.consultarCliente("123456789"));
}

    @Test
public void testListarClientes() {
    GerenciadorDeClientes gerenciadorMock = mock(GerenciadorDeClientes.class);

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
    GerenciadorDeClientes gerenciadorMock = mock(GerenciadorDeClientes.class);

    gerenciadorMock.consultarCliente("999999999");
    verify(gerenciadorMock).consultarCliente("999999999");

    assertEquals(null, gerenciadorMock.consultarCliente("999999999"));
}

    @Test
public void testEditarClienteInexistente() {
    GerenciadorDeClientes gerenciadorMock = mock(GerenciadorDeClientes.class);

    Cliente clienteMock = mock(Cliente.class);

    gerenciadorMock.editarCliente("999999999", clienteMock);
    verify(gerenciadorMock).editarCliente("999999999", clienteMock);

    assertEquals(0, gerenciadorMock.getClientes().size());
}

    @Test
public void testExcluirClienteInexistente() {
    GerenciadorDeClientes gerenciadorMock = mock(GerenciadorDeClientes.class);

    gerenciadorMock.excluirCliente("999999999");
    verify(gerenciadorMock).excluirCliente("999999999");

    assertEquals(0, gerenciadorMock.getClientes().size());
}

    @Test
public void testListarClientesVazio() {
    GerenciadorDeClientes gerenciadorMock = mock(GerenciadorDeClientes.class);

    gerenciadorMock.listarClientes();
    verify(gerenciadorMock).listarClientes();

    assertEquals("Nenhum cliente cadastrado.", gerenciadorMock.listarClientes());
}
}
