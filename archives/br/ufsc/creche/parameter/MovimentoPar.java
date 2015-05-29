package br.ufsc.creche.parameter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.SortOrder;

public class MovimentoPar {

	private Date dataInicial = new Date();
	private Date dataFinal = new Date();
	private Integer consulta;
	private Integer operacao;
	private Integer empresa;
	private List<Integer> movimentoTipo = new ArrayList<Integer>();
	private List<String> situacao = new ArrayList<String>();
	private Number colCod;
	private Integer primeriroRegistro;
	private Integer qtdeRegistro;
	private String sortField;
	private SortOrder sortOrder;

	public void addMovimentoTipo(int valor){
		movimentoTipo.add(valor);
	}
	public void addSituacao(String valor){
		situacao.add(valor);
	}
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	public Integer getConsulta() {
		return consulta;
	}
	public void setConsulta(Integer consulta) {
		this.consulta = consulta;
	}
	public Integer getOperacao() {
		return operacao;
	}
	public void setOperacao(Integer operacao) {
		this.operacao = operacao;
	}
	public Integer getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Integer empresa) {
		this.empresa = empresa;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public SortOrder getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Integer getQtdeRegistro() {
		return qtdeRegistro;
	}
	public void setQtdeRegistro(Integer qtdeRegistro) {
		this.qtdeRegistro = qtdeRegistro;
	}
	public List<Integer> getMovimentoTipo() {
		return movimentoTipo;
	}
	public void setMovimentoTipo(List<Integer> movimentoTipo) {
		this.movimentoTipo = movimentoTipo;
	}
	public List<String> getSituacao() {
		return situacao;
	}
	public void setSituacao(List<String> situacao) {
		this.situacao = situacao;
	}
	public Integer getPrimeriroRegistro() {
		return primeriroRegistro;
	}
	public void setPrimeriroRegistro(Integer primeriroRegistro) {
		this.primeriroRegistro = primeriroRegistro;
	}
	public Number getColCod() {
		return colCod;
	}
	public void setColCod(Number colCod) {
		this.colCod = colCod;
	}
}