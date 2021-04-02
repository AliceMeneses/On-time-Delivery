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

	private BigDecimal taxaFrete;
	private LocalDateTime dataPedido;
	private LocalDate dataEntrega;
	private Boolean servicoDeCargaEDescarga;
	private String descricao;

	@Enumerated(value = EnumType.STRING)
	private PesoPedido peso;
	
	@Enumerated(value = EnumType.STRING)
	private TipoVeiculo tipoVeiculo;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Endereco enderecoEntrega;
	
	@ManyToOne
	private Endereco enderecoRetirada;
	
	public Pedido() {
	}

	public Pedido(Usuario usuario, Endereco enderecoEntrega, Endereco enderecoRetirada, BigDecimal taxaFrete, LocalDateTime dataPedido,
			LocalDate dataEntrega, PesoPedido peso, TipoVeiculo tipoVeiculo, String descricao, Boolean servicoDeCargaEDescarga) {
		this.enderecoEntrega = enderecoEntrega;
		this.enderecoRetirada = enderecoRetirada;
		this.taxaFrete = taxaFrete;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.peso = peso;
		this.tipoVeiculo = tipoVeiculo;
		this.descricao = descricao;
		this.servicoDeCargaEDescarga = servicoDeCargaEDescarga;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public Endereco getEnderecoRetirada() {
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
	
	public Boolean getServicoDeCargaEDescarga() {
		return servicoDeCargaEDescarga;
	}

	public String getDescricao() {
		return descricao;
	}


}
