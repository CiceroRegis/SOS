package com.sgi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sgi.model.Fornecedor;
import com.sgi.model.Usuario;
import com.sgi.utils.EntityManagerUtil;

public class FornecedorDAO {
	EntityManager em = EntityManagerUtil.getEM();

	public void salvar(Fornecedor fornecedor) {
		try {
			em.getTransaction().begin();
			em.persist(fornecedor);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao tentar salvar o Fornecedor>> " + e.getMessage());
		}
	}

	public void atualizar(Fornecedor fornecedor) {
		try {
			em.getTransaction().begin();
			em.merge(fornecedor);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao tentar atualizar o fornecedor>> " + e.getMessage());
		}
	}

	public Fornecedor carregar(Integer id) {
		try {
			return em.find(Fornecedor.class, id);
		} catch (Exception e) {
			System.out.println("Erro ao tentar carregar o fornecedor por id>> " + e.getMessage());
		}
		return null;

	}

	public void excluir(Fornecedor fornecedor) {
		try {
			em.getTransaction().begin();
			Fornecedor fn= em.find(Fornecedor.class, fornecedor.getId());
			System.out.println("excluindo o Fornecedor >>> " + fornecedor.getId());
			em.remove(fn);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro o tentar excluir O Fornecedor>> " + e.getMessage());
		}
		{
		}
	}

	public Usuario buscarId(Integer id) {
		Usuario usuario = null;
		try {
			
			usuario = em.find(Usuario.class, id);
		} finally {
		}
		return usuario;
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> getList() {
		try {
			Thread.sleep(1200);
			Query query = em.createQuery("select f from Fornecedor f");
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao listar fornecedor fornecedores>> " + e.getMessage());
		}
		return null;
	}

	
	  public Fornecedor visualizarForncedor(Integer id) {
		  EntityManager em =  EntityManagerUtil.getEM(); 
		try {
			Thread.sleep(1200);
			return em.createNamedQuery("visualizar.Fornecedor",Fornecedor.class)
					.setParameter("id", id)
					.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
 
	  }
	 

}

/*
 * public Usuario findByid(Integer id) { EntityManager em =
 * EntityManagerUtil.getEM(); try {
 * 
 * System.out.println("ID: " + id); return
 * em.createNamedQuery("Usuario.findByid", Usuario.class).setParameter("id",
 * 6).getSingleResult();
 * 
 * } catch (NoResultException nre) {
 * 
 * } return null;
 * 
 * }
 */