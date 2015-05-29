package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Colaborador generated by hbm2java
 */
@Entity
@Table(name = "COLABORADOR")
public class Colaborador implements java.io.Serializable {

	private static final long serialVersionUID = 8599887387330806414L;

	private Integer colCod;
	//	private AtividadeComercial atividadeComercial;
	private Municipio municipio;
	private String colApe;
	private String colRaz;
	private String colNmf;
	private String colTip;
	private String colInf;
	private String colIne;
	private String colInm;
	private Date colDti;
	private String colEnd;
	private Integer colNro;
	private String colCpl;
	private String colBai;
	private String colCep;
	private String colDdd;
	private String colFon;
	private String colDdf;
	private String colFax;
	private String colDdc;
	private String colCel;
	private String colEma;
	private String colWeb;
	private String colCon;
	private String colCxp;
	private Integer colRam;
	private String colIcm;
	private String colTie;
	private String colCnh;
	private Date colDch;
	private String colPis;
	private Date colDtu;
	private String colIsf;
	private Integer colPai;
	private String colOsf;
	//	private Usuario usuario;
	//	private Set<Enviocolaboradorfilial> enviocolaboradorfilials = new HashSet<Enviocolaboradorfilial>(0);
	//	private Motorista motorista;
	//	private Set<ColaboradorContato> colaboradorContatos = new HashSet<ColaboradorContato>(0);
	//	private Set<ColaboradorEmpresa> colaboradorEmpresas = new HashSet<ColaboradorEmpresa>(0);
	//	private Set<ColaboradorConta> colaboradorContas = new HashSet<ColaboradorConta>(0);
	//	private Set<Veiculo> veiculos = new HashSet<Veiculo>(0);	



	@Id
	@Column(name = "COL_COD", unique = true, nullable = false)
	public Integer getColCod() {
		return this.colCod;
	}




	public void setColCod(Integer colCod) {
		this.colCod = colCod;
	}

	/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COL_ATC")
	public AtividadeComercial getAtividadecomercial() {
		return this.atividadeComercial;
	}

