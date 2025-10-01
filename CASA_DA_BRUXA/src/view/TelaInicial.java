package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaInicial extends JPanel {
	private static final long serialVersionUID = 1L;
	JLabel lbCPF, lbSenha;
	JButton btnentrar, btncadastro;
	JTextField tfcpf, tfsenha;

	public TelaInicial() {
		setPreferredSize(new Dimension(400,300));
		setLayout(null);
		setBackground(new Color(0,0,0));
		
		this.btnentrar = new JButton("Entrar");
		this.btnentrar.setBackground(new Color(72,61,139));
		this.btnentrar.setForeground(new Color(255,255,255));
		this.btnentrar.setBounds(270, 250, 70, 25);
		add(this.btnentrar);
		
		this.btncadastro= new JButton("Cadastrar");
		this.btncadastro.setBackground(new Color(72,61,139));
		this.btncadastro.setForeground(new Color(255,255,255));
		this.btncadastro.setBounds(150, 250, 100, 25);
		add(this.btncadastro);
		
		this.lbCPF = new JLabel("CPF:");
		this.lbCPF.setBounds(150,80,200,25);
		this.lbCPF.setForeground(new Color(255,255,255));
		add(lbCPF);
		
		this.tfcpf = new JTextField();
		this.tfcpf.setBounds(150, 100, 200, 25);
		add(this.tfcpf);
		
		this.lbSenha = new JLabel("Senha:");
		this.lbSenha.setBounds(150,120,200,25);
		this.lbSenha.setForeground(new Color(255,255,255));
		add(lbSenha);
		
		this.tfsenha= new JTextField();
		this.tfsenha.setBounds(150, 140, 200, 25);
		add(this.tfsenha);
		
		ImageIcon imagem = new ImageIcon("recursos/fundo.png");
		JLabel label = new JLabel(imagem);
		label.setBounds(0, 0, 400, 300);
		add(label);
	}
	
	public String getCPF() {
		return this.tfcpf.getText();
	}
	
	public String getSenha() {
		return this.tfsenha.getText();
	}
	
	public void logar (ActionListener actionListener) {
		this.btnentrar.addActionListener(actionListener);
	}
	
	public void cadastrar(ActionListener actionListener) {
		this.btncadastro.addActionListener(actionListener);
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
