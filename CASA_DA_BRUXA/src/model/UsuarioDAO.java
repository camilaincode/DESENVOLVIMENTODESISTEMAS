package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conn.connection;

public class UsuarioDAO {

	public void adicionarUsuario(Usuario usuario) {
		String sql = "insert into usuarios (nome,sobrenome,cpf,tipo,senha) values (?,?,?,?,?)";
		Connection conexao = null;
		PreparedStatement pstm = null;

		try {
			conexao = connection.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getSobrenome());
			pstm.setString(3, usuario.getCpf());
			pstm.setString(4, usuario.getTipo());
			pstm.setString(5, usuario.getSenha());
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
	
	public void atualizarUsuario(Usuario usuario) {
		String sql = "update usuario set nome=?, sobrenome =?, cpf =?, tipo = ?, senha = ? where id=?";
		Connection conexao = null;
		PreparedStatement pstm = null;
		try {
			conexao = connection.conectar();
			pstm = conexao.prepareStatement(sql);
			pstm.setString(1, usuario.getNome());
			pstm.setString(2, usuario.getSobrenome());
			pstm.setString(3, usuario.getCpf());
			pstm.setString(4, usuario.getTipo());
			pstm.setString(5, usuario.getSenha());
			pstm.setString(6, usuario.getId());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.desconectar(conexao);
		}
	}
	
	public void excluirUsuario(String id) {
		 String sql = "DELETE FROM usuarios WHERE id = ?";
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
	
	public List<Usuario> listarUsuarios(){
		String sql = "select * from usuarios";
		List<Usuario> lista = new ArrayList<>();
		Connection conexao = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			conexao = connection.conectar();
			pstm = conexao.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getString("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setSobrenome(rs.getString("sobrenome"));
				usuario.setCpf(rs.getString("cpf"));
				usuario.setTipo(rs.getString("tipo"));
				usuario.setSenha(rs.getString("senha"));
				lista.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.desconectar(conexao);
		}
		return lista;
	}
	
	public Usuario pesquisarUsuario(String cpf) {
        String sql = "SELECT * FROM usuarios WHERE cpf = ?";
        try (Connection conexao = connection.conectar();
             PreparedStatement pstm = conexao.prepareStatement(sql)) {
            pstm.setString(1, cpf);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getString("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setSobrenome(rs.getString("sobrenome"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setTipo(rs.getString("tipo"));
                    usuario.setSenha(rs.getString("senha"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
	
	
}