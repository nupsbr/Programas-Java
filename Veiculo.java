
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Veiculo extends JFrame implements ActionListener {
    private ArrayList<VeiculoInfo> veiculos; // ArrayList para armazenar os veículos

    private JButton btCadastrar, btPesquisar, btLimpar, btSair;
    private JLabel lbplaca, lbfabricante, lbmodelo, lbdatacompra, lbano, lbvalor;
    private JTextField txplaca, txtModelo, txdatacompra, txano, txvalor;
    private JComboBox<String> combo;

    public Veiculo() {
        super("Controle de Frota - Cadastro de Veículos");
        setSize(600, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        veiculos = new ArrayList<>(); // Inicializa o ArrayList de veículos

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 4, 5, 5));

        JPanel painelBotao = new JPanel();
        painelBotao.setLayout(new GridLayout(1, 4, 2, 2));

        btCadastrar = new JButton("Cadastrar");
        btPesquisar = new JButton("Pesquisar");
        btLimpar = new JButton("Limpar");
        btSair = new JButton("Sair");

        lbplaca = new JLabel("Placa ");
        lbfabricante = new JLabel("Fabricante ");
        lbmodelo = new JLabel("Modelo ");
        lbdatacompra = new JLabel("Data Compra ");
        lbano = new JLabel("Ano");
        lbvalor = new JLabel("Valor (R$)");

        combo = new JComboBox<>(new String[]{"Outras Opções","Toyota", "Ford", "Volkswagen", "Honda", "Chevrolet","Nissan","BMW","Mercedes-Benz","Audi"});

        txplaca = new JTextField(20);
        txano = new JTextField(20);
        txtModelo = new JTextField(20);
        txdatacompra = new JTextField(20);
        txvalor = new JTextField(20);

        inputPanel.add(lbplaca);
        inputPanel.add(txplaca);
        inputPanel.add(lbfabricante);
        inputPanel.add(combo);
        inputPanel.add(lbmodelo);
        inputPanel.add(txtModelo);
        inputPanel.add(lbano);
        inputPanel.add(txano);
        inputPanel.add(lbdatacompra);
        inputPanel.add(txdatacompra);
        inputPanel.add(lbvalor);
        inputPanel.add(txvalor);

        painelBotao.add(btCadastrar);
        painelBotao.add(btPesquisar);
        painelBotao.add(btLimpar);
        painelBotao.add(btSair);

        add(inputPanel, BorderLayout.NORTH);
        add(painelBotao, BorderLayout.SOUTH);

        setVisible(true);

        btCadastrar.addActionListener(this);
        btPesquisar.addActionListener(this);
        btLimpar.addActionListener(this);
        btSair.addActionListener(this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Veiculo());
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btCadastrar) {
            cadastrar();
        } else if (evento.getSource() == btPesquisar) {
            pesquisar();
        } else if (evento.getSource() == btLimpar) {
            limpar();
        } else if (evento.getSource() == btSair) {
            dispose();
        }
    }

    private void cadastrar() {
        // Recuperando os valores dos campos
        String placa = txplaca.getText();
        String fabricante = (String) combo.getSelectedItem();
        String modelo = txtModelo.getText();
        int anoFabricacao = Integer.parseInt(txano.getText());
        float valor = Float.parseFloat(txvalor.getText());
        String dataCompra = txdatacompra.getText();

        // Criando um novo objeto VeiculoInfo
        VeiculoInfo veiculo = new VeiculoInfo(placa, fabricante, modelo, anoFabricacao, valor, dataCompra);

        // Adicionando o veículo à lista de veículos
        veiculos.add(veiculo);

        // Limpa os campos de texto após cadastrar
        limpar();

        // Mensagem de confirmação
        JOptionPane.showMessageDialog(this, "Veículo cadastrado com sucesso!");
    }


    private void pesquisar() {
        // Obtém a placa para pesquisar
        String placaPesquisa = JOptionPane.showInputDialog(this, "Digite a placa do veículo:");
        if (placaPesquisa != null && !placaPesquisa.isEmpty()) {
            boolean encontrado = false;
            for (VeiculoInfo veiculo : veiculos) {
                if (veiculo.getPlaca().equals(placaPesquisa)) {
                    JOptionPane.showMessageDialog(this, "Veículo encontrado:\n" + veiculo);
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(this, "Veículo não encontrado.");
            }
        }
    }

    private void limpar() {
        // Limpa os campos de texto
        txplaca.setText("");
        txtModelo.setText("");
        txano.setText("");
        txdatacompra.setText("");
        txvalor.setText("");
    }

    public void dispose() {
        super.dispose();
    }
}

class VeiculoInfo {
    private String placa;
    private String fabricante;
    private String modelo;
    private int anoFabricacao;
    private float valor;
    private String dataCompra;

    public VeiculoInfo(String placa, String fabricante, String modelo, int anoFabricacao, float valor, String dataCompra) {
        this.placa = placa;
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.valor = valor;
        this.dataCompra = dataCompra;
    }

    public String getPlaca() {
        return placa;
    }

    @Override
    public String toString() {
        return "Placa: " + placa + "\nFabricante: " + fabricante + "\nModelo: " + modelo + "\nAno de Fabricação: " + anoFabricacao + "\nData de Compra: " + dataCompra;
    }
    }