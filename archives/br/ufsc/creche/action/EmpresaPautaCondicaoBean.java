package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.EmpresaPautaCondicao;
import br.ufsc.creche.negocio.EmpresaPautaCondicaoRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class EmpresaPautaCondicaoBean extends ActionBean {

	private EmpresaPautaCondicao empresaCondicao = new EmpresaPautaCondicao();
	private List<EmpresaPautaCondicao> lista;

	public List<EmpresaPautaCondicao> getLista() {
		if (lista == null){
			lista = new EmpresaPautaCondicaoRN().pesquisar(null);
		}
		return lista;
	}

	public void setLista(List<EmpresaPautaCondicao> lista) {
		this.lista = lista;
	}

	public void novo() {
		empresaCondicao = new EmpresaPautaCondicao();
	}

	public void salvar() {
		EmpresaPautaCondicaoRN condRn = new EmpresaPautaCondicaoRN();


		try {
			condRn.salvar(empresaCondicao);
		} catch (RNException e) {
			apresentarMenssagemDeErro(e);
			return;
		}

		empresaCondicao = null;
	}

	public void excluir()  {
		EmpresaPautaCondicaoRN condRn = new EmpresaPautaCondicaoRN();
		try {
			empresaCondicao = condRn.obterPorId(empresaCondicao);
			condRn.excluir(empresaCondicao);
			lista = null;
		} catch (RNException e) {
			apresentarMenssagemDeErro(e.getMessage());
			return;
		}

	}

	public void editar() {
		EmpresaPautaCondicaoRN condRn = new EmpresaPautaCondicaoRN();
		empresaCondicao = condRn.obterPorId(empresaCondicao);
	}


	public void empresaPautaCondicaoSelecionadoDialog(EmpresaPautaCondicao cond) {
		FacesUtil.closeDialog(cond);
	}
}
