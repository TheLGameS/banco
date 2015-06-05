package br.ufsc.creche.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "seq_pagamento", sequenceName = "seq_pagamento", allocationSize = 1, initialValue = 1)
@Table(name = "pagamento", schema="public")
public class Pagamento implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	private Integer codigoPagamento;
	private Date dataPagamento;
	private BigDecimal juros;
	private BigDecimal valorLiquido;
	private BigDecimal valorBruto;
	private Aluno aluno;
	private ContasReceber contasReceber;
	
	
	public Pagamento() {

	}

	@Id  
    @GeneratedValue(generator="seq_pagamento") 
    @Column(name="cd_pagamento", unique=true, nullable=false)
	public Integer getCodigoPagamento() {
		return codigoPagamento;
	}


	public void setCodigoPagamento(Integer codigoPagamento) {
		this.codigoPagamento = codigoPagamento;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "data_pagamento", length = 10)
	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Column(name = "juros", nullable = false, precision = 14)
	public BigDecimal getJuros() {
		return juros;
	}


	public void setJuros(BigDecimal juros) {
		this.juros = juros;
	}

	@Column(name = "valor_liquido", nullable = false, precision = 14)
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}


	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	@Column(name = "valor_bruto", nullable = false, precision = 14)
	public BigDecimal getValorBruto() {
		return valorBruto;
	}


	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_aluno", nullable = false)
	public Aluno getCodigoAluno() {
		return this.aluno;
	}

	public void setCodigoAluno(Aluno codigoAluno) {
		this.aluno = codigoAluno;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_contas_receber", nullable = false)
	public ContasReceber getCodigoContasReceber() {
		return this.contasReceber;
	}


	public void setCodigoContasReceber(ContasReceber codigoContasReceber) {
		this.contasReceber = codigoContasReceber;
	}
}