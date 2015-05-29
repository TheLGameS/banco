package br.ufsc.creche.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import br.ufsc.creche.model.Estado;
import br.ufsc.creche.model.Municipio;
import br.ufsc.creche.negocio.MunicipioRN;
import br.ufsc.creche.util.ContextoUtil;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@ViewScoped
public class MunicipioBean extends ActionBean implements Serializable {

	private static final long serialVersionUID = -4177793411154000287L;
	private Municipio municipio = new Municipio();
	private Estado ufSelecionada = new Estado();
	private List<Municipio> lista;
	private List<Municipio> listaPorEstado;
	private int tabIndex = 0;
	private boolean renderTabCadastro = true;
	private boolean renderTabPesquisa = false;
	private String 	siglaEstado="SC";


	public List<Municipio> getLista() {
		if (lista == null){
			lista = new MunicipioRN().pesquisar(null);
		}
		return lista;
	}

	public void setLista(List<Municipio> lista) {
		this.lista = lista;
	}

	public List<Municipio> getListaPorEstado() {
		if(listaPorEstado==null ){
			Municipio filtro = new Municipio();
			Estado 	 est = new Estado();
			est.setEstSig(siglaEstado);
			filtro.setEstado(est);
			listaPorEstado = new MunicipioRN().pesquisar(filtro);
		}
		return listaPorEstado;
	}

	public void setListaPorEstado(List<Municipio> listaPorEstado) {
		this.listaPorEstado = listaPorEstado;
	}

	public List<Municipio> listaPeloBotao(){
		return getLista();
	}

	public void novo() {
		municipio = new Municipio();

		ufSelecionada = null;

		renderTabCadastro = false;
		renderTabPesquisa = true;
		tabIndex = 1;
	}

	public void salvar() {
		MunicipioRN mun = new MunicipioRN();
		municipio.setEstado(ufSelecionada);

		try {
			mun.salvar(municipio);
		} catch (RNException e) {
			apresentarMenssagemDeErro(e);
			return;
		}

		municipio = null;
		ufSelecionada = null;
		renderTabCadastro = true;
		renderTabPesquisa = false;
		tabIndex = 0;
		lista =null;
	}

	public void tabInicial(){
		municipio = null;
		ufSelecionada = null;
		renderTabCadastro = true;
		renderTabPesquisa = false;
		tabIndex = 0;
		lista =null;
	}

	public void excluir()  {
		MunicipioRN munRN = new MunicipioRN();
		try {
			municipio = munRN.obterPorId(municipio);
			munRN.excluir(municipio);
			lista = null;
		} catch (RNException e) {
			apresentarMenssagemDeErro(e.getMessage());
			return;
		}

	}

	public void editar() {
		MunicipioRN munRN = new MunicipioRN();
		municipio = munRN.obterPorId(municipio);
		ufSelecionada = municipio.getEstado();
		renderTabCadastro = false;
		renderTabPesquisa = true;
		tabIndex = 1;
	}

	public void setEstadoAtivo(ValueChangeEvent event) {
		String estado = String.valueOf(event.getNewValue());
		siglaEstado = estado;
		listaPorEstado = null;
		ufSelecionada = new Estado();
		ufSelecionada.setEstSig(estado);
		getListaPorEstado();
	}

	public MunicipioBean() {
		renderTabCadastro = true;
		renderTabPesquisa = false;
		tabIndex = 0;
	}

	public void onRowSelect() {
		municipio.setEstado(ufSelecionada);
	}

	public int getTabIndex() {
		return tabIndex;
	}

	public void setTabIndex(int tabIndex) {
		this.tabIndex = tabIndex;
	}

	public boolean isRenderTabCadastro() {
		return renderTabCadastro;
	}

	public void setRenderTabCadastro(boolean renderTabCadastro) {
		this.renderTabCadastro = renderTabCadastro;
	}

	public boolean isRenderTabPesquisa() {
		return renderTabPesquisa;
	}

	public void setRenderTabPesquisa(boolean renderTabPesquisa) {
		this.renderTabPesquisa = renderTabPesquisa;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Estado getUfSelecionada() {
		return ufSelecionada;
	}

	public void setUfSelecionada(Estado ufSelecionada) {
		this.ufSelecionada = ufSelecionada;
	}

	public void municipioSelecionadoDialog(Municipio mun) {
		FacesUtil.closeDialog(mun);
	}

	public String getSiglaEstado() {
		return siglaEstado;
	}

	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}
}
