package view.console;

import controller.CorretoraController;
import model.Inquilino;
import java.util.List;
import java.util.Scanner;

public class InquilinoView {
    private CorretoraController controller;
    private Scanner scanner;
    
    public InquilinoView(CorretoraController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }
    
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== GERENCIAR INQUILINOS ===");
            System.out.println("1 - Cadastrar Inquilino");
            System.out.println("2 - Listar Inquilinos");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    cadastrarInquilino();
                    break;
                case 2:
                    listarInquilinos();
                    break;
            }
        } while (opcao != 0);
    }
    
    public void cadastrarInquilino() {
        System.out.println("\n=== CADASTRAR INQUILINO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        Inquilino inquilino = new Inquilino(nome, telefone, cpf);
        controller.cadastrarInquilino(inquilino);
        System.out.println("Inquilino cadastrado com sucesso!");
    }
    
    public void listarInquilinos() {
        System.out.println("\n=== LISTA DE INQUILINOS ===");
        List<Inquilino> inquilinos = controller.listarInquilinos();
        
        if (inquilinos.isEmpty()) {
            System.out.println("Nenhum inquilino cadastrado.");
            return;
        }
        
        for (int i = 0; i < inquilinos.size(); i++) {
            System.out.println((i + 1) + " - " + inquilinos.get(i));
        }
    }
}