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
	private String localidade;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String uf;
	
	@Column(length = 9)
	private String cep;
	
	public Endereco() {
	}

	public Endereco(String logradouro, String numero, String bairro, String localidade, String uf, String cep) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.cep = cep;
	}

	public Long getId() {
		return id;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public String getUF() {
		return uf;
	}
	
	public String getNumero() {
		return numero;
	}

	public String getCep() {
		return cep;
	}
	
	
}
