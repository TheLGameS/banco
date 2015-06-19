package br.ufsc.creche.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.ufsc.creche.VO.GraficoVO;
import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.model.ContasReceber;
import br.ufsc.creche.negocio.AlunoRN;
import br.ufsc.creche.negocio.ContasReceberRN;
import br.ufsc.creche.util.Diversos;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class ContasReceberBean extends ActionBean {

	private Date aux = new Date();
	private ContasReceber contasReceber = new ContasReceber();
	private Aluno alunoPesquisa = new Aluno();
	private List<ContasReceber> lista;
	private List<ContasReceber> listaPorAluno;
	private List<GraficoVO> listaPeriodo;
	private List<GraficoVO> listaPeriodoPagamento;
	private Date dtInicial = Diversos.PrimeiroDiaAno(aux);
	private Date dtFinal = Diversos.UltimoDiaAno(aux);
	private BarChartModel graficoAnimado;

	@PostConstruct
	public void consulta(){
		listaPeriodo = null;
		listaPeriodoPagamento = null;
		criaGraficoAnimado();
	}

	private void criaGraficoAnimado() {
		graficoAnimado = initBarModel();
		graficoAnimado.setTitle("Financeiro");
		graficoAnimado.setAnimate(true);
		graficoAnimado.setLegendPosition("ne");
		Axis yAxis = graficoAnimado.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(1000);
	}


	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		getListaPeriodo();
		getListaPeriodoPagamento();

		ChartSeries receber = new ChartSeries();
		receber.setLabel("Receber");

		for(GraficoVO aux : listaPeriodo){
			receber.set(aux.getData(), aux.getValor());
		}

		ChartSeries recebido = new ChartSeries();
		recebido.setLabel("Recebido");

		for(GraficoVO aux : listaPeriodoPagamento){
			recebido.set(aux.getData(), aux.getValor());
		}

		model.addSeries(receber);
		model.addSeries(recebido);

		return model;
	}

	public void novo() {
		contasReceber = new ContasReceber();
		alunoPesquisa = new Aluno();
	}


	public void excluir(){
		try {
			ContasReceberRN urn = new ContasReceberRN();
			urn.excluir(contasReceber);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir ContasReceber");
		}
	}

	public void salvar() {
		try {
			ContasReceberRN urn = new ContasReceberRN();
			AlunoRN alunoService = new AlunoRN();
			alunoPesquisa = alunoService.obterPorId(alunoPesquisa);
			contasReceber.setObservacao(alunoPesquisa.getNome());
			contasReceber.setAluno(alunoPesquisa);
			urn.salvar(contasReceber);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar ContasReceber");
		}
	}

	public void obterPorId(){
		ContasReceberRN colRN = new ContasReceberRN();
		contasReceber = colRN.obterPorId(contasReceber);

		AlunoRN alunoService = new AlunoRN();
		alunoPesquisa = alunoService.obterPorId(contasReceber.getAluno());


	}

	public ContasReceber getContasReceber() {
		return contasReceber;
	}

	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}

	public List<ContasReceber> getLista() {
		if(lista == null){
			lista = new ContasReceberRN().pesquisar(null);
		}
		return lista;
	}

	public void setLista(List<ContasReceber> lista) {
		this.lista = lista;
	}

	public List<ContasReceber> getListaPorAluno() {
		if(listaPorAluno == null){
			listaPorAluno = new ContasReceberRN().listaContasReceberPorAluno(alunoPesquisa);
		}
		return listaPorAluno;
	}

	public void setListaPorAluno(List<ContasReceber> lista) {
		this.listaPorAluno = lista;
	}

	public void escolhaDeAluno() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 400); // vert
		options.put("contentWidth", 600);// horizon
		options.put("closeOnEscape", true);
		FacesUtil.openDialog("frame_busca_aluno", options, null);
	}

	public void retornoAlunoSelecionado(SelectEvent event) {
		if(event.getObject()!=null){
			Aluno al = (Aluno) event.getObject();
			contasReceber.setAluno(al);
			alunoPesquisa = al;
		}
	}

	public void buscaAluno(){
		AlunoRN alRN = new AlunoRN();
		Aluno al = new Aluno();

		al = alRN.obterPorId(alunoPesquisa);
		alunoPesquisa = al;
		if(al==null){
			alunoPesquisa = new Aluno();
		}
	}


	public Aluno getAlunoPesquisa() {
		return alunoPesquisa;
	}


	public void setAlunoPesquisa(Aluno alunoPesquisa) {
		this.alunoPesquisa = alunoPesquisa;
	}


	public List<GraficoVO> getListaPeriodo() {
		if(listaPeriodo == null){
			listaPeriodo = new ContasReceberRN().montarGrafico(dtInicial, dtFinal);
		}

		return listaPeriodo;
	}

	public void setListaPeriodo(List<GraficoVO> listaPeriodo) {
		this.listaPeriodo = listaPeriodo;
	}


	public List<GraficoVO> getListaPeriodoPagamento() {
		if(listaPeriodoPagamento == null){
			listaPeriodoPagamento = new ContasReceberRN().montarGraficoPagamento(dtInicial, dtFinal);
		}

		return listaPeriodoPagamento;
	}

	public void setListaPeriodoPagamento(List<GraficoVO> listaPeriodo) {
		this.listaPeriodoPagamento = listaPeriodo;
	}


	public Date getDtInicial() {
		return dtInicial;
	}


	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}


	public Date getDtFinal() {
		return dtFinal;
	}


	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}


	public BarChartModel getGraficoAnimado() {
		return graficoAnimado;
	}


	public void setGraficoAnimado(BarChartModel graficoAnimado) {
		this.graficoAnimado = graficoAnimado;
	}

}
