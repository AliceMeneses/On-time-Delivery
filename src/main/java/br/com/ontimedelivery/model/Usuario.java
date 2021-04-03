package br.com.ontimedelivery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@NamedQuery(name="buscarUsuario", query="SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha")
@NamedQuery(name="buscarUsuarioPeloEmail", query="SELECT u FROM Usuario u WHERE u.email = :email")

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@Column(length = 15)
	private String telefone;
	
	private String email;	
	
	@Column(length = 15)
	private String senha;

	public Usuario() {

	}

	public Usuario(String nome, String telefone, String email, String senha) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
