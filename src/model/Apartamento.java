package model;

public class Apartamento extends Imovel {
    private int andar;
    private String bloco;
    private boolean temVaranda;
    
    public Apartamento(String endereco, int numero, Proprietario proprietario, 
                      double valorAluguelMensal, int andar, String bloco, boolean temVaranda) {
        super(endereco, numero, proprietario, valorAluguelMensal);
        this.andar = andar;
        this.bloco = bloco;
        this.temVaranda = temVaranda;
    }
    
    @Override
    public String estaAlugado() {
        String status = alugado ? "está alugado" : "está disponível";
        return "O apartamento de número " + numero + " " + status;
    }
    
    @Override
    public String contatoProprietario() {
        return "Proprietário do apartamento: " + proprietario.toString();
    }
    
    // Getters específicos
    public int getAndar() { return andar; }
    public String getBloco() { return bloco; }
    public boolean isTemVaranda() { return temVaranda; }
    
    @Override
    public String toString() {
        return "Apartamento - End: " + endereco + ", Nº: " + numero + 
               ", Andar: " + andar + ", Bloco: " + bloco + 
               ", Varanda: " + (temVaranda ? "Sim" : "Não") +
               ", Aluguel: R$ " + valorAluguelMensal + " - " + estaAlugado();
    }
}