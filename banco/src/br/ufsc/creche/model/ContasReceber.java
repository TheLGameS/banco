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
@SequenceGenerator(name = "seq_contas_receber", sequenceName = "seq_contas_receber", allocationSize = 1, initialValue = 1)
@Table(name = "contasreceber", schema="public")
public class ContasReceber implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	private Integer codigoContasReceber;
	private Date dataVencimento;
	private BigDecimal valor;
	private Aluno aluno;
	private String observacao;

	
	@Id  
    @GeneratedValue(generator="seq_contas_receber") 
    @Column(name="cd_contas_receber", unique=true, nullable=false)
	public Integer getCodigoContasReceber() {
		return codigoContasReceber;
	}

	public void setCodigoContasReceber(Integer codigoContasReceber) {
		this.codigoContasReceber = codigoContasReceber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento", length = 10)
	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Column(name = "valor", nullable = false, precision = 14)
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cd_aluno", nullable = false)
	public Aluno getCodigoAluno() {
		return this.aluno;
	}

	public void setCodigoAluno(Aluno codigoAluno) {
		this.aluno = codigoAluno;
	}

	@Column(name = "observacao", length = 50)
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public ContasReceber() {

	}
}