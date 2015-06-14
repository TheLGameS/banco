package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.DatasComemorativa;
import br.ufsc.creche.negocio.DataComemorativaRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class DataComemorativaBean extends ActionBean {

	private DatasComemorativa dataComemorativa = new DatasComemorativa();
	private List<DatasComemorativa> lista;

	public void novo() {
		dataComemorativa = new DatasComemorativa();
	}


	public void excluir(){
		try {
			DataComemorativaRN urn = new DataComemorativaRN();
			urn.excluir(dataComemorativa);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir DatasComemorativa");
		}
	}

	public void salvar() {
		try {
			DataComemorativaRN urn = new DataComemorativaRN();
			urn.salvar(dataComemorativa);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar DatasComemorativa");
		}
	}


	public void obterPorId(){
		DataComemorativaRN colRN = new DataComemorativaRN();
		dataComemorativa = colRN.obterPorId(dataComemorativa);
	}

	public DatasComemorativa getDataComemorativa() {
		return dataComemorativa;
	}


	public void setDataComemorativa(DatasComemorativa dataComemorativa) {
		this.dataComemorativa = dataComemorativa;
	}


	public List<DatasComemorativa> getLista() {
		if(lista == null){
			lista = new DataComemorativaRN().pesquisar(null);
		}
		return lista;
	}


	public void setLista(List<DatasComemorativa> lista) {
		this.lista = lista;
	}

	public void dataComemorativaSelecionadoDialog(DatasComemorativa dt) {
		FacesUtil.closeDialog(dt);
	}

}
