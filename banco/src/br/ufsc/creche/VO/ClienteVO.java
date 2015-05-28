package br.ufsc.creche.VO;

import java.io.Serializable;
import java.util.Date;

import br.ufsc.creche.util.Diversos;


public class ClienteVO implements Serializable{

	private static final long serialVersionUID = -4207094304499150777L;

	private Number cliCol;
	private Date   cliDti;
	private Date   cliDtf;
	private String cliOb1;
	private String cliOb2;
	private String cliOb3;
	private String cliEen;
	private String cliCen;
	private Number cliEnu;
	private String cliEcp;
	private String cliEba;
	private String cliEce;
	private Number cliCnu;
	private String cliCcp;
	private String cliCba;

	private Number cliCmu;
	private String cliCmun;
	private String cliCsig;

	private Number cliEmu;
	private String cliEmun;
	private String cliEsig;

	private String cliCce;
	private String cliCpp;
	private Number cliRes;
	private String cliLra;
	private Number cliCfd;
	private Number cliCtf;
	private Number cliEmp;

	private Number colMun;
	private String munNom;
	private String estSig;

	private String colDdc;
	private String colCel;
	private String colDdf;
	private String colFax;
	private String colDdd;
	private String colFon;

	private String colRaz;
	private String colTip;
	private String colInf;
	private String colNmf;
	private String colApe;
	private String colInm;
	private String colIne;
	private Date   colDti;
	private String colEnd;
	private Number colNro;
	private String colCpl;
	private String colBai;
	private String colCep;
	private String colCxp;
	private String colEma;
	private String colWeb;
	private String colCon;
	private String colIcm;

