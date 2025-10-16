package model;

public abstract class Imovel {
    protected String endereco;
    protected int numero;
    protected boolean alugado;
    protected Proprietario proprietario;
    protected Inquilino inquilino;
    protected double valorAluguelMensal;
    
    public Imovel(String endereco, int numero, Proprietario proprietario, double valorAluguelMensal) {
        this.endereco = endereco;
        this.numero = numero;
        this.alugado = false;
        this.proprietario = proprietario;
        this.valorAluguelMensal = valorAluguelMensal;
        this.inquilino = null;
    }
    
    
    public abstract String estaAlugado();
    public abstract String contatoProprietario();
    
    
    public double calcularAluguel(int meses) {
        double total = valorAluguelMensal * meses;
        
        
        if (meses >= 36) {
            total *= 0.85;
        } else if (meses >= 24) {
            total *= 0.90;
        } else if (meses >= 12) {
            total *= 0.95;
        }
        
        return total;
    }
    
    
    public boolean alugar(Inquilino inquilino) {
        if (!alugado) {
            this.alugado = true;
            this.inquilino = inquilino;
            return true;
        }
        return false;
    }
    
    
    public boolean liberar() {
        if (alugado) {
            this.alugado = false;
            this.inquilino = null;
            return true;
        }
        return false;
    }
    

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    
    public boolean isAlugado() { return alugado; }
    
    public Proprietario getProprietario() { return proprietario; }
    public void setProprietario(Proprietario proprietario) { this.proprietario = proprietario; }
    
    public double getValorAluguelMensal() { return valorAluguelMensal; }
    public void setValorAluguelMensal(double valorAluguelMensal) { 
        this.valorAluguelMensal = valorAluguelMensal; 
    }
    
    public Inquilino getInquilino() { return inquilino; }
}
