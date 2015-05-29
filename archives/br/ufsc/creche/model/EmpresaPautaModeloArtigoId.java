package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * EmpresaPautaModeloArtigoId generated by hbm2java
 */
@Embeddable
public class EmpresaPautaModeloArtigoId implements java.io.Serializable {

	private static final long serialVersionUID = 3315459131233839215L;
	private Integer epmEmp;
	private Integer epmPat;
	private Integer epmMdl;
	private Integer epmAtg;

	public EmpresaPautaModeloArtigoId() {
	}

	public EmpresaPautaModeloArtigoId(Integer epmEmp, Integer epmPat, Integer epmMdl,
			Integer epmAtg) {
		this.epmEmp = epmEmp;
		this.epmPat = epmPat;
		this.epmMdl = epmMdl;
		this.epmAtg = epmAtg;
	}

	@Column(name = "EPM_EMP", nullable = false)
	public Integer getEpmEmp() {
		return this.epmEmp;
	}

	public void setEpmEmp(Integer epmEmp) {
		this.epmEmp = epmEmp;
	}

	@Column(name = "EPM_PAT", nullable = false)
	public Integer getEpmPat() {
		return this.epmPat;
	}

	public void setEpmPat(Integer epmPat) {
		this.epmPat = epmPat;
	}

	@Column(name = "EPM_MDL", nullable = false)
	public Integer getEpmMdl() {
		return this.epmMdl;
	}

	public void setEpmMdl(Integer epmMdl) {
		this.epmMdl = epmMdl;
	}

	@Column(name = "EPM_ATG", nullable = false)
	public Integer getEpmAtg() {
		return this.epmAtg;
	}

	public void setEpmAtg(Integer epmAtg) {
		this.epmAtg = epmAtg;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EmpresaPautaModeloArtigoId))
			return false;
		EmpresaPautaModeloArtigoId castOther = (EmpresaPautaModeloArtigoId) other;

		return (this.getEpmEmp() == castOther.getEpmEmp())
				&& (this.getEpmPat() == castOther.getEpmPat())
				&& (this.getEpmMdl() == castOther.getEpmMdl())
				&& (this.getEpmAtg() == castOther.getEpmAtg());
	}

	public int hashCode() {
		Integer result = 17;

		result = 37 * result + this.getEpmEmp();
		result = 37 * result + this.getEpmPat();
		result = 37 * result + this.getEpmMdl();
		result = 37 * result + this.getEpmAtg();
		return result;
	}

}