package com.ufal.br.model;
import java.io.Serializable;

public class Consulta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nomePaciente;
	private String descricao;
	
	
	public Consulta (int id,String nomePaciente, String descricao) {
		this.id = id;
		this.nomePaciente = nomePaciente;
		this.descricao = descricao;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
