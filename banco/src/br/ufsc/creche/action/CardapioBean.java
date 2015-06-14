package br.ufsc.creche.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.SelectEvent;

import br.ufsc.creche.model.Cardapio;
import br.ufsc.creche.model.Funcionario;
import br.ufsc.creche.negocio.CardapioRN;
import br.ufsc.creche.negocio.FuncionarioRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class CardapioBean extends ActionBean {

	private Cardapio cardapio = new Cardapio();
	private List<Cardapio> lista;
	private Funcionario funcionarioPesquisa = new Funcionario();

	public void novo() {
		cardapio = new Cardapio();
	}


	public void excluir(){
		try {
			CardapioRN urn = new CardapioRN();
			urn.excluir(cardapio);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir Cardapio");
		}
	}

	public void salvar() {
		try {
			CardapioRN urn = new CardapioRN();
			cardapio.setFuncionario(funcionarioPesquisa);
			urn.salvar(cardapio);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar Cardapio");
		}
	}


	public void obterPorId(){
		CardapioRN colRN = new CardapioRN();
		cardapio = colRN.obterPorId(cardapio);

		funcionarioPesquisa = cardapio.getFuncionario();
		FuncionarioRN funcionarioService = new FuncionarioRN();
		funcionarioPesquisa = funcionarioService.obterPorId(funcionarioPesquisa);


	}

	public Cardapio getCardapio() {
		return cardapio;
	}


	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}


	public List<Cardapio> getLista() {
		if(lista == null){
			lista = new CardapioRN().pesquisar(null);
		}
		return lista;
	}


	public void setLista(List<Cardapio> lista) {
		this.lista = lista;
	}



	public void cardapioSelecionadoDialog(Cardapio c) {
		FacesUtil.closeDialog(c);
	}


	public Funcionario getFuncionarioPesquisa() {
		return funcionarioPesquisa;
	}


	public void setFuncionarioPesquisa(Funcionario funcionarioPesquisa) {
		this.funcionarioPesquisa = funcionarioPesquisa;
	}


	public void escolhaDeFuncionario() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 400); // vert
		options.put("contentWidth", 600);// horizon
		options.put("closeOnEscape", true);
		FacesUtil.openDialog("frame_busca_funcionario", options, null);
	}

	public void retornoFuncionarioSelecionado(SelectEvent event) {
		if(event.getObject()!=null){
			Funcionario al = (Funcionario) event.getObject();
			cardapio.setFuncionario(al);
			funcionarioPesquisa = al;
		}
	}

	public void buscaFuncionario(){
		FuncionarioRN alRN = new FuncionarioRN();
		Funcionario al = new Funcionario();

		al = alRN.obterPorId(funcionarioPesquisa);
		funcionarioPesquisa = al;
		if(al==null){
			funcionarioPesquisa   = new Funcionario();
		}
	}

}
