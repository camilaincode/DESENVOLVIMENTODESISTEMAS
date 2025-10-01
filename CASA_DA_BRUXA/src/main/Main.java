package main;

import controller.CadastroController;
import controller.LoginController;
import controller.Navegador;
import model.UsuarioDAO;
import view.Janela;
import view.TelaCadastro;
import view.TelaHome;
import view.TelaInicial;

public class Main {

	public static void main(String[] args) {
		Janela janela = new Janela();
		Navegador navegador = new Navegador(janela);
		UsuarioDAO dao = new UsuarioDAO();
		
		TelaInicial telaInicial = new TelaInicial();
		LoginController loginController = new LoginController(telaInicial, dao, navegador);
		
		TelaCadastro telaCadastro = new TelaCadastro();
		CadastroController cadastroController = new CadastroController(telaCadastro, dao, navegador);
		telaCadastro.adicionarOuvinte(cadastroController);
		
		TelaHome telaHome = new TelaHome();
		
		navegador.adicionarPainel("TELA LOGIN", telaInicial);
		navegador.adicionarPainel("CADASTRO", telaCadastro);
		navegador.adicionarPainel("HOMEPAGE", telaHome);
		
		janela.setLocationRelativeTo(null);
		janela.setVisible(true); 

		navegador.navegarPara("TELA LOGIN");

	}
}