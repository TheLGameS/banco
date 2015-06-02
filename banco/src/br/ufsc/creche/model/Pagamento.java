package br.ufsc.creche.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "\"PAGAMENTO\"")
public class Pagamento implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigoPagamento;
	private Date dataPagamento;
	private BigDecimal juros;
	private BigDecimal valorLiquido;
	private BigDecimal valorBruto;
	private Integer codigoAluno;
	private Integer codigoContasReceber;
	
	
	public Pagamento() {

	}

	@Column(name = "\"CD_PAGAMENTO\"", unique = true, nullable = false)
	public Integer getCodigoPagamento() {
		return codigoPagamento;
	}


	public void setCodigoPagamento(Integer codigoPagamento) {
		this.codigoPagamento = codigoPagamento;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_PAGAMENTO", length = 10)
	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Column(name = "JUROS", nullable = false, precision = 14)
	public BigDecimal getJuros() {
		return juros;
	}


	public void setJuros(BigDecimal juros) {
		this.juros = juros;
	}

	@Column(name = "VALOR_LIQUIDO", nullable = false, precision = 14)
	public BigDecimal getValorLiquido() {
		return valorLiquido;
	}


	public void setValorLiquido(BigDecimal valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	@Column(name = "VALOR_BRUTO", nullable = false, precision = 14)
	public BigDecimal getValorBruto() {
		return valorBruto;
	}


	public void setValorBruto(BigDecimal valorBruto) {
		this.valorBruto = valorBruto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_ALUNO", nullable = false)
	public Integer getCodigoAluno() {
		return codigoAluno;
	}


	public void setCodigoAluno(Integer codigoAluno) {
		this.codigoAluno = codigoAluno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_CONTAS_RECEBER", nullable = false)
	public Integer getCodigoContasReceber() {
		return codigoContasReceber;
	}


	public void setCodigoContasReceber(Integer codigoContasReceber) {
		this.codigoContasReceber = codigoContasReceber;
	}
}