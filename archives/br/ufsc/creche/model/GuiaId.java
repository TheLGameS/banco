package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * GuiaId generated by hbm2java
 */
@Embeddable
public class GuiaId implements java.io.Serializable {

	private static final long serialVersionUID = 4942508989359994631L;
	private int guiEmp;
	private int guiFil;
	private int guiCod;

	public GuiaId() {
	}

	public GuiaId(int guiEmp, int guiFil, int guiCod) {
		this.guiEmp = guiEmp;
		this.guiFil = guiFil;
		this.guiCod = guiCod;
	}

	@Column(name = "GUI_EMP", nullable = false)
	public int getGuiEmp() {
		return this.guiEmp;
	}

	public void setGuiEmp(int guiEmp) {
		this.guiEmp = guiEmp;
	}

	@Column(name = "GUI_FIL", nullable = false)
	public int getGuiFil() {
		return this.guiFil;
	}

	public void setGuiFil(int guiFil) {
		this.guiFil = guiFil;
	}

	@Column(name = "GUI_COD", nullable = false)
	public int getGuiCod() {
		return this.guiCod;
	}

	public void setGuiCod(int guiCod) {
		this.guiCod = guiCod;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof GuiaId))
			return false;
		GuiaId castOther = (GuiaId) other;

		return (this.getGuiEmp() == castOther.getGuiEmp())
				&& (this.getGuiFil() == castOther.getGuiFil())
				&& (this.getGuiCod() == castOther.getGuiCod());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getGuiEmp();
		result = 37 * result + this.getGuiFil();
		result = 37 * result + this.getGuiCod();
		return result;
	}

}
