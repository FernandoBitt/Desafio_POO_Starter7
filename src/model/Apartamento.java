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
    public boolean estaAlugado() {
        boolean status = alugado ? true : false;
        return status;
    }
    
    @Override
    public String contatoProprietario() {
        return "Proprietário do apartamento: " + proprietario.toString();
    }

    //sobreposição do método para utilização de polimorfismo
    @Override
    public double calcularAluguel(int meses) {
        // 10% extra de condominio
        valorAluguelMensal = valorAluguelMensal*1.10;
        double total = valorAluguelMensal * meses;
          
        if (meses >= 36) {
            total *= 0.80;
        } else if (meses >= 24) {
            total *= 0.85;
        } else if (meses >= 12) {
            total *= 0.90;
        }
        return total;
    }
    
    
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