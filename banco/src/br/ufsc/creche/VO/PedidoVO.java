package br.ufsc.creche.VO;

import java.io.Serializable;
import java.util.Date;


public class PedidoVO implements Serializable{

	private static final long serialVersionUID = -4207094304499150777L;

	private Number pedCod;
	private Number pedSeq;
	private Number pedEmp;
	private Number pedFil;
	private Number pedRep;
	private Date   pedDte;
	private Date   pedDtp;
	private String pedDoc;
	private Number pedTot;
	private String pedSts;
	private String pedOb1;
	private String pedOst;
	private Number pedVfr;

	private Number pedPac;
	private Number pedAcr;
	private Number pedPou;
	private Number pedOut;

	private Number pedPde;
	private Number pedDes;
	private Number pedPda;
	private Number pedDad;
	private Number pedPds;
	private Number pedDsp;

	private Number pedCol;
	private String colNom;

	private Number pedEpt;
	private String eptNom;

	private Number pedFpa;
	private String fpaNom;

	private Number peiQtd;
	private Number peiQtc;
	private Number peiQte;


	public String getPedSts() {
		if(pedSts.equals("D")){
			pedSts = "Digitação";
		}
		if(pedSts.equals("N")){
			pedSts = "Análise";
		}
		if(pedSts.equals("A")){
			pedSts = "Aprovado";
		}
		if(pedSts.equals("R")){
			pedSts = "Reprovado";
		}
		if(pedSts.equals("I")){
			pedSts = "Cancelado";
		}
		return pedSts;
	}
	public void setPedSts(String pedSts) {
		this.pedSts = pedSts;
	}
	public Number getPedCod() {
		return pedCod;
	}
	public void setPedCod(Number pedCod) {
		this.pedCod = pedCod;
	}
	public Number getPedSeq() {
		return pedSeq;
	}
	public void setPedSeq(Number pedSeq) {
		this.pedSeq = pedSeq;
	}
	public Number getPedEmp() {
		return pedEmp;
	}
	public void setPedEmp(Number pedEmp) {
		this.pedEmp = pedEmp;
	}
	public Number getPedFil() {
		return pedFil;
	}
	public void setPedFil(Number pedFil) {
		this.pedFil = pedFil;
	}
	public Number getPedRep() {
		return pedRep;
	}
	public void setPedRep(Number pedRep) {
		this.pedRep = pedRep;
	}
	public Date getPedDte() {
		return pedDte;
	}
	public void setPedDte(Date pedDte) {
		this.pedDte = pedDte;
	}
	public Date getPedDtp() {
		return pedDtp;
	}
	public void setPedDtp(Date pedDtp) {
		this.pedDtp = pedDtp;
	}
	public String getPedDoc() {
		return pedDoc;
	}
	public void setPedDoc(String pedDoc) {
		this.pedDoc = pedDoc;
	}
	public Number getPedTot() {
		return pedTot;
	}
	public void setPedTot(Number pedTot) {
		this.pedTot = pedTot;
	}
	public String getPedOb1() {
		return pedOb1;
	}
	public void setPedOb1(String pedOb1) {
		this.pedOb1 = pedOb1;
	}
	public String getPedOst() {
		return pedOst;
	}
	public void setPedOst(String pedOst) {
		this.pedOst = pedOst;
	}
	public Number getPedVfr() {
		return pedVfr;
	}
	public void setPedVfr(Number pedVfr) {
		this.pedVfr = pedVfr;
	}
	public Number getPedPac() {
		return pedPac;
	}
	public void setPedPac(Number pedPac) {
		this.pedPac = pedPac;
	}
	public Number getPedAcr() {
		return pedAcr;
	}
	public void setPedAcr(Number pedAcr) {
		this.pedAcr = pedAcr;
	}
	public Number getPedPou() {
		return pedPou;
	}
	public void setPedPou(Number pedPou) {
		this.pedPou = pedPou;
	}
	public Number getPedOut() {
		return pedOut;
	}
	public void setPedOut(Number pedOut) {
		this.pedOut = pedOut;
	}
	public Number getPedPde() {
		return pedPde;
	}
	public void setPedPde(Number pedPde) {
		this.pedPde = pedPde;
	}
	public Number getPedDes() {
		return pedDes;
	}
	public void setPedDes(Number pedDes) {
		this.pedDes = pedDes;
	}
	public Number getPedPda() {
		return pedPda;
	}
	public void setPedPda(Number pedPda) {
		this.pedPda = pedPda;
	}
	public Number getPedDad() {
		return pedDad;
	}
	public void setPedDad(Number pedDad) {
		this.pedDad = pedDad;
	}
	public Number getPedPds() {
		return pedPds;
	}
	public void setPedPds(Number pedPds) {
		this.pedPds = pedPds;
	}
	public Number getPedDsp() {
		return pedDsp;
	}
	public void setPedDsp(Number pedDsp) {
		this.pedDsp = pedDsp;
	}
	public Number getPedCol() {
		return pedCol;
	}
	public void setPedCol(Number pedCol) {
		this.pedCol = pedCol;
	}
	public String getColNom() {
		return colNom;
	}
	public void setColNom(String colNom) {
		this.colNom = colNom;
	}
	public Number getPedEpt() {
		return pedEpt;
	}
	public void setPedEpt(Number pedEpt) {
		this.pedEpt = pedEpt;
	}
	public String getEptNom() {
		return eptNom;
	}
	public void setEptNom(String eptNom) {
		this.eptNom = eptNom;
	}
	public Number getPedFpa() {
		return pedFpa;
	}
	public void setPedFpa(Number pedFpa) {
		this.pedFpa = pedFpa;
	}
	public String getFpaNom() {
		return fpaNom;
	}
	public void setFpaNom(String fpaNom) {
		this.fpaNom = fpaNom;
	}
	public Number getPeiQtd() {
		return peiQtd;
	}
	public void setPeiQtd(Number peiQtd) {
		this.peiQtd = peiQtd;
	}
	public Number getPeiQtc() {
		return peiQtc;
	}
	public void setPeiQtc(Number peiQtc) {
		this.peiQtc = peiQtc;
	}
	public Number getPeiQte() {
		return peiQte;
	}
	public void setPeiQte(Number peiQte) {
		this.peiQte = peiQte;
	}
}
