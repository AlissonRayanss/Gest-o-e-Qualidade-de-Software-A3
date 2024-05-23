package IntegrationTests;

import entidades.Cliente;
import entidades.GerenciarClientes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TesteIntegracao {

    @Test
    public void testeIntegracaoGerenciarClientes() {
        GerenciarClientes gerenciador = new GerenciarClientes();

        Cliente cliente1 = new Cliente("12345678900", "João Silva", "Rua A, 123", "123456789", "joao@email.com");
        gerenciador.cadastrarCliente(cliente1);

        Cliente clienteConsultado = gerenciador.consultarCliente("12345678900");
        Assertions.assertEquals(cliente1, clienteConsultado);

        Cliente clienteAtualizado = new Cliente("12345678900", "João da Silva", "Rua B, 456", "987654321", "joao_novo@email.com");
        gerenciador.editarCliente("12345678900", clienteAtualizado);
        Cliente clienteAtualizadoConsultado = gerenciador.consultarCliente("12345678900");
        Assertions.assertEquals(clienteAtualizado, clienteAtualizadoConsultado);

        gerenciador.cadastrarCliente(new Cliente("98765432100", "Maria Souza", "Rua C, 789", "987654321", "maria@email.com"));
        gerenciador.cadastrarCliente(new Cliente("65432198700", "Pedro Oliveira", "Rua D, 456", "654321987", "pedro@email.com"));
        gerenciador.listarClientes();

        gerenciador.excluirCliente("98765432100");
        Cliente clienteExcluido = gerenciador.consultarCliente("98765432100");
        Assertions.assertNull(clienteExcluido);
    }
}