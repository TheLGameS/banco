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
@Table(name = "\"CONTASRECEBER\"")
public class ContasReceber implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigoContasReceber;
	private Date dataVencimento;
	private BigDecimal valor;
	private Integer codigoAluno;
	private String observacao;

	@Column(name = "\"CD_CONTAS_RECEBER\"", unique = true, nullable = false)
	public Integer getCodigoContasReceber() {
		return codigoContasReceber;
	}

	public void setCodigoContasReceber(Integer codigoContasReceber) {
		this.codigoContasReceber = codigoContasReceber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_VENCIMENTO", length = 10)
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Column(name = "VALOR", nullable = false, precision = 14)
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_ALUNO", nullable = false)
	public Integer getCodigoAluno() {
		return codigoAluno;
	}

	public void setCodigoAluno(Integer codigoAluno) {
		this.codigoAluno = codigoAluno;
	}

	@Column(name = "\"OBSERVACAO\"", length = 50)
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ContasReceber() {

	}
}