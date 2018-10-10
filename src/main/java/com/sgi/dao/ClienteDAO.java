package com.sgi.dao;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.sgi.model.Cliente;
import com.sgi.utils.EntityManagerUtil;

public class ClienteDAO {
	EntityManager em = EntityManagerUtil.getEM();
	
	public void salvar(Cliente cliente) {
		try {
			em.getTransaction().begin();
			em.persist(cliente);
			em.getTransaction().commit();
			em.getFlushMode();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Ops! Ocorreu um erro ao tentar salvar este Cliente", ""));
		} {
		}
	}
	
	public void atualizar(Cliente cliente){
        try{
            em.merge(cliente);
        }catch (Exception e) {
        	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Ops! Ocorreu um erro ao tentar atualizar os dados este Cliente", ""));
		}{
        }
    }
	
	public Cliente carregar(Integer id) {
		return  em.find(Cliente.class, id);
				
	}

	public void excluir(Integer id) {
		try {
			Cliente cliente = em.find(Cliente.class, id);
			em.remove(cliente);
		} finally {
		}

	}

	public Cliente buscarId(Integer id) {
		
		Cliente cliente= null;
		try {
			cliente = em.find(Cliente.class, id);
		} finally {
		}
		return cliente;
	}
	
	public List<Cliente> porNomeSemelhante(String nome) {
		return em.createQuery("from Cliente where nome like :nome", Cliente.class)
				.setParameter("nome", "%" + nome + "%")
				.getResultList();
	}

	public List<Cliente> getList(){
		Query query = em.createQuery("select c from Cliente c");
		return query.getResultList();
	}
	
}
