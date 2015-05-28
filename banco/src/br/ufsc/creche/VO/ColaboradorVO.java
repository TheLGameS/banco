package br.ufsc.creche.VO;

import java.io.Serializable;
import java.util.Date;

import br.ufsc.creche.util.Diversos;



public class ColaboradorVO implements Serializable{

	private static final long serialVersionUID = -4207094304499150777L;
	private Number colCod;
	private String colDdd;
	private String colRaz;
	private String colFon;
	private String colBai;
	private String colCep;
	private Date   colDtu;
	private String colEma;
	private String colEnd;
	private Number colNro;
	private String colWeb;

	private String colApe;
	private String colNmf;
	private String colInf;
	private String colIne;
	private String colTip;
	private Date   colDti;

	private String colCon;
	private String colFax;
	private String colIcm;
	private String colCel;
	private String colOsf;
	private String colCpl;
	private String colDdc;
	private String colDdf;

	private Number colMun;
	private Number colPai;

	private String munNom;
	private String estSig;

	public Number getColCod() {
		return colCod;
	}
	public void setColCod(Number colCod) {
		this.colCod = colCod;
	}
	public String getColDdd() {
		return colDdd;
	}
	public void setColDdd(String colDdd) {
		this.colDdd = colDdd;
	}
	public String getColRaz() {
		return colRaz;
	}
	public void setColRaz(String colRaz) {
		this.colRaz = colRaz;
	}
	public String getColFon() {
		return Diversos.mascaraTelefone(colDdd, colFon);
	}

	public void setColFon(String colFon) {
		this.colFon = colFon;
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

	public String getColEma() {
		return colEma;
	}
	public void setColEma(String colEma) {
		this.colEma = colEma;
	}
	public String getColEnd() {
		return colEnd;
	}
	public void setColEnd(String colEnd) {
		this.colEnd = colEnd;
	}

	public String getColWeb() {
		return colWeb;
	}
	public void setColWeb(String colWeb) {
		this.colWeb = colWeb;
	}
	public Number getColNro() {
		return colNro;
	}
	public void setColNro(Number colNro) {
		this.colNro = colNro;
	}
	public Date getColDtu() {
		return colDtu;
	}
	public void setColDtu(Date colDtu) {
		this.colDtu = colDtu;
	}
	public String getColApe() {
		return colApe;
	}
	public void setColApe(String colApe) {
		this.colApe = colApe;
	}
	public String getColNmf() {
		return colNmf;
	}
	public void setColNmf(String colNmf) {
		this.colNmf = colNmf;
	}
	public String getColInf() {
		return colInf;
	}
	public void setColInf(String colInf) {
		this.colInf = colInf;
	}
	public String getColIne() {
		return colIne;
	}
	public void setColIne(String colIne) {
		this.colIne = colIne;
	}
	public String getColTip() {
		return colTip;
	}
	public void setColTip(String colTip) {
		this.colTip = colTip;
	}
	public Date getColDti() {
		return colDti;
	}
	public void setColDti(Date colDti) {
		this.colDti = colDti;
	}
	public String getColCon() {
		return colCon;
	}
	public void setColCon(String colCon) {
		this.colCon = colCon;
	}

	public String getColFax() {
		return Diversos.mascaraTelefone(colDdf, colFax);
	}

	public void setColFax(String colFax) {
		this.colFax = colFax;
	}
	public String getColIcm() {
		return colIcm;
	}
	public void setColIcm(String colIcm) {
		this.colIcm = colIcm;
	}

	public String getColCel() {
		return Diversos.mascaraTelefone(colDdc, colCel);
	}

	public void setColCel(String colCel) {
		this.colCel = colCel;
	}
	public String getColOsf() {
		return colOsf;
	}
	public void setColOsf(String colOsf) {
		this.colOsf = colOsf;
	}
	public String getColCpl() {
		return colCpl;
	}
	public void setColCpl(String colCpl) {
		this.colCpl = colCpl;
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
	public Number getColMun() {
		return colMun;
	}
	public void setColMun(Number colMun) {
		this.colMun = colMun;
	}
	public Number getColPai() {
		return colPai;
	}
	public void setColPai(Number colPai) {
		this.colPai = colPai;
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

}
