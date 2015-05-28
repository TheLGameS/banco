package br.ufsc.creche.model;

// Generated 19/03/2015 10:22:22 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TelaWebUsuarioId generated by hbm2java
 */
@Embeddable
public class TelaWebUsuarioId implements java.io.Serializable {

	private static final long serialVersionUID = -1104181246236289238L;
	private Integer twuUsu;
	private String twuFrm;
	private String twuCpo;

	public TelaWebUsuarioId() {
	}

	public TelaWebUsuarioId(Integer twuUsu, String twuFrm, String twuCpo) {
		this.twuUsu = twuUsu;
		this.twuFrm = twuFrm;
		this.twuCpo = twuCpo;
	}

	@Column(name = "TWU_USU", nullable = false)
	public Integer getTwuUsu() {
		return this.twuUsu;
	}

	public void setTwuUsu(Integer twuUsu) {
		this.twuUsu = twuUsu;
	}

	@Column(name = "TWU_FRM", nullable = false, length = 40)
	public String getTwuFrm() {
		return this.twuFrm;
	}

	public void setTwuFrm(String twuFrm) {
		this.twuFrm = twuFrm;
	}

	@Column(name = "TWU_CPO", nullable = false, length = 20)
	public String getTwuCpo() {
		return this.twuCpo;
	}

	public void setTwuCpo(String twuCpo) {
		this.twuCpo = twuCpo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TelaWebUsuarioId))
			return false;
		TelaWebUsuarioId castOther = (TelaWebUsuarioId) other;

		return (this.getTwuUsu() == castOther.getTwuUsu())
				&& ((this.getTwuFrm() == castOther.getTwuFrm()) || (this
						.getTwuFrm() != null && castOther.getTwuFrm() != null && this
						.getTwuFrm().equals(castOther.getTwuFrm())))
				&& ((this.getTwuCpo() == castOther.getTwuCpo()) || (this
						.getTwuCpo() != null && castOther.getTwuCpo() != null && this
						.getTwuCpo().equals(castOther.getTwuCpo())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getTwuUsu();
		result = 37 * result
				+ (getTwuFrm() == null ? 0 : this.getTwuFrm().hashCode());
		result = 37 * result
				+ (getTwuCpo() == null ? 0 : this.getTwuCpo().hashCode());
		return result;
	}

}
