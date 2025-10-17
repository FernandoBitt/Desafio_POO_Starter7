package view.gui;

import controller.CorretoraController;
import javax.swing.JOptionPane;

public class CorretoraViewInterface {
    private CorretoraController controller;

    public CorretoraViewInterface() {
        this.controller = new CorretoraController();
    }

    public void iniciar() {
        String[] opcoes = {
            "Imóveis",
            "Proprietários",
            "Inquilinos",
            "Relatórios",
            "Sair"
        };

        int escolha;

        do {
            escolha = JOptionPane.showOptionDialog(
                    null,
                    "Escolha uma das opções abaixo:",
                    "SISTEMA CORRETORA IMOBILIÁRIA",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            if (escolha == JOptionPane.CLOSED_OPTION) {
                escolha = 4; // tratar como "Sair"
            }

            switch (escolha) {
                
                case 0 -> new ImovelViewInterface(controller).exibirMenu();        

                case 1 -> new ProprietarioViewInterface(controller).exibirMenu();

                case 2 -> new InquilinoViewInterface(controller).exibirMenu();
                
                case 3 -> new RelatorioViewInterface(controller).exibirRelatorios();

                case 4 -> JOptionPane.showMessageDialog(null,
                        "Saindo do sistema...",
                        "Encerrando",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } while (escolha != 4);
    }

}
