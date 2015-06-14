package br.ufsc.creche.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seq_funcionario", sequenceName = "seq_funcionario", allocationSize = 1, initialValue = 1)
@Table(name = "funcionario", schema="public")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	private Integer codigoFuncionario;
	private String nome;
	private String cpf;
	private String cargo;
	private String telefone;
	private BigDecimal salario;


	public Funcionario() {

	}

	@Id
	@GeneratedValue(generator="seq_funcionario")
	@Column(name="cd_funcionario", unique=true, nullable=false)
	public Integer getCodigoFuncionario() {
		return codigoFuncionario;
	}


	public void setCodigoFuncionario(Integer codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	@Column(name = "nome", length = 50)
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cpf", length = 14, unique=true)
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "cargo", length = 50)
	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Column(name = "telefone", length = 13)
	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "salario", nullable = false, precision = 12, scale=2	)
	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

}