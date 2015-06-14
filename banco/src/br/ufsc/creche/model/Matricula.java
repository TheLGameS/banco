package br.ufsc.creche.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "matricula", schema="public")
public class Matricula implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	private MatriculaId id;
	private String nomeAluno;
	private String nomeTurma;

	@EmbeddedId
	@AttributeOverrides({
		@AttributeOverride(name = "turma", column = @Column(name = "cd_turma", nullable = false)),
		@AttributeOverride(name = "aluno", column = @Column(name = "cd_aluno", nullable = false)) })

	public MatriculaId getId() {
		return id;
	}

	public void setId(MatriculaId id) {
		this.id = id;
	}

	@Column(name="nome_aluno",length=50)
	public String getNomeAluno() {
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}

	@Column(name="nome_turma",length=50)
	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}




}