package br.com.ontimedelivery.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String enderecoEntrega;
	private String enderecoRetirada;
	private BigDecimal taxaFrete;
	private LocalDateTime dataPedido;
	private LocalDate dataEntrega;
	
	@Enumerated(value = EnumType.STRING)
	private PesoPedido peso;
	
	@Enumerated(value = EnumType.STRING)
	private TipoVeiculo tipoVeiculo;
	
	@ManyToOne
	private Endereco endereco;
	
	@ManyToOne
	private Usuario usuario;

	public Pedido(String enderecoEntrega, String enderecoRetirada, BigDecimal taxaFrete, LocalDateTime dataPedido,
			LocalDate dataEntrega, PesoPedido peso, TipoVeiculo tipoVeiculo) {
		this.enderecoEntrega = enderecoEntrega;
		this.enderecoRetirada = enderecoRetirada;
		this.taxaFrete = taxaFrete;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.peso = peso;
		this.tipoVeiculo = tipoVeiculo;
	}

	public Long getId() {
		return id;
	}

	public String getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public String getEnderecoRetirada() {
		return enderecoRetirada;
	}

	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}

	public LocalDateTime getDataPedido() {
		return dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public PesoPedido getPeso() {
		return peso;
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}


}
