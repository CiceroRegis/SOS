package com.sgi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.sgi.model.Perfil;
import com.sgi.model.Usuario;
import com.sgi.utils.EntityManagerUtil;
public class UsuarioDAO {
	
	EntityManager em = EntityManagerUtil.getEM();
	public boolean salvar(Usuario usuario) {
		try {

			em.persist(usuario);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.getMessage();
		} finally {

		}
		return false;

	}

	public boolean atualizar(Usuario usuario) {

		try {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();

		} catch (Exception e) {

			e.getMessage();
		}
		return false;
	}

	/*public Usuario carregar(Integer id) {
		return em.find(Usuario.class, id);
	}*/

	public boolean excluir(Integer id) {

		try {
			Usuario usuario = em.find(Usuario.class, id);
			Thread.sleep(1200);
			em.remove(usuario);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
		}
		return false;

	}

	public Usuario buscarId(Long idUsuario) {

		EntityManager em = EntityManagerUtil.getEM();
		return em.find(Usuario.class, idUsuario);
	}

	public Usuario findByid(Integer id) throws InterruptedException {

		try {
			System.out.println("ID: " + id);
			return em.createNamedQuery("Usuario.findByid", Usuario.class).setParameter("id", id).getSingleResult();
		} catch (NoResultException nre) {

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> getList() {
		Query query = em.createQuery("select c from Usuario c ORDER BY nome ASC");
		return query.getResultList();
	}

	public Usuario visualizarUsuario(String nome) {

		return em.createNamedQuery("visualiza.usuario", Usuario.class).setParameter("nome", nome).getSingleResult();
	}

	public Usuario autenticarUsuarioPorCpf(String cpf) {

		Query query = em.createQuery("FROM User u where u.cpf = :cpf and u.pw =:pw)");
		query.setParameter("cpf", cpf);
		return (Usuario) query.getSingleResult();

	}
	
	public Usuario carregaUsuarioPorCpf(String cpf) {
		
		try {
			Query query = em.createQuery("FROM Usuario u where u.cpf= :cpf");
			query.setParameter("cpf", cpf);
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	
public Perfil carregarPerfilPorId(Long idPerfil) {
		
		try {
			Query query = em.createQuery("FROM Perfil p where p.id_perfil = :id_perfil");
			query.setParameter("id_perfil", idPerfil);
			return (Perfil) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
