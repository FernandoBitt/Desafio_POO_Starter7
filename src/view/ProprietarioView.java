package view;

import controller.CorretoraController;
import model.Proprietario;
import java.util.List;
import java.util.Scanner;

public class ProprietarioView {
    private CorretoraController controller;
    private Scanner scanner;
    
    public ProprietarioView(CorretoraController controller, Scanner scanner) {
        this.controller = controller;
        this.scanner = scanner;
    }
    
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== GERENCIAR PROPRIETÁRIOS ===");
            System.out.println("1 - Cadastrar Proprietário");
            System.out.println("2 - Listar Proprietários");
            System.out.println("0 - Voltar");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    cadastrarProprietario();
                    break;
                case 2:
                    listarProprietarios();
                    break;
            }
        } while (opcao != 0);
    }
    
    public void cadastrarProprietario() {
        System.out.println("\n=== CADASTRAR PROPRIETÁRIO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        Proprietario proprietario = new Proprietario(nome, telefone, cpf);
        controller.cadastrarProprietario(proprietario);
        System.out.println("Proprietário cadastrado com sucesso!");
    }
    
    public void listarProprietarios() {
        System.out.println("\n=== LISTA DE PROPRIETÁRIOS ===");
        List<Proprietario> proprietarios = controller.listarProprietarios();
        
        if (proprietarios.isEmpty()) {
            System.out.println("Nenhum proprietário cadastrado.");
            return;
        }
        
        for (int i = 0; i < proprietarios.size(); i++) {
            System.out.println((i + 1) + " - " + proprietarios.get(i));
        }
    }
}
