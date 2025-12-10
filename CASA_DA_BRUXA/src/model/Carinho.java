package model;

public class Carinho {
	int id;
	String id_usuario;
	String nome_produto;
	int quatidade;
	
	public Carinho() {}
	
	public Carinho(String id_usuario, String nome_produto, int quatidade) {
		this.id_usuario = id_usuario;
		this.nome_produto = nome_produto;
		this.quatidade = quatidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNome_produto() {
		return nome_produto;
	}

	public void setNome_produto(String nome_produto) {
		this.nome_produto = nome_produto;
	}

	public int getQuatidade() {
		return quatidade;
	}

	public void setQuatidade(int quatidade) {
		this.quatidade = quatidade;
	}

}
