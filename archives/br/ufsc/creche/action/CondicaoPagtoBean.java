package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.CondicaoPagto;
import br.ufsc.creche.negocio.CondicaoPagtoRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class CondicaoPagtoBean extends ActionBean {

	private CondicaoPagto condicao = new CondicaoPagto();
	private List<CondicaoPagto> lista;

	public List<CondicaoPagto> getLista() {
		if (lista == null){
			lista = new CondicaoPagtoRN().pesquisar(null);
		}
		return lista;
	}

	public void setLista(List<CondicaoPagto> lista) {
		this.lista = lista;
	}

	public void novo() {
		condicao = new CondicaoPagto();
	}

	public void salvar() {
		CondicaoPagtoRN condRn = new CondicaoPagtoRN();


		try {
			condRn.salvar(condicao);
		} catch (RNException e) {
			apresentarMenssagemDeErro(e);
			return;
		}

		condicao = null;
	}

	public void excluir()  {
		CondicaoPagtoRN condRn = new CondicaoPagtoRN();
		try {
			condicao = condRn.obterPorId(condicao);
			condRn.excluir(condicao);
			lista = null;
		} catch (RNException e) {
			apresentarMenssagemDeErro(e.getMessage());
			return;
		}

	}

	public void editar() {
		CondicaoPagtoRN condRn = new CondicaoPagtoRN();
		condicao = condRn.obterPorId(condicao);
	}


	public void condicaoPagtoSelecionadoDialog(CondicaoPagto cond) {
		FacesUtil.closeDialog(cond);
	}
}
