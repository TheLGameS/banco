package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ClienteRepresentanteId generated by hbm2java
 */
@Embeddable
public class ClienteRepresentanteId implements java.io.Serializable {

	private static final long serialVersionUID = 2078339891520617028L;
	private Integer clrEmp;
	private Integer clrFil;
	private Integer clrCli;
	private Integer clrRep;

	public ClienteRepresentanteId() {
	}

	public ClienteRepresentanteId(Integer clrEmp, Integer clrFil, Integer clrCli, Integer clrRep) {
		this.clrEmp = clrEmp;
		this.clrFil = clrFil;
		this.clrCli = clrCli;
		this.clrRep = clrRep;
	}

	@Column(name = "CLR_EMP", nullable = false)
	public Integer getClrEmp() {
		return this.clrEmp;
	}

	public void setClrEmp(Integer clrEmp) {
		this.clrEmp = clrEmp;
	}

	@Column(name = "CLR_FIL", nullable = false)
	public Integer getClrFil() {
		return this.clrFil;
	}

	public void setClrFil(Integer clrFil) {
		this.clrFil = clrFil;
	}

	@Column(name = "CLR_CLI", nullable = false)
	public Integer getClrCli() {
		return this.clrCli;
	}

	public void setClrCli(Integer clrCli) {
		this.clrCli = clrCli;
	}

	@Column(name = "CLR_REP", nullable = false)
	public Integer getClrRep() {
		return this.clrRep;
	}

	public void setClrRep(Integer clrRep) {
		this.clrRep = clrRep;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof ClienteRepresentanteId)) {
			return false;
		}
		ClienteRepresentanteId castOther = (ClienteRepresentanteId) other;

		return (this.getClrEmp() == castOther.getClrEmp())
				&& (this.getClrFil() == castOther.getClrFil())
				&& (this.getClrCli() == castOther.getClrCli())
				&& (this.getClrRep() == castOther.getClrRep());
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getClrEmp();
		result = 37 * result + this.getClrFil();
		result = 37 * result + this.getClrCli();
		result = 37 * result + this.getClrRep();
		return result;
	}

}