package view;

import controller.CorretoraController;
import java.util.Scanner;

public class RelatorioView {
    private CorretoraController controller;
    private Scanner scanner;
    
    public RelatorioView(CorretoraController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }
    
    public void exibirRelatorios() {
        System.out.println("\n=== RELATÓRIOS ===");
        System.out.println("Total de Imóveis: " + controller.getTotalImoveis());
        System.out.println("Total de Proprietários: " + controller.getTotalProprietarios());
        System.out.println("Total de Inquilinos: " + controller.getTotalInquilinos());
        System.out.println("Imóveis Disponíveis: " + controller.getTotalDisponiveis());
        System.out.println("Imóveis Alugados: " + controller.getTotalAlugados());
        
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }
}