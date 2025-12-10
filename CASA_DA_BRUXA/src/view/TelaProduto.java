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

import model.Produto;

public class TelaProduto extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel lbl_nome, lbl_preco, lbl_quantidade, lbl_detalhes,lbl_imagem;
	JButton btn_voltar, btn_excluir, btn_comprar;
	ImageIcon imagemProduto;

	public TelaProduto(Produto produto) {
		setPreferredSize(new Dimension(200, 100));
		setLayout(null);
		setBackground(new Color(0, 0, 0));

		setLayout(null);
		setPreferredSize(new Dimension(400, 300));
		setBackground(new Color(72, 61, 139));

		this.lbl_nome = new JLabel(produto.getNome());
		this.lbl_nome.setBounds(225, 40, 300, 25);
		this.lbl_nome.setForeground(new Color(0, 0, 0));
		add(this.lbl_nome);

		this.lbl_detalhes = new JLabel("Descrição: " + produto.getDescricao());
		this.lbl_detalhes.setBounds(225, 60, 300, 25);
		this.lbl_detalhes.setForeground(new Color(0, 0, 0));
		add(this.lbl_detalhes);

		this.lbl_preco = new JLabel("Preço: R$ " + produto.getPreco());
		this.lbl_preco.setForeground(new Color(0, 0, 0));
		this.lbl_preco.setBounds(225, 80, 300, 25);
		add(this.lbl_preco);

		this.lbl_quantidade = new JLabel("Quantidade: " + produto.getQuantidade());
		this.lbl_quantidade.setBounds(225, 100, 300, 25);
		this.lbl_quantidade.setForeground(new Color(0, 0, 0));
		add(this.lbl_quantidade);

		this.btn_excluir = new JButton("Excluir");
		this.btn_excluir.setBackground(new Color(72, 61, 139));
		this.btn_excluir.setForeground(new Color(0, 0, 0));
		this.btn_excluir.setBounds(225, 240, 160, 30);

		this.btn_comprar = new JButton("Comprar");
		this.btn_comprar.setBackground(new Color(72, 61, 139));
		this.btn_comprar.setForeground(new Color(0, 0, 0));
		this.btn_comprar.setBounds(225, 200, 160, 30);
		
		ImageIcon voltar = new ImageIcon("recursos/morcego.png");
		this.btn_voltar = new JButton(voltar);
		this.btn_voltar.setBackground(null);
		this.btn_voltar.setBorder(null);
		this.btn_voltar.setBounds(0, 0, 25, 25);
		add(this.btn_voltar);
		
		this.imagemProduto = new ImageIcon("recursos/produto.png");
		this.lbl_imagem = new JLabel(imagemProduto);
		this.lbl_imagem.setBounds(16, 45, 200, 200);
		add(this.lbl_imagem);
	}
	
	public void configurarTela() {
		if(model.Sessao.isBruxa()) {
			add(this.btn_excluir);
		} else {
			add(this.btn_comprar);
		}
	}
	
	public String getNome() {
		return lbl_nome.getText();
	}
	
	public void voltar(ActionListener actionListener) {
		this.btn_voltar.addActionListener(actionListener);
	}
	
	public void comprar(ActionListener actionListener) {
		this.btn_comprar.addActionListener(actionListener);
	}
	
	public void excluir(ActionListener actionListener) {
		this.btn_excluir.addActionListener(actionListener);
	}
	
	public void exibirMensagem(String titulo, String mensagem, int tipoMensagem) {
		JOptionPane.showMessageDialog(
				null,
				mensagem,
				titulo,
				tipoMensagem
				);
	}
	
	public void adicionarOuvinte(ComponentListener listener) {
		this.addComponentListener(listener);
	}

}
