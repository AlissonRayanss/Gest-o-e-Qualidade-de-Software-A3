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
public void testExcluirCliente() {
    Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
    gerenciadorMock.cadastrarCliente(cliente);
    gerenciadorMock.excluirCliente("123456789");
    assertEquals(0, gerenciadorMock.getClientes().size());
}

@Test
public void testConsultarCliente() {
    Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", 1234567890, "fulano@example.com");
    gerenciadorMock.cadastrarCliente(cliente);
    assertEquals(cliente, gerenciadorMock.consultarCliente("123456789"));
}

@Test
public void testListarClientes() {
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
    GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class); // Adicionando a criação do mock
    when(gerenciadorMock.consultarCliente("999999999")).thenReturn(null);
    assertNull(gerenciadorMock.consultarCliente("999999999"));
}

@Test
public void testEditarClienteInexistente() {
    gerenciadorMock.editarCliente("999999999", clienteMock);
    assertEquals(0, gerenciadorMock.getClientes().size());
}

@Test
public void testExcluirClienteInexistente() {
    gerenciadorMock.excluirCliente("999999999");
    assertEquals(0, gerenciadorMock.getClientes().size());
}

@Test
public void testListarClientesVazio() {
    assertEquals("Nenhum cliente cadastrado.", gerenciadorMock.listarClientes());
}
}

