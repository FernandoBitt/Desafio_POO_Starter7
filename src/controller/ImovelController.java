package controller;

import model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ImovelController {
    private List<Imovel> imoveis;
    
    public ImovelController() {
        this.imoveis = new ArrayList<>();
    }
    
    // CRUD operations
    public void cadastrarImovel(Imovel imovel) {
        imoveis.add(imovel);
    }
    
    public boolean deletarImovel(int index) {
        if (index >= 0 && index < imoveis.size()) {
            imoveis.remove(index);
            return true;
        }
        return false;
    }
    
    public Imovel getImovel(int index) {
        if (index >= 0 && index < imoveis.size()) {
            return imoveis.get(index);
        }
        return null;
    }
    
    public List<Imovel> listarImoveis() {
        return new ArrayList<>(imoveis);
    }
    
    public List<Imovel> listarImoveisDisponiveis() {
        return imoveis.stream()
                     .filter(imovel -> !imovel.isAlugado())
                     .collect(Collectors.toList());
    }
    
    public List<Imovel> listarImoveisAlugados() {
        return imoveis.stream()
                     .filter(Imovel::isAlugado)
                     .collect(Collectors.toList());
    }
    
    // Business operations
    public boolean alugarImovel(int indexImovel, Inquilino inquilino) {
        if (indexImovel >= 0 && indexImovel < imoveis.size() && inquilino != null) {
            Imovel imovel = imoveis.get(indexImovel);
            return imovel.alugar(inquilino);
        }
        return false;
    }
    
    public boolean liberarImovel(int index) {
        if (index >= 0 && index < imoveis.size()) {
            return imoveis.get(index).liberar();
        }
        return false;
    }
    
    public double calcularAluguelImovel(int index, int meses) {
        if (index >= 0 && index < imoveis.size()) {
            return imoveis.get(index).calcularAluguel(meses);
        }
        return 0.0;
    }
    
    // Statistics
    public int getTotalImoveis() { 
        return imoveis.size(); 
    }
    
    public int getTotalDisponiveis() { 
        return (int) imoveis.stream().filter(imovel -> !imovel.isAlugado()).count(); 
    }
    
    public int getTotalAlugados() { 
        return (int) imoveis.stream().filter(Imovel::isAlugado).count(); 
    }
    
    // Data initialization
    public void adicionarImovelExemplo(Imovel imovel) {
        imoveis.add(imovel);
    }
}
