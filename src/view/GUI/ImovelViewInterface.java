package view.GUI;

import controller.CorretoraController;
import model.*;
import javax.swing.*;
import java.util.List;

public class ImovelViewInterface {
    private final CorretoraController controller;

    public ImovelViewInterface(CorretoraController controller) {
        this.controller = controller;
    }

    public void exibirMenu() {
        String[] opcoes = {
                "Cadastrar Imóvel",
                "Listar Todos os Imóveis",
                "Deletar Imóvel",
                "Alugar Imóvel",
                "Liberar Imóvel",
                "Calcular Aluguel por Período",
                "Listar Imóveis Disponíveis",
                "Listar Imóveis Alugados",
                "Voltar"
        };

        int escolha;
        do {
            escolha = JOptionPane.showOptionDialog(
                    null,
                    "Selecione uma opção:",
                    "🏘️ GERENCIAR IMÓVEIS",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]);

            if (escolha == JOptionPane.CLOSED_OPTION || escolha == 8) {
                escolha = 8; // Voltar
            }

            switch (escolha) {
                case 0 -> cadastrarImovel();
                case 1 -> listarImoveis();
                case 2 -> deletarImovel();
                case 3 -> alugarImovel();
                case 4 -> liberarImovel();
                case 5 -> calcularAluguelPeriodo();
                case 6 -> listarImoveisDisponiveis();
                case 7 -> listarImoveisAlugados();
            }

        } while (escolha != 8);
    }

    private void cadastrarImovel() {
        String[] tipos = { "Casa", "Apartamento" };
        String tipoSelecionado = (String) JOptionPane.showInputDialog(
                null,
                "Selecione o tipo de imóvel:",
                "Cadastrar Imóvel",
                JOptionPane.PLAIN_MESSAGE,
                null,
                tipos,
                tipos[0]);
        if (tipoSelecionado == null)
            return;

        String endereco = JOptionPane.showInputDialog("Endereço:");
        if (endereco == null || endereco.isBlank())
            return;

        String numeroStr = JOptionPane.showInputDialog("Número:");
        if (numeroStr == null || numeroStr.isBlank())
            return;
        int numero = Integer.parseInt(numeroStr);

        String valorStr = JOptionPane.showInputDialog("Valor do aluguel mensal:");
        if (valorStr == null || valorStr.isBlank())
            return;
        double valorAluguel = Double.parseDouble(valorStr);

        
        List<Proprietario> proprietarios = controller.listarProprietarios();
        if (proprietarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "É necessário cadastrar um proprietário primeiro!");
            return;
        }

        StringBuilder sb = new StringBuilder("Selecione o proprietário:\n");
        for (int i = 0; i < proprietarios.size(); i++) {
            sb.append(i + 1).append(" - ").append(proprietarios.get(i)).append("\n");
        }

        String propStr = JOptionPane.showInputDialog(sb.toString());
        if (propStr == null || propStr.isBlank())
            return;
        int indexProprietario = Integer.parseInt(propStr) - 1;
        if (indexProprietario < 0 || indexProprietario >= proprietarios.size()) {
            JOptionPane.showMessageDialog(null, "Proprietário inválido!");
            return;
        }

        Proprietario proprietario = proprietarios.get(indexProprietario);

        if (tipoSelecionado.equals("Casa")) {
            boolean temQuintal = JOptionPane.showConfirmDialog(null, "Tem quintal?", "Casa",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            String quartosStr = JOptionPane.showInputDialog("Número de quartos:");
            if (quartosStr == null || quartosStr.isBlank())
                return;
            int quartos = Integer.parseInt(quartosStr);

            Casa casa = new Casa(endereco, numero, proprietario, valorAluguel, temQuintal, quartos);
            controller.cadastrarImovel(casa);
            JOptionPane.showMessageDialog(null, "Casa cadastrada com sucesso!");

        } else if (tipoSelecionado.equals("Apartamento")) {
            String andarStr = JOptionPane.showInputDialog("Andar:");
            if (andarStr == null || andarStr.isBlank())
                return;
            int andar = Integer.parseInt(andarStr);

            String bloco = JOptionPane.showInputDialog("Bloco:");
            if (bloco == null || bloco.isBlank())
                return;

            boolean temVaranda = JOptionPane.showConfirmDialog(null, "Tem varanda?", "Apartamento",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            Apartamento apto = new Apartamento(endereco, numero, proprietario, valorAluguel, andar, bloco, temVaranda);
            controller.cadastrarImovel(apto);
            JOptionPane.showMessageDialog(null, "Apartamento cadastrado com sucesso!");
        }
    }

    private void listarImoveis() {
        List<Imovel> imoveis = controller.listarImoveis();
        if (imoveis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum imóvel cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== LISTA DE IMÓVEIS ===\n\n");
        for (int i = 0; i < imoveis.size(); i++) {
            Imovel imovel = imoveis.get(i);
            sb.append(i + 1).append(" - ").append(imovel).append("\n");
            sb.append("   ").append(imovel.contatoProprietario()).append("\n");
            if (imovel.isAlugado() && imovel.getInquilino() != null) {
                sb.append("   Inquilino: ").append(imovel.getInquilino()).append("\n");
            }
            sb.append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Imóveis", JOptionPane.PLAIN_MESSAGE);
    }

    private void deletarImovel() {
        List<Imovel> imoveis = controller.listarImoveis();
        if (imoveis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum imóvel cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder("Digite o número do imóvel a ser deletado:\n");
        for (int i = 0; i < imoveis.size(); i++) {
            sb.append(i + 1).append(" - ").append(imoveis.get(i)).append("\n");
        }

        String input = JOptionPane.showInputDialog(sb.toString());
        if (input == null || input.isBlank())
            return;
        int index = Integer.parseInt(input) - 1;

        if (controller.deletarImovel(index)) {
            JOptionPane.showMessageDialog(null, "Imóvel deletado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Índice inválido!");
        }
    }

    private void alugarImovel() {
        List<Imovel> disponiveis = controller.listarImoveisDisponiveis();
        List<Inquilino> inquilinos = controller.listarInquilinos();

        if (disponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum imóvel disponível para alugar.");
            return;
        }

        if (inquilinos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum inquilino cadastrado.");
            return;
        }

        
        StringBuilder sbImoveis = new StringBuilder("Imóveis disponíveis:\n");
        for (int i = 0; i < disponiveis.size(); i++) {
            sbImoveis.append(i + 1).append(" - ").append(disponiveis.get(i)).append("\n");
        }
        String escolhaImovel = JOptionPane.showInputDialog(sbImoveis.toString() + "Escolha o imóvel:");
        if (escolhaImovel == null || escolhaImovel.isBlank())
            return;
        int indexImovel = Integer.parseInt(escolhaImovel) - 1;

        
        StringBuilder sbInquilinos = new StringBuilder("Inquilinos:\n");
        for (int i = 0; i < inquilinos.size(); i++) {
            sbInquilinos.append(i + 1).append(" - ").append(inquilinos.get(i)).append("\n");
        }
        String escolhaInquilino = JOptionPane.showInputDialog(sbInquilinos.toString() + "Escolha o inquilino:");
        if (escolhaInquilino == null || escolhaInquilino.isBlank())
            return;
        int indexInquilino = Integer.parseInt(escolhaInquilino) - 1;

        if (controller.alugarImovel(indexImovel, indexInquilino)) {
            JOptionPane.showMessageDialog(null, "Imóvel alugado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao alugar imóvel!");
        }
    }

    private void liberarImovel() {
        List<Imovel> alugados = controller.listarImoveisAlugados();

        if (alugados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum imóvel alugado no momento.");
            return;
        }

        StringBuilder sb = new StringBuilder("Imóveis alugados:\n");
        for (int i = 0; i < alugados.size(); i++) {
            sb.append(i + 1).append(" - ").append(alugados.get(i)).append("\n");
        }

        String escolha = JOptionPane.showInputDialog(sb.toString() + "Escolha o imóvel a liberar:");
        if (escolha == null || escolha.isBlank())
            return;
        int index = Integer.parseInt(escolha) - 1;

        if (controller.liberarImovel(index)) {
            JOptionPane.showMessageDialog(null, "Imóvel liberado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao liberar imóvel!");
        }
    }

    private void calcularAluguelPeriodo() {
        List<Imovel> imoveis = controller.listarImoveis();
        if (imoveis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum imóvel cadastrado.");
            return;
        }

        StringBuilder sb = new StringBuilder("Selecione o imóvel:\n");
        for (int i = 0; i < imoveis.size(); i++) {
            sb.append(i + 1).append(" - ").append(imoveis.get(i)).append("\n");
        }
        String escolhaImovel = JOptionPane.showInputDialog(sb.toString());
        if (escolhaImovel == null || escolhaImovel.isBlank())
            return;
        int index = Integer.parseInt(escolhaImovel) - 1;

        String mesesStr = JOptionPane.showInputDialog("Digite o número de meses:");
        if (mesesStr == null || mesesStr.isBlank())
            return;
        int meses = Integer.parseInt(mesesStr);

        double total = controller.calcularAluguelImovel(index, meses);
        Imovel imovel = imoveis.get(index);

        StringBuilder resultado = new StringBuilder();
        resultado.append("Imóvel: ").append(imovel).append("\n");
        resultado.append("Período: ").append(meses).append(" meses\n");
        resultado.append("Valor mensal: R$ ").append(imovel.getValorAluguelMensal()).append("\n");
        resultado.append("Valor total: R$ ").append(total).append("\n");

        if (meses >= 12) {
            double desconto = (imovel.getValorAluguelMensal() * meses) - total;
            resultado.append("Desconto aplicado: R$ ").append(desconto).append("\n");
        }

        JOptionPane.showMessageDialog(null, resultado.toString(), "Cálculo do Aluguel",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void listarImoveisDisponiveis() {
        List<Imovel> disponiveis = controller.listarImoveisDisponiveis();
        if (disponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum imóvel disponível.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== IMÓVEIS DISPONÍVEIS ===\n");
        for (Imovel imovel : disponiveis) {
            sb.append(imovel).append("\n");
            sb.append("   ").append(imovel.contatoProprietario()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Imóveis Disponíveis", JOptionPane.PLAIN_MESSAGE);
    }

    private void listarImoveisAlugados() {
        List<Imovel> alugados = controller.listarImoveisAlugados();
        if (alugados.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum imóvel alugado no momento.");
            return;
        }

        StringBuilder sb = new StringBuilder("=== IMÓVEIS ALUGADOS ===\n");
        for (Imovel imovel : alugados) {
            sb.append(imovel).append("\n");
            sb.append("   Inquilino: ").append(imovel.getInquilino()).append("\n");
            sb.append("   ").append(imovel.contatoProprietario()).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), "Imóveis Alugados", JOptionPane.PLAIN_MESSAGE);
    }

}
