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

    public void cadastrarProprietario(Proprietario proprietario) {
        proprietarioController.cadastrarProprietario(proprietario);
    }

    public List<Proprietario> listarProprietarios() {
        return proprietarioController.listarProprietarios();
    }

    public Proprietario getProprietario(int index) {
        return proprietarioController.getProprietario(index);
    }

    public void cadastrarInquilino(Inquilino inquilino) {
        inquilinoController.cadastrarInquilino(inquilino);
    }

    public List<Inquilino> listarInquilinos() {
        return inquilinoController.listarInquilinos();
    }

    public Inquilino getInquilino(int index) {
        return inquilinoController.getInquilino(index);
    }

    public int getTotalImoveis() {
        return imovelController.getTotalImoveis();
    }

    public int getTotalProprietarios() {
        return proprietarioController.getTotalProprietarios();
    }

    public int getTotalInquilinos() {
        return inquilinoController.getTotalInquilinos();
    }

    public int getTotalDisponiveis() {
        return imovelController.getTotalDisponiveis();
    }

    public int getTotalAlugados() {
        return imovelController.getTotalAlugados();
    }

    public ImovelController getImovelController() {
        return imovelController;
    }

    public ProprietarioController getProprietarioController() {
        return proprietarioController;
    }

    public InquilinoController getInquilinoController() {
        return inquilinoController;
    }

    private void inicializarDadosExemplo() {
        // Proprietários exemplo
        Proprietario prop1 = new Proprietario("João Silva", "(11) 9999-8888", "123.456.789-00");
        Proprietario prop2 = new Proprietario("Maria Santos", "(11) 7777-6666", "987.654.321-00");
        Proprietario prop3 = new Proprietario("Pedro Almeida", "(11) 4444-5555", "222.333.444-55");
        Proprietario prop4 = new Proprietario("Cláudia Rocha", "(11) 8888-9999", "666.777.888-99");

        proprietarioController.adicionarProprietarioExemplo(prop1);
        proprietarioController.adicionarProprietarioExemplo(prop2);
        proprietarioController.adicionarProprietarioExemplo(prop3);
        proprietarioController.adicionarProprietarioExemplo(prop4);

        // Inquilinos exemplo
        Inquilino inquilino1 = new Inquilino("Carlos Oliveira", "(11) 5555-4444", "111.222.333-44");
        Inquilino inquilino2 = new Inquilino("Ana Costa", "(11) 3333-2222", "555.666.777-88");
        Inquilino inquilino3 = new Inquilino("Lucas Ferreira", "(11) 2222-1111", "999.888.777-66");
        Inquilino inquilino4 = new Inquilino("Beatriz Lima", "(11) 6666-7777", "444.555.666-33");

        inquilinoController.adicionarInquilinoExemplo(inquilino1);
        inquilinoController.adicionarInquilinoExemplo(inquilino2);
        inquilinoController.adicionarInquilinoExemplo(inquilino3);
        inquilinoController.adicionarInquilinoExemplo(inquilino4);

        // Imóveis exemplo
        Casa casa1 = new Casa("Rua das Flores, 123", 100, prop1, 1500.0, true, 3);
        Apartamento apto1 = new Apartamento("Av. Principal, 456", 201, prop2, 1200.0, 5, "A", true);
        Casa casa2 = new Casa("Rua dos Girassóis, 78", 50, prop3, 1800.0, false, 4);
        Apartamento apto2 = new Apartamento("Rua das Palmeiras, 321", 302, prop4, 1300.0, 3, "B", false);

        imovelController.adicionarImovelExemplo(casa1);
        imovelController.adicionarImovelExemplo(apto1);
        imovelController.adicionarImovelExemplo(casa2);
        imovelController.adicionarImovelExemplo(apto2);

        // Alugar alguns imóveis de exemplo
        casa1.alugar(inquilino1);
        apto1.alugar(inquilino2);
    }

}