	public void setAtividadecomercial(AtividadeComercial atividadeComercial) {
		this.atividadeComercial = atividadeComercial;
	}*/

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "COL_MUN", nullable = false)
	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@Column(name = "COL_APE", length = 15)
	public String getColApe() {
		return this.colApe;
	}

	public void setColApe(String colApe) {
		this.colApe = colApe;
	}

	@Column(name = "COL_RAZ", nullable = false, length = 50)
	public String getColRaz() {
		return this.colRaz;
	}

	public void setColRaz(String colRaz) {
		this.colRaz = colRaz;
	}

	@Column(name = "COL_NMF", length = 50)
	public String getColNmf() {
		return this.colNmf;
	}

	public void setColNmf(String colNmf) {
		this.colNmf = colNmf;
	}

	@Column(name = "COL_TIP", nullable = false, length = 4)
	public String getColTip() {
		return this.colTip;
	}

	public void setColTip(String colTip) {
		this.colTip = colTip;
	}

	@Column(name = "COL_INF", length = 14)
	public String getColInf() {
		return this.colInf;
	}

	public void setColInf(String colInf) {
		this.colInf = colInf;
	}

	@Column(name = "COL_INE", length = 20)
	public String getColIne() {
		return this.colIne;
	}

	public void setColIne(String colIne) {
		this.colIne = colIne;
	}

	@Column(name = "COL_INM", length = 20)
	public String getColInm() {
		return this.colInm;
	}

	public void setColInm(String colInm) {
		this.colInm = colInm;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "COL_DTI", length = 10)
	public Date getColDti() {
		return this.colDti;
	}

	public void setColDti(Date colDti) {
		this.colDti = colDti;
	}

	@Column(name = "COL_END", nullable = false, length = 40)
	public String getColEnd() {
		return this.colEnd;
	}

	public void setColEnd(String colEnd) {
		this.colEnd = colEnd;
	}

	@Column(name = "COL_NRO")
	public Integer getColNro() {
		return this.colNro;
	}

	public void setColNro(Integer colNro) {
		this.colNro = colNro;
	}

	@Column(name = "COL_CPL", length = 20)
	public String getColCpl() {
		return this.colCpl;
	}

	public void setColCpl(String colCpl) {
		this.colCpl = colCpl;
	}

	@Column(name = "COL_BAI", length = 20)
	public String getColBai() {
		return this.colBai;
	}

	public void setColBai(String colBai) {
		this.colBai = colBai;
	}

	@Column(name = "COL_CEP", nullable = false, length = 8)
	public String getColCep() {
		return this.colCep;
	}

	public void setColCep(String colCep) {
		this.colCep = colCep;
	}

	@Column(name = "COL_DDD", length = 2)
	public String getColDdd() {
		return this.colDdd;
	}

	public void setColDdd(String colDdd) {
		this.colDdd = colDdd;
	}

	@Column(name = "COL_FON", length = 8)
	public String getColFon() {
		return this.colFon;
	}

	public void setColFon(String colFon) {
		this.colFon = colFon;
	}

	@Column(name = "COL_DDF", length = 2)
	public String getColDdf() {
		return this.colDdf;
	}

	public void setColDdf(String colDdf) {
		this.colDdf = colDdf;
	}

	@Column(name = "COL_FAX", length = 8)
	public String getColFax() {
		return this.colFax;
	}

	public void setColFax(String colFax) {
		this.colFax = colFax;
	}

	@Column(name = "COL_DDC", length = 2)
	public String getColDdc() {
		return this.colDdc;
	}

	public void setColDdc(String colDdc) {
		this.colDdc = colDdc;
	}

	@Column(name = "COL_CEL", length = 9)
	public String getColCel() {
		return this.colCel;
	}

	public void setColCel(String colCel) {
		this.colCel = colCel;
	}

	@Column(name = "COL_EMA", length = 50)
	public String getColEma() {
		return this.colEma;
	}

	public void setColEma(String colEma) {
		this.colEma = colEma;
	}

	@Column(name = "COL_WEB", length = 50)
	public String getColWeb() {
		return this.colWeb;
	}

	public void setColWeb(String colWeb) {
		this.colWeb = colWeb;
	}

	@Column(name = "COL_CON", length = 50)
	public String getColCon() {
		return this.colCon;
	}

	public void setColCon(String colCon) {
		this.colCon = colCon;
	}

	@Column(name = "COL_CXP", length = 15)
	public String getColCxp() {
		return this.colCxp;
	}

	public void setColCxp(String colCxp) {
		this.colCxp = colCxp;
	}

	@Column(name = "COL_RAM")
	public Integer getColRam() {
		return this.colRam;
	}

	public void setColRam(Integer colRam) {
		this.colRam = colRam;
	}

	@Column(name = "COL_ICM", nullable = false, length = 1)
	public String getColIcm() {
		return this.colIcm;
	}

	public void setColIcm(String colIcm) {
		this.colIcm = colIcm;
	}

	@Column(name = "COL_TIE", length = 11)
	public String getColTie() {
		return this.colTie;
	}

	public void setColTie(String colTie) {
		this.colTie = colTie;
	}

	@Column(name = "COL_CNH", length = 11)
	public String getColCnh() {
		return this.colCnh;
	}

	public void setColCnh(String colCnh) {
		this.colCnh = colCnh;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "COL_DCH", length = 10)
	public Date getColDch() {
		return this.colDch;
	}

	public void setColDch(Date colDch) {
		this.colDch = colDch;
	}

	@Column(name = "COL_PIS", length = 11)
	public String getColPis() {
		return this.colPis;
	}

	public void setColPis(String colPis) {
		this.colPis = colPis;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COL_DTU", nullable = false, length = 19)
	public Date getColDtu() {
		return this.colDtu;
	}

	public void setColDtu(Date colDtu) {
		this.colDtu = colDtu;
	}

	@Column(name = "COL_ISF", length = 9)
	public String getColIsf() {
		return this.colIsf;
	}

	public void setColIsf(String colIsf) {
		this.colIsf = colIsf;
	}

	@Column(name = "COL_PAI", nullable = false)
	public Integer getColPai() {
		return this.colPai;
	}

	public void setColPai(Integer colPai) {
		this.colPai = colPai;
	}

	@Column(name = "COL_OSF", nullable = false, length = 1)
	public String getColOsf() {
		return this.colOsf;
	}

	public void setColOsf(String colOsf) {
		this.colOsf = colOsf;
	}

	/*	@OneToOne(fetch = FetchType.LAZY, mappedBy = "colaborador")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}*/

	//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "colaborador")
	//	public Set<Enviocolaboradorfilial> getEnviocolaboradorfilials() {
	//		return this.enviocolaboradorfilials;
	//	}
	//
	//	public void setEnviocolaboradorfilials(
	//			Set<Enviocolaboradorfilial> enviocolaboradorfilials) {
	//		this.enviocolaboradorfilials = enviocolaboradorfilials;
	//	}

	/*	@OneToOne(fetch = FetchType.LAZY, mappedBy = "colaborador")
	public Motorista getMotorista() {
		return this.motorista;
	}

	public void setMotorista(Motorista motorista) {
		this.motorista = motorista;
	}*/

	//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "colaborador")
	//	public Set<ColaboradorContato> getColaboradorContatos() {
	//		return this.colaboradorContatos;
	//	}
	//
	//	public void setColaboradorContatos(
	//			Set<ColaboradorContato> colaboradorContatos) {
	//		this.colaboradorContatos = colaboradorContatos;
	//	}
	//
	
	
	/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "colaborador")
		public Set<ColaboradorEmpresa> getColaboradorEmpresas() {
			return this.colaboradorEmpresas;
		}
	
		public void setColaboradorEmpresas(
				Set<ColaboradorEmpresa> colaboradorEmpresas) {
			this.colaboradorEmpresas = colaboradorEmpresas;
		}*/
		
		
	//
	//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "colaborador")
	//	public Set<ColaboradorConta> getColaboradorContas() {
	//		return this.colaboradorContas;
	//	}
	//
	//	public void setColaboradorContas(Set<ColaboradorConta> colaboradorContas) {
	//		this.colaboradorContas = colaboradorContas;
	//	}

	/*@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "COLABORADORVEICULO", joinColumns = { @JoinColumn(name = "CLV_COL", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "CLV_PLC", nullable = false, updatable = false) })
	public Set<Veiculo> getVeiculos() {
		return this.veiculos;
	}

	public void setVeiculos(Set<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}*/

}