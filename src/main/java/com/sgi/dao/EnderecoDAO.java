package com.sgi.dao;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import com.sgi.model.Endereco;
import com.sgi.model.Usuario;
import com.sgi.utils.EntityManagerUtil;

public class EnderecoDAO {
	EntityManager em = EntityManagerUtil.getEM();

	public boolean salvar(Endereco endereco) {
		try {

			em.getTransaction().begin();
			em.persist(endereco);
			em.getTransaction().commit();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Ops! Ocorreu um erro ao tentar salvar este endereço!", ""));
		} finally {

		}
		return false;
	}
	
}
