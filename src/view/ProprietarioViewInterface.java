package view;

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
        String[] opcoes = { "Cadastrar Proprietário", "Listar Proprietários", "Voltar" };
        int escolha;

        do {
            escolha = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    "🏡 GERENCIAR PROPRIETÁRIOS",
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
                case 0 -> cadastrarProprietario();
                case 1 -> listarProprietarios();
            }

        } while (escolha != 2);
    }

    private void cadastrarProprietario() {
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do proprietário:", "Cadastrar Proprietário", JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.isBlank()) return;

        String telefone = JOptionPane.showInputDialog(null, "Digite o telefone:", "Cadastrar Proprietário", JOptionPane.QUESTION_MESSAGE);
        if (telefone == null || telefone.isBlank()) return;

        String cpf = JOptionPane.showInputDialog(null, "Digite o CPF:", "Cadastrar Proprietário", JOptionPane.QUESTION_MESSAGE);
        if (cpf == null || cpf.isBlank()) return;

        Proprietario proprietario = new Proprietario(nome, telefone, cpf);
        controller.cadastrarProprietario(proprietario);

        JOptionPane.showMessageDialog(null, "Proprietário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarProprietarios() {
        List<Proprietario> proprietarios = controller.listarProprietarios();

        if (proprietarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum proprietário cadastrado.", "Lista de Proprietários", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("=== LISTA DE PROPRIETÁRIOS ===\n\n");
        for (int i = 0; i < proprietarios.size(); i++) {
            sb.append((i + 1)).append(" - ").append(proprietarios.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Proprietários", JOptionPane.PLAIN_MESSAGE);
    }

}
