package test;

import entidades.Cliente;
import entidades.GerenciarClientes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import aplicacao.Programa;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProgramaTeste {

    private GerenciarClientes gerenciadorClientes;
    private Programa programa;
    
    @BeforeEach
    public void setUp() {
        gerenciadorClientes = mock(GerenciarClientes.class);
        programa = new Programa();
        programa.sistema = gerenciadorClientes; 
    }

    @Test
    public void testCadastrarCliente() {
        Cliente cliente = new Cliente("Rua A", "João", "joao@example.com", "12345678900", "123456789");
        when(gerenciadorClientes.cadastrarCliente(cliente)).thenReturn(true);

        programa.cadastrarCliente("12345678900", "João", "joao@example.com", "Rua A", "123456789");

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
}
