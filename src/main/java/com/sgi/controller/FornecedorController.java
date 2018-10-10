package com.sgi.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.sgi.model.Fornecedor;
import com.sgi.rn.FornecedorRN;

@ManagedBean
@SessionScoped
public class FornecedorController implements Serializable{

	private static final long serialVersionUID = 1L;

	Fornecedor fornecedor = new Fornecedor();
	FornecedorRN fornecedorRN = new FornecedorRN();
	private List<Fornecedor> listarFornecedores;

	@PostConstruct
	public void init() {
		FornecedorRN listarFornecedores = new FornecedorRN();
		this.listarFornecedores = listarFornecedores.listar();
	}

	public String novo() {
		this.fornecedor = new Fornecedor();
		this.fornecedor.setDataCadastro(new Date());
		return "/fornecedores/fornecedor_cad.xhtml?faces-redirect=true";
	}

	public String editar() {
		this.fornecedor.setDataCadastro(new Date());
		this.fornecedor =  this.fornecedorRN.carregar(this.fornecedor.getId());
		return "/fornecedores/fornecedor_cad.xhtml?faces-redirect=true";
	}

	public String salvar() {
		this.fornecedorRN.salvar(this.fornecedor);
		this.fornecedor =  new Fornecedor();
		return "/fornecedores/fornecedores_list?faces-redirect=true";
	}

	public String visualizarFornecedor() {
		if (this.fornecedor != null) {
			FornecedorRN fornecedorRN = new FornecedorRN();
			if (this.fornecedor.getTelefone().isEmpty()) {
				this.fornecedor.setTelefone("Não informado");
			}
			if (this.fornecedor.getEmail().isEmpty()) {
				this.fornecedor.setEmail("Não Informado");
			}
			if (this.fornecedor.getCep().isEmpty()) {
				this.fornecedor.setCep("Não Informado");
			}
			if (this.fornecedor.getLogradouro().isEmpty()) {
				this.fornecedor.setLogradouro("Não Informado");
			}
			if (this.fornecedor.getComplemento().isEmpty()) {
				this.fornecedor.setComplemento("Não Informado");
			}
			if (this.fornecedor.getNumero().isEmpty()) {
				this.fornecedor.setNumero("Não Informado");
			}
			fornecedorRN.visualizar(this.fornecedor.getId());
		}
		return "/fornecedores/visualizarFornecedor_cad?faces-redirect=true";

	}
	
	public String excluir() {
		try {
			this.fornecedorRN.excluir(this.fornecedor);
			System.out.println("Excluindo o Fornecedor >>>" + fornecedor.getNome());
		} catch (Exception e) {
			System.out.println("Erro ao tentar excluir Fornecedor >> " + e.getMessage());
		}
		return "/fornecedores/fornecedores_list?faces-redirect=true";
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public List<Fornecedor> getListarFornecedores() {
		return listarFornecedores;
	}

	public void setListarFornecedores(List<Fornecedor> listarFornecedores) {
		this.listarFornecedores = listarFornecedores;
	}

}
