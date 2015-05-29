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

	
	
	/*@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn
	public Colaborador getColaborador() {
		return this.colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}*/
	
	@Column(name = "SENHA", length = 15)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	/*@Column(name = "USU_EMP", nullable = false, length = 1)
	public char getUsuEmp() {
		return this.usuEmp;
	}

	public void setUsuEmp(char usuEmp) {
		this.usuEmp = usuEmp;
	}*/
	
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
	
	/*@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}*/
	
	
/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	public Set<TelaWebUsuario> getTelaWebUsuarios() {
		return this.telaWebUsuarios;
	}

	public void setTelaWebUsuarios(Set<TelaWebUsuario> telaWebUsuarios) {
		this.telaWebUsuarios = telaWebUsuarios;
	}*/
}
