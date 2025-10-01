package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Janela extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CardLayout cardLayout;
	
	public Janela() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 400, 600);
		setDefaultLookAndFeelDecorated(true);
		setIconImage(new ImageIcon("recursos/moon.png").getImage());
		
		this.cardLayout = new CardLayout();
		this.contentPane = new JPanel(this.cardLayout);
		this.contentPane.setSize(new Dimension(400,300));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setBackground(new Color(0,0,0));
		setContentPane(this.contentPane);
	}
	
	public void adicionarTela(String nome, JPanel tela) {
		setTitle(nome);
		this.contentPane.add(nome,tela);
	}
	
	public void mostrarTela(String nome) {
		this.cardLayout.show(this.contentPane, nome);
		this.pack();
	}

}
