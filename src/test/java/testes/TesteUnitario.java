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
        sistema = new GerenciarClientes(); // Inicializa o objeto GerenciarClientes
    }
    
    @Test
    public void testCadastrarCliente() {
        Cliente cliente = new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com");
        sistema.cadastrarCliente(cliente);
        
        assertNotNull(sistema.consultarCliente("12345678900")); // Verifica se o cliente foi cadastrado corretamente
    }
    
    @Test
    public void testEditarCliente() {
        Cliente clienteAtualizado = new Cliente("12345678900", "João da Silva", "Rua B", "987654321", "joao.silva@example.com");
        sistema.cadastrarCliente(new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com"));
        
        assertTrue(sistema.editarCliente("12345678900", clienteAtualizado)); // Verifica se o cliente foi editado corretamente
    }
    
    @Test
    public void testExcluirCliente() {
        sistema.cadastrarCliente(new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com"));
        
        assertTrue(sistema.excluirCliente("12345678900")); // Verifica se o cliente foi excluído corretamente
    }
    
    @Test
    public void testConsultarCliente() {
        Cliente cliente = new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com");
        sistema.cadastrarCliente(cliente);
        
        assertEquals(cliente, sistema.consultarCliente("12345678900")); // Verifica se o cliente foi consultado corretamente
    }
    
    @Test
    public void testListarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("12345678900", "João", "Rua A", "123456789", "joao@example.com"));
        sistema.cadastrarCliente(clientes.get(0));
        
        assertEquals(clientes, sistema.listarClientes()); // Verifica se a lista de clientes está correta
    }
    
    @Test
    public void testConsultarClienteInexistente() {
        assertNull(sistema.consultarCliente("12345678900")); // Verifica se um cliente inexistente retorna null ao ser consultado
    }
    
    @Test
    public void testEditarClienteInexistente() {
        assertFalse(sistema.editarCliente("12345678900", new Cliente())); // Verifica se editar um cliente inexistente retorna false
    }
    
    @Test
    public void testExcluirClienteInexistente() {
        assertFalse(sistema.excluirCliente("12345678900")); // Verifica se excluir um cliente inexistente retorna false
    }
    
    @Test
    public void testListarClientesVazio() {
        assertTrue(sistema.listarClientes().isEmpty()); // Verifica se a lista de clientes está vazia
    }
}
