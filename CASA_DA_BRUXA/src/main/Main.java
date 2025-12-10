package main;

import controller.AdicionarProdutoController;
import controller.CadastroController;
import controller.CarinhoController;
import controller.EstoqueController;
import controller.LoginController;
import controller.Navegador;
import model.CarinhoDAO;
import model.ProdutoDAO;
import model.Sessao;
import model.UsuarioDAO;
import view.Janela;
import view.TelaAdicionarProduto;
import view.TelaCadastro;
import view.TelaCarinho;
import view.TelaEstoque;
import view.TelaInicial;

public class Main {

	public static void main(String[] args) {
		Janela janela = new Janela();
		Navegador navegador = new Navegador(janela);
		UsuarioDAO dao = new UsuarioDAO();
		ProdutoDAO pDao = new ProdutoDAO();
		CarinhoDAO cDao = new CarinhoDAO();

		TelaInicial telaInicial = new TelaInicial();
		LoginController loginController = new LoginController(telaInicial, dao, navegador);

		TelaCadastro telaCadastro = new TelaCadastro();
		CadastroController cadastroController = new CadastroController(telaCadastro, dao, navegador);
		telaCadastro.adicionarOuvinte(cadastroController);
		
		
		TelaEstoque telaEstoque = new TelaEstoque();
		EstoqueController estoqueController = new EstoqueController(telaEstoque, pDao, navegador);
		telaEstoque.adicionarOuvinte(estoqueController);

		TelaAdicionarProduto telaAdicionarProduto = new TelaAdicionarProduto();
		AdicionarProdutoController adicionarProdutoController = new AdicionarProdutoController(telaAdicionarProduto,
				pDao, navegador);
		telaAdicionarProduto.adicionarOuvinte(adicionarProdutoController);
		
		TelaCarinho telaCarinho = new TelaCarinho();
		CarinhoController carinhoController = new CarinhoController(telaCarinho, cDao, navegador);
		telaCarinho.adicionarOuvinte(carinhoController);
		
		navegador.adicionarPainel("CARINHO", telaCarinho);
		navegador.adicionarPainel("TELA LOGIN", telaInicial);
		navegador.adicionarPainel("CADASTRO", telaCadastro);
		navegador.adicionarPainel("ADICIONAR PRODUTO", telaAdicionarProduto);

		janela.setLocationRelativeTo(null);
		janela.setVisible(true);

		navegador.navegarPara("TELA LOGIN");

	}
}