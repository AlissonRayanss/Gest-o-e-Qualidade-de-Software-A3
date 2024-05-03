import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        
        verify(sistema, times(1)).cadastrarCliente(cliente);
    }
    
    @Test
    public void testEditarCliente() {
        Cliente clienteAtualizado = new Cliente("12345678900", "João da Silva", "Rua B", "987654321", "joao.silva@example.com");
        when(sistema.consultarCliente("12345678900")).thenReturn(new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com"));
        
        assertTrue(sistema.editarCliente("12345678900", clienteAtualizado));
        
        verify(sistema, times(1)).consultarCliente("12345678900");
        verify(sistema, times(1)).editarCliente("12345678900", clienteAtualizado);
    }
    
    @Test
    public void testExcluirCliente() {
        when(sistema.consultarCliente("12345678900")).thenReturn(new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com"));
        
        assertTrue(sistema.excluirCliente("12345678900"));
        
        verify(sistema, times(1)).consultarCliente("12345678900");
        verify(sistema, times(1)).excluirCliente("12345678900");
    }
    
    @Test
    public void testConsultarCliente() {
        Cliente cliente = new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com");
        when(sistema.consultarCliente("12345678900")).thenReturn(cliente);
        
        assertEquals(cliente, sistema.consultarCliente("12345678900"));
        
        verify(sistema, times(1)).consultarCliente("12345678900");
    }
    
    @Test
    public void testListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com"));
        when(sistema.listarClientes()).thenReturn(clientes);
        
        assertEquals(clientes, sistema.listarClientes());
        
        verify(sistema, times(1)).listarClientes();
    }
    
    @Test
    public void testConsultarClienteInexistente() {
        when(sistema.consultarCliente("12345678900")).thenReturn(null);
        
        assertNull(sistema.consultarCliente("12345678900"));
        
        verify(sistema, times(1)).consultarCliente("12345678900");
    }
    
    @Test
    public void testEditarClienteInexistente() {
        when(sistema.consultarCliente("12345678900")).thenReturn(null);
        
        assertFalse(sistema.editarCliente("12345678900", new Cliente()));
        
        verify(sistema, times(1)).consultarCliente("12345678900");
        verify(sistema, never()).editarCliente(anyString(), any(Cliente.class));
    }
    
    @Test
    public void testExcluirClienteInexistente() {
        when(sistema.consultarCliente("12345678900")).thenReturn(null);
        
        assertFalse(sistema.excluirCliente("12345678900"));
        
        verify(sistema, times(1)).consultarCliente("12345678900");
        verify(sistema, never()).excluirCliente(anyString());
    }
    
    @Test
    public void testListarClientesVazio() {
        List<Cliente> clientes = new ArrayList<>();
        when(sistema.listarClientes()).thenReturn(clientes);
        
        assertTrue(sistema.listarClientes().isEmpty());
        
        verify(sistema, times(1)).listarClientes();
    }
}
