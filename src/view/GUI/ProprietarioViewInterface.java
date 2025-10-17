package view.GUI;

import controller.CorretoraController;
import model.Proprietario;
import javax.swing.JOptionPane;
import java.util.List;

public class ProprietarioViewInterface {
    private CorretoraController controller;

    public ProprietarioViewInterface(CorretoraController controller) {
        this.controller = controller;
    }

    public void exibirMenu() {
        String[] opcoes = { "Cadastrar Proprietário", "Listar Proprietários", "Deletar Proprietários", "Voltar" };
        int escolha;

        do {
            escolha = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    "GERENCIAR PROPRIETÁRIOS",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]);

            if (escolha == JOptionPane.CLOSED_OPTION || escolha == 3) {
                escolha = 3; // Voltar
            }

            switch (escolha) {
                case 0 -> cadastrarProprietario();
                case 1 -> listarProprietarios();
                case 2 -> deletarProprietario();
            }

        } while (escolha != 3);
    }

    private void cadastrarProprietario() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do proprietário:", "Cadastrar Proprietário",
                JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.isBlank())
            return;

        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone:", "Cadastrar Proprietário",
                JOptionPane.QUESTION_MESSAGE);
        if (telefone == null || telefone.isBlank())
            return;

        String cpf = JOptionPane.showInputDialog(null, "Digite o CPF:", "Cadastrar Proprietário",
                JOptionPane.QUESTION_MESSAGE);
        if (cpf == null || cpf.isBlank())
            return;

        Proprietario proprietario = new Proprietario(nome, telefone, cpf);
        controller.cadastrarProprietario(proprietario);

        JOptionPane.showMessageDialog(null, "Proprietário cadastrado com sucesso!", "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarProprietarios() {
        List<Proprietario> proprietarios = controller.listarProprietarios();

        if (proprietarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum proprietário cadastrado.", "Lista de Proprietários",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("=== LISTA DE PROPRIETÁRIOS ===\n\n");
        for (int i = 0; i < proprietarios.size(); i++) {
            sb.append((i + 1)).append(" - ").append(proprietarios.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Proprietários", JOptionPane.PLAIN_MESSAGE);
    }

    private void deletarProprietario() {
        List<Proprietario> proprietarios = controller.listarProprietarios();

        if (proprietarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum proprietário cadastrado.", "Deletar Proprietário",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Montar a lista de proprietários para exibir
        StringBuilder sb = new StringBuilder("Selecione o proprietário a ser deletado:\n\n");
        for (int i = 0; i < proprietarios.size(); i++) {
            sb.append(i + 1).append(" - ").append(proprietarios.get(i)).append("\n");
        }

        String input = JOptionPane.showInputDialog(null, sb.toString(), "Deletar Proprietário",
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

        if (index < 0 || index >= proprietarios.size()) {
            JOptionPane.showMessageDialog(null, "Proprietário inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Remover o proprietário
        Proprietario removido = proprietarios.get(index);
        if (controller.deletarProprietario(index)) {
            JOptionPane.showMessageDialog(null, "Proprietário '" + removido + "' deletado com sucesso!", "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao deletar proprietário!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
