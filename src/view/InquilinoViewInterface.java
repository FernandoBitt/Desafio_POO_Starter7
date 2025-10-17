package view;

import controller.CorretoraController;
import model.Inquilino;
import javax.swing.JOptionPane;
import java.util.List;

public class InquilinoViewInterface {
    private CorretoraController controller;

    public InquilinoViewInterface(CorretoraController controller) {
        this.controller = controller;
    }

    public void exibirMenu() {
        String[] opcoes = { "Cadastrar Inquilino", "Listar Inquilinos", "Voltar" };
        int escolha;

        do {
            escolha = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    "🏠 GERENCIAR INQUILINOS",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha == JOptionPane.CLOSED_OPTION || escolha == 2) {
                escolha = 2; // Voltar
            }

            switch (escolha) {
                case 0 -> cadastrarInquilino();
                case 1 -> listarInquilinos();
            }

        } while (escolha != 2);
    }

    private void cadastrarInquilino() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do inquilino:", "Cadastrar Inquilino", JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.isBlank()) return;

        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone:", "Cadastrar Inquilino", JOptionPane.QUESTION_MESSAGE);
        if (telefone == null || telefone.isBlank()) return;

        String cpf = JOptionPane.showInputDialog(null, "Digite o CPF:", "Cadastrar Inquilino", JOptionPane.QUESTION_MESSAGE);
        if (cpf == null || cpf.isBlank()) return;

        Inquilino inquilino = new Inquilino(nome, telefone, cpf);
        controller.cadastrarInquilino(inquilino);

        JOptionPane.showMessageDialog(null, "Inquilino cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarInquilinos() {
        List<Inquilino> inquilinos = controller.listarInquilinos();

        if (inquilinos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum inquilino cadastrado.", "Lista de Inquilinos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("=== LISTA DE INQUILINOS ===\n\n");
        for (int i = 0; i < inquilinos.size(); i++) {
            sb.append((i + 1)).append(" - ").append(inquilinos.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Inquilinos", JOptionPane.PLAIN_MESSAGE);
    }

}
