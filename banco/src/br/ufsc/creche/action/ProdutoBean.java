package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.VO.ProdutoVO;
import br.ufsc.creche.model.Produto;
import br.ufsc.creche.negocio.ProdutoRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class ProdutoBean extends ActionBean {

	private Produto produto = new Produto();
	private List<Produto> lista;
	private ProdutoVO produtoVO = new ProdutoVO();
	private List<ProdutoVO> listaVO;

	public List<Produto> getLista() {
		if (lista == null){
			lista = new ProdutoRN().pesquisar(null);
		}
		return lista;
	}

	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}


	public List<ProdutoVO> getListaVO() {
		if (listaVO == null){
			listaVO = new ProdutoRN().pesquisarVO(null);
		}
		return listaVO;
	}

	public void setListaVO(List<ProdutoVO> listaVO) {
		this.listaVO = listaVO;
	}


	public void novo() {
		produto = new Produto();
	}

	public void salvar() {

		ProdutoRN mun = new ProdutoRN();


		try {
			mun.salvar(produto);
		} catch (RNException e) {
			apresentarMenssagemDeErro(e);
			return;
		}

		produto = null;
	}

	public void excluir()  {
		ProdutoRN munRN = new ProdutoRN();
		try {
			produto = munRN.obterPorId(produto);
			munRN.excluir(produto);
			lista = null;
		} catch (RNException e) {
			apresentarMenssagemDeErro(e.getMessage());
			return;
		}

	}
	public void editar() {
		ProdutoRN munRN = new ProdutoRN();
		produto = munRN.obterPorId(produto);
	}
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ProdutoVO getProdutoVO() {
		return produtoVO;
	}

	public void setProdutoVO(ProdutoVO produtoVO) {
		this.produtoVO = produtoVO;
	}

	public void produtoSelecionadoDialog(ProdutoVO mun) {
		FacesUtil.closeDialog(mun);
	}

}
