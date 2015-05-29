package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Agencia generated by hbm2java
 */
@Entity
@Table(name = "AGENCIA")
public class Agencia implements java.io.Serializable {
	
	private static final long serialVersionUID = 7339935169563155223L;
	private AgenciaId id;
	private Banco banco;
	private Municipio municipio;
	private Integer ageDig;
	private String ageApe;
	private String ageNom;
	private String ageEnd;
	private Integer ageNro;
	private String ageCpl;
	private String ageBai;
	private String ageCep;
	private String ageDdd;
	private String ageFon;
	private String ageDdf;
	private String ageFax;
	private String ageEma;
	private String ageCon;
	private String ageCxp;
	private Integer ageRam;
//	private Set<Conta> contas = new HashSet<Conta>(0);
	

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ageBco", column = @Column(name = "AGE_BCO", nullable = false)),
			@AttributeOverride(name = "ageCod", column = @Column(name = "AGE_COD", nullable = false)) })
	public AgenciaId getId() {
		return this.id;
	}

	public void setId(AgenciaId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGE_BCO", nullable = false, insertable = false, updatable = false)
	public Banco getBanco() {
		return this.banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AGE_MUN")
	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Column(name = "AGE_DIG")
	public Integer getAgeDig() {
		return this.ageDig;
	}

	public void setAgeDig(Integer ageDig) {
		this.ageDig = ageDig;
	}

	@Column(name = "AGE_APE", length = 15)
	public String getAgeApe() {
		return this.ageApe;
	}

	public void setAgeApe(String ageApe) {
		this.ageApe = ageApe;
	}

	@Column(name = "AGE_NOM", nullable = false, length = 40)
	public String getAgeNom() {
		return this.ageNom;
	}

	public void setAgeNom(String ageNom) {
		this.ageNom = ageNom;
	}

	@Column(name = "AGE_END", length = 40)
	public String getAgeEnd() {
		return this.ageEnd;
	}

	public void setAgeEnd(String ageEnd) {
		this.ageEnd = ageEnd;
	}

	@Column(name = "AGE_NRO")
	public Integer getAgeNro() {
		return this.ageNro;
	}

	public void setAgeNro(Integer ageNro) {
		this.ageNro = ageNro;
	}

	@Column(name = "AGE_CPL", length = 40)
	public String getAgeCpl() {
		return this.ageCpl;
	}

	public void setAgeCpl(String ageCpl) {
		this.ageCpl = ageCpl;
	}

	@Column(name = "AGE_BAI", length = 20)
	public String getAgeBai() {
		return this.ageBai;
	}

	public void setAgeBai(String ageBai) {
		this.ageBai = ageBai;
	}

	@Column(name = "AGE_CEP", length = 8)
	public String getAgeCep() {
		return this.ageCep;
	}

	public void setAgeCep(String ageCep) {
		this.ageCep = ageCep;
	}

	@Column(name = "AGE_DDD", length = 2)
	public String getAgeDdd() {
		return this.ageDdd;
	}

	public void setAgeDdd(String ageDdd) {
		this.ageDdd = ageDdd;
	}

	@Column(name = "AGE_FON", length = 8)
	public String getAgeFon() {
		return this.ageFon;
	}

	public void setAgeFon(String ageFon) {
		this.ageFon = ageFon;
	}

	@Column(name = "AGE_DDF", length = 2)
	public String getAgeDdf() {
		return this.ageDdf;
	}

	public void setAgeDdf(String ageDdf) {
		this.ageDdf = ageDdf;
	}

	@Column(name = "AGE_FAX", length = 8)
	public String getAgeFax() {
		return this.ageFax;
	}

	public void setAgeFax(String ageFax) {
		this.ageFax = ageFax;
	}

	@Column(name = "AGE_EMA", length = 50)
	public String getAgeEma() {
		return this.ageEma;
	}

	public void setAgeEma(String ageEma) {
		this.ageEma = ageEma;
	}

	@Column(name = "AGE_CON", length = 50)
	public String getAgeCon() {
		return this.ageCon;
	}

	public void setAgeCon(String ageCon) {
		this.ageCon = ageCon;
	}

	@Column(name = "AGE_CXP", length = 15)
	public String getAgeCxp() {
		return this.ageCxp;
	}

	public void setAgeCxp(String ageCxp) {
		this.ageCxp = ageCxp;
	}

	@Column(name = "AGE_RAM")
	public Integer getAgeRam() {
		return this.ageRam;
	}

	public void setAgeRam(Integer ageRam) {
		this.ageRam = ageRam;
	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "agencia")
//	public Set<Conta> getContas() {
//		return this.contas;
//	}
//
//	public void setContas(Set<Conta> contas) {
//		this.contas = contas;
//	}

}