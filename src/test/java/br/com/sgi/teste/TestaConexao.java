package br.com.sgi.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sgi.utils.EntityManagerUtil;

public class TestaConexao {

	private static EntityManagerFactory factory = null;
	
	 public static void main(String[] args) {
		  
		  EntityManager em = EntityManagerUtil.getEM();
		        System.out.println("Servidor conectado com sucesso!");
		        
		        em.getTransaction().begin();
		        if (factory == null) {
		            factory = Persistence.createEntityManagerFactory("sgiPU");
		        }
		        em.getTransaction().commit();
		        em.close(); 
	 }

}
