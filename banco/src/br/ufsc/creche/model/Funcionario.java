package br.ufsc.creche.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"FUNCIONARIO\"")
public class Funcionario implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer codigoFuncionario;
	private String nome;
	private String cpf;
	private String cargo;
	private String telefone;
	private BigDecimal salario;
	

	public Funcionario() {

	}


	@Column(name = "\"CD_FUNCIONARIO\"", unique = true, nullable = false)
	public Integer getCodigoFuncionario() {
		return codigoFuncionario;
	}


	public void setCodigoFuncionario(Integer codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}

	@Column(name = "\"NOME\"", length = 50)
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "\"CPF\"", length = 11)
	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "\"CARGO\"", length = 50)
	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Column(name = "\"TELEFONE\"", length = 11)
	public String getTelefone() {
		return telefone;
	}


	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "SALARIO", nullable = false, precision = 14)
	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

}