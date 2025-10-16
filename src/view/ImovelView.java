package view;

import controller.CorretoraController;
import model.*;
import java.util.List;
import java.util.Scanner;

public class ImovelView {
    private CorretoraController controller;
    private Scanner scanner;
    
    public ImovelView(CorretoraController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }
    
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== GERENCIAR IMÓVEIS ===");
            System.out.println("1 - Cadastrar Imóvel");
            System.out.println("2 - Listar Todos os Imóveis");
            System.out.println("3 - Deletar Imóvel");
            System.out.println("4 - Alugar Imóvel");
            System.out.println("5 - Liberar Imóvel");
            System.out.println("6 - Calcular Aluguel por Período");
            System.out.println("7 - Listar Imóveis Disponíveis");
            System.out.println("8 - Listar Imóveis Alugados");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    cadastrarImovel();
                    break;
                case 2:
                    listarImoveis();
                    break;
                case 3:
                    deletarImovel();
                    break;
                case 4:
                    alugarImovel();
                    break;
                case 5:
                    liberarImovel();
                    break;
                case 6:
                    calcularAluguelPeriodo();
                    break;
                case 7:
                    listarImoveisDisponiveis();
                    break;
                case 8:
                    listarImoveisAlugados();
                    break;
            }
        } while (opcao != 0);
    }
    
    public void cadastrarImovel() {
        System.out.println("\n=== CADASTRAR IMÓVEL ===");
        System.out.println("1 - Casa");
        System.out.println("2 - Apartamento");
        System.out.print("Escolha o tipo: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        System.out.print("Número: ");
        int numero = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Valor do aluguel mensal: ");
        double valorAluguel = scanner.nextDouble();
        scanner.nextLine();
        
        // Selecionar proprietário
        List<Proprietario> proprietarios = controller.listarProprietarios();
        if (proprietarios.isEmpty()) {
            System.out.println("É necessário cadastrar um proprietário primeiro!");
            return;
        }
        
        System.out.println("\nSelecione o proprietário:");
        for (int i = 0; i < proprietarios.size(); i++) {
            System.out.println((i + 1) + " - " + proprietarios.get(i));
        }
        System.out.print("Escolha: ");
        int indexProprietario = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (indexProprietario < 0 || indexProprietario >= proprietarios.size()) {
            System.out.println("Proprietário inválido!");
            return;
        }
        
        Proprietario proprietario = proprietarios.get(indexProprietario);
        
        if (tipo == 1) { // Casa
            System.out.print("Tem quintal? (true/false): ");
            boolean temQuintal = scanner.nextBoolean();
            System.out.print("Número de quartos: ");
            int quartos = scanner.nextInt();
            scanner.nextLine();
            
            Casa casa = new Casa(endereco, numero, proprietario, valorAluguel, temQuintal, quartos);
            controller.cadastrarImovel(casa);
            System.out.println("Casa cadastrada com sucesso!");
            
        } else if (tipo == 2) { // Apartamento
            System.out.print("Andar: ");
            int andar = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Bloco: ");
            String bloco = scanner.nextLine();
            
            System.out.print("Tem varanda? (true/false): ");
            boolean temVaranda = scanner.nextBoolean();
            scanner.nextLine();
            
            Apartamento apto = new Apartamento(endereco, numero, proprietario, valorAluguel, andar, bloco, temVaranda);
            controller.cadastrarImovel(apto);
            System.out.println("Apartamento cadastrado com sucesso!");
        }
    }
    
    public void listarImoveis() {
        System.out.println("\n=== LISTA DE IMÓVEIS ===");
        List<Imovel> imoveis = controller.listarImoveis();
        
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
            return;
        }
        
        for (int i = 0; i < imoveis.size(); i++) {
            Imovel imovel = imoveis.get(i);
            System.out.println((i + 1) + " - " + imovel);
            System.out.println("   " + imovel.contatoProprietario());
            if (imovel.isAlugado() && imovel.getInquilino() != null) {
                System.out.println("   Inquilino: " + imovel.getInquilino());
            }
            System.out.println();
        }
    }
    
    public void deletarImovel() {
        List<Imovel> imoveis = controller.listarImoveis();
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
            return;
        }
        
        listarImoveis();
        System.out.print("Digite o número do imóvel a ser deletado: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (controller.deletarImovel(index)) {
            System.out.println("Imóvel deletado com sucesso!");
        } else {
            System.out.println("Índice inválido!");
        }
    }
    
    public void alugarImovel() {
        List<Imovel> disponiveis = controller.listarImoveisDisponiveis();
        List<Inquilino> inquilinos = controller.listarInquilinos();
        
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum imóvel disponível para alugar.");
            return;
        }
        
        if (inquilinos.isEmpty()) {
            System.out.println("Nenhum inquilino cadastrado.");
            return;
        }
        
        System.out.println("\nImóveis disponíveis:");
        for (int i = 0; i < disponiveis.size(); i++) {
            System.out.println((i + 1) + " - " + disponiveis.get(i));
        }
        
        System.out.print("Escolha o imóvel: ");
        int indexImovel = scanner.nextInt() - 1;
        scanner.nextLine();
        
        System.out.println("\nInquilinos:");
        for (int i = 0; i < inquilinos.size(); i++) {
            System.out.println((i + 1) + " - " + inquilinos.get(i));
        }
        
        System.out.print("Escolha o inquilino: ");
        int indexInquilino = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (controller.alugarImovel(indexImovel, indexInquilino)) {
            System.out.println("Imóvel alugado com sucesso!");
        } else {
            System.out.println("Erro ao alugar imóvel!");
        }
    }
    
    public void liberarImovel() {
        List<Imovel> alugados = controller.listarImoveisAlugados();
        
        if (alugados.isEmpty()) {
            System.out.println("Nenhum imóvel alugado no momento.");
            return;
        }
        
        System.out.println("\nImóveis alugados:");
        for (int i = 0; i < alugados.size(); i++) {
            System.out.println((i + 1) + " - " + alugados.get(i));
        }
        
        System.out.print("Escolha o imóvel a ser liberado: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (controller.liberarImovel(index)) {
            System.out.println("Imóvel liberado com sucesso!");
        } else {
            System.out.println("Erro ao liberar imóvel!");
        }
    }
    
    public void calcularAluguelPeriodo() {
        List<Imovel> imoveis = controller.listarImoveis();
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel cadastrado.");
            return;
        }
        
        listarImoveis();
        System.out.print("Digite o número do imóvel: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        
        System.out.print("Digite o número de meses: ");
        int meses = scanner.nextInt();
        scanner.nextLine();
        
        double total = controller.calcularAluguelImovel(index, meses);
        Imovel imovel = imoveis.get(index);
        
        System.out.println("\n=== CÁLCULO DO ALUGUEL ===");
        System.out.println("Imóvel: " + imovel);
        System.out.println("Período: " + meses + " meses");
        System.out.println("Valor mensal: R$ " + imovel.getValorAluguelMensal());
        System.out.println("Valor total: R$ " + total);
        
        if (meses >= 12) {
            double desconto = (imovel.getValorAluguelMensal() * meses) - total;
            System.out.println("Desconto aplicado: R$ " + desconto);
        }
    }
    
    public void listarImoveisDisponiveis() {
        System.out.println("\n=== IMÓVEIS DISPONÍVEIS ===");
        List<Imovel> disponiveis = controller.listarImoveisDisponiveis();
        
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum imóvel disponível.");
            return;
        }
        
        for (Imovel imovel : disponiveis) {
            System.out.println(imovel);
            System.out.println("   " + imovel.contatoProprietario());
            System.out.println();
        }
    }
    
    public void listarImoveisAlugados() {
        System.out.println("\n=== IMÓVEIS ALUGADOS ===");
        List<Imovel> alugados = controller.listarImoveisAlugados();
        
        if (alugados.isEmpty()) {
            System.out.println("Nenhum imóvel alugado no momento.");
            return;
        }
        
        for (Imovel imovel : alugados) {
            System.out.println(imovel);
            System.out.println("   Inquilino: " + imovel.getInquilino());
            System.out.println("   " + imovel.contatoProprietario());
            System.out.println();
        }
    }
}