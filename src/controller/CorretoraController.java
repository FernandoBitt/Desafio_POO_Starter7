package controller;

import model.*;
import java.util.List;

public class CorretoraController {
    private ImovelController imovelController;
    private ProprietarioController proprietarioController;
    private InquilinoController inquilinoController;
    
    public CorretoraController() {
        this.imovelController = new ImovelController();
        this.proprietarioController = new ProprietarioController();
        this.inquilinoController = new InquilinoController();
        inicializarDadosExemplo();
    }
    
    // Delegation methods for Imoveis
    public void cadastrarImovel(Imovel imovel) {
        imovelController.cadastrarImovel(imovel);
    }
    
    public boolean deletarImovel(int index) {
        return imovelController.deletarImovel(index);
    }
    
    public List<Imovel> listarImoveis() {
        return imovelController.listarImoveis();
    }
    
    public List<Imovel> listarImoveisDisponiveis() {
        return imovelController.listarImoveisDisponiveis();
    }
    
    public List<Imovel> listarImoveisAlugados() {
        return imovelController.listarImoveisAlugados();
    }
    
    public boolean alugarImovel(int indexImovel, int indexInquilino) {
        Inquilino inquilino = inquilinoController.getInquilino(indexInquilino);
        return imovelController.alugarImovel(indexImovel, inquilino);
    }
    
    public boolean liberarImovel(int index) {
        return imovelController.liberarImovel(index);
    }
    
    public double calcularAluguelImovel(int index, int meses) {
        return imovelController.calcularAluguelImovel(index, meses);
    }
    
    // Delegation methods for Proprietarios
    public void cadastrarProprietario(Proprietario proprietario) {
        proprietarioController.cadastrarProprietario(proprietario);
    }
    
    public List<Proprietario> listarProprietarios() {
        return proprietarioController.listarProprietarios();
    }
    
    public Proprietario getProprietario(int index) {
        return proprietarioController.getProprietario(index);
    }
    
    // Delegation methods for Inquilinos
    public void cadastrarInquilino(Inquilino inquilino) {
        inquilinoController.cadastrarInquilino(inquilino);
    }
    
    public List<Inquilino> listarInquilinos() {
        return inquilinoController.listarInquilinos();
    }
    
    public Inquilino getInquilino(int index) {
        return inquilinoController.getInquilino(index);
    }
    
    // Statistics
    public int getTotalImoveis() { return imovelController.getTotalImoveis(); }
    public int getTotalProprietarios() { return proprietarioController.getTotalProprietarios(); }
    public int getTotalInquilinos() { return inquilinoController.getTotalInquilinos(); }
    public int getTotalDisponiveis() { return imovelController.getTotalDisponiveis(); }
    public int getTotalAlugados() { return imovelController.getTotalAlugados(); }
    
    // Getters for specific controllers
    public ImovelController getImovelController() { return imovelController; }
    public ProprietarioController getProprietarioController() { return proprietarioController; }
    public InquilinoController getInquilinoController() { return inquilinoController; }
    
    private void inicializarDadosExemplo() {
        // Proprietários exemplo
        Proprietario prop1 = new Proprietario("João Silva", "(11) 9999-8888", "123.456.789-00");
        Proprietario prop2 = new Proprietario("Maria Santos", "(11) 7777-6666", "987.654.321-00");
        proprietarioController.adicionarProprietarioExemplo(prop1);
        proprietarioController.adicionarProprietarioExemplo(prop2);
        
        // Inquilinos exemplo
        Inquilino inquilino1 = new Inquilino("Carlos Oliveira", "(11) 5555-4444", "111.222.333-44");
        Inquilino inquilino2 = new Inquilino("Ana Costa", "(11) 3333-2222", "555.666.777-88");
        inquilinoController.adicionarInquilinoExemplo(inquilino1);
        inquilinoController.adicionarInquilinoExemplo(inquilino2);
        
        // Imóveis exemplo
        Casa casa1 = new Casa("Rua das Flores, 123", 100, prop1, 1500.0, true, 3);
        Apartamento apto1 = new Apartamento("Av. Principal, 456", 201, prop2, 1200.0, 5, "A", true);
        
        imovelController.adicionarImovelExemplo(casa1);
        imovelController.adicionarImovelExemplo(apto1);
        
        // Alugar um imóvel de exemplo
        casa1.alugar(inquilino1);
    }
}