	public Date getCliDtf() {
		return cliDtf;
	}
	public void setCliDtf(Date cliDtf) {
		this.cliDtf = cliDtf;
	}
	public Number getCliCol() {
		return cliCol;
	}
	public void setCliCol(Number cliCol) {
		this.cliCol = cliCol;
	}
	public Date getCliDti() {
		return cliDti;
	}
	public void setCliDti(Date cliDti) {
		this.cliDti = cliDti;
	}
	public String getCliOb1() {
		return cliOb1;
	}
	public void setCliOb1(String cliOb1) {
		this.cliOb1 = cliOb1;
	}
	public String getCliOb2() {
		return cliOb2;
	}
	public void setCliOb2(String cliOb2) {
		this.cliOb2 = cliOb2;
	}
	public String getCliOb3() {
		return cliOb3;
	}
	public void setCliOb3(String cliOb3) {
		this.cliOb3 = cliOb3;
	}
	public String getCliCen() {
		return cliCen;
	}
	public void setCliCen(String cliCen) {
		this.cliCen = cliCen;
	}
	/**
	 * @return the cliEen
	 */
	public String getCliEen() {
		return cliEen;
	}
	/**
	 * @param cliEen the cliEen to set
	 */
	public void setCliEen(String cliEen) {
		this.cliEen = cliEen;
	}
	public Number getCliEnu() {
		return cliEnu;
	}
	public void setCliEnu(Number cliEnu) {
		this.cliEnu = cliEnu;
	}
	public String getCliEcp() {
		return cliEcp;
	}
	public void setCliEcp(String cliEcp) {
		this.cliEcp = cliEcp;
	}
	public String getCliEba() {
		return cliEba;
	}
	public void setCliEba(String cliEba) {
		this.cliEba = cliEba;
	}
	public Number getCliEmu() {
		return cliEmu;
	}
	public void setCliEmu(Number cliEmu) {
		this.cliEmu = cliEmu;
	}
	public String getCliEce() {
		return cliEce;
	}
	public void setCliEce(String cliEce) {
		this.cliEce = cliEce;
	}
	/**
	 * @return the cliCnu
	 */
	public Number getCliCnu() {
		return cliCnu;
	}
	/**
	 * @param cliCnu the cliCnu to set
	 */
	public void setCliCnu(Number cliCnu) {
		this.cliCnu = cliCnu;
	}
	/**
	 * @return the cliCba
	 */
	public String getCliCba() {
		return cliCba;
	}
	/**
	 * @param cliCba the cliCba to set
	 */
	public void setCliCba(String cliCba) {
		this.cliCba = cliCba;
	}
	/**
	 * @return the cliCcp
	 */
	public String getCliCcp() {
		return cliCcp;
	}
	/**
	 * @param cliCcp the cliCcp to set
	 */
	public void setCliCcp(String cliCcp) {
		this.cliCcp = cliCcp;
	}
	/**
	 * @return the cliCce
	 */
	public String getCliCce() {
		return cliCce;
	}
	/**
	 * @param cliCce the cliCce to set
	 */
	public void setCliCce(String cliCce) {
		this.cliCce = cliCce;
	}
	/**
	 * @return the cliCmu
	 */
	public Number getCliCmu() {
		return cliCmu;
	}
	/**
	 * @param cliCmu the cliCmu to set
	 */
	public void setCliCmu(Number cliCmu) {
		this.cliCmu = cliCmu;
	}
	/**
	 * @return the cliCpp
	 */
	public String getCliCpp() {
		return cliCpp;
	}
	/**
	 * @param cliCpp the cliCpp to set
	 */
	public void setCliCpp(String cliCpp) {
		this.cliCpp = cliCpp;
	}
	/**
	 * @return the cliLra
	 */
	public String getCliLra() {
		return cliLra;
	}
	/**
	 * @param cliLra the cliLra to set
	 */
	public void setCliLra(String cliLra) {
		this.cliLra = cliLra;
	}
	/**
	 * @return the cliRes
	 */
	public Number getCliRes() {
		return cliRes;
	}
	/**
	 * @param cliRes the cliRes to set
	 */
	public void setCliRes(Number cliRes) {
		this.cliRes = cliRes;
	}
	/**
	 * @return the cliCfd
	 */
	public Number getCliCfd() {
		return cliCfd;
	}
	/**
	 * @param cliCfd the cliCfd to set
	 */
	public void setCliCfd(Number cliCfd) {
		this.cliCfd = cliCfd;
	}
	/**
	 * @return the cliCtf
	 */
	public Number getCliCtf() {
		return cliCtf;
	}
	/**
	 * @param cliCtf the cliCtf to set
	 */
	public void setCliCtf(Number cliCtf) {
		this.cliCtf = cliCtf;
	}
	/**
	 * @return the cliEmp
	 */
	public Number getCliEmp() {
		return cliEmp;
	}
	/**
	 * @param cliEmp the cliEmp to set
	 */
	public void setCliEmp(Number cliEmp) {
		this.cliEmp = cliEmp;
	}
	public String getColRaz() {
		return colRaz;
	}
	public void setColRaz(String colRaz) {
		this.colRaz = colRaz;
	}
	public String getMunNom() {
		return munNom;
	}
	public void setMunNom(String munNom) {
		this.munNom = munNom;
	}
	public String getEstSig() {
		return estSig;
	}
	public void setEstSig(String estSig) {
		this.estSig = estSig;
	}
	public Number getColMun() {
		return colMun;
	}
	public void setColMun(Number colMun) {
		this.colMun = colMun;
	}
	public String getColDdc() {
		return colDdc;
	}
	public void setColDdc(String colDdc) {
		this.colDdc = colDdc;
	}
	public String getColDdf() {
		return colDdf;
	}
	public void setColDdf(String colDdf) {
		this.colDdf = colDdf;
	}
	public String getColCel() {
		return Diversos.mascaraTelefone(colDdc, colCel);
	}
	public void setColCel(String colCel) {
		this.colCel = colCel;
	}
	public String getColFax() {
		return Diversos.mascaraTelefone(colDdf, colFax);
	}
	public void setColFax(String colFax) {
		this.colFax = colFax;
	}
	public String getColDdd() {
		return colDdd;
	}
	public void setColDdd(String colDdd) {
		this.colDdd = colDdd;
	}
	public String getColFon() {
		return Diversos.mascaraTelefone(colDdd, colFon);
	}
	public void setColFon(String colFon) {
		this.colFon = colFon;
	}
	public String getCliCmun() {
		return cliCmun;
	}
	public void setCliCmun(String cliCmun) {
		this.cliCmun = cliCmun;
	}
	public String getCliEmun() {
		return cliEmun;
	}
	public void setCliEmun(String cliEmun) {
		this.cliEmun = cliEmun;
	}
	public String getCliCsig() {
		return cliCsig;
	}
	public void setCliCsig(String cliCsig) {
		this.cliCsig = cliCsig;
	}
	public String getCliEsig() {
		return cliEsig;
	}
	public void setCliEsig(String cliEsig) {
		this.cliEsig = cliEsig;
	}
	public String getColTip() {
		return colTip;
	}
	public void setColTip(String colTip) {
		this.colTip = colTip;
	}
	public String getColInf() {
		return colInf;
	}
	public void setColInf(String colInf) {
		this.colInf = colInf;
	}
	public String getColNmf() {
		return colNmf;
	}
	public void setColNmf(String colNmf) {
		this.colNmf = colNmf;
	}
	public String getColApe() {
		return colApe;
	}
	public void setColApe(String colApe) {
		this.colApe = colApe;
	}
	public String getColInm() {
		return colInm;
	}
	public void setColInm(String colInm) {
		this.colInm = colInm;
	}
	public String getColIne() {
		return colIne;
	}
	public void setColIne(String colIne) {
		this.colIne = colIne;
	}
	public Date getColDti() {
		return colDti;
	}
	public void setColDti(Date colDti) {
		this.colDti = colDti;
	}
	public String getColEnd() {
		return colEnd;
	}
	public void setColEnd(String colEnd) {
		this.colEnd = colEnd;
	}
	public Number getColNro() {
		return colNro;
	}
	public void setColNro(Number colNro) {
		this.colNro = colNro;
	}
	public String getColCpl() {
		return colCpl;
	}
	public void setColCpl(String colCpl) {
		this.colCpl = colCpl;
	}
	public String getColBai() {
		return colBai;
	}
	public void setColBai(String colBai) {
		this.colBai = colBai;
	}
	public String getColCep() {
		return colCep;
	}
	public void setColCep(String colCep) {
		this.colCep = colCep;
	}
	public String getColCxp() {
		return colCxp;
	}
	public void setColCxp(String colCxp) {
		this.colCxp = colCxp;
	}
	public String getColEma() {
		return colEma;
	}
	public void setColEma(String colEma) {
		this.colEma = colEma;
	}
	public String getColWeb() {
		return colWeb;
	}
	public void setColWeb(String colWeb) {
		this.colWeb = colWeb;
	}
	public String getColCon() {
		return colCon;
	}
	public void setColCon(String colCon) {
		this.colCon = colCon;
	}
	public String getColIcm() {
		return colIcm;
	}
	public void setColIcm(String colIcm) {
		this.colIcm = colIcm;
	}

}
