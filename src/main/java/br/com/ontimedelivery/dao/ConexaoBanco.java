package br.com.ontimedelivery.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoBanco {

	public static EntityManager entityManager = null;
	
	public static EntityManager getEntityManager(){
		if(entityManager != null) {
			return entityManager;
		}
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ontimedelivery");
		entityManager = entityManagerFactory.createEntityManager();
		
		return entityManager;
	}
}
