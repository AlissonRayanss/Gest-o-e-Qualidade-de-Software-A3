package entidades;

import java.util.ArrayList;
import java.util.List;

public class GerenciarClientes {
    private List<Cliente> clientes;

    public GerenciarClientes() {
        clientes = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void editarCliente(String cpf, Cliente novoCliente) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            if (cliente.getCpf().equals(cpf)) {
                clientes.set(i, novoCliente);
                break;
            }
        }
    }

    public void excluirCliente(String cpf) {
        clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
    }

    public Cliente consultarCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public void listarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Telefone: " + cliente.getTelefone());
            System.out.println();
        }
    }
}