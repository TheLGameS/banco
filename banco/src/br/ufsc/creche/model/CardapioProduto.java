package br.ufsc.creche.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_cardapio_produto", sequenceName = "seq_cardapio_produto", allocationSize = 1, initialValue = 1)
@Table(name = "cardapioproduto", schema="public")
public class CardapioProduto implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	private Integer codigoCardapioProduto;
	private Cardapio cardapio;
	private Produto produto;
	private Integer quantidade;


	@Id
	@GeneratedValue(generator="seq_cardapio_produto")
	@Column(name="cd_cardapio_produto", unique=true, nullable=false )
	public Integer getCodigoCardapioProduto() {
		return codigoCardapioProduto;
	}

	public void setCodigoCardapioProduto(Integer codigoCardapioProduto) {
		this.codigoCardapioProduto = codigoCardapioProduto;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cd_cardapio", nullable = false)
	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "cd_produto", nullable = false)
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Column(name="quantidade", nullable=false)
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


}