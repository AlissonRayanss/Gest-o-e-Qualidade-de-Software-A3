package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import entidades.Cliente;
import entidades.GerenciarClientes;

public class TesteIntegracao {

    private GerenciarClientes sistema;

    @BeforeEach
    public void setUp() {
        sistema = new GerenciarClientes();
    }

    @Test
    public void testCRUD() {
        // Teste de Cadastro
        Cliente cliente1 = new Cliente("12345678901", "João", "Rua A", 123456789, "joao@example.com");
        sistema.cadastrarCliente(cliente1);
        assertEquals(1, sistema.listarClientes().size());

        // Teste de Consulta
        Cliente clienteConsultado = sistema.consultarCliente("12345678901");
        assertEquals("João", clienteConsultado.getNome());

        // Teste de Edição
        Cliente clienteEditado = new Cliente("12345678901", "João Silva", "Rua B", 987654321, "joao.silva@example.com");
        sistema.editarCliente("12345678901", clienteEditado);
        clienteConsultado = sistema.consultarCliente("12345678901");
        assertEquals("João Silva", clienteConsultado.getNome());
        assertEquals("Rua B", clienteConsultado.getEndereco());
        assertEquals(987654321, clienteConsultado.getTelefone());

        // Teste de Exclusão
        sistema.excluirCliente("12345678901");
        assertEquals(0, sistema.listarClientes().size());
    }
}
