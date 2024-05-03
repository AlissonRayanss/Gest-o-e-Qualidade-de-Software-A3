package entidades;

import java.util.ArrayList;
import java.util.List;
    
    public GerenciarClientes() {
        clientes = new ArrayList<>();
    }
    
    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    
    public boolean editarCliente(String cpf, Cliente clienteAtualizado) {
        Cliente cliente = consultarCliente(cpf);
        if (cliente != null) {
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEndereco(clienteAtualizado.getEndereco());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            cliente.setEmail(clienteAtualizado.getEmail());
            return true;
        }
        return false;
    }
    
    public boolean excluirCliente(String cpf) {
        Cliente cliente = consultarCliente(cpf);
        if (cliente != null) {
            clientes.remove(cliente);
            return true;
        }
        return false;
    }
    
    public Cliente consultarCliente(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }
    
    public List<Cliente> listarClientes() {
        return clientes;
    }
}
