package view.console;

import controller.CorretoraController;

import java.util.Scanner;

public class CorretoraView {
    private CorretoraController controller;
    private Scanner scanner;
    private ImovelView imovelView;
    private ProprietarioView proprietarioView;
    private InquilinoView inquilinoView;
    private RelatorioView relatorioView;
    
    public CorretoraView() {
        this.controller = new CorretoraController();
        this.scanner = new Scanner(System.in);
        this.imovelView = new ImovelView(controller, scanner);
        this.proprietarioView = new ProprietarioView(controller, scanner);
        this.inquilinoView = new InquilinoView(controller, scanner);
        this.relatorioView = new RelatorioView(controller, scanner);
    }
    
    public void iniciar() {
        System.out.println("=== SISTEMA CORRETORA IMOBILIÁRIA (MVC ESPECÍFICO) ===");
        
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    imovelView.exibirMenu();
                    break;
                case 2:
                    proprietarioView.exibirMenu();
                    break;
                case 3:
                    inquilinoView.exibirMenu();
                    break;
                case 4:
                    relatorioView.exibirRelatorios();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
        
        scanner.close();
    }
    
    private void exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1 - Gerenciar Imóveis");
        System.out.println("2 - Gerenciar Proprietários");
        System.out.println("3 - Gerenciar Inquilinos");
        System.out.println("4 - Relatórios");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
}