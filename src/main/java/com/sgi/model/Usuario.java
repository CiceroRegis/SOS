package com.sgi.model;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Email;

import com.sgi.constantes.PERFIL;
import com.sgi.constantes.SEXO;
@NamedQueries({
	@NamedQuery(name = "Usuario.buscaUsuarioPorCpf", query = "SELECT u FROM Usuario u WHERE u.cpf = :cpf") })

@NamedQuery(name = "visualiza.usuario", query = "select u from Usuario u where u.nome = :nome") 
@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idUsuario;

	private Long idResponsavel;

	private String nome;

	private String cpf;

	private String rg;

	private Date nascimento;

	private SEXO sexo;

	private Date data_cadastro;

	@Email(message = "E-mail inválido")
	private String email;

	private String telefone;

	private String celular;
	
	private Integer idPerfil;

	private PERFIL perfil;
	
	private Perfil perfilId;
	
	private String pw;
	
	private String senha;

	private boolean ativo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Endereco endereco;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdResponsavel() {
		return idResponsavel;
	}

	public void setIdResponsavel(Long idResponsavel) {
		this.idResponsavel = idResponsavel;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	
	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public SEXO getSexo() {
		return sexo;
	}
	
	public void setSexo(SEXO sexo) {
		this.sexo = sexo;
	}


	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public Integer getIdPerfil() {
		return idPerfil;
	}
	
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public PERFIL getPerfil() {
		return perfil;
	}

	public void setPerfil(PERFIL perfil) {
		this.perfil = perfil;
	}
	
	
	public Perfil getPerfilId() {
		return perfilId;
	}

	public void setPerfilId(Perfil perfilId) {
		this.perfilId = perfilId;
	}

	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = "Inativo";

		if (ativo) {
			ativoFormatado = "Ativo";
		}

		return ativoFormatado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuario == null) ? 0 : idUsuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (idUsuario == null) {
			if (other.idUsuario != null)
				return false;
		} else if (!idUsuario.equals(other.idUsuario))
			return false;
		return true;
	}
	/*
	 * O omnifaces.SelectItemsConverterpermite preencher um componente de seleção
	 * com objetos de modelo Java complexos (os_cad.xhtml) como valor
	 * <f:selectItems>
	 */
	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getIdUsuario());
	}
	

}
