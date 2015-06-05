package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.ContasReceber;
import br.ufsc.creche.negocio.ContasReceberRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class ContasReceberBean extends ActionBean {

	private ContasReceber contasReceber = new ContasReceber();
	private List<ContasReceber> lista;
	private List<ContasReceber> listaPorAluno;

	public void novo() {
		contasReceber = new ContasReceber();
	}


	public void excluir(){
		try {
			ContasReceberRN urn = new ContasReceberRN();
			urn.excluir(contasReceber);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir ContasReceber");
		}
	}

	public void salvar() {
		try {
			ContasReceberRN urn = new ContasReceberRN();
			urn.salvar(contasReceber);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar ContasReceber");
		}
	}

	
	public void obterPorId(){
		ContasReceberRN colRN = new ContasReceberRN();
		contasReceber = colRN.obterPorId(contasReceber);
	}
	
	public ContasReceber getContasReceber() {
		return contasReceber;
	}


	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}


	public List<ContasReceber> getLista() {
		if(lista == null){
			lista = new ContasReceberRN().pesquisar(null);
		}
		return lista;
	}

	public void setLista(List<ContasReceber> lista) {
		this.lista = lista;
	}

	public List<ContasReceber> getListaPorAluno() {
		if(listaPorAluno == null){
			listaPorAluno = new ContasReceberRN().pesquisar(contasReceber);
		}
		return listaPorAluno;
	}

	public void setListaPorAluno(List<ContasReceber> lista) {
		this.listaPorAluno = lista;
	}


}
