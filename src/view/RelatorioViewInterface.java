package view;

import controller.CorretoraController;
import javax.swing.JOptionPane;

public class RelatorioViewInterface {
    private final CorretoraController controller;

    public RelatorioViewInterface(CorretoraController controller) {
        this.controller = controller;
    }

    public void exibirRelatorios() {
        StringBuilder relatorio = new StringBuilder();
        relatorio.append("=== RELATÓRIOS ===\n\n");
        relatorio.append("Total de Imóveis: ").append(controller.getTotalImoveis()).append("\n");
        relatorio.append("Total de Proprietários: ").append(controller.getTotalProprietarios()).append("\n");
        relatorio.append("Total de Inquilinos: ").append(controller.getTotalInquilinos()).append("\n");
        relatorio.append("Imóveis Disponíveis: ").append(controller.getTotalDisponiveis()).append("\n");
        relatorio.append("Imóveis Alugados: ").append(controller.getTotalAlugados()).append("\n");

        JOptionPane.showMessageDialog(
                null,
                relatorio.toString(),
                "Relatórios",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
