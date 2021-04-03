package br.com.ontimedelivery.dao;

import javax.persistence.EntityManager;

import br.com.ontimedelivery.model.Pedido;

public class PedidoDAO {
	
	private EntityManager entityManager;
	
	public PedidoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void inserir(Pedido pedido) {
		
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();
	}
}
