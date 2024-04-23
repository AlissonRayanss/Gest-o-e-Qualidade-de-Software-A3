package test;

import entidades.Cliente;
import entidades.GerenciarClientes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import aplicacao.Programa;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProgramaTeste {

    @Mock
    private GerenciarClientes gerenciadorClientes;
    private Programa programa;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        programa = new Programa();
        programa.sistema = gerenciadorClientes; 
    }

    @Test
    public void testCadastrarCliente() {
        Cliente cliente = new Cliente("Rua A", "João", "joao@example.com", "12345678900", "123456789");
        when(gerenciadorClientes.cadastrarCliente(cliente)).thenReturn(true);

        boolean result = programa.cadastrarCliente("12345678900", "João", "joao@example.com", "Rua A", "123456789");

        assertTrue(result);
        verify(gerenciadorClientes, times(1)).cadastrarCliente(cliente);
    }

    @Test
    public void testConsultarClienteExistente() {
        Cliente cliente = new Cliente("Rua B", "Maria", "maria@example.com", "98765432100", "987654321");
        when(gerenciadorClientes.consultarCliente("98765432100")).thenReturn(cliente);

        String result = programa.consultarCliente("98765432100");

        assertEquals("CPF: 98765432100\nNome: Maria\nEndereço: Rua B\nTelefone: 987654321\nE-mail: maria@example.com\n", result);
    }

    @Test
    public void testConsultarClienteNaoExistente() {
        when(gerenciadorClientes.consultarCliente("12345678900")).thenReturn(null);

        String result = programa.consultarCliente("12345678900");

        assertEquals("Cliente não encontrado.", result);
    }

    @Test
    public void testListarClientes() {
        Cliente cliente1 = new Cliente("Rua A", "João", "joao@example.com", "12345678900", "123456789");
        Cliente cliente2 = new Cliente("Rua B", "Maria", "maria@example.com", "98765432100", "987654321");

        when(gerenciadorClientes.listarClientes()).thenReturn("Clientes:\n" +
                "CPF: 12345678900\nNome: João\nEndereço: Rua A\nTelefone: 123456789\nE-mail: joao@example.com\n" +
                "CPF: 98765432100\nNome: Maria\nEndereço: Rua B\nTelefone: 987654321\nE-mail: maria@example.com\n");

        String result = programa.listarClientes();

        assertEquals("Clientes:\n" +
                "CPF: 12345678900\nNome: João\nEndereço: Rua A\nTelefone: 123456789\nE-mail: joao@example.com\n" +
                "CPF: 98765432100\nNome: Maria\nEndereço: Rua B\nTelefone: 987654321\nE-mail: maria@example.com\n", result);
    }

    @Test
    public void testRemoverClienteExistente() {
        Cliente cliente = new Cliente("Rua C", "Carlos", "carlos@example.com", "55555555555", "555555555");
        when(gerenciadorClientes.removerCliente("55555555555")).thenReturn(true);

        boolean result = programa.removerCliente("55555555555");

        assertTrue(result);
        verify(gerenciadorClientes, times(1)).removerCliente("55555555555");
    }

    @Test
    public void testRemoverClienteNaoExistente() {
        when(gerenciadorClientes.removerCliente("99999999999")).thenReturn(false);

        boolean result = programa.removerCliente("99999999999");

        assertFalse(result);
        verify(gerenciadorClientes, times(1)).removerCliente("99999999999");
    }

    @Test
    public void testAtualizarClienteExistente() {
        Cliente cliente = new Cliente("Rua D", "Diego", "diego@example.com", "44444444444", "444444444");
        when(gerenciadorClientes.atualizarCliente(cliente)).thenReturn(true);

        boolean result = programa.atualizarCliente("44444444444", "Diego", "diego@example.com", "Rua D", "444444444");

        assertTrue(result);
        verify(gerenciadorClientes, times(1)).atualizarCliente(cliente);
    }

    @Test
    public void testAtualizarClienteNaoExistente() {
        when(gerenciadorClientes.atualizarCliente(any(Cliente.class))).thenReturn(false);

        boolean result = programa.atualizarCliente("99999999999", "Diego", "diego@example.com", "Rua D", "444444444");

        assertFalse(result);
        verify(gerenciadorClientes, never()).atualizarCliente(any(Cliente.class));
    }

    @Test
    public void testContarClientes() {
        when(gerenciadorClientes.contarClientes()).thenReturn(3);

        int result = programa.contarClientes();

        assertEquals(3, result);
        verify(gerenciadorClientes, times(1)).contarClientes();
    }

    @Test
    public void testBuscarClientePorNomeExistente() {
        Cliente cliente1 = new Cliente("Rua E", "Eduardo", "eduardo@example.com", "11111111111", "111111111");
        Cliente cliente2 = new Cliente("Rua F", "Fernanda", "fernanda@example.com", "22222222222", "222222222");
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);

        when(gerenciadorClientes.buscarClientesPorNome("F")).thenReturn(clientes);

        List<Cliente> result = programa.buscarClientesPorNome("F");

        assertEquals(1, result.size());
        assertEquals("Fernanda", result.get(0).getNome());
        verify(gerenciadorClientes, times(1)).buscarClientesPorNome("F");
    }

    @Test
    public void testBuscarClientePorNomeNaoExistente() {
        when(gerenciadorClientes.buscarClientesPorNome("Z")).thenReturn(Collections.emptyList());

        List<Cliente> result = programa.buscarClientesPorNome("Z");

        assertTrue(result.isEmpty());
        verify(gerenciadorClientes, times(1)).buscarClientesPorNome("Z");
    }

    @Test
    public void testBuscarClientePorTelefoneExistente() {
        Cliente cliente = new Cliente("Rua G", "Gustavo", "gustavo@example.com", "33333333333", "333333333");
        when(gerenciadorClientes.buscarClientePorTelefone("333333333")).thenReturn(cliente);

