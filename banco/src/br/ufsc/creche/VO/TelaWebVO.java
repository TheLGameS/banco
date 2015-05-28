package br.ufsc.creche.VO;

import java.io.Serializable;

public class TelaWebVO implements Serializable{


	private static final long serialVersionUID = -4892528539724960609L;
	private Integer TWU_POS;
	private Integer TLW_POS;

	private String  TWU_CPO;
	private String  TLW_CPO;

	private Integer  TWU_FIL;
	private Integer  TLW_FIL;

	private String  TLW_DES;
	private String  TLW_OBG;
	private String  TLW_DIS;
	private String  TLW_DEF;

	private String  filterMatchMode="startsWith";


	public String getTWU_CPO() {
		return TWU_CPO;
	}
	public void setTWU_CPO(String tWU_CPO) {
		TWU_CPO = tWU_CPO;
	}
	public String getTLW_DES() {
		return TLW_DES;
	}
	public void setTLW_DES(String tLW_DES) {
		TLW_DES = tLW_DES;
	}
	public Integer getTWU_POS() {
		return TWU_POS;
	}
	public void setTWU_POS(Integer tWU_POS) {
		TWU_POS = tWU_POS;
	}
	public String getTLW_OBG() {
		return TLW_OBG;
	}
	public void setTLW_OBG(String tLW_OBG) {
		TLW_OBG = tLW_OBG;
	}
	public String getTLW_DIS() {
		return TLW_DIS;
	}
	public void setTLW_DIS(String tLW_DIS) {
		TLW_DIS = tLW_DIS;
	}
	public Integer getTLW_POS() {
		return TLW_POS;
	}
	public void setTLW_POS(Integer tLW_POS) {
		TLW_POS = tLW_POS;
	}
	public String getTLW_CPO() {
		return TLW_CPO;
	}
	public void setTLW_CPO(String tLW_CPO) {
		TLW_CPO = tLW_CPO;
	}
	public String getTLW_DEF() {
		return TLW_DEF;
	}
	public void setTLW_DEF(String tLW_DEF) {
		TLW_DEF = tLW_DEF;
	}
	public Integer getTWU_FIL() {
		return TWU_FIL;
	}
	public void setTWU_FIL(Integer tWU_FIL) {
		TWU_FIL = tWU_FIL;
	}
	public Integer getTLW_FIL() {
		return TLW_FIL;
	}
	public void setTLW_FIL(Integer tLW_FIL) {
		TLW_FIL = tLW_FIL;
	}

	public String getFilterMatchMode() {
		retornaTipoFiltro();
		return filterMatchMode;
	}
	public void setFilterMatchMode(String filterMatchMode) {
		this.filterMatchMode = filterMatchMode;
	}

	public void retornaTipoFiltro(){
		switch (TWU_FIL) {
		case 0:
			break;
		case 1:
			filterMatchMode= "startsWith";
			break;
		case 2:
			filterMatchMode= "contains";
			break;
		default:
			break;
		}
	}

	public String getFiltroPorDescricao(){
		switch (TWU_FIL) {
		case 0:
			return "Não Filtra";
		case 1:
			return "Inicia com";
		case 2:
			return "Contém";
		default:
			return "Não Filtra";
		}
	}



}
