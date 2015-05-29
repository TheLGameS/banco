package br.ufsc.creche.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 2471765773806278661L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cdUsuario;
	private String senha;
	private String status;
	private String permissao;
	private String login;

	public Usuario() {
		
	}
	
	@Column(name = "CD_USUARIO", unique = true, nullable = false)
	public Integer getCdUsuario() {
		return this.cdUsuario;
	}

	public void setCdUsuario(Integer cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	@Column(name = "SENHA", length = 15)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "PERMISSAO", length = 50)
	public String getPermissao() {
		return this.permissao;
	}
	
	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}
	
	@Column(name = "LOGIN", length = 50)
	public String getLogin() {
		return this.login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cdUsuario == null) ? 0 : cdUsuario.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((permissao == null) ? 0 : permissao.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		if (cdUsuario == null) {
			if (other.cdUsuario != null) {
				return false;
			}
		} else if (!cdUsuario.equals(other.cdUsuario)) {
			return false;
		}
		if (login == null) {
			if (other.login != null) {
				return false;
			}
		} else if (!login.equals(other.login)) {
			return false;
		}
		if (permissao == null) {
			if (other.permissao != null) {
				return false;
			}
		} else if (!permissao.equals(other.permissao)) {
			return false;
		}
		if (senha == null) {
			if (other.senha != null) {
				return false;
			}
		} else if (!senha.equals(other.senha)) {
			return false;
		}
		if (status == null) {
			if (other.status != null) {
				return false;
			}
		} else if (!status.equals(other.status)) {
			return false;
		}
		return true;
	}
	
}
