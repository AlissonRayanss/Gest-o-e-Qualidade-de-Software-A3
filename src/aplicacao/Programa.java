package aplicacao;

import java.util.Scanner;

import entidades.Cliente;
import entidades.GerenciarClientes;

public class Programa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GerenciarClientes sistema = new GerenciarClientes();

        while (true) {
            System.out.println("");
            System.out.println("-Sistema de Gerenciamento de Clientes-");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Consultar cliente");
            System.out.println("3. Listar clientes");
            System.out.println("4. Sair");
            System.out.println("");
            System.out.print("Escolha uma opção: ");
            int op = sc.nextInt();

            switch (op) {
                case 1:
                    System.out.print("CPF: ");
                    String cpf = sc.next();

                    System.out.print("Nome: ");
                    sc.nextLine();
                    String nome = sc.nextLine();
                    
                    System.out.print("Email: ");
                    String email = sc.next();

                    System.out.print("Endereço: ");
                    sc.nextLine();
                    String endereco = sc.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = sc.next();

                    sistema.cadastrarCliente(new Cliente(endereco, nome, email, cpf, telefone));
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2:
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

                case 3:
                    System.out.println("-Lista de Clientes-");
                    sistema.listarClientes();
                    break;

                case 4:
                    System.out.println("Saindo do programa...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Opção inválida. Digite um número de 1 a 4.");
                    break;
            }
        }
    }
}
