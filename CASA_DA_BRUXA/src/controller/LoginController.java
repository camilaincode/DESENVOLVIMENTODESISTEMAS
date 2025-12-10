package controller;

import model.ProdutoDAO;
import model.Sessao;
import model.Usuario;
import model.UsuarioDAO;
import view.TelaEstoque;
import view.TelaInicial;

public class LoginController {
	private final TelaInicial view;
	private final UsuarioDAO model;
	private final Navegador navegador;

	public LoginController(TelaInicial view, UsuarioDAO model, Navegador navegador) {
		super();
		this.view = view;
		this.model = model;
		this.navegador = navegador;

		this.view.logar(e -> {
			String cpf = view.getCPF();
			String senha = view.getSenha();
			Usuario user = this.model.pesquisarUsuario(cpf);
			if (cpf != null || senha != null || !cpf.isBlank() || !senha.isBlank()) {
				if (user != null) {
					Sessao.setUsuarioLogado(user);
					this.view.exibirMensagem("LOGIN!", "Login Aprovado", 1);
					TelaEstoque telaEstoque = new TelaEstoque();
					EstoqueController estoqueController = new EstoqueController(telaEstoque, new ProdutoDAO(),
							navegador);
					telaEstoque.adicionarOuvinte(estoqueController);
					telaEstoque.configurarTela();
					navegador.adicionarPainel("PRODUTOS", telaEstoque);
					navegador.navegarPara("PRODUTOS");
				} else {
					this.view.exibirMensagem("ERROR", "Usuario não encontrado!", 0);
				}
			} else {
				this.view.exibirMensagem("ERROR", "PREENCHA TODOS OS DADOS", 0);
			}
		});

		this.view.cadastrar(e -> {
			this.navegador.navegarPara("CADASTRO");
		});
	}

}
