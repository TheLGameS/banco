package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Artigo generated by hbm2java
 */
@Entity
@Table(name = "ARTIGO")
public class Artigo implements java.io.Serializable {

	private static final long serialVersionUID = 6467668374411611706L;
	private Integer atgCod;
	private String atgNom;
	private String atgUmp;
	private String atgUmd;
	private String atgCor;
	/* private Set<Empresapautamodeloartigo> empresapautamodeloartigos = new HashSet<Empresapautamodeloartigo>(0);
	private Set<Estoqueminimo> estoqueminimos = new HashSet<Estoqueminimo>(0);
	private Set<Operacaopautaproduto> operacaopautaprodutos = new HashSet<Operacaopautaproduto>(0);
	private Set<Modeloartigo> modeloartigos = new HashSet<Modeloartigo>(0); */
	private Set<Produto> produtos = new HashSet<Produto>(0);

	public Artigo() {
	}

	public Artigo(Integer atgCod, String atgNom, String atgUmp, String atgUmd) {
		this.atgCod = atgCod;
		this.atgNom = atgNom;
		this.atgUmp = atgUmp;
		this.atgUmd = atgUmd;
	}

/*	public Artigo(Integer atgCod, String atgNom, String atgUmp, String atgUmd,
			String atgCor,
			Set<Empresapautamodeloartigo> empresapautamodeloartigos,
			Set<Estoqueminimo> estoqueminimos,
			Set<Operacaopautaproduto> operacaopautaprodutos,
			Set<Produto> produtos, Set<Modeloartigo> modeloartigos) {
		this.atgCod = atgCod;
		this.atgNom = atgNom;
		this.atgUmp = atgUmp;
		this.atgUmd = atgUmd;
		this.atgCor = atgCor;
		this.empresapautamodeloartigos = empresapautamodeloartigos;
		this.estoqueminimos = estoqueminimos;
		this.operacaopautaprodutos = operacaopautaprodutos;
		this.produtos = produtos;
		this.modeloartigos = modeloartigos;
	}*/

	@Id
	@Column(name = "ATG_COD", unique = true, nullable = false)
	public Integer getAtgCod() {
		return this.atgCod;
	}

	public void setAtgCod(Integer atgCod) {
		this.atgCod = atgCod;
	}

	@Column(name = "ATG_NOM", nullable = false, length = 30)
	public String getAtgNom() {
		return this.atgNom;
	}

	public void setAtgNom(String atgNom) {
		this.atgNom = atgNom;
	}

	@Column(name = "ATG_UMP", nullable = false, length = 1)
	public String getAtgUmp() {
		return this.atgUmp;
	}

	public void setAtgUmp(String atgUmp) {
		this.atgUmp = atgUmp;
	}

	@Column(name = "ATG_UMD", nullable = false, length = 1)
	public String getAtgUmd() {
		return this.atgUmd;
	}

	public void setAtgUmd(String atgUmd) {
		this.atgUmd = atgUmd;
	}

	@Column(name = "ATG_COR", length = 80)
	public String getAtgCor() {
		return this.atgCor;
	}

	public void setAtgCor(String atgCor) {
		this.atgCor = atgCor;
	}

/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "artigo")
	public Set<Empresapautamodeloartigo> getEmpresapautamodeloartigos() {
		return this.empresapautamodeloartigos;
	}

	public void setEmpresapautamodeloartigos(
			Set<Empresapautamodeloartigo> empresapautamodeloartigos) {
		this.empresapautamodeloartigos = empresapautamodeloartigos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "artigo")
	public Set<Estoqueminimo> getEstoqueminimos() {
		return this.estoqueminimos;
	}

	public void setEstoqueminimos(Set<Estoqueminimo> estoqueminimos) {
		this.estoqueminimos = estoqueminimos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "artigo")
	public Set<Operacaopautaproduto> getOperacaopautaprodutos() {
		return this.operacaopautaprodutos;
	}

	public void setOperacaopautaprodutos(
			Set<Operacaopautaproduto> operacaopautaprodutos) {
		this.operacaopautaprodutos = operacaopautaprodutos;
	}*/

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "artigo")
	public Set<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}

/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "artigo")
	public Set<Modeloartigo> getModeloartigos() {
		return this.modeloartigos;
	}

	public void setModeloartigos(Set<Modeloartigo> modeloartigos) {
		this.modeloartigos = modeloartigos;
	}*/

}
