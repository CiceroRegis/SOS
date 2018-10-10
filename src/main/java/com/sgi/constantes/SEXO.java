package com.sgi.constantes;

public enum SEXO {

	MASCULINO("1", "Masculino"), FEMININO("2", "Feminino");

	private String codigo;
	private String descricao;

	private SEXO(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
