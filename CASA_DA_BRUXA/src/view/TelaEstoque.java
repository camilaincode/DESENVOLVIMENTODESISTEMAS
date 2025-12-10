package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Produto;
import model.Sessao;

public class TelaEstoque extends JPanel {
	private static final long serialVersionUID = 1L;
	JButton btn_adicionar, btn_carinho;
	JTable tb_produtos;
	public DefaultTableModel jtable_model;

	public TelaEstoque() {
		setPreferredSize(new Dimension(400, 300));
		setLayout(null);
		setBackground(new Color(0, 0, 0));

		this.tb_produtos = new JTable();
		this.tb_produtos.setDefaultEditor(Object.class, null);
		this.tb_produtos.setRowHeight(25);
		this.tb_produtos.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Descrição", "Preço", "Quantidade" }));
		
		this.jtable_model = (DefaultTableModel) this.tb_produtos.getModel();
		
		
		JScrollPane scrollPaneTabela = new JScrollPane(this.tb_produtos);
		scrollPaneTabela.setBounds(0,0,400,200);
		add(scrollPaneTabela);
		
		this.btn_carinho = new JButton("Carinho");
		this.btn_carinho.setBackground(new Color(72, 61, 139));
		this.btn_carinho.setForeground(new Color(255, 255, 255));
		this.btn_carinho.setBounds(130, 210, 160, 30);
		

		this.btn_adicionar = new JButton("Adicionar produto");
		this.btn_adicionar.setBackground(new Color(72, 61, 139));
		this.btn_adicionar.setForeground(new Color(255, 255, 255));
		this.btn_adicionar.setBounds(130, 250, 160, 30);
		
	}
	
	public void configurarTela() {
        if (model.Sessao.isBruxa()) {
            add(btn_adicionar);
        } else {
        	add(btn_carinho);
        }
    }
	
	public void setTabelaProdutos(List<Produto> produtos) {
		this.jtable_model.setRowCount(0);

	    for (Produto p : produtos) {
	        this.jtable_model.addRow(new Object[] {
	            p.getNome(),
	            p.getDescricao(),
	            p.getPreco(),
	            p.getQuantidade()
	        });
	    }
	}
	
	public JTable getTable() {
		return this.tb_produtos;
	}
	
	public void tipoBruxa() {
		add(btn_adicionar);
	}
	
	public void tipoCliente() {
		add(btn_carinho);
	}
	
	public void carinho(ActionListener actionListener) {
		this.btn_carinho.addActionListener(actionListener);
	}

	public void adicionar(ActionListener actionListener) {
		this.btn_adicionar.addActionListener(actionListener);
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
