package com.sgi.rn;

import java.util.List;

import com.sgi.dao.ClienteDAO;
import com.sgi.model.Cliente;

public class ClienteRN {

	private ClienteDAO clienteDAO = new ClienteDAO();


	public Cliente carregar(int id) {
		return this.clienteDAO.carregar(id);
	}

	public void salvar(Cliente cliente) {
		Integer id = cliente.getId();
		if (id == null || id == 0) {
			this.clienteDAO.salvar(cliente);
		} else
			this.clienteDAO.atualizar(cliente);
	}

	public void excluir(Integer id) {
		this.clienteDAO.excluir(id);
	}

	public List<Cliente> listar() {
		return this.clienteDAO.getList();
	}

	public void porId(Integer id) {
		this.clienteDAO.buscarId(id);
	}

	public void atualizar(Cliente cliente) {
		this.clienteDAO.atualizar(cliente);
	}

	public Cliente pesquisarPorCodigo(Integer id) {
		return this.clienteDAO.carregar(id);
	}
	
	public List<Cliente> porNomesSemelhante(String nome) {
		return this.clienteDAO.porNomeSemelhante(nome);
	}
}
