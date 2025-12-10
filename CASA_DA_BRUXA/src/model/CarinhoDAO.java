package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conn.connection;

public class CarinhoDAO {
	public void adicionarAoCarinho(Carinho carinho) {
		String sql = "insert into carinho(id_usuario,nome_produto,quantidade) values (?,?,?)";
		Connection conexao = null;
		PreparedStatement pstm = null;
		try {
			conexao = connection.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, carinho.getId_usuario());
			pstm.setString(2, carinho.getNome_produto());
			pstm.setInt(3, carinho.getQuatidade());
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
	
	public void deletarProduto(String nome) {
		String sql = "delete from carinho where nome_produto =?";
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
	
	public void esvaziarCarinho(String id) {
		String sql = "delete from carinho where id_usuario=?";
		Connection conexao = null;
		PreparedStatement pstm = null;
		 try {
	    	 conexao = connection.conectar();
	    	 pstm = conexao.prepareStatement(sql);
	    	 pstm.setString(1, id);
	    	 pstm.execute();
	     } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.desconectar(conexao);
		}
	}
	
	public List<Carinho> listarCarinho(String id_usuario) {
		List<Carinho> lista = new ArrayList<>();
		String sql = "select * from carinho where id_usuario = ?";
		Connection conexao = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conexao = connection.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, id_usuario);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Carinho carinho = new Carinho();
				carinho.setId(rs.getInt("id"));
				carinho.setId_usuario(Integer.toString(rs.getInt("id_usuario")));
				carinho.setNome_produto(rs.getString("nome_produto"));
				carinho.setQuatidade(rs.getInt("quantidade"));
				lista.add(carinho);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.desconectar(conexao);
		}
		return lista;
	}
	
	
}
