package com.sgi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sgi.model.OrdemServico;
import com.sgi.utils.EntityManagerUtil;

public class OrdemServicoDAO {

	EntityManager em = EntityManagerUtil.getEM();

	public void salvar(OrdemServico os) {
		try {
			em.getTransaction().begin();
			Thread.sleep(1500);
			em.persist(os);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao tentar salvar a ordem de Serivço>> " + e.getMessage());
		}
	}

	public void atualizar(OrdemServico os) {
		try {
			em.merge(os);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao tentar atualizar a ordem de serviço>> " + e.getMessage());
		}
	}

	public OrdemServico carregar(Integer id) {
		try {
			EntityManager em = EntityManagerUtil.getEM();
			return em.find(OrdemServico.class, id);

		} catch (Exception e) {
			System.out.println("Erro ao tentar carregar o Ordem De servico por id>> " + e.getMessage());
		}
		return null;

	}

	public void excluir(OrdemServico ordemServico) {
		try {
			em.getTransaction().begin();
			OrdemServico os = em.find(OrdemServico.class, ordemServico.getId());
			System.out.println("excluindo a ordem de Serviço>> " + ordemServico.getId());
			em.remove(os);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro o tentar excluir a ordem de Serviço>> " + e.getMessage());
		}
		{
		}

	}

	@SuppressWarnings("unchecked")
	public List<OrdemServico> getList() {
		EntityManager em = EntityManagerUtil.getEM();
		Query query = em.createQuery("select o from OrdemServico o");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<OrdemServico> busca() {
		try {
			List ordemServico = em.createQuery("SELECT p FROM OrdemServico e JOIN e.cliente p").getResultList();
			for (Object p : ordemServico) {
				System.out.println("listando ordem de Serviço>>" + p);
			}
			return ordemServico;
		} catch (Exception e) {
			System.out.println("Erro ao tentar enviar dados para o formulario de edição>> " + e.getMessage());
		}
		return null;

	}
	
	public OrdemServico visualizarOS(Integer id) {
		EntityManager em = EntityManagerUtil.getEM();
		    Query query = em.createNamedQuery("visualiza.os");
		    query.setParameter("id", id);
		    return (OrdemServico) query.getSingleResult();
	}

}
