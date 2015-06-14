package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.Produto;
import br.ufsc.creche.negocio.ProdutoRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class ProdutoBean extends ActionBean {

	private Produto produto = new Produto();
	private List<Produto> lista;

	public void novo() {
		produto = new Produto();
	}


	public void excluir(){
		try {
			ProdutoRN urn = new ProdutoRN();
			urn.excluir(produto);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir Produto");
		}
	}

	public void salvar() {
		try {
			ProdutoRN urn = new ProdutoRN();
			urn.salvar(produto);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar Produto");
		}
	}

	public void obterPorId(){
		ProdutoRN colRN = new ProdutoRN();
		produto = colRN.obterPorId(produto);
	}

	public Produto getProduto() {
		return produto;
	}


	public void setProduto(Produto produto) {
		this.produto = produto;
	}


	public List<Produto> getLista() {
		if(lista == null){
			lista = new ProdutoRN().pesquisar(null);
		}
		return lista;
	}


	public void setLista(List<Produto> lista) {
		this.lista = lista;
	}

	public void produtoSelecionadoDialog(Produto p) {
		FacesUtil.closeDialog(p);
	}

}
