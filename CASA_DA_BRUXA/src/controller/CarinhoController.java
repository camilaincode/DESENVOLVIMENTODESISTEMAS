package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Carinho;
import model.CarinhoDAO;
import model.Produto;
import model.ProdutoDAO;
import model.Sessao;
import view.TelaCarinho;

public class CarinhoController extends ComponentAdapter {
	private final TelaCarinho view;
	private final CarinhoDAO model;
	ProdutoDAO pModel = new ProdutoDAO();
	private final Navegador navegador;
	
	public CarinhoController(TelaCarinho view, CarinhoDAO model, Navegador navegador) {
		this.view = view;
		this.model = model;
		this.navegador = navegador;
		
		this.view.voltar(e -> {
			navegador.navegarPara("PRODUTOS");
		});
		
		this.view.removerProduto(e -> {
			this.model.deletarProduto(this.view.getNome());
			inicializarTabela();
		});
		
		this.view.comprarTudo(e -> {
			comprarTudo();
		});
	}
	
	@Override
	public void componentShown(ComponentEvent e) {
		this.inicializarTabela();
	}
	
	public void comprarTudo() {
	    try {

	        List<Carinho> carinho = this.model.listarCarinho(Sessao.getUsuarioLogado().getId());
	        if (carinho.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "Seu carrinho está vazio!", "Aviso", JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        List<Produto> produtos = new ArrayList<>();
	        double total = 0.0;
	        for (Carinho c : carinho) {
	            Produto p = pModel.pesquisarProduto(c.getNome_produto());
	            produtos.add(p);
	            pModel.alterarQuantidade(p.getQuantidade()-c.getQuatidade(), p.getNome());
	            total += p.getPreco();
	        }

	        StringBuilder mensagem = new StringBuilder("=== NOTA FISCAL ===\n");
	        mensagem.append("Cliente: ").append(Sessao.getUsuarioLogado().getNome()).append("\n\n");
	        mensagem.append("Itens:\n");
	        for (Produto p : produtos) {
	            mensagem.append("- ").append(p.getNome())
	                    .append("  R$ ").append(String.format("%.2f", p.getPreco())).append("\n");
	        }
	        mensagem.append("\nTotal: R$ ").append(String.format("%.2f", total)).append("\n");
	        mensagem.append("===================\n");
	        mensagem.append("Compra finalizada com sucesso!");

	        JOptionPane.showMessageDialog(null, mensagem.toString(), "Compra Realizada", JOptionPane.INFORMATION_MESSAGE);

	        this.model.esvaziarCarinho(Sessao.getUsuarioLogado().getId());

	        inicializarTabela();

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Erro ao finalizar compra: " + ex.getMessage(),
	                "Erro", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
	public void inicializarTabela() {
		try {
		List<Produto> produtos = new ArrayList<>();
		List<Carinho> carinho = this.model.listarCarinho(Sessao.getUsuarioLogado().getId());
		for ( int i = 0; i < carinho.size(); i++) {
			produtos.add(pModel.pesquisarProduto(carinho.get(i).getNome_produto()));
		}
		this.view.setCarinho(produtos);
		}catch(Exception ex) {
			this.view.exibirMensagem("ERROR", "ERRO EM RECUPERAR DADOS DO CARINHO" + ex.getMessage(), 0);
		}
	}
}
