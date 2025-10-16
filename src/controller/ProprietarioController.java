package controller;

import model.Proprietario;
import java.util.ArrayList;
import java.util.List;

public class ProprietarioController {
    private List<Proprietario> proprietarios;
    
    public ProprietarioController() {
        this.proprietarios = new ArrayList<>();
    }
    
    // CRUD operations
    public void cadastrarProprietario(Proprietario proprietario) {
        proprietarios.add(proprietario);
    }
    
    public boolean deletarProprietario(int index) {
        if (index >= 0 && index < proprietarios.size()) {
            proprietarios.remove(index);
            return true;
        }
        return false;
    }
    
    public Proprietario getProprietario(int index) {
        if (index >= 0 && index < proprietarios.size()) {
            return proprietarios.get(index);
        }
        return null;
    }
    
    public List<Proprietario> listarProprietarios() {
        return new ArrayList<>(proprietarios);
    }
    
    // Statistics
    public int getTotalProprietarios() { 
        return proprietarios.size(); 
    }
    
    // Data initialization
    public void adicionarProprietarioExemplo(Proprietario proprietario) {
        proprietarios.add(proprietario);
    }
}
