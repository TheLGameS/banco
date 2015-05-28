package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ESTADO")
public class Estado implements java.io.Serializable {

	private static final long serialVersionUID = 4381320669598087903L;

	private String estSig;
	private String estNom;
	private Integer estReg;
	private BigDecimal estAlq;
	private BigDecimal estAli;
	//private Set<Municipio> municipios = new HashSet<Municipio>(0);

	public Estado() {
	}

	public Estado(String estSig, String estNom) {
		this.estSig = estSig;
		this.estNom = estNom;
	}

	public Estado(String estSig) {
		super();
		this.estSig = estSig;
	}



	@Id
	@Column(name = "EST_SIG", unique = true, nullable = false, length = 2)
	public String getEstSig() {
		return this.estSig;
	}

	public void setEstSig(String estSig) {
		this.estSig = estSig;
	}

	@Column(name = "EST_NOM", nullable = false, length = 30)
	public String getEstNom() {
		return this.estNom;
	}

	public void setEstNom(String estNom) {
		this.estNom = estNom;
	}

	@Column(name = "EST_REG")
	public Integer getEstReg() {
		return this.estReg;
	}

	public void setEstReg(Integer estReg) {
		this.estReg = estReg;
	}

	@Column(name = "EST_ALQ", precision = 6)
	public BigDecimal getEstAlq() {
		return this.estAlq;
	}

	public void setEstAlq(BigDecimal estAlq) {
		this.estAlq = estAlq;
	}

	@Column(name = "EST_ALI", precision = 6)
	public BigDecimal getEstAli() {
		return this.estAli;
	}

	public void setEstAli(BigDecimal estAli) {
		this.estAli = estAli;
	}

	/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
	public Set<Municipio> getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Set<Municipio> municipios) {
		this.municipios = municipios;
	}*/





}
