package controller;

import java.awt.event.ComponentAdapter;

import model.Produto;
import model.ProdutoDAO;
import view.TelaAdicionarProduto;

public class AdicionarProdutoController extends ComponentAdapter {
	private final TelaAdicionarProduto view;
	private final ProdutoDAO model;
	private final Navegador navegador;

	public AdicionarProdutoController(TelaAdicionarProduto view, ProdutoDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;

		this.view.adicionar(e -> {
			String nome = this.view.getNome();
			String descricao = this.view.getDescricao();
			double preco = this.view.getPreco();
			int quantidade = this.view.getQuantidade();

			if (nome != null && nome.isBlank() && nome.isEmpty() && descricao != null && descricao.isBlank()
					&& descricao.isEmpty() && preco < 0 && quantidade < 0) {
				try {
				Produto produto = new Produto(nome, descricao, preco, quantidade);
				this.model.adicionarProduto(produto);
				if (this.model.pesquisarProduto(nome) != null) {
					this.view.exibirMensagem("CADASTRO!", "CADASTRO REALIZADO COM SUCESSO", 1);
					this.navegador.navegarPara("PRODUTOS");
				} else {
					this.view.exibirMensagem("ERROR!", "ACONTECEU ALGUM ERRO COM O CADASTRO", 0);
				}
				}catch (Exception ex) {
					this.view.exibirMensagem("ERROR", "ERROR EM CADASTRAR PRODUTO!: " + ex.getMessage(), 0);
				}
			} else {
				this.view.exibirMensagem("ERROR", "Preencher todas as informações!", 0);
			}

		});

	}

}
