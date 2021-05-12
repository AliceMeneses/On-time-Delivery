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
	
	public Usuario buscarUsuario(Usuario usuario) {
		
		TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha", Usuario.class)
			.setParameter("email", usuario.getEmail())
			.setParameter("senha", usuario.getSenha());
		
		try {
			 
			return query.getSingleResult();
		}
		catch(NoResultException  e) {
			return null;
		}
		
	}
	
	public Boolean buscarUsuarioCadastrado(Usuario usuario) {
		TypedQuery<Usuario> query = entityManager.createNamedQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
				.setParameter("email", usuario.getEmail());

		try {
			query.getSingleResult();
			return true;
		}
		catch(NoResultException  e) {
			return false;
		}
	}
}
