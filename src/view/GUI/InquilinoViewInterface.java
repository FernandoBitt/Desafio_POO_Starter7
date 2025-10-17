package view.gui;

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
        String[] opcoes = { "Cadastrar Inquilino", "Listar Inquilinos", "Deletar Inquilinos", "Voltar" };
        int escolha;

        do {
            escolha = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    "GERENCIAR INQUILINOS",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]);

            if (escolha == JOptionPane.CLOSED_OPTION || escolha == 3) {
                escolha = 3; // Voltar
            }

            switch (escolha) {
                case 0 -> cadastrarInquilino();
                case 1 -> listarInquilinos();
                case 2 -> deletarInquilino();
            }

        } while (escolha != 3);
    }

    private void cadastrarInquilino() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do inquilino:", "Cadastrar Inquilino",
                JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.isBlank())
            return;

        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone:", "Cadastrar Inquilino",
                JOptionPane.QUESTION_MESSAGE);
        if (telefone == null || telefone.isBlank())
            return;

        String cpf = JOptionPane.showInputDialog(null, "Digite o CPF:", "Cadastrar Inquilino",
                JOptionPane.QUESTION_MESSAGE);
        if (cpf == null || cpf.isBlank())
            return;

        Inquilino inquilino = new Inquilino(nome, telefone, cpf);
        controller.cadastrarInquilino(inquilino);

        JOptionPane.showMessageDialog(null, "Inquilino cadastrado com sucesso!", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarInquilinos() {
        List<Inquilino> inquilinos = controller.listarInquilinos();

        if (inquilinos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum inquilino cadastrado.", "Lista de Inquilinos",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("=== LISTA DE INQUILINOS ===\n\n");
        for (int i = 0; i < inquilinos.size(); i++) {
            sb.append((i + 1)).append(" - ").append(inquilinos.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Inquilinos", JOptionPane.PLAIN_MESSAGE);
    }

    private void deletarInquilino() {
        List<Inquilino> inquilinos = controller.listarInquilinos();

        if (inquilinos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum inquilino cadastrado.", "Deletar Inquilino",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Montar a lista de inquilinos para exibir
        StringBuilder sb = new StringBuilder("Selecione o inquilino a ser deletado:\n\n");
        for (int i = 0; i < inquilinos.size(); i++) {
            sb.append(i + 1).append(" - ").append(inquilinos.get(i)).append("\n");
        }

        String input = JOptionPane.showInputDialog(null, sb.toString(), "Deletar Inquilino",
                JOptionPane.QUESTION_MESSAGE);
        if (input == null || input.isBlank())
            return;

        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Número inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (index < 0 || index >= inquilinos.size()) {
            JOptionPane.showMessageDialog(null, "Inquilino inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Remover o inquilino
        Inquilino removido = inquilinos.get(index);
        if (controller.deletarInquilinos(index)) {
            JOptionPane.showMessageDialog(null, "Inquilino '" + removido + "' deletado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao deletar inquilino!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
