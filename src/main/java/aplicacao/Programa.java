package aplicacao;

import entidades.Cliente;
import entidades.GerenciarClientes;

import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciarClientes sistema = new GerenciarClientes();

        while (true) {
            System.out.println("-Sistema de Gerenciamento de Clientes-");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Editar cliente");
            System.out.println("3. Excluir cliente");
            System.out.println("4. Consultar cliente");
            System.out.println("5. Listar clientes");
            System.out.println("6. Sair");
            System.out.println("");
            System.out.print("Escolha uma opção: ");
            int op;
            try {
                op = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
                continue;
            }

            switch (op) {
                case 1:
                    System.out.println("-Cadastro de Cliente-");
                    System.out.println("CPF: ");
                    String cpf = sc.next();
                    System.out.println("Nome: ");
                    String nome = sc.next();
                    System.out.print("Endereço: ");
                    String endereco = sc.next();
                    System.out.println("Telefone: ");
                    String telefone = sc.next();
                    System.out.println("E-mail: ");
                    String email = sc.next();
                    Cliente novoCliente = new Cliente(cpf, nome, endereco, telefone, email);
                    sistema.cadastrarCliente(novoCliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("-Edição de Cliente-");
                    System.out.print("CPF do cliente a ser editado: ");
                    String clienteCpf = sc.next();
                    Cliente clienteExistente = sistema.consultarCliente(clienteCpf);
                    if (clienteExistente != null) {
                        System.out.print("Novo nome: ");
                        String novoNome = sc.next();
                        System.out.print("Novo endereço: ");
                        String novoEndereco = sc.next();
                        System.out.print("Novo telefone: ");
                        String novoTelefone = sc.next();
                        System.out.print("Novo e-mail: ");
                        String novoEmail = sc.next();
                        Cliente clienteAtualizado = new Cliente(clienteCpf, novoNome, novoEndereco, novoTelefone, novoEmail);
                        sistema.editarCliente(clienteCpf, clienteAtualizado);
                        System.out.println("Cliente atualizado com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("-Exclusão de Cliente-");
                    System.out.print("CPF do cliente a ser excluído: ");
                    String clienteCpfExcluir = sc.next();
                    sistema.excluirCliente(clienteCpfExcluir);
                    System.out.println("Cliente excluído com sucesso!");
                    break;
                case 4:
                    System.out.println("-Consulta de Cliente-");
                    System.out.print("CPF do cliente a ser consultado: ");
                    String clienteCpfConsultar = sc.next();
                    Cliente clienteConsultado = sistema.consultarCliente(clienteCpfConsultar);
                    if (clienteConsultado != null) {
                        System.out.println("CPF: " + clienteConsultado.getCpf());
                        System.out.println("Nome: " + clienteConsultado.getNome());
                        System.out.println("Endereço: " + clienteConsultado.getEndereco());
                        System.out.println("Telefone: " + clienteConsultado.getTelefone());
                        System.out.println("E-mail: " + clienteConsultado.getEmail());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 5:
                    System.out.println("-Listagem de Clientes-");
                    sistema.listarClientes();
                    break;
                case 6:
                    System.out.println("Programa finalizado");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
