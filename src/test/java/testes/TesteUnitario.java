package testes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import entidades.GerenciarClientes;
import aplicacao.Programa;
import entidades.Cliente;
import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.util.Scanner;

public class TesteUnitario {

    @Mock
    private Scanner scannerMock;

    @Mock
    private GerenciarClientes gerenciarClientesMock;

    @Test
    public void CadastrarClienteComSucesso() {
        
        String cpf = "12345678900";
        String nome = "Fulano de Tal";
        String endereco = "Rua dos Bobos, 0";
        long telefone = 1234567890L;
        String email = "fulano@email.com";
        Cliente novoCliente = new Cliente(cpf, nome, endereco, telefone, email);

        when(scannerMock.next()).thenReturn(cpf, nome, endereco, String.valueOf(telefone), email);

        
        Programa programa = new Programa(gerenciarClientesMock, scannerMock);
        programa.main(new String[0]);

        
        verify(gerenciarClientesMock).cadastrarCliente(novoCliente);
    }
}
