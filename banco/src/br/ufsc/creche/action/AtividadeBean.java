package br.ufsc.creche.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.SelectEvent;

import br.ufsc.creche.model.Atividade;
import br.ufsc.creche.model.DatasComemorativa;
import br.ufsc.creche.negocio.AtividadeRN;
import br.ufsc.creche.negocio.DataComemorativaRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class AtividadeBean extends ActionBean {

	private Atividade atividade = new Atividade();
	private List<Atividade> lista;
	private DatasComemorativa dataPesquisa = new DatasComemorativa();

	public void novo() {
		atividade = new Atividade();
	}


	public void excluir(){
		try {
			AtividadeRN urn = new AtividadeRN();
			urn.excluir(atividade);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir Atividade");
		}
	}

	public void salvar() {
		try {
			AtividadeRN urn = new AtividadeRN();
			
			if(dataPesquisa.getCodigoDataComemorativa()!=null){
				atividade.setDataComemorativa(dataPesquisa);
			}
			
			urn.salvar(atividade);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar Atividade");
		}
	}


	public void obterPorId(){
		AtividadeRN colRN = new AtividadeRN();
		atividade = colRN.obterPorId(atividade);
	}

	public Atividade getAtividade() {
		return atividade;
	}


	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}


	public List<Atividade> getLista() {
		if(lista == null){
			lista = new AtividadeRN().pesquisar(null);
		}
		return lista;
	}


	public void setLista(List<Atividade> lista) {
		this.lista = lista;
	}


	public void atividadeSelecionadoDialog(Atividade at) {
		FacesUtil.closeDialog(at);
	}
	
	
	public void escolhaDataComemorativa() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 400); // vert
		options.put("contentWidth", 600);// horizon
		options.put("closeOnEscape", true);
		FacesUtil.openDialog("frame_busca_data_comemorativa", options, null);
	}

	public void retornoDataComemorativaSelecionado(SelectEvent event) {
		if(event.getObject()!=null){
			DatasComemorativa dt = (DatasComemorativa) event.getObject();
			atividade.setDataComemorativa(dt);
			dataPesquisa = dt;
		}
	}

	public void buscaDataComemorativa(){
		DataComemorativaRN dtRN = new DataComemorativaRN();
		DatasComemorativa dt = new DatasComemorativa();

		dt = dtRN.obterPorId(dataPesquisa);
		dataPesquisa = dt;
		if(dt==null){
			dataPesquisa   = new DatasComemorativa();
		}
	}


	public DatasComemorativa getDataPesquisa() {
		return dataPesquisa;
	}


	public void setDataPesquisa(DatasComemorativa dataPesquisa) {
		this.dataPesquisa = dataPesquisa;
	}

}

