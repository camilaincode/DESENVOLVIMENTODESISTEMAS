package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Produto;

public class TelaCarinho extends JPanel {
	 private static final long serialVersionUID = 1L;
	 private JTable tabelaCarrinho;
	    private DefaultTableModel modeloTabela;

	    private JLabel lblNome;
	    private JLabel lblDescricao;
	    private JLabel lblPreco;
	    private JLabel lblQuantidade;

	    private JButton btnRemover, btnComprar, btnVoltar;

	    public TelaCarinho() {
	        setPreferredSize(new java.awt.Dimension(400, 300));
	        setLayout(null);
	        setBackground(new Color(0, 0, 0));


	        modeloTabela = new DefaultTableModel(new Object[]{"Nome", "Preço", "Quantidade"}, 0);
	        tabelaCarrinho = new JTable(modeloTabela);
	        tabelaCarrinho.setRowHeight(25);
	        tabelaCarrinho.setDefaultEditor(Object.class, null);

	        JScrollPane scroll = new JScrollPane(tabelaCarrinho);
	        scroll.setBounds(35, 10, 180, 180);
	        add(scroll);


	        lblNome = criarLabel("Nome: ", 230, 20);
	        lblDescricao = criarLabel("Descrição: ", 230, 50);
	        lblPreco = criarLabel("Preço: ", 230, 80);
	        lblQuantidade = criarLabel("Quantidade: ", 230, 110);

	        add(lblNome);
	        add(lblDescricao);
	        add(lblPreco);
	        add(lblQuantidade);


	        btnRemover = new JButton("Remover");
	        btnRemover.setBounds(230, 150, 160, 30);
	        btnRemover.setBackground(new Color(72, 61, 139));
	        btnRemover.setForeground(Color.WHITE);
	        add(btnRemover);

	        btnComprar = new JButton("Comprar Tudo");
	        btnComprar.setBounds(230, 190, 160, 30);
	        btnComprar.setBackground(new Color(0, 128, 0));
	        btnComprar.setForeground(Color.WHITE);
	        add(btnComprar);


	        tabelaCarrinho.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (e.getClickCount() == 2 && tabelaCarrinho.getSelectedRow() != -1) {
	                    int linha = tabelaCarrinho.getSelectedRow();
	                    mostrarDetalhes(linha);
	                }
	            }
	        });
	        
	        ImageIcon voltar = new ImageIcon("recursos/morcego.png");
			this.btnVoltar = new JButton(voltar);
			this.btnVoltar.setBackground(null);
			this.btnVoltar.setBorder(null);
			this.btnVoltar.setBounds(0, 0, 25, 25);
			add(this.btnVoltar);

	    }

	    private JLabel criarLabel(String texto, int x, int y) {
	        JLabel label = new JLabel(texto);
	        label.setBounds(x, y, 180, 25);
	        label.setForeground(Color.WHITE);
	        return label;
	    }

	    public void setCarinho(List<Produto> produtos) {
	    	this.modeloTabela.setRowCount(0);

		    for (Produto p : produtos) {
		        this.modeloTabela.addRow(new Object[] {
		            p.getNome(),
		            p.getDescricao(),
		            p.getPreco(),
		            p.getQuantidade()
		        });
		    }
	    }
	    
	    private void mostrarDetalhes(int linha) {
	        String nome = modeloTabela.getValueAt(linha, 0).toString();
	        String preco = modeloTabela.getValueAt(linha, 1).toString();
	        String qtd = modeloTabela.getValueAt(linha, 2).toString();

	        lblNome.setText(nome);
	        lblDescricao.setText("Descrição: Produto exemplo");
	        lblPreco.setText("Preço: R$ " + preco);
	        lblQuantidade.setText("Quantidade: " + qtd);
	    }

	    public void removerProduto(ActionListener actionListener) {
	       this.btnRemover.addActionListener(actionListener);
	    }

	    public void comprarTudo(ActionListener actionListener) {
	        this.btnComprar.addActionListener(actionListener);
	    }
	    
	    public void voltar(ActionListener actionListener) {
			this.btnVoltar.addActionListener(actionListener);
		}
	    
	    public String getNome() {
	    	return lblNome.getText();
	    }

	    private void limparDetalhes() {
	        lblNome.setText("Nome: ");
	        lblDescricao.setText("Descrição: ");
	        lblPreco.setText("Preço: ");
	        lblQuantidade.setText("Quantidade: ");
	    }
	    
	    public void adicionarOuvinte(ComponentListener listener) {
			this.addComponentListener(listener);
		}
	    
	    public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
			JOptionPane.showMessageDialog(
					null,
					mensagem,
					titulo,
					tipoMensagem
					);
		}
}
