package br.ufsc.creche.parameter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PedidoVendaParametro {

	private Date dataInicial = new Date();
	private Date dataFinal = new Date();
	private Number codigoCliente;
	private boolean digitacao;
	private boolean   analise;
	private boolean  aprovado;
	private boolean reprovado;
	private boolean cancelado;
	private List<String> situacao = new ArrayList<String>();


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
	public Number getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(Number codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public boolean isDigitacao() {
		return digitacao;
	}
	public void setDigitacao(boolean digitacao) {
		this.digitacao = digitacao;
	}
	public boolean isAnalise() {
		return analise;
	}
	public void setAnalise(boolean analise) {
		this.analise = analise;
	}
	public boolean isAprovado() {
		return aprovado;
	}
	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}
	public boolean isReprovado() {
		return reprovado;
	}
	public void setReprovado(boolean reprovado) {
		this.reprovado = reprovado;
	}
	public boolean isCancelado() {
		return cancelado;
	}
	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}
	public List<String> getSituacao() {

		situacao = new ArrayList<String>();

		if(digitacao){
			situacao.add("D");
		}
		if(analise){
			situacao.add("N");
		}
		if(aprovado){
			situacao.add("A");
		}
		if(reprovado){
			situacao.add("R");
		}
		if(cancelado){
			situacao.add("I");
		}

		return situacao;
	}
	public void setSituacao(List<String> situacao) {
		this.situacao = situacao;
	}

}