package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.Pagamento;
import br.ufsc.creche.negocio.PagamentoRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class PagamentoBean extends ActionBean {

	private Pagamento pagamento = new Pagamento();
	private List<Pagamento> lista;

	public void novo() {
		pagamento = new Pagamento();
	}


	public void excluir(){
		try {
			PagamentoRN urn = new PagamentoRN();
			urn.excluir(pagamento);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir Pagamento");
		}
	}

	public void salvar() {
		try {
			PagamentoRN urn = new PagamentoRN();
			urn.salvar(pagamento);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar Pagamento");
		}
	}

	
	public void obterPorId(){
		PagamentoRN colRN = new PagamentoRN();
		pagamento = colRN.obterPorId(pagamento);
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}


	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}


	public List<Pagamento> getLista() {
		if(lista == null){
			lista = new PagamentoRN().pesquisar(null);
		}
		return lista;
	}


	public void setLista(List<Pagamento> lista) {
		this.lista = lista;
	}


	 
	 

}
