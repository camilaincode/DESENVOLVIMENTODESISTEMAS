package controller;

import java.util.List;
import java.awt.event.ComponentAdapter;

import model.Usuario;
import model.UsuarioDAO;
import view.TelaCadastro;

public class CadastroController extends ComponentAdapter {
	private final TelaCadastro view;
	private final UsuarioDAO model;
	private final Navegador navegador;
	public CadastroController(TelaCadastro view, UsuarioDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		try{
			List<Usuario> lista = this.model.listarUsuarios();
		}catch(Exception e) {
			this.view.exibirMensagem("ERROR", "ERRO NA LISTAGEM DE USUARIOS! " + e.getMessage(), 0);
		}
		
		this.view.cadastrar(e ->{
			String nome = this.view.getNome();
			String sobrenome = this.view.getSobrenome();
			String cpf = this.view.getCPF();
			String senha = this.view.getSenha();
			String tipo = this.view.getTipo();
			if (nome != null && !nome.isBlank() && !nome.isEmpty() && sobrenome != null && !sobrenome.isBlank() && !sobrenome.isEmpty() &&
					cpf != null && !cpf.isBlank() && !cpf.isEmpty() && senha != null && !senha.isBlank() && !senha.isEmpty() && 
					tipo != null && !tipo.isBlank() && !tipo.isEmpty()) {
				
						Usuario existente = this.model.pesquisarUsuario(cpf);
						if (existente != null) {
		                this.view.exibirMensagem("ERROR", "Esse CPF já está em uso!", 0);
		                return;
						}
						try {
						Usuario user = new Usuario(nome, sobrenome, cpf, tipo, senha);
						model.adicionarUsuario(user);
						if (this.model.pesquisarUsuario(cpf) != null) {
							this.view.exibirMensagem("CADASTRO!", "CADASTRO REALIZADO COM SUCESSO", 1);
							this.navegador.navegarPara("TELA LOGIN");
						} else {
							this.view.exibirMensagem("ERROR!", "ACONTECEU ALGUM ERRO COM O CADASTRO", 0);
						}
						}
						catch(IllegalArgumentException ex) {
							this.view.exibirMensagem("ERROR", "DADOS INVALIDOS: " + ex.getMessage(), 0);
						} catch (Exception ex) {
							this.view.exibirMensagem("ERROR", "ERROR EM CADASTRAR USUARIO!: " + ex.getMessage(), 0);
						}
				
			} else {
				this.view.exibirMensagem("ERROR", "Preencher todas as informações!", 0);
			}
			
		});
	}
	
	
}
