package br.ufsc.creche.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class MatriculaId implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	private Turma turma;
	private Aluno aluno;

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cd_turma", nullable = false)
	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	@ManyToOne(fetch = FetchType.LAZY )
	@JoinColumn(name = "cd_aluno", nullable = false)
	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}


}