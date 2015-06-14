package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.Turma;
import br.ufsc.creche.negocio.TurmaRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class TurmaBean extends ActionBean {

	private Turma turma = new Turma();
	private List<Turma> lista;

	public void novo() {
		turma = new Turma();
	}


	public void excluir(){
		try {
			TurmaRN urn = new TurmaRN();
			urn.excluir(turma);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir Turma");
		}
	}

	public void salvar() {
		try {
			TurmaRN urn = new TurmaRN();
			urn.salvar(turma);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar Turma");
		}
	}

	public void obterPorId(){
		TurmaRN colRN = new TurmaRN();
		turma = colRN.obterPorId(turma);
	}

	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}


	public List<Turma> getLista() {
		if(lista == null){
			lista = new TurmaRN().pesquisar(null);
		}
		return lista;
	}


	public void setLista(List<Turma> lista) {
		this.lista = lista;
	}

	public void turmaSelecionadoDialog(Turma t) {
		FacesUtil.closeDialog(t);
	}

}
