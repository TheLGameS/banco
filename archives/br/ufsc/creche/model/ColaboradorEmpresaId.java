package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ColaboradorEmpresaId generated by hbm2java
 */
@Embeddable
public class ColaboradorEmpresaId implements java.io.Serializable {

	private static final long serialVersionUID = -7901693114739647807L;
	private Integer coeEmp;
	private Integer coeCol;

	public ColaboradorEmpresaId() {
	}

	public ColaboradorEmpresaId(Integer coeEmp, Integer coeCol) {
		this.coeEmp = coeEmp;
		this.coeCol = coeCol;
	}

	@Column(name = "COE_EMP", nullable = false)
	public Integer getCoeEmp() {
		return this.coeEmp;
	}

	public void setCoeEmp(Integer coeEmp) {
		this.coeEmp = coeEmp;
	}

	@Column(name = "COE_COL", nullable = false)
	public Integer getCoeCol() {
		return this.coeCol;
	}

	public void setCoeCol(Integer coeCol) {
		this.coeCol = coeCol;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coeCol == null) ? 0 : coeCol.hashCode());
		result = prime * result + ((coeEmp == null) ? 0 : coeEmp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ColaboradorEmpresaId)) {
			return false;
		}
		ColaboradorEmpresaId other = (ColaboradorEmpresaId) obj;
		if (coeCol == null) {
			if (other.coeCol != null) {
				return false;
			}
		} else if (!coeCol.equals(other.coeCol)) {
			return false;
		}
		if (coeEmp == null) {
			if (other.coeEmp != null) {
				return false;
			}
		} else if (!coeEmp.equals(other.coeEmp)) {
			return false;
		}
		return true;
	}



}