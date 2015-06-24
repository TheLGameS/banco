package br.ufsc.creche.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.DonutChartModel;

import br.ufsc.creche.VO.GraficoDietaVO;
import br.ufsc.creche.model.Cardapio;
import br.ufsc.creche.negocio.CardapioProdutoRN;
import br.ufsc.creche.negocio.CardapioRN;
import br.ufsc.creche.util.FacesUtil;


@ManagedBean
@RequestScoped
public class ProdutoGraficoBean extends ActionBean {

	private DonutChartModel donutModel;
	private List<GraficoDietaVO> lista;
	private Cardapio cardapio = new Cardapio();

	@PostConstruct
	public void init() {
		createDonutModels();
	}

	private void createDonutModels() {
		donutModel = initDonutModel();
		donutModel.setTitle("Gráfico Alimentos da Dieta");
		donutModel.setLegendPosition("e");
		donutModel.setSliceMargin(5);
		donutModel.setShowDataLabels(true);
		donutModel.setDataFormat("value");
		donutModel.setShadow(true);
	}

	private DonutChartModel initDonutModel() {
		DonutChartModel model = new DonutChartModel();

		getLista();

		Map<String, Number> circle = new LinkedHashMap<String, Number>();

		if(lista == null){
			circle.put("",0);
		}else{
			for(GraficoDietaVO prod : lista){
				circle.put(prod.getDescricao(), prod.getQuantidade());
			}
		}

		model.addCircle(circle);


		return model;
	}


	public DonutChartModel getDonutModel() {
		return donutModel;
	}

	public void setDonutModel(DonutChartModel donutModel) {
		this.donutModel = donutModel;
	}

	public List<GraficoDietaVO> getLista() {
		if(lista == null && cardapio.getCodigoCardapio() != null){
			lista = new CardapioProdutoRN().montarGrafico(cardapio);
		}
		return lista;
	}

	public void setLista(List<GraficoDietaVO> lista) {
		this.lista = lista;
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
			cardapio = cp;

			lista = null;
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

	}

}
