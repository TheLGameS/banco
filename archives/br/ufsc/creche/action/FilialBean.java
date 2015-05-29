package br.ufsc.creche.action;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.Filial;
import br.ufsc.creche.negocio.FilialRN;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class FilialBean extends ActionBean {

	private Filial filial = new Filial();
	private List<Filial> lista;
	private String filApe= "";

	@PostConstruct
	public void init(){
		lista = new FilialRN().pesquisar(null);
	}

	public List<Filial> getLista() {
		if (lista == null){
			lista = new FilialRN().pesquisar(null);
		}
		return lista;
	}

	public void setLista(List<Filial> lista) {
		this.lista = lista;
	}

	public void novo() {
		filial = new Filial();
	}

	public void salvar() {
		FilialRN filRn = new FilialRN();
		try {
			filRn.salvar(filial);
		} catch (RNException e) {
			apresentarMenssagemDeErro(e);
			return;
		}

		filial = null;
	}

	public void excluir()  {
		FilialRN filRn = new FilialRN();
		try {
			filial = filRn.obterPorId(filial);
			filRn.excluir(filial);
			lista = null;
		} catch (RNException e) {
			apresentarMenssagemDeErro(e.getMessage());
			return;
		}

	}

	public void editar() {
		FilialRN filRn = new FilialRN();
		filial = filRn.obterPorId(filial);
	}

	public String getFilApe() {
		return filApe;
	}

	public void setFilApe(String filApe) {
		this.filApe = filApe;
	}

}
