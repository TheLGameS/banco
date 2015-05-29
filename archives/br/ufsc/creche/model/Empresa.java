package br.ufsc.creche.model;

// Generated 22/12/2014 15:06:02 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
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
 * Empresa generated by hbm2java
 */
@Entity
@Table(name = "EMPRESA")
public class Empresa implements java.io.Serializable {
	
	private static final long serialVersionUID = -975827147897382687L;
	
	private int empCod;
	private Municipio municipioByEmpMun;
	private Municipio municipioByEmpRmu;
	private String empApe;
	private String empRaz;
	private String empNmf;
	private String empEnd;
	private Integer empNro;
	private String empCpl;
	private String empBai;
	private String empCep;
	private String empDdd;
	private String empFon;
	private String empDdf;
	private String empFax;
	private String empDdc;
	private String empCel;
	private String empTip;
	private String empInf;
	private String empIne;
	private String empInm;
	private Date empDti;
	private Date empDtf;
	private String empEma;
	private String empWeb;
	private String empRno;
	private String empRca;
	private String empRcp;
	private String empRrg;
	private String empRen;
	private Integer empRnu;
	private String empRco;
	private String empRba;
	private String empRce;
	private String empRdd;
	private String empRfo;
	private Integer empRef;
	private String empRes;
	private String empPrs;
	private String empMas;
	private Integer empRpm;
	private Integer empRpj;
	private Integer empRpd;
	private Integer empPpm;
	private Integer empPpj;
	private Integer empPpd;
	private Integer empCpd;
	private char empTcp;
	private char empCmp;
	private Integer empNdm;
	private char empCap;
	private Integer empNda;
	private char empCcp;
	private Integer empNdc;
	private int empDpr;
	private char empTcm;
	private int empDmp;
	private Integer empDmg;
	private int empDeq;
	private char empDfp;
	private char empCoc;
	private char empAmc;
	private char empAma;
	private char empUda;
	private char empTcg;
	private Integer empDms;
	private Integer empDmc;
	private Integer empDmv;
	private char empCgm;
	private char empCsm;
	private char empCcm;
	private char empCvm;
	private char empTpr;
	private char empTcb;
	private char empDft;
	private Integer empPcc;
	private Integer empPcf;
	private String empCcv;
	private String empIsf;
	private BigDecimal empSpn;
	private BigDecimal empCmv;
	private BigDecimal empCmg;
	private BigDecimal empMgl;
	private BigDecimal empDpf;
	private char empDma;
	private char empGsm;
	private int empLgm;
	private char empMpac;
	private Integer empPbj;
	private Integer empPbd;
	private Integer empPbi;
	private char empUcmc;
	private BigDecimal empIcm;
	private BigDecimal empIpc;
	private BigDecimal empFre;
	private BigDecimal empOut;
	private Integer empPcr;
	private BigDecimal empTxf;
	private String empPea;
	private Integer empNea;
//	private Set<Planodecontas> planodecontases = new HashSet<Planodecontas>(0);
//	private Set<Remessaproducao> remessaproducaos = new HashSet<Remessaproducao>(0);
//	private Set<Tributacao> tributacaos = new HashSet<Tributacao>(0);
//	private Set<Exercicio> exercicios = new HashSet<Exercicio>(0);
//	private Set<Estoqueminimo> estoqueminimos = new HashSet<Estoqueminimo>(0);
//	private Set<Linhaproducao> linhaproducaos = new HashSet<Linhaproducao>(0);
	private Set<Filial> filials = new HashSet<Filial>(0);
//	private Set<Operacao> operacaos = new HashSet<Operacao>(0);
//	private Set<Produto> produtos = new HashSet<Produto>(0);
//	private Set<Avisodebitohistorico> avisodebitohistoricos = new HashSet<Avisodebitohistorico>(0);
//	private Set<Categoriafiscal> categoriafiscals = new HashSet<Categoriafiscal>(0);
//	private Set<Modelo> modelos = new HashSet<Modelo>(0);
	private Set<ColaboradorEmpresa> colaboradorEmpresas = new HashSet<ColaboradorEmpresa>(0);
//	private Set<Categoriafinanceira> categoriafinanceiras = new HashSet<Categoriafinanceira>(0);
//	private Set<Remessaproducaosimulacao> remessaproducaosimulacaos = new HashSet<Remessaproducaosimulacao>(0);
	private Set<EmpresaPauta> empresaPautas = new HashSet<EmpresaPauta>(0);
//	private Set<Pedidoproducao> pedidoproducaos = new HashSet<Pedidoproducao>(0);
//	private Set<Dre> dres = new HashSet<Dre>(0);
	

