package br.ufsc.creche.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_turma", sequenceName = "seq_turma", allocationSize = 1, initialValue = 1)
@Table(name = "turma", schema="public")
public class Turma implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	private Integer codigoTurma;
	private String descricao;


	@Id
	@GeneratedValue(generator="seq_turma")
	@Column(name="cd_turma", unique=true, nullable=false)
	public Integer getCodigoTurma() {
		return codigoTurma;
	}

	public void setCodigoTurma(Integer codigo) {
		this.codigoTurma = codigo;
	}

	@Column(name = "descricao", length = 50)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}