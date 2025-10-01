package controller;

import model.Usuario;
import model.UsuarioDAO;
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
			if(cpf != null || senha != null || !cpf.isBlank() || !senha.isBlank()) {
			if(user != null) {
				this.view.exibirMensagem("LOGIN!", "Login Aprovado", 1);
				this.navegador.navegarPara("HOMEPAGE");
			} else {
				this.view.exibirMensagem("ERROR", "Usuario não encontrado!", 0);
			}
		} else {
			this.view.exibirMensagem("ERROR", "PREENCHA TODOS OS DADOS", 0);
		}});
		
		this.view.cadastrar(e -> {
			this.navegador.navegarPara("CADASTRO");
		});
	}
	
	
}
