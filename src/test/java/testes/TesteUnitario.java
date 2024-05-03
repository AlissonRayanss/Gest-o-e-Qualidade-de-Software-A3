package testes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aplicacao.Programa;
import entidades.Cliente;
import entidades.GerenciarClientes;

public class TesteUnitario {
    private GerenciarClientes sistema;
    
    @BeforeEach
    public void setUp() {
        sistema = mock(GerenciarClientes.class);
    }
    
    @Test
    public void testCadastrarCliente() {
        Cliente cliente = new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com");
        sistema.cadastrarCliente(cliente);
    }
    
    @Test
    public void testEditarCliente() {
        Cliente clienteAtualizado = new Cliente("12345678900", "João da Silva", "Rua B", "987654321", "joao.silva@example.com");
        sistema.editarCliente(eq("12345678900"), clienteAtualizado);
    }
    
    @Test
    public void testExcluirCliente() {
        sistema.excluirCliente(eq("12345678900"));
    }
    
    @Test
    public void testConsultarCliente() {
        Cliente cliente = new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com");
        when(sistema.consultarCliente(eq("12345678900"))).thenReturn(cliente);
        
        assertEquals(cliente, sistema.consultarCliente("12345678900"));
    }
    
    @Test
    public void testListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com"));
        when(sistema.listarClientes()).thenReturn(clientes);
        
        assertEquals(clientes, sistema.listarClientes());
    }
    
    @Test
    public void testConsultarClienteInexistente() {
        when(sistema.consultarCliente(eq("12345678900"))).thenReturn(null);
        
        assertNull(sistema.consultarCliente("12345678900"));
    }
    
    @Test
    public void testEditarClienteInexistente() {
        when(sistema.consultarCliente(eq("12345678900"))).thenReturn(null);
        
        assertFalse(GerenciarClientes.editarCliente(sistema, "12345678900", new Cliente()));
        
        verify(sistema, times(1)).consultarCliente(eq("12345678900"));
        verify(sistema, never()).editarCliente(anyString(), any(Cliente.class));
    }
    
    @Test
    public void testExcluirClienteInexistente() {
        when(sistema.excluirCliente(eq("12345678900"))).thenReturn(false);
        
        assertFalse(GerenciarClientes.excluirCliente(sistema, "12345678900"));
        
        verify(sistema, times(1)).excluirCliente(eq("12345678900"));
    }
    
    @Test
    public void testListarClientesVazio() {
        List<Cliente> clientes = new ArrayList<>();
        when(sistema.listarClientes()).thenReturn(clientes);
        
        assertTrue(GerenciarClientes.listarClientes(sistema).isEmpty());
        
        verify(sistema, times(1)).listarClientes();
    }
}
