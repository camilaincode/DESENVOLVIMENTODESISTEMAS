package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import model.Produto;
import model.ProdutoDAO;
import model.Sessao;
import view.TelaCarinho;
import view.TelaEstoque;
import view.TelaProduto;

public class EstoqueController extends ComponentAdapter {
	private final TelaEstoque view;
	private final ProdutoDAO model;
	private final Navegador navegador;

	public EstoqueController(TelaEstoque view, ProdutoDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		this.view.carinho(e -> {
			navegador.navegarPara("CARINHO");
		});

		this.view.adicionar(e -> {
			this.navegador.navegarPara("ADICIONAR PRODUTO");
		});

		this.view.getTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && view.getTable().getSelectedRow() != -1) {
					int linha = view.getTable().getSelectedRow();

					String nome = view.jtable_model.getValueAt(linha, 0).toString();
					String descricao = view.jtable_model.getValueAt(linha, 1).toString();
					double preco = Double.parseDouble(view.jtable_model.getValueAt(linha, 2).toString());
					int quantidade = Integer.parseInt(view.jtable_model.getValueAt(linha, 3).toString());

					Produto produto = new Produto(nome, descricao, preco, quantidade);

					TelaProduto telaProduto = new TelaProduto(produto);
					telaProduto.configurarTela();
					ProdutoController produtoController = new ProdutoController(telaProduto, model, navegador);
					telaProduto.adicionarOuvinte(produtoController);
					navegador.adicionarPainel("DETALHE PRODUTO", telaProduto);
					navegador.navegarPara("DETALHE PRODUTO");
				}
			}
		});

	}

	@Override
	public void componentShown(ComponentEvent e) {
		this.inicializarTabela();
	}

	private void inicializarTabela() {
		try {
		List<Produto> produtos = this.model.listarProdutos();
		this.view.setTabelaProdutos(produtos);
		}catch(Exception ex) {
			this.view.exibirMensagem("ERROR", "ERRO EM RECUPERAR DADOS DO ESTOQUE!", 0);
		}
	}

}
