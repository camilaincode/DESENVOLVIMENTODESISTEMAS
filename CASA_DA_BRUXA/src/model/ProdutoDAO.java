package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conn.connection;

public class ProdutoDAO {
	public void adicionarProduto(Produto produto) {
		String sql = "insert into produtos(nome,descricao,preco,quantidade) values (?,?,?,?)";
		Connection conexao = null;
		PreparedStatement pstm = null;
		try {
			conexao = connection.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			pstm.setDouble(3, produto.getPreco());
			pstm.setInt(4, produto.getQuantidade());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.desconectar(conexao);
			if (pstm != null) {
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
	
	public void atualizarProduto(Produto produto) {
		String sql = "update produtos set nome = ?, descricao = ?, preco = ?, quantidade =? where id = ?";
		Connection conexao = null;
		PreparedStatement pstm = null;
		try {
			conexao = connection.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			pstm.setDouble(3, produto.getPreco());
			pstm.setInt(4, produto.getQuantidade());
			pstm.setInt(5, produto.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.desconectar(conexao);
		}
	}
	
	public void alterarQuantidade(int quantidade, String nome) {
		String sql = "update produtos set quantidade = ? where nome = ?";
		Connection conexao = null;
		PreparedStatement pstm = null;
		try {
			conexao = connection.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setInt(1, quantidade);
			pstm.setString(2, nome);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.desconectar(conexao);
		}
	}
	
	public void deletarProduto(String nome) {
		String sql = "delete from produtos where nome = ?";
		Connection conexao = null;
		PreparedStatement pstm = null;
		 try {
	    	 conexao = connection.conectar();
	    	 pstm = conexao.prepareStatement(sql);
	    	 pstm.setString(1, nome);
	    	 pstm.execute();
	     } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.desconectar(conexao);
		}
	}
	
	public List<Produto> listarProdutos(){
		List<Produto> lista = new ArrayList();
		String sql = "select * from produtos";
		Connection conexao = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conexao = connection.conectar();
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQuantidade(rs.getInt("quantidade"));
				lista.add(produto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.desconectar(conexao);
		}
		return lista;
	}
	
	public Produto pesquisarProduto(String nome) {
		String sql = "select * from produtos where nome = ?";
		Connection conexao = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conexao = connection.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, nome);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setQuantidade(rs.getInt("quantidade"));
				return produto;
			}
		} catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
	}
	
}