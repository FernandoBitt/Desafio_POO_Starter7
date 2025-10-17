package model;

public class Inquilino {
    private String nome;
    private String telefone;
    private String cpf;
    
    public Inquilino(String nome, String telefone, String cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
    }
    
    public String getNome() { return nome; }
    public String getTelefone() { return telefone; }
    public String getCpf() { return cpf; }
    
    @Override
    public String toString() {
        return nome + " - Tel: " + telefone + " - CPF: " + cpf;
    }
}
