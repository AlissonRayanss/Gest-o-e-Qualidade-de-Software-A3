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
    public Cliente testCadastrarCliente() {
        Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
        gerenciador.cadastrarCliente(cliente);
        assertEquals(1, gerenciador.getClientes().size());
    }

    @Test
    public Cliente testEditarCliente() {
        Cliente clienteAntigo = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
        Cliente clienteNovo = new Cliente("123456789", "Beltrano", "Rua B", 987654321, "beltrano@example.com");
        gerenciador.cadastrarCliente(clienteAntigo);
        gerenciador.editarCliente("123456789", clienteNovo);
        assertEquals("Beltrano", gerenciador.consultarCliente("123456789").getNome());
    }

    @Test
    public Cliente testExcluirCliente() {
        Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
        gerenciador.cadastrarCliente(cliente);
        gerenciador.excluirCliente("123456789");
        assertEquals(0, gerenciador.getClientes().size());
    }

    @Test
    public Cliente testConsultarCliente() {
        Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
        gerenciador.cadastrarCliente(cliente);
        assertEquals(cliente, gerenciador.consultarCliente("123456789"));
    }

    @Test
    public Cliente testListarClientes() {
        Cliente cliente1 = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
        Cliente cliente2 = new Cliente("987654321", "Beltrano", "Rua B", 987654321, "beltrano@example.com");
        gerenciador.cadastrarCliente(cliente1);
        gerenciador.cadastrarCliente(cliente2);

        String expected = "CPF: 123456789\n" + "Nome: Fulano\n" + "Telefone: 1234567890\n\n" + "CPF: 987654321\n"
                + "Nome: Beltrano\n" + "Telefone: 987654321\n\n";

        assertEquals(expected, gerenciador.listarClientes());
    }

    @Test
    public Cliente testConsultarClienteInexistente() {
        assertEquals(null, gerenciador.consultarCliente("999999999"));
    }

    @Test
    public Cliente testEditarClienteInexistente() {
        gerenciador.editarCliente("999999999", clienteMock);
        assertEquals(0, gerenciador.getClientes().size());
    }

    @Test
    public Cliente testExcluirClienteInexistente() {
        gerenciador.excluirCliente("999999999");
        assertEquals(0, gerenciador.getClientes().size());
    }

    @Test
    public Cliente testListarClientesVazio() {
        assertEquals("Nenhum cliente cadastrado.", gerenciador.listarClientes());
    }
}
