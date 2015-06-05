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
@SequenceGenerator(name = "seq_aluno", sequenceName = "seq_aluno", allocationSize = 1, initialValue = 1)
@Table(name = "aluno", schema="public")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;

	private Integer codigoAluno;
	private String nome;
	private String nomePai;
	private String nomeMae;
	private String nomePediatra;
	private String telefonePai;
	private String telefoneMae;
	private String telefonePediatra;
	private String autorizaRetirada;
	private   Date dataNascimento;
	private String matricula;
	
	public Aluno() {

	}

	@Id  
    @GeneratedValue(generator="seq_aluno") 
    @Column(name="cd_aluno", unique=true, nullable=false ) 
	public Integer getCodigoAluno() {
		return codigoAluno;
	}

	public void setCodigoAluno(Integer codigoAluno) {
		this.codigoAluno = codigoAluno;
	}

	@Column(name = "nome", length = 50)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "nome_pai", length = 50)
	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	@Column(name = "nome_mae", length = 50)
	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	@Column(name = "nome_pediatra", length = 50)
	public String getNomePediatra() {
		return nomePediatra;
	}

	public void setNomePediatra(String nomePediatra) {
		this.nomePediatra = nomePediatra;
	}

	@Column(name = "telefone_pai", length = 11)
	public String getTelefonePai() {
		return telefonePai;
	}

	public void setTelefonePai(String telefonePai) {
		this.telefonePai = telefonePai;
	}

	@Column(name = "telefone_mae", length = 11)
	public String getTelefoneMae() {
		return telefoneMae;
	}

	public void setTelefoneMae(String telefoneMae) {
		this.telefoneMae = telefoneMae;
	}

	@Column(name = "telefone_pediatra", length = 11)
	public String getTelefonePediatra() {
		return telefonePediatra;
	}

	public void setTelefonePediatra(String telefonePediatra) {
		this.telefonePediatra = telefonePediatra;
	}

	@Column(name = "autoriza_retirada", length = 50)
	public String getAutorizaRetirada() {
		return autorizaRetirada;
	}

	public void setAutorizaRetirada(String autorizaRetirada) {
		this.autorizaRetirada = autorizaRetirada;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento", length = 10)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Column(name = "matricula", length = 15, nullable=false, unique=true)
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
   
}