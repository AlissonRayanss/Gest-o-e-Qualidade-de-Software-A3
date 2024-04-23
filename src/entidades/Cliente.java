package entidades;

public class Cliente {
	public String cpf;
    public String nome;
    public String endereco;
    public String telefone;
    public String email;

    public Cliente() {
    }
    
    public Cliente(String endereco, String nome, String email, String clienteCpf, String novoTelefone ) {
    	  this.cpf = clienteCpf; 
          this.nome = nome; 
          this.endereco = endereco;
          this.telefone = novoTelefone; 
          this.email = email; 
    }

    public String getCpf() {
        return cpf;

    }

    public void setCpf(String cpf) {
        this.cpf = cpf;

    }

    public String getNome() {
        return nome;

    }

    public void setNome(String nome) {
        this.nome = nome;

    }

    public String getEndereco() {
        return endereco;

    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;

    }

    public String getTelefone() {
        return telefone;

    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;

    }

    public String getEmail() {
        return email;

    }

    public void setEmail(String email) {
        this.email = email;

    }

}