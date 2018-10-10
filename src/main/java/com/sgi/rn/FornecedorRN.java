package com.sgi.rn;

import java.util.Date;
import java.util.List;

import com.sgi.dao.FornecedorDAO;
import com.sgi.model.Fornecedor;
import com.sgi.model.Usuario;

public class FornecedorRN {

	private FornecedorDAO fornecedorDAO = new FornecedorDAO();
private Usuario usuario = new Usuario();

	public Fornecedor  carregar(int id) {
		return this.fornecedorDAO.carregar(id);
	}

	public void salvar(Fornecedor fornecedor) {
		Integer id = fornecedor.getId();
		if (id == null || id == 0) {
			fornecedor.setDataCadastro(new Date());
			
			this.fornecedorDAO.salvar(fornecedor);
		} else
			this.fornecedorDAO.atualizar(fornecedor);
	}
	
	public Fornecedor visualizar(Integer id) {
		return this.fornecedorDAO.visualizarForncedor(id);
	}

	public void excluir(Fornecedor fornecedor) {
		this.fornecedorDAO.excluir(fornecedor );
	}

	public List<Fornecedor> listar() {
		return this.fornecedorDAO.getList();
	}

	public void porId(Integer id) {
		this.fornecedorDAO.buscarId(id);
	}
}
