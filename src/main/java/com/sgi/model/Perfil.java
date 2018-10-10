package com.sgi.model;

import java.io.Serializable;

public class Perfil implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idPerfil;
	private String nome;
	private String descricao;

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
