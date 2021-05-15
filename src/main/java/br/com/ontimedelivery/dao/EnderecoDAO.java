package br.com.ontimedelivery.dao;

import javax.persistence.EntityManager;

import br.com.ontimedelivery.model.Endereco;

public class EnderecoDAO {

	private EntityManager entityManager;

	public EnderecoDAO(EntityManager entityManager) {

		this.entityManager = entityManager;
	}

	public void inserir(Endereco endereco) {

		entityManager.persist(endereco);
	}
}
