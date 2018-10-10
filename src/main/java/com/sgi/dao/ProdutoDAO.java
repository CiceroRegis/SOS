package com.sgi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sgi.model.Produto;
import com.sgi.utils.EntityManagerUtil;

public class ProdutoDAO {

	EntityManager em = EntityManagerUtil.getEM();
	public void salvar(Produto produto) {
		try {
			em.persist(produto);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao tentar salvar os Produtos>> " + e.getMessage());
		}
	}

	public void atualizar(Produto produto) {
		try {
			em.merge(produto);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao tentar atualizar os Produtos>> " + e.getMessage());
		}
	}

	public Produto carregar(Integer id) {
		try {
			return em.find(Produto.class, id);

		} catch (Exception e) {
			System.out.println("Erro ao tentar carregar o Produtos por id>> " + e.getMessage());
		}
		return null;

	}

	public void excluir(Integer id) {
		try {
			Produto produto = em.find(Produto.class, id);
			em.remove(produto);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro o tentar excluir o Produtos>> " + e.getMessage());
		}
		{
		}

	}

	public Produto buscarId(Integer id) {
		Produto produto = null;
		try {
			produto = em.find(Produto.class, id);
		} finally {
		}
		return produto;
	}

	public List<Produto> Listar(Integer id) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
	    CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
	    Root<Produto> acc = query.from(Produto.class);

	    Predicate cond = builder.gt(acc.get("id"), id);
	    query.where(cond);
	    TypedQuery<Produto> q = em.createQuery(query);
	    List<Produto> resultList = q.getResultList();
	    System.out.println(resultList);
		return resultList;
		
	}
}
