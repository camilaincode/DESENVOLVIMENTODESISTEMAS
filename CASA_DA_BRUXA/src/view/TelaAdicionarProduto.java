package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TelaAdicionarProduto extends JPanel {
	private static final long serialVersionUID = 1L;
	JTextField tf_nome, tf_descricao,tf_preco,tf_quantidade;
	JLabel lb_nome, lb_descricao, lb_preco, lb_quantidade;
	JButton btn_adicionar;

	public TelaAdicionarProduto() {
		setPreferredSize(new Dimension(400,300));
		setLayout(null);
		setBackground(new Color(0,0,0));
		
		this.lb_nome = new JLabel("Nome:");
		this.lb_nome.setBounds(150,0,200,25);
		this.lb_nome.setForeground(new Color(255,255,255));
		add(this.lb_nome);
		
		this.tf_nome = new JTextField();
		this.tf_nome.setBounds(150,20,200,25);
		add(tf_nome);
		
		this.lb_descricao = new JLabel("Descrição:");
		this.lb_descricao.setBounds(150,40,200,25);
		this.lb_descricao.setForeground(new Color(255,255,255));
		add(this.lb_descricao);
		
		this.tf_descricao = new JTextField();
		this.tf_descricao.setBounds(150,60,200,25);
		add(this.tf_descricao);
		
		this.lb_preco = new JLabel("Preço:");
		this.lb_preco.setBounds(150,80,200,25);
		this.lb_preco.setForeground(new Color(255,255,255));
		add(this.lb_preco);
		
		this.tf_preco = new JTextField();
		this.tf_preco.setBounds(150,100,200,25);
		add(this.tf_preco);
		
		this.lb_quantidade = new JLabel("Quantidade:");
		this.lb_quantidade.setBounds(150,120,200,25);
		this.lb_quantidade.setForeground(new Color(255,255,255));
		add(lb_quantidade);
		
		this.tf_quantidade = new JTextField();
		this.tf_quantidade.setBounds(150, 140, 200, 25);
		add(this.tf_quantidade);
		
		this.btn_adicionar = new JButton("Adicionar");
		this.btn_adicionar.setBackground(new Color(72,61,139));
		this.btn_adicionar.setForeground(new Color(255,255,255));
		this.btn_adicionar.setBounds(150,180,200,25);
		add(this.btn_adicionar);
		
		ImageIcon imagem = new ImageIcon("recursos/fundo.png");
		JLabel label = new JLabel(imagem);
		label.setBounds(0, 0, 400, 300);
		add(label);
	}
	
	public String getNome() {
		return this.tf_nome.getText();
	}
	
	public String getDescricao() {
		return this.tf_descricao.getText();
	}
	
	public Double getPreco() {
		Double preco = Double.parseDouble(this.tf_preco.getText());
		return preco;
	}
	
	public int getQuantidade() {
		int quantidade = Integer.parseInt(this.tf_quantidade.getText());
		return quantidade;
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
