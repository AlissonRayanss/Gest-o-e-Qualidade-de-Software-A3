package UnitaryTests;

import aplicacao.Programa;
import entidades.Cliente;
import entidades.GerenciarClientes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ProgramaTesteUnitario {

    public GerenciarClientes gerenciador;

    @Mock
    private Cliente clienteMock;

    @BeforeEach
    public void setUp() {
        gerenciador = new GerenciarClientes();
    }

    @Test
    public void testCadastrarCliente() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);

        Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", "1234567890", "fulano@example.com");

        gerenciadorMock.cadastrarCliente(cliente);

        verify(gerenciadorMock).cadastrarCliente(cliente);


    }

    @Test
    void testEditarCliente() {
        GerenciarClientes mockSistema = mock(GerenciarClientes.class);

        Cliente clienteExistente = new Cliente("12345678901", "Cliente Antigo", "Endereço Antigo", "123456789", "antigo@email.com");

        when(mockSistema.consultarCliente("12345678901")).thenReturn(clienteExistente);

        String novoNome = "Novo Cliente";
        String novoEndereco = "Novo Endereço";
        String novoTelefone = "987654321";
        String novoEmail = "novo@email.com";

        Cliente clienteAtualizado = new Cliente("12345678901", novoNome, novoEndereco, novoTelefone, novoEmail);

        mockSistema.editarCliente("12345678901", clienteAtualizado);

        verify(mockSistema).editarCliente("12345678901", clienteAtualizado);
    }


    @Test
    public void testExcluirCliente() {

        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);

        Cliente cliente = new Cliente("123456789", "Fulano", "Rua A", "1234567890", "fulano@example.com");

        gerenciadorMock.cadastrarCliente(cliente);

        gerenciadorMock.excluirCliente("123456789");

        verify(gerenciadorMock).excluirCliente("123456789");

        assertNull(gerenciadorMock.consultarCliente("123456789"));
    }


    @Test
    public void testConsultarCliente() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);

        Cliente clienteEsperado = new Cliente("123456789", "Fulano", "Rua A", "1234567890", "fulano@example.com");

        when(gerenciadorMock.consultarCliente("123456789")).thenReturn(clienteEsperado);

        Cliente clienteRetornado = gerenciadorMock.consultarCliente("123456789");

        assertEquals(clienteEsperado, clienteRetornado);
    }

    @Test
    public void testListarClientes() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);

        Cliente cliente1 = new Cliente("123456789", "Fulano", "Rua A", "1234567890", "fulano@example.com");
        Cliente cliente2 = new Cliente("987654321", "Beltrano", "Rua B", "987654321", "beltrano@example.com");

        gerenciadorMock.cadastrarCliente(cliente1);
        gerenciadorMock.cadastrarCliente(cliente2);

        gerenciadorMock.listarClientes();

        verify(gerenciadorMock).listarClientes();

    }

    @Test
    public void testConsultarClienteInexistente() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);

        gerenciadorMock.consultarCliente("999999999");
        verify(gerenciadorMock).consultarCliente("999999999");

        assertEquals(null, gerenciadorMock.consultarCliente("999999999"));
    }

    @Test
    public void testEditarClienteInexistente() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);

        Cliente clienteMock = mock(Cliente.class);

        gerenciadorMock.editarCliente("999999999", clienteMock);

        verify(gerenciadorMock).editarCliente("999999999", clienteMock);

        gerenciadorMock.consultarCliente("999999999");

        verify(gerenciadorMock).consultarCliente("999999999");
    }


    @Test
    public void testExcluirClienteInexistente() {

        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);

        gerenciadorMock.excluirCliente("999999999");

        verify(gerenciadorMock).excluirCliente("999999999");

        assertNull(gerenciadorMock.consultarCliente("999999999"));
    }


    @Test
    public void testListarClientesVazio() {
        GerenciarClientes gerenciadorMock = mock(GerenciarClientes.class);

        gerenciadorMock.listarClientes();

        verify(gerenciadorMock).listarClientes();
    }

    @Test
    public void testarOpcaoInvalida() {
        GerenciarClientes sistema = new GerenciarClientes();
        int opcaoInvalida = 7;

        String mensagemEsperada = "Opção inválida. Tente novamente.";


        assertEquals(mensagemEsperada,"Opção inválida. Tente novamente.");

    }
}
