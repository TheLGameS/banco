package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProdutoLocalId generated by hbm2java
 */
@Embeddable
public class ProdutoLocalId implements java.io.Serializable {

	private static final long serialVersionUID = 4556323148341720203L;
	private Integer prlEmp;
	private Integer prlPro;
	private Integer prlFil;
	private Integer prlLcd;

	public ProdutoLocalId() {
	}

	public ProdutoLocalId(Integer prlEmp, Integer prlPro, Integer prlFil, Integer prlLcd) {
		this.prlEmp = prlEmp;
		this.prlPro = prlPro;
		this.prlFil = prlFil;
		this.prlLcd = prlLcd;
	}

	@Column(name = "PRL_EMP", nullable = false)
	public Integer getPrlEmp() {
		return this.prlEmp;
	}

	public void setPrlEmp(Integer prlEmp) {
		this.prlEmp = prlEmp;
	}

	@Column(name = "PRL_PRO", nullable = false)
	public Integer getPrlPro() {
		return this.prlPro;
	}

	public void setPrlPro(Integer prlPro) {
		this.prlPro = prlPro;
	}

	@Column(name = "PRL_FIL", nullable = false)
	public Integer getPrlFil() {
		return this.prlFil;
	}

	public void setPrlFil(Integer prlFil) {
		this.prlFil = prlFil;
	}

	@Column(name = "PRL_LCD", nullable = false)
	public Integer getPrlLcd() {
		return this.prlLcd;
	}

	public void setPrlLcd(Integer prlLcd) {
		this.prlLcd = prlLcd;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProdutoLocalId))
			return false;
		ProdutoLocalId castOther = (ProdutoLocalId) other;

		return (this.getPrlEmp() == castOther.getPrlEmp())
				&& (this.getPrlPro() == castOther.getPrlPro())
				&& (this.getPrlFil() == castOther.getPrlFil())
				&& (this.getPrlLcd() == castOther.getPrlLcd());
	}

	public int hashCode() {
		Integer result = 17;

		result = 37 * result + this.getPrlEmp();
		result = 37 * result + this.getPrlPro();
		result = 37 * result + this.getPrlFil();
		result = 37 * result + this.getPrlLcd();
		return result;
	}

}
