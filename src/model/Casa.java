package model;

public class Casa extends Imovel {
    private boolean temQuintal;
    private int numeroQuartos;
    
    public Casa(String endereco, int numero, Proprietario proprietario, 
                double valorAluguelMensal, boolean temQuintal, int numeroQuartos) {
        super(endereco, numero, proprietario, valorAluguelMensal);
        this.temQuintal = temQuintal;
        this.numeroQuartos = numeroQuartos;
    }
    
    @Override
    public boolean estaAlugado() {
        return alugado ? true : false;
    }
    
    @Override
    public String contatoProprietario() {
        return "Proprietário da casa: " + proprietario.toString();
    }
    
    // Getters específicos
    public boolean isTemQuintal() { return temQuintal; }
    public int getNumeroQuartos() { return numeroQuartos; }
    
    @Override
    public String toString() {
        return "Casa - End: " + endereco + ", Nº: " + numero + 
               ", Quartos: " + numeroQuartos + ", Quintal: " + (temQuintal ? "Sim" : "Não") +
               ", Aluguel: R$ " + valorAluguelMensal + " - " + estaAlugado();
    }
}