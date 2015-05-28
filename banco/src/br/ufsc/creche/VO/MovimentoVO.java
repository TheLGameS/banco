package br.ufsc.creche.VO;

import java.util.Date;

public class MovimentoVO {


	private Date dataMovimento;
	private Integer idFilial;
	private String razaoSocial;
	private String apelido;
	private Number total;
	private Number acrescimo;
	private Number desconto;
	private Number totalProdutos;
	private Number qtdadeProdutos;
	private Number qtdadeVendas;
	private Integer idColaborador;
	private Integer idMunicipio;
	private Integer idGuia;
	private Integer idRepresentante;
	private String municipio;
	private String uf;
	private String guia;
	

	public Date getDataMovimento() {
		return dataMovimento;
	}

	public void setDataMovimento(Date dataMovimento) {
		this.dataMovimento = dataMovimento;
	}

	public Integer getIdFilial() {
		return idFilial;
	}
	public void setIdFilial(Integer idFilial) {
		this.idFilial = idFilial;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getApelido() {
		return apelido;
	}
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public Number getTotal() {
		return total;
	}

	public void setTotal(Number total) {
		this.total = total;
	}

	public Number getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(Number acrescimo) {
		this.acrescimo = acrescimo;
	}

	public Number getDesconto() {
		return desconto;
	}

	public void setDesconto(Number desconto) {
		this.desconto = desconto;
	}

	public Number getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(Number totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public Number getQtdadeProdutos() {
		
		return qtdadeProdutos == null ? 0 : qtdadeProdutos;
	}

	public void setQtdadeProdutos(Number qtdadeProdutos) {
		this.qtdadeProdutos = qtdadeProdutos;
	}

	public Number getQtdadeVendas() {
		return qtdadeVendas;
	}

	public void setQtdadeVendas(Number qtdadeVendas) {
		this.qtdadeVendas = qtdadeVendas;
	}

	public Integer getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(Integer idColaborador) {
		this.idColaborador = idColaborador;
	}

	public Integer getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(Integer idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public Integer getIdGuia() {
		return idGuia == null ? 0 : idGuia;
	}

	public void setIdGuia(Integer idGuia) {
		this.idGuia = idGuia;
	}

	public Integer getIdRepresentante() {
		return idRepresentante;
	}

	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getGuia() {
		return guia;
	}

	public void setGuia(String guia) {
		this.guia = guia;
	}


}
