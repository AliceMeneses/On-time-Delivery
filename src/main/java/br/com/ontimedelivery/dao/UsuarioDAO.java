package br.com.ontimedelivery.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.ontimedelivery.model.Usuario;

public class UsuarioDAO {
	private EntityManager entityManager;

	public UsuarioDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void inserir(Usuario usuario) {
		
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		
	}
	
	public boolean usuarioCadastrado(Usuario usuario) {
		
		TypedQuery<Usuario> query = entityManager.createNamedQuery("usuarioCadastrado", Usuario.class);
		query.setParameter("email", usuario.getEmail());
		query.setParameter("senha", usuario.getSenha());
		
		try {
			query.getSingleResult();
			return true;
		}
		catch (NoResultException e) {
			return false;
		}

	}
}
