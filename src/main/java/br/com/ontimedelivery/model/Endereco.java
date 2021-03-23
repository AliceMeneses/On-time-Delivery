package br.com.ontimedelivery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cidade;
	private String endereco;
	private String numero;
	private String bairro;
	private String estado;
	
	@Column(length = 8)
	private String cep;
	
	public Endereco() {
	}

	public Endereco(String cidade, String endereco, String numero, String bairro, String estado, String cep) {
		this.cidade = cidade;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.estado = estado;
		this.cep = cep;
	}

	public Long getId() {
		return id;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getEstado() {
		return estado;
	}

	public String getCep() {
		return cep;
	}
	
	
}
