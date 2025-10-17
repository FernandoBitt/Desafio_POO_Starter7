package controller;

import model.Inquilino;
import java.util.ArrayList;
import java.util.List;

public class InquilinoController {
    private List<Inquilino> inquilinos;
    
    public InquilinoController() {
        this.inquilinos = new ArrayList<>();
    }
    
    
    public void cadastrarInquilino(Inquilino inquilino) {
        inquilinos.add(inquilino);
    }
    
    public boolean deletarInquilino(int index) {
        if (index >= 0 && index < inquilinos.size()) {
            inquilinos.remove(index);
            return true;
        }
        return false;
    }
    
    public Inquilino getInquilino(int index) {
        if (index >= 0 && index < inquilinos.size()) {
            return inquilinos.get(index);
        }
        return null;
    }
    
    public List<Inquilino> listarInquilinos() {
        return new ArrayList<>(inquilinos);
    }
    
    
    public int getTotalInquilinos() { 
        return inquilinos.size(); 
    }
    
    
    public void adicionarInquilinoExemplo(Inquilino inquilino) {
        inquilinos.add(inquilino);
    }
}