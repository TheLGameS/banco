package br.ufsc.creche.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "MUNICIPIO")
public class Municipio implements java.io.Serializable {

	private static final long serialVersionUID = -6274570515097126970L;

	private Integer munCod;
	private Estado estado;
	private String munNom;
	private String munCep;
	private String munIbg;
	private Integer munHab = 3;
	private Set<Colaborador> colaboradors = new HashSet<Colaborador>(0);

	@Id
	@Column(name = "MUN_COD", unique = true, nullable = false)
	public Integer getMunCod() {
		return this.munCod;
	}

	public void setMunCod(Integer munCod) {
		this.munCod = munCod;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MUN_EST", nullable = false)
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Column(name = "MUN_NOM", nullable = false, length = 40)
	public String getMunNom() {
		return this.munNom;
	}

	public void setMunNom(String munNom) {
		this.munNom = munNom;
	}

	@Column(name = "MUN_CEP", length = 8)
	public String getMunCep() {
		return this.munCep;
	}

	public void setMunCep(String munCep) {
		this.munCep = munCep;
	}

	@Column(name = "MUN_IBG", length = 7)
	public String getMunIbg() {
		return this.munIbg;
	}

	public void setMunIbg(String munIbg) {
		this.munIbg = munIbg;
	}

	@Column(name = "MUN_HAB", nullable = false)
	public Integer getMunHab() {
		return this.munHab;
	}

	public void setMunHab(Integer munHab) {
		this.munHab = munHab;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "municipio")
	public Set<Colaborador> getColaboradors() {
		return this.colaboradors;
	}

	public void setColaboradors(Set<Colaborador> colaboradors) {
		this.colaboradors = colaboradors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((colaboradors == null) ? 0 : colaboradors.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((munCep == null) ? 0 : munCep.hashCode());
		result = prime * result + ((munCod == null) ? 0 : munCod.hashCode());
		result = prime * result + ((munHab == null) ? 0 : munHab.hashCode());
		result = prime * result + ((munIbg == null) ? 0 : munIbg.hashCode());
		result = prime * result + ((munNom == null) ? 0 : munNom.hashCode());
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
		if (!(obj instanceof Municipio)) {
			return false;
		}
		Municipio other = (Municipio) obj;
		if (colaboradors == null) {
			if (other.colaboradors != null) {
				return false;
			}
		} else if (!colaboradors.equals(other.colaboradors)) {
			return false;
		}
		if (estado == null) {
			if (other.estado != null) {
				return false;
			}
		} else if (!estado.equals(other.estado)) {
			return false;
		}
		if (munCep == null) {
			if (other.munCep != null) {
				return false;
			}
		} else if (!munCep.equals(other.munCep)) {
			return false;
		}
		if (munCod == null) {
			if (other.munCod != null) {
				return false;
			}
		} else if (!munCod.equals(other.munCod)) {
			return false;
		}
		if (munHab == null) {
			if (other.munHab != null) {
				return false;
			}
		} else if (!munHab.equals(other.munHab)) {
			return false;
		}
		if (munIbg == null) {
			if (other.munIbg != null) {
				return false;
			}
		} else if (!munIbg.equals(other.munIbg)) {
			return false;
		}
		if (munNom == null) {
			if (other.munNom != null) {
				return false;
			}
		} else if (!munNom.equals(other.munNom)) {
			return false;
		}
		return true;
	}








}
