package br.com.ontimedelivery.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoBanco {
	
	public static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("ontimedelivery");

	public static EntityManager entityManager = null;
	
	public static EntityManager getEntityManager(){
		if(entityManager != null) {
			return entityManager;
		}
				
		return FACTORY.createEntityManager();
	}
	
	public static void desconectar() {
		
		if(entityManager != null) {
			entityManager.close();
		}
		
	}
}