	@Id
	@Column(name = "EMP_COD", unique = true, nullable = false)
	public int getEmpCod() {
		return this.empCod;
	}

	public void setEmpCod(int empCod) {
		this.empCod = empCod;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_MUN", nullable = false)
	public Municipio getMunicipioByEmpMun() {
		return this.municipioByEmpMun;
	}

	public void setMunicipioByEmpMun(Municipio municipioByEmpMun) {
		this.municipioByEmpMun = municipioByEmpMun;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "EMP_RMU")
	public Municipio getMunicipioByEmpRmu() {
		return this.municipioByEmpRmu;
	}

	public void setMunicipioByEmpRmu(Municipio municipioByEmpRmu) {
		this.municipioByEmpRmu = municipioByEmpRmu;
	}

	@Column(name = "EMP_APE", nullable = false, length = 15)
	public String getEmpApe() {
		return this.empApe;
	}

	public void setEmpApe(String empApe) {
		this.empApe = empApe;
	}

	@Column(name = "EMP_RAZ", nullable = false, length = 50)
	public String getEmpRaz() {
		return this.empRaz;
	}

	public void setEmpRaz(String empRaz) {
		this.empRaz = empRaz;
	}

	@Column(name = "EMP_NMF", length = 50)
	public String getEmpNmf() {
		return this.empNmf;
	}

	public void setEmpNmf(String empNmf) {
		this.empNmf = empNmf;
	}

	@Column(name = "EMP_END", nullable = false, length = 40)
	public String getEmpEnd() {
		return this.empEnd;
	}

	public void setEmpEnd(String empEnd) {
		this.empEnd = empEnd;
	}

	@Column(name = "EMP_NRO")
	public Integer getEmpNro() {
		return this.empNro;
	}

	public void setEmpNro(Integer empNro) {
		this.empNro = empNro;
	}

	@Column(name = "EMP_CPL", length = 20)
	public String getEmpCpl() {
		return this.empCpl;
	}

	public void setEmpCpl(String empCpl) {
		this.empCpl = empCpl;
	}

	@Column(name = "EMP_BAI", length = 20)
	public String getEmpBai() {
		return this.empBai;
	}

	public void setEmpBai(String empBai) {
		this.empBai = empBai;
	}

	@Column(name = "EMP_CEP", nullable = false, length = 8)
	public String getEmpCep() {
		return this.empCep;
	}

	public void setEmpCep(String empCep) {
		this.empCep = empCep;
	}

	@Column(name = "EMP_DDD", length = 2)
	public String getEmpDdd() {
		return this.empDdd;
	}

	public void setEmpDdd(String empDdd) {
		this.empDdd = empDdd;
	}

	@Column(name = "EMP_FON", length = 8)
	public String getEmpFon() {
		return this.empFon;
	}

	public void setEmpFon(String empFon) {
		this.empFon = empFon;
	}

	@Column(name = "EMP_DDF", length = 2)
	public String getEmpDdf() {
		return this.empDdf;
	}

	public void setEmpDdf(String empDdf) {
		this.empDdf = empDdf;
	}

	@Column(name = "EMP_FAX", length = 8)
	public String getEmpFax() {
		return this.empFax;
	}

	public void setEmpFax(String empFax) {
		this.empFax = empFax;
	}

	@Column(name = "EMP_DDC", length = 2)
	public String getEmpDdc() {
		return this.empDdc;
	}

	public void setEmpDdc(String empDdc) {
		this.empDdc = empDdc;
	}

	@Column(name = "EMP_CEL", length = 9)
	public String getEmpCel() {
		return this.empCel;
	}

	public void setEmpCel(String empCel) {
		this.empCel = empCel;
	}

	@Column(name = "EMP_TIP", nullable = false, length = 4)
	public String getEmpTip() {
		return this.empTip;
	}

	public void setEmpTip(String empTip) {
		this.empTip = empTip;
	}

	@Column(name = "EMP_INF", nullable = false, length = 14)
	public String getEmpInf() {
		return this.empInf;
	}

	public void setEmpInf(String empInf) {
		this.empInf = empInf;
	}

	@Column(name = "EMP_INE", length = 20)
	public String getEmpIne() {
		return this.empIne;
	}

	public void setEmpIne(String empIne) {
		this.empIne = empIne;
	}

	@Column(name = "EMP_INM", length = 20)
	public String getEmpInm() {
		return this.empInm;
	}

	public void setEmpInm(String empInm) {
		this.empInm = empInm;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EMP_DTI", length = 10)
	public Date getEmpDti() {
		return this.empDti;
	}

	public void setEmpDti(Date empDti) {
		this.empDti = empDti;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EMP_DTF", length = 10)
	public Date getEmpDtf() {
		return this.empDtf;
	}

	public void setEmpDtf(Date empDtf) {
		this.empDtf = empDtf;
	}

	@Column(name = "EMP_EMA", length = 50)
	public String getEmpEma() {
		return this.empEma;
	}

	public void setEmpEma(String empEma) {
		this.empEma = empEma;
	}

	@Column(name = "EMP_WEB", length = 50)
	public String getEmpWeb() {
		return this.empWeb;
	}

	public void setEmpWeb(String empWeb) {
		this.empWeb = empWeb;
	}

	@Column(name = "EMP_RNO", length = 40)
	public String getEmpRno() {
		return this.empRno;
	}

	public void setEmpRno(String empRno) {
		this.empRno = empRno;
	}

	@Column(name = "EMP_RCA", length = 20)
	public String getEmpRca() {
		return this.empRca;
	}

	public void setEmpRca(String empRca) {
		this.empRca = empRca;
	}

	@Column(name = "EMP_RCP", length = 11)
	public String getEmpRcp() {
		return this.empRcp;
	}

	public void setEmpRcp(String empRcp) {
		this.empRcp = empRcp;
	}

	@Column(name = "EMP_RRG", length = 15)
	public String getEmpRrg() {
		return this.empRrg;
	}

	public void setEmpRrg(String empRrg) {
		this.empRrg = empRrg;
	}

	@Column(name = "EMP_REN", length = 40)
	public String getEmpRen() {
		return this.empRen;
	}

	public void setEmpRen(String empRen) {
		this.empRen = empRen;
	}

	@Column(name = "EMP_RNU")
	public Integer getEmpRnu() {
		return this.empRnu;
	}

	public void setEmpRnu(Integer empRnu) {
		this.empRnu = empRnu;
	}

	@Column(name = "EMP_RCO", length = 20)
	public String getEmpRco() {
		return this.empRco;
	}

	public void setEmpRco(String empRco) {
		this.empRco = empRco;
	}

	@Column(name = "EMP_RBA", length = 20)
	public String getEmpRba() {
		return this.empRba;
	}

	public void setEmpRba(String empRba) {
		this.empRba = empRba;
	}

	@Column(name = "EMP_RCE", length = 8)
	public String getEmpRce() {
		return this.empRce;
	}

	public void setEmpRce(String empRce) {
		this.empRce = empRce;
	}

	@Column(name = "EMP_RDD", length = 2)
	public String getEmpRdd() {
		return this.empRdd;
	}

	public void setEmpRdd(String empRdd) {
		this.empRdd = empRdd;
	}

	@Column(name = "EMP_RFO", length = 8)
	public String getEmpRfo() {
		return this.empRfo;
	}

	public void setEmpRfo(String empRfo) {
		this.empRfo = empRfo;
	}

	@Column(name = "EMP_REF")
	public Integer getEmpRef() {
		return this.empRef;
	}

	public void setEmpRef(Integer empRef) {
		this.empRef = empRef;
	}

	@Column(name = "EMP_RES", length = 13)
	public String getEmpRes() {
		return this.empRes;
	}

	public void setEmpRes(String empRes) {
		this.empRes = empRes;
	}

	@Column(name = "EMP_PRS", length = 14)
	public String getEmpPrs() {
		return this.empPrs;
	}

	public void setEmpPrs(String empPrs) {
		this.empPrs = empPrs;
	}

	@Column(name = "EMP_MAS", nullable = false, length = 30)
	public String getEmpMas() {
		return this.empMas;
	}

	public void setEmpMas(String empMas) {
		this.empMas = empMas;
	}

	@Column(name = "EMP_RPM")
	public Integer getEmpRpm() {
		return this.empRpm;
	}

	public void setEmpRpm(Integer empRpm) {
		this.empRpm = empRpm;
	}

	@Column(name = "EMP_RPJ")
	public Integer getEmpRpj() {
		return this.empRpj;
	}

	public void setEmpRpj(Integer empRpj) {
		this.empRpj = empRpj;
	}

	@Column(name = "EMP_RPD")
	public Integer getEmpRpd() {
		return this.empRpd;
	}

	public void setEmpRpd(Integer empRpd) {
		this.empRpd = empRpd;
	}

	@Column(name = "EMP_PPM")
	public Integer getEmpPpm() {
		return this.empPpm;
	}

	public void setEmpPpm(Integer empPpm) {
		this.empPpm = empPpm;
	}

	@Column(name = "EMP_PPJ")
	public Integer getEmpPpj() {
		return this.empPpj;
	}

	public void setEmpPpj(Integer empPpj) {
		this.empPpj = empPpj;
	}

	@Column(name = "EMP_PPD")
	public Integer getEmpPpd() {
		return this.empPpd;
	}

	public void setEmpPpd(Integer empPpd) {
		this.empPpd = empPpd;
	}

	@Column(name = "EMP_CPD")
	public Integer getEmpCpd() {
		return this.empCpd;
	}

	public void setEmpCpd(Integer empCpd) {
		this.empCpd = empCpd;
	}

	@Column(name = "EMP_TCP", nullable = false, length = 1)
	public char getEmpTcp() {
		return this.empTcp;
	}

	public void setEmpTcp(char empTcp) {
		this.empTcp = empTcp;
	}

	@Column(name = "EMP_CMP", nullable = false, length = 1)
	public char getEmpCmp() {
		return this.empCmp;
	}

	public void setEmpCmp(char empCmp) {
		this.empCmp = empCmp;
	}

	@Column(name = "EMP_NDM")
	public Integer getEmpNdm() {
		return this.empNdm;
	}

	public void setEmpNdm(Integer empNdm) {
		this.empNdm = empNdm;
	}

	@Column(name = "EMP_CAP", nullable = false, length = 1)
	public char getEmpCap() {
		return this.empCap;
	}

	public void setEmpCap(char empCap) {
		this.empCap = empCap;
	}

	@Column(name = "EMP_NDA")
	public Integer getEmpNda() {
		return this.empNda;
	}

	public void setEmpNda(Integer empNda) {
		this.empNda = empNda;
	}

	@Column(name = "EMP_CCP", nullable = false, length = 1)
	public char getEmpCcp() {
		return this.empCcp;
	}

	public void setEmpCcp(char empCcp) {
		this.empCcp = empCcp;
	}

	@Column(name = "EMP_NDC")
	public Integer getEmpNdc() {
		return this.empNdc;
	}

	public void setEmpNdc(Integer empNdc) {
		this.empNdc = empNdc;
	}

	@Column(name = "EMP_DPR", nullable = false)
	public int getEmpDpr() {
		return this.empDpr;
	}

	public void setEmpDpr(int empDpr) {
		this.empDpr = empDpr;
	}

	@Column(name = "EMP_TCM", nullable = false, length = 1)
	public char getEmpTcm() {
		return this.empTcm;
	}

	public void setEmpTcm(char empTcm) {
		this.empTcm = empTcm;
	}

	@Column(name = "EMP_DMP", nullable = false)
	public int getEmpDmp() {
		return this.empDmp;
	}

	public void setEmpDmp(int empDmp) {
		this.empDmp = empDmp;
	}

	@Column(name = "EMP_DMG")
	public Integer getEmpDmg() {
		return this.empDmg;
	}

	public void setEmpDmg(Integer empDmg) {
		this.empDmg = empDmg;
	}

	@Column(name = "EMP_DEQ", nullable = false)
	public int getEmpDeq() {
		return this.empDeq;
	}

	public void setEmpDeq(int empDeq) {
		this.empDeq = empDeq;
	}

	@Column(name = "EMP_DFP", nullable = false, length = 1)
	public char getEmpDfp() {
		return this.empDfp;
	}

	public void setEmpDfp(char empDfp) {
		this.empDfp = empDfp;
	}

	@Column(name = "EMP_COC", nullable = false, length = 1)
	public char getEmpCoc() {
		return this.empCoc;
	}

	public void setEmpCoc(char empCoc) {
		this.empCoc = empCoc;
	}

	@Column(name = "EMP_AMC", nullable = false, length = 1)
	public char getEmpAmc() {
		return this.empAmc;
	}

	public void setEmpAmc(char empAmc) {
		this.empAmc = empAmc;
	}

	@Column(name = "EMP_AMA", nullable = false, length = 1)
	public char getEmpAma() {
		return this.empAma;
	}

	public void setEmpAma(char empAma) {
		this.empAma = empAma;
	}

	@Column(name = "EMP_UDA", nullable = false, length = 1)
	public char getEmpUda() {
		return this.empUda;
	}

	public void setEmpUda(char empUda) {
		this.empUda = empUda;
	}

	@Column(name = "EMP_TCG", nullable = false, length = 1)
	public char getEmpTcg() {
		return this.empTcg;
	}

	public void setEmpTcg(char empTcg) {
		this.empTcg = empTcg;
	}

	@Column(name = "EMP_DMS")
	public Integer getEmpDms() {
		return this.empDms;
	}

	public void setEmpDms(Integer empDms) {
		this.empDms = empDms;
	}

	@Column(name = "EMP_DMC")
	public Integer getEmpDmc() {
		return this.empDmc;
	}

	public void setEmpDmc(Integer empDmc) {
		this.empDmc = empDmc;
	}

	@Column(name = "EMP_DMV")
	public Integer getEmpDmv() {
		return this.empDmv;
	}

	public void setEmpDmv(Integer empDmv) {
		this.empDmv = empDmv;
	}

	@Column(name = "EMP_CGM", nullable = false, length = 1)
	public char getEmpCgm() {
		return this.empCgm;
	}

	public void setEmpCgm(char empCgm) {
		this.empCgm = empCgm;
	}

	@Column(name = "EMP_CSM", nullable = false, length = 1)
	public char getEmpCsm() {
		return this.empCsm;
	}

	public void setEmpCsm(char empCsm) {
		this.empCsm = empCsm;
	}

	@Column(name = "EMP_CCM", nullable = false, length = 1)
	public char getEmpCcm() {
		return this.empCcm;
	}

	public void setEmpCcm(char empCcm) {
		this.empCcm = empCcm;
	}

	@Column(name = "EMP_CVM", nullable = false, length = 1)
	public char getEmpCvm() {
		return this.empCvm;
	}

	public void setEmpCvm(char empCvm) {
		this.empCvm = empCvm;
	}

	@Column(name = "EMP_TPR", nullable = false, length = 1)
	public char getEmpTpr() {
		return this.empTpr;
	}

	public void setEmpTpr(char empTpr) {
		this.empTpr = empTpr;
	}

	@Column(name = "EMP_TCB", nullable = false, length = 1)
	public char getEmpTcb() {
		return this.empTcb;
	}

	public void setEmpTcb(char empTcb) {
		this.empTcb = empTcb;
	}

	@Column(name = "EMP_DFT", nullable = false, length = 1)
	public char getEmpDft() {
		return this.empDft;
	}

	public void setEmpDft(char empDft) {
		this.empDft = empDft;
	}

	@Column(name = "EMP_PCC")
	public Integer getEmpPcc() {
		return this.empPcc;
	}

	public void setEmpPcc(Integer empPcc) {
		this.empPcc = empPcc;
	}

	@Column(name = "EMP_PCF")
	public Integer getEmpPcf() {
		return this.empPcf;
	}

	public void setEmpPcf(Integer empPcf) {
		this.empPcf = empPcf;
	}

	@Column(name = "EMP_CCV", nullable = false, length = 1)
	public String getEmpCcv() {
		return this.empCcv;
	}

	public void setEmpCcv(String empCcv) {
		this.empCcv = empCcv;
	}

	@Column(name = "EMP_ISF", length = 9)
	public String getEmpIsf() {
		return this.empIsf;
	}

	public void setEmpIsf(String empIsf) {
		this.empIsf = empIsf;
	}

	@Column(name = "EMP_SPN", nullable = false, precision = 6)
	public BigDecimal getEmpSpn() {
		return this.empSpn;
	}

	public void setEmpSpn(BigDecimal empSpn) {
		this.empSpn = empSpn;
	}

	@Column(name = "EMP_CMV", nullable = false, precision = 6)
	public BigDecimal getEmpCmv() {
		return this.empCmv;
	}

	public void setEmpCmv(BigDecimal empCmv) {
		this.empCmv = empCmv;
	}

	@Column(name = "EMP_CMG", nullable = false, precision = 6)
	public BigDecimal getEmpCmg() {
		return this.empCmg;
	}

	public void setEmpCmg(BigDecimal empCmg) {
		this.empCmg = empCmg;
	}

	@Column(name = "EMP_MGL", nullable = false, precision = 6)
	public BigDecimal getEmpMgl() {
		return this.empMgl;
	}

	public void setEmpMgl(BigDecimal empMgl) {
		this.empMgl = empMgl;
	}

	@Column(name = "EMP_DPF", nullable = false, precision = 6)
	public BigDecimal getEmpDpf() {
		return this.empDpf;
	}

	public void setEmpDpf(BigDecimal empDpf) {
		this.empDpf = empDpf;
	}

	@Column(name = "EMP_DMA", nullable = false, length = 1)
	public char getEmpDma() {
		return this.empDma;
	}

	public void setEmpDma(char empDma) {
		this.empDma = empDma;
	}

	@Column(name = "EMP_GSM", nullable = false, length = 1)
	public char getEmpGsm() {
		return this.empGsm;
	}

	public void setEmpGsm(char empGsm) {
		this.empGsm = empGsm;
	}

	@Column(name = "EMP_LGM", nullable = false)
	public int getEmpLgm() {
		return this.empLgm;
	}

	public void setEmpLgm(int empLgm) {
		this.empLgm = empLgm;
	}

	@Column(name = "EMP_MPAC", nullable = false, length = 1)
	public char getEmpMpac() {
		return this.empMpac;
	}

	public void setEmpMpac(char empMpac) {
		this.empMpac = empMpac;
	}

	@Column(name = "EMP_PBJ")
	public Integer getEmpPbj() {
		return this.empPbj;
	}

	public void setEmpPbj(Integer empPbj) {
		this.empPbj = empPbj;
	}

	@Column(name = "EMP_PBD")
	public Integer getEmpPbd() {
		return this.empPbd;
	}

	public void setEmpPbd(Integer empPbd) {
		this.empPbd = empPbd;
	}

	@Column(name = "EMP_PBI")
	public Integer getEmpPbi() {
		return this.empPbi;
	}

	public void setEmpPbi(Integer empPbi) {
		this.empPbi = empPbi;
	}

	@Column(name = "EMP_UCMC", nullable = false, length = 1)
	public char getEmpUcmc() {
		return this.empUcmc;
	}

	public void setEmpUcmc(char empUcmc) {
		this.empUcmc = empUcmc;
	}

	@Column(name = "EMP_ICM", nullable = false, precision = 6)
	public BigDecimal getEmpIcm() {
		return this.empIcm;
	}

	public void setEmpIcm(BigDecimal empIcm) {
		this.empIcm = empIcm;
	}

	@Column(name = "EMP_IPC", nullable = false, precision = 6)
	public BigDecimal getEmpIpc() {
		return this.empIpc;
	}

	public void setEmpIpc(BigDecimal empIpc) {
		this.empIpc = empIpc;
	}

	@Column(name = "EMP_FRE", nullable = false, precision = 6)
	public BigDecimal getEmpFre() {
		return this.empFre;
	}

	public void setEmpFre(BigDecimal empFre) {
		this.empFre = empFre;
	}

	@Column(name = "EMP_OUT", nullable = false, precision = 6)
	public BigDecimal getEmpOut() {
		return this.empOut;
	}

	public void setEmpOut(BigDecimal empOut) {
		this.empOut = empOut;
	}

	@Column(name = "EMP_PCR")
	public Integer getEmpPcr() {
		return this.empPcr;
	}

	public void setEmpPcr(Integer empPcr) {
		this.empPcr = empPcr;
	}

	@Column(name = "EMP_TXF", nullable = false, precision = 6)
	public BigDecimal getEmpTxf() {
		return this.empTxf;
	}

	public void setEmpTxf(BigDecimal empTxf) {
		this.empTxf = empTxf;
	}

	@Column(name = "EMP_PEA", length = 8)
	public String getEmpPea() {
		return this.empPea;
	}

	public void setEmpPea(String empPea) {
		this.empPea = empPea;
	}

	@Column(name = "EMP_NEA")
	public Integer getEmpNea() {
		return this.empNea;
	}

	public void setEmpNea(Integer empNea) {
		this.empNea = empNea;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Planodecontas> getPlanodecontases() {
//		return this.planodecontases;
//	}
//
//	public void setPlanodecontases(Set<Planodecontas> planodecontases) {
//		this.planodecontases = planodecontases;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Remessaproducao> getRemessaproducaos() {
//		return this.remessaproducaos;
//	}
//
//	public void setRemessaproducaos(Set<Remessaproducao> remessaproducaos) {
//		this.remessaproducaos = remessaproducaos;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Tributacao> getTributacaos() {
//		return this.tributacaos;
//	}
//
//	public void setTributacaos(Set<Tributacao> tributacaos) {
//		this.tributacaos = tributacaos;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Exercicio> getExercicios() {
//		return this.exercicios;
//	}
//
//	public void setExercicios(Set<Exercicio> exercicios) {
//		this.exercicios = exercicios;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Estoqueminimo> getEstoqueminimos() {
//		return this.estoqueminimos;
//	}
//
//	public void setEstoqueminimos(Set<Estoqueminimo> estoqueminimos) {
//		this.estoqueminimos = estoqueminimos;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Linhaproducao> getLinhaproducaos() {
//		return this.linhaproducaos;
//	}
//
//	public void setLinhaproducaos(Set<Linhaproducao> linhaproducaos) {
//		this.linhaproducaos = linhaproducaos;
//	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<Filial> getFilials() {
		return this.filials;
	}

	public void setFilials(Set<Filial> filials) {
		this.filials = filials;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Operacao> getOperacaos() {
//		return this.operacaos;
//	}
//
//	public void setOperacaos(Set<Operacao> operacaos) {
//		this.operacaos = operacaos;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Produto> getProdutos() {
//		return this.produtos;
//	}
//
//	public void setProdutos(Set<Produto> produtos) {
//		this.produtos = produtos;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Avisodebitohistorico> getAvisodebitohistoricos() {
//		return this.avisodebitohistoricos;
//	}
//
//	public void setAvisodebitohistoricos(
//			Set<Avisodebitohistorico> avisodebitohistoricos) {
//		this.avisodebitohistoricos = avisodebitohistoricos;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Categoriafiscal> getCategoriafiscals() {
//		return this.categoriafiscals;
//	}
//
//	public void setCategoriafiscals(Set<Categoriafiscal> categoriafiscals) {
//		this.categoriafiscals = categoriafiscals;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Modelo> getModelos() {
//		return this.modelos;
//	}
//
//	public void setModelos(Set<Modelo> modelos) {
//		this.modelos = modelos;
//	}
//
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<ColaboradorEmpresa> getColaboradorEmpresas() {
		return this.colaboradorEmpresas;
	}

	public void setColaboradorEmpresas(
			Set<ColaboradorEmpresa> colaboradorEmpresas) {
		this.colaboradorEmpresas = colaboradorEmpresas;
	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Categoriafinanceira> getCategoriafinanceiras() {
//		return this.categoriafinanceiras;
//	}
//
//	public void setCategoriafinanceiras(
//			Set<Categoriafinanceira> categoriafinanceiras) {
//		this.categoriafinanceiras = categoriafinanceiras;
//	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Remessaproducaosimulacao> getRemessaproducaosimulacaos() {
//		return this.remessaproducaosimulacaos;
//	}
//
//	public void setRemessaproducaosimulacaos(
//			Set<Remessaproducaosimulacao> remessaproducaosimulacaos) {
//		this.remessaproducaosimulacaos = remessaproducaosimulacaos;
//	}
//
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
	public Set<EmpresaPauta> getEmpresaPautas() {
		return this.empresaPautas;
	}

	public void setEmpresaPautas(Set<EmpresaPauta> empresaPautas) {
		this.empresaPautas = empresaPautas;
	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Pedidoproducao> getPedidoproducaos() {
//		return this.pedidoproducaos;
//	}
//
//	public void setPedidoproducaos(Set<Pedidoproducao> pedidoproducaos) {
//		this.pedidoproducaos = pedidoproducaos;
//	}
//
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
//	public Set<Dre> getDres() {
//		return this.dres;
//	}
//
//	public void setDres(Set<Dre> dres) {
//		this.dres = dres;
//	}

}