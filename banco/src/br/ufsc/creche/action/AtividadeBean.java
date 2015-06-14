package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.Atividade;
import br.ufsc.creche.negocio.AtividadeRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class AtividadeBean extends ActionBean {

	private Atividade atividade = new Atividade();
	private List<Atividade> lista;

	public void novo() {
		atividade = new Atividade();
	}


	public void excluir(){
		try {
			AtividadeRN urn = new AtividadeRN();
			urn.excluir(atividade);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir Atividade");
		}
	}

	public void salvar() {
		try {
			AtividadeRN urn = new AtividadeRN();
			urn.salvar(atividade);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar Atividade");
		}
	}


	public void obterPorId(){
		AtividadeRN colRN = new AtividadeRN();
		atividade = colRN.obterPorId(atividade);
	}

	public Atividade getAtividade() {
		return atividade;
	}


	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}


	public List<Atividade> getLista() {
		if(lista == null){
			lista = new AtividadeRN().pesquisar(null);
		}
		return lista;
	}


	public void setLista(List<Atividade> lista) {
		this.lista = lista;
	}



	public void atividadeSelecionadoDialog(Atividade at) {
		FacesUtil.closeDialog(at);
	}

}
