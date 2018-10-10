package com.sgi.rn;

import java.util.Date;
import java.util.List;

import com.sgi.dao.ProdutoDAO;
import com.sgi.model.Produto;

public class ProdutoRN {

	ProdutoDAO aparelhoDAO = new ProdutoDAO();

	public Produto carregar(int id) {
		return this.aparelhoDAO.carregar(id);
	}

	public void salvar(Produto produto) {
		Integer id = produto.getId();
		if (id == null || id == 0) {
			produto.setDataRecebimento(new Date());
			this.aparelhoDAO.salvar(produto);
		} else
			this.aparelhoDAO.atualizar(produto);
	}

	public void excluir(Integer id) {
		this.aparelhoDAO.excluir(id);
	}

	public List<Produto> listar(Integer id) {
		return this.aparelhoDAO.Listar(id);
	}

	public void porId(Integer id) {
		this.aparelhoDAO.buscarId(id);
	}

	public void atualizar(Produto produto) {
		this.aparelhoDAO.atualizar(produto);
	}
}
