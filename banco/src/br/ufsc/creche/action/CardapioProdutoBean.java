package br.ufsc.creche.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import br.ufsc.creche.model.Cardapio;
import br.ufsc.creche.model.CardapioProduto;
import br.ufsc.creche.model.Produto;
import br.ufsc.creche.negocio.CardapioProdutoRN;
import br.ufsc.creche.negocio.CardapioRN;
import br.ufsc.creche.negocio.ProdutoRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@ViewScoped
public class CardapioProdutoBean extends ActionBean {

	private CardapioProduto cardapioProduto = new CardapioProduto();
	private List<CardapioProduto> lista;
	private Produto produtoPesquisa = new Produto();
	private Cardapio cardapio = new Cardapio();

	public void novo() {
		cardapioProduto = new CardapioProduto();
		produtoPesquisa = new Produto();
	}


	public void excluir(){
		try {
			CardapioProdutoRN urn = new CardapioProdutoRN();
			urn.excluir(cardapioProduto);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir CardapioProduto");
		}
	}

	public void salvar() {
		try {
			CardapioProdutoRN urn = new CardapioProdutoRN();
			ProdutoRN produtoService = new ProdutoRN();
			cardapioProduto.setCardapio(cardapio);
			cardapioProduto.setProduto(produtoPesquisa);
			produtoPesquisa.setEstoque(produtoPesquisa.getEstoque()-cardapioProduto.getQuantidade());
			urn.salvar(cardapioProduto);
			produtoService.salvar(produtoPesquisa);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar CardapioProduto");
		}

	}


	public void obterPorId(){
		CardapioProdutoRN colRN = new CardapioProdutoRN();
		cardapioProduto = colRN.obterPorId(cardapioProduto);
	}

	public CardapioProduto getCardapioProduto() {
		return cardapioProduto;
	}


	public void setCardapioProduto(CardapioProduto cardapioProduto) {
		this.cardapioProduto = cardapioProduto;
	}


	public List<CardapioProduto> getLista() {
		if(lista == null  && cardapio.getCodigoCardapio() != null){
			lista = new CardapioProdutoRN().pesquisarProduto(cardapio);
		}
		return lista;
	}


	public void setLista(List<CardapioProduto> lista) {
		this.lista = lista;
	}


	public Produto getProdutoPesquisa() {
		return produtoPesquisa;
	}


	public void setProdutoPesquisa(Produto produtoPesquisa) {
		this.produtoPesquisa = produtoPesquisa;
	}


	public void escolhaDeProduto() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 400); // vert
		options.put("contentWidth", 600);// horizon
		options.put("closeOnEscape", true);
		FacesUtil.openDialog("frame_busca_produto", options, null);
	}

	public void retornoProdutoSelecionado(SelectEvent event) {
		if(event.getObject()!=null){
			Produto al = (Produto) event.getObject();
			cardapioProduto.setProduto(al);
			produtoPesquisa = al;
		}
	}

	public void buscaProduto(){
		ProdutoRN alRN = new ProdutoRN();
		Produto al = new Produto();

		al = alRN.obterPorId(produtoPesquisa);
		produtoPesquisa = al;
		if(al==null){
			produtoPesquisa= new Produto();
		}
	}


	public Cardapio getCardapio() {
		return cardapio;
	}


	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}



	public void escolhaDeCardapio() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 400); // vert
		options.put("contentWidth", 600);// horizon
		options.put("closeOnEscape", true);
		FacesUtil.openDialog("frame_busca_cardapio", options, null);
	}

	public void retornoCardapioSelecionado(SelectEvent event) {
		if(event.getObject()!=null){
			Cardapio cp = (Cardapio) event.getObject();
			cardapioProduto.setCardapio(cp);
			cardapio = cp;

			lista = null;
			getLista();
		}
	}

	public void buscaCardapio(){
		CardapioRN alRN = new CardapioRN();
		Cardapio al = new Cardapio();

		al = alRN.obterPorId(cardapio);
		cardapio = al;
		if(al==null){
			cardapio= new Cardapio();
		}

		lista = null;
		getLista();

	}
}
