package com.sgi.constantes;

public enum PERFIL {

	ADMINISTRADOR(new Integer(1),"Admnistrador"),
	GERENTE(new Integer(2),"Gerente"),
	TECNICO(new Integer(3),"Tecnico"),
	ATENDENTE(new Integer(4),"Atendente");
	
	private Integer codigo;
	private String descricao;
	
	private PERFIL(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static String getDescricao(Integer codigo) {
		for(PERFIL perfil : values()) {
			if(codigo != null) {
				if(perfil.codigo.longValue() == codigo.longValue()) {
					return perfil.getDescricao();
				}
			}
		}
		return null;
	}


	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	
}
