package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaCadastro extends JPanel {
	private static final long serialVersionUID = 1L;
	JTextField tf_nome, tf_sobrenome, tf_cpf, tf_senha;
	JLabel lb_nome, lb_sobrenome, lb_cpf, lb_senha, lb_tipo;
	JButton btn_cadastrar;
	String [] tipos = {"Bruxa","Cliente"};
	JComboBox<String> cb_tipo;
	
	public TelaCadastro() {
		setPreferredSize(new Dimension(400,300));
		setLayout(null);
		setBackground(new Color(0,0,0));
		
		this.lb_tipo = new JLabel("Tipo:");
		this.lb_tipo.setBounds(150,0,200,25);
		this.lb_tipo.setForeground(new Color(255,255,255));
		add(this.lb_tipo);
		
		this.cb_tipo = new JComboBox<String>(tipos);
		this.cb_tipo.setSelectedIndex(1);
		this.cb_tipo.setBounds(150,20,200,25);
		add(cb_tipo);
		
		this.lb_nome = new JLabel("Nome:");
		this.lb_nome.setBounds(150,40,200,25);
		this.lb_nome.setForeground(new Color(255,255,255));
		add(this.lb_nome);
		
		this.tf_nome = new JTextField();
		this.tf_nome.setBounds(150,60,200,25);
		add(this.tf_nome);
		
		this.lb_sobrenome = new JLabel("Sobrenome:");
		this.lb_sobrenome.setBounds(150,80,200,25);
		this.lb_sobrenome.setForeground(new Color(255,255,255));
		add(this.lb_sobrenome);
		
		this.tf_sobrenome = new JTextField();
		this.tf_sobrenome.setBounds(150,100,200,25);
		add(this.tf_sobrenome);
		
		this.lb_cpf = new JLabel("CPF:");
		this.lb_cpf.setBounds(150,120,200,25);
		this.lb_cpf.setForeground(new Color(255,255,255));
		add(lb_cpf);
		
		this.tf_cpf = new JTextField();
		this.tf_cpf.setBounds(150, 140, 200, 25);
		add(this.tf_cpf);
		
		this.lb_senha = new JLabel("Senha:");
		this.lb_senha.setBounds(150,160,200,25);
		this.lb_senha.setForeground(new Color(255,255,255));
		add(lb_senha);
		
		this.tf_senha= new JTextField();
		this.tf_senha.setBounds(150, 180, 200, 25);
		add(this.tf_senha);
		
		this.btn_cadastrar = new JButton("Cadastrar");
		this.btn_cadastrar.setBackground(new Color(72,61,139));
		this.btn_cadastrar.setForeground(new Color(255,255,255));
		this.btn_cadastrar.setBounds(200, 220, 120, 25);
		add(this.btn_cadastrar);
		
		ImageIcon imagem = new ImageIcon("recursos/fundo.png");
		JLabel label = new JLabel(imagem);
		label.setBounds(0, 0, 400, 300);
		add(label);
	}
	
	public String getNome() {
		return this.tf_nome.getText();
	}
	
	public String getSobrenome() {
		return this.tf_sobrenome.getText();
	}
	
	public String getSenha() {
		return this.tf_senha.getText();
	}
	
	public String getCPF() {
		return this.tf_cpf.getText();
	}
	
	public String getTipo() {
		return this.cb_tipo.getSelectedItem().toString();
	}
	
	public void cadastrar(ActionListener actionListener) {
		this.btn_cadastrar.addActionListener(actionListener);
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
