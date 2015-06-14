package br.ufsc.creche.model;

import java.io.Serializable;
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
@SequenceGenerator(name = "seq_cardapio", sequenceName = "seq_cardapio", allocationSize = 1, initialValue = 1)
@Table(name = "cardapio", schema="public")
public class Cardapio implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	private Integer codigoCardapio;
	private   Date data;
	private String descricao;
	private Funcionario funcionario;

	@Id
	@GeneratedValue(generator="seq_cardapio")
	@Column(name="cd_cardapio", unique=true, nullable=false )
	public Integer getCodigoCardapio() {
		return codigoCardapio;
	}

	public void setCodigoCardapio(Integer codigoCardapio) {
		this.codigoCardapio = codigoCardapio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data", length = 10)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "descricao", length = 50)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cd_funcionario", nullable = false)
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}