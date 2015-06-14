package br.ufsc.creche.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "seq_atividade", sequenceName = "seq_atividade", allocationSize = 1, initialValue = 1)
@Table(name = "atividade", schema="public")
public class Atividade implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	private Integer codigoAtividade;
	private String descricao;
	private   Date data;
	private String horario;

	@Id
	@GeneratedValue(generator="seq_atividade")
	@Column(name="cd_atividade", unique=true, nullable=false )
	public Integer getCodigoAtividade() {
		return codigoAtividade;
	}

	public void setCodigoAtividade(Integer codigoAtividade) {
		this.codigoAtividade = codigoAtividade;
	}

	@Column(name = "descricao", length = 50)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data", length = 10, nullable=false)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "horario", length = 5, nullable=false)
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}






}