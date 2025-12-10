package controller;

import java.awt.event.ComponentAdapter;

import javax.swing.JOptionPane;

import model.Carinho;
import model.CarinhoDAO;
import model.Produto;
import model.ProdutoDAO;
import model.Sessao;
import view.TelaProduto;

public class ProdutoController extends ComponentAdapter {
	private final TelaProduto view;
	private final ProdutoDAO model;
	private final Navegador navegador;
	
	public ProdutoController(TelaProduto view, ProdutoDAO model, Navegador navegador) {
		super();
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		this.view.voltar(e -> {
			navegador.navegarPara("PRODUTOS");
		});
		
		this.view.excluir(e -> {
			this.model.deletarProduto(view.getNome());
			navegador.navegarPara("PRODUTOS");
		});
		
		this.view.comprar(e -> {
			try{
		int quantidade= Integer.parseInt(JOptionPane.showInputDialog("informe a quantidade desejada"));
		Carinho carinho = new Carinho(Sessao.getUsuarioLogado().getId(),this.view.getNome(),quantidade);
		CarinhoDAO cDAO = new CarinhoDAO();
		cDAO.adicionarAoCarinho(carinho);
			}catch (Exception ex) {
				this.view.exibirMensagem("ERROR", "ERROR EM ADICIONAR COMPRA!: " + ex.getMessage(), 0);
			}
		navegador.navegarPara("PRODUTOS");
		});
	
	}
}
