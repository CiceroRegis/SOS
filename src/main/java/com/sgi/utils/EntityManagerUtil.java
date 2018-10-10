package com.sgi.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

	private static EntityManagerFactory factory = null;
    private static EntityManager em = null;

    public static EntityManager getEM() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("sgiPU");
        }
        if (em == null) {
            em = factory.createEntityManager();
        }
        return em;
    }
}
