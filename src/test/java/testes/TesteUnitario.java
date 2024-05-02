package testes;

import entidades.Cliente;
import entidades.GerenciarClientes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class TesteUnitario {

    private GerenciarClientes gerenciador;

    @Mock
    private Cliente clienteMock;

    @BeforeEach
    public void setUp() {
        gerenciador = new GerenciarClientes();
    }

    @Test
    public void testCadastrarCliente() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);
        Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
        doNothing().when(gerenciadorMock).cadastrarCliente(cliente);
        gerenciadorMock.cadastrarCliente(cliente);
        verify(gerenciadorMock, times(1)).cadastrarCliente(cliente);
        assertEquals(1, gerenciadorMock.getClientes().size());
    }

    @Test
    public void testEditarCliente() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);
        Cliente clienteAntigo = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
        Cliente clienteNovo = new Cliente("123456789", "Beltrano", "Rua B", 987654321, "beltrano@example.com");
        when(gerenciadorMock.consultarCliente("123456789")).thenReturn(clienteAntigo);
        gerenciadorMock.editarCliente("123456789", clienteNovo);
        verify(gerenciadorMock, times(1)).consultarCliente("123456789");
        verify(gerenciadorMock, times(1)).editarCliente("123456789", clienteNovo);
        assertEquals("Beltrano", gerenciadorMock.consultarCliente("123456789").getNome());
    }

    @Test
    public void testExcluirCliente() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);
        Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
        gerenciadorMock.cadastrarCliente(cliente);
        gerenciadorMock.excluirCliente("123456789");
        assertEquals(0, gerenciadorMock.getClientes().size());
    }

    @Test
    public void testConsultarCliente() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);
        Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
        gerenciadorMock.cadastrarCliente(cliente);
        assertEquals(cliente, gerenciadorMock.consultarCliente("123456789"));
    }

    @Test
    public void testListarClientes() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);
        Cliente cliente1 = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
        Cliente cliente2 = new Cliente("987654321", "Beltrano", "Rua B", 987654321, "beltrano@example.com");
        gerenciadorMock.cadastrarCliente(cliente1);
        gerenciadorMock.cadastrarCliente(cliente2);
        String expected = "CPF: 123456789\n" + "Nome: Fulano\n" + "Telefone: 1234567890\n\n" + "CPF: 987654321\n"
                + "Nome: Beltrano\n" + "Telefone: 987654321\n\n";
        assertEquals(expected, gerenciadorMock.listarClientes());
    }

    @Test
    public void testConsultarClienteInexistente() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);
        when(gerenciadorMock.consultarCliente("999999999")).thenReturn(null);
        assertNull(gerenciadorMock.consultarCliente("999999999"));
    }
}
