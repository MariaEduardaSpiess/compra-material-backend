package com.testeSenior.testeSenior;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "solicitacao")
public class Solicitacao{
	


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public static final String SOLICITANTE="solicitante";
	@Column(name = "solicitante", nullable = false)
	private String solicitante;
	
	public static final String DESCPRODUTO="descProduto";
	@Column(name = "desc_produto", nullable = false)
	private String descProduto;
	
	@Column(name = "preco_produto", nullable = false)
	private double precoProduto;
	
	public static final String STATUS = "status";
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "observacao", nullable = true)
	private String observacao;

	public Solicitacao() {
	}
	
	public Solicitacao(String solicitante, String descProduto, double precoProduto, String status) {
		this.solicitante = solicitante;
		this.descProduto = descProduto;
		this.precoProduto = precoProduto;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public String getDescProduto() {
		return descProduto;
	}

	public void setDescProduto(String descProduto) {
		this.descProduto = descProduto;
	}

	public double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
