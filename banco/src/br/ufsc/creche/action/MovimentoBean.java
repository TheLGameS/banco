package br.ufsc.creche.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.ufsc.creche.VO.MovimentoVO;
import br.ufsc.creche.negocio.MovimentoRN;
import br.ufsc.creche.parameter.MovimentoPar;
import br.ufsc.creche.util.RNException;


@ManagedBean
@ViewScoped
public class MovimentoBean extends ActionBean implements Serializable{

	private static final long serialVersionUID = -4152502001588686630L;
	private List<MovimentoVO> lista;
	private LazyDataModel<MovimentoVO> listaLazy;
	private Date dataInicial = new Date();
	private Date dataFinal  = new Date();
	private Integer consulta;
	private Integer operacao=2;


	public void consultaLazy(){
		listaLazy=null;

		MovimentoRN movimentoRN = new MovimentoRN();
 
		listaLazy = new LazyDataModel<MovimentoVO>(){

			private static final long serialVersionUID = 1L;

			@Override
			public List<MovimentoVO> load(int first, int pageSize,String sortField, SortOrder sortOrder,Map<String, Object> filters) {
				MovimentoPar movPar = new MovimentoPar();
				movPar.setPrimeriroRegistro(first);
				movPar.setQtdeRegistro(pageSize);
				movPar.setSortField(sortField);
				movPar.setSortOrder(sortOrder);

				movPar.setDataInicial(dataInicial);
				movPar.setDataFinal(dataFinal);
				movPar.setConsulta(consulta);
				movPar.setEmpresa(1);

				switch (operacao) {
				case 0:
					movPar.addMovimentoTipo(4);
					movPar.addMovimentoTipo(5);
					movPar.addSituacao("S");
					movPar.setOperacao(0);
					break;
				case 1:
					movPar.addMovimentoTipo(9);
					movPar.addSituacao("E");
					movPar.setOperacao(1);
					break;
				case 2:
					movPar.addMovimentoTipo(4);
					movPar.addMovimentoTipo(5);
					movPar.addMovimentoTipo(9);
					movPar.addSituacao("S");
					movPar.addSituacao("E");
					movPar.setOperacao(2);
					break;
				case 3:
					movPar.addMovimentoTipo(4);
					movPar.addMovimentoTipo(5);
					movPar.addSituacao("C");
					movPar.setOperacao(3);
					break;
				default:
					movPar=null;
					break;
				}

				try {
					setRowCount(movimentoRN.dataSize(movPar));	

					lista = (List<MovimentoVO>) movimentoRN.pesquisar(movPar);
				} catch (RNException e) {
					apresentarMenssagemDeErro(e);
				}

				return lista;
			}
		};
	}

	public void consulta(){
		lista=null;

		MovimentoRN movimentoRN = new MovimentoRN();
		
		try {

			MovimentoPar movPar = new MovimentoPar();
			movPar.setDataInicial(dataInicial);
			movPar.setDataFinal(dataFinal);
			movPar.setConsulta(consulta);
			movPar.setEmpresa(1);
			
			switch (operacao) {
			case 0:
				movPar.addMovimentoTipo(4);
				movPar.addMovimentoTipo(5);
				movPar.addSituacao("S");
				movPar.setOperacao(0);
				break;
			case 1:
				movPar.addMovimentoTipo(9);
				movPar.addSituacao("E");
				movPar.setOperacao(1);
				break;
			case 2:
				movPar.addMovimentoTipo(4);
				movPar.addMovimentoTipo(5);
				movPar.addMovimentoTipo(9);
				movPar.addSituacao("S");
				movPar.addSituacao("E");
				movPar.setOperacao(2);
				break;
			case 3:
				movPar.addMovimentoTipo(4);
				movPar.addMovimentoTipo(5);
				movPar.addSituacao("C");
				movPar.setOperacao(3);
				break;
			default:
				movPar=null;
				break;
			}

			lista = movimentoRN.pesquisar(movPar);
		} catch (RNException e) {
			apresentarMenssagemDeErro(e);
		}
		
	}

	public void setLista(List<MovimentoVO> lista) {
		this.lista = lista;
	}	
	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public LazyDataModel<MovimentoVO> getListaLazy() {
		return listaLazy;
	}

	public void setListaLazy(LazyDataModel<MovimentoVO> listaLazy) {
		this.listaLazy = listaLazy;
	}
	public Integer getConsulta() {
		return consulta;
	}

	public void setConsulta(Integer consulta) {

		this.consulta = consulta;
	}

	public Integer getOperacao() {
		return operacao;
	}

	public void setOperacao(Integer operacao) {
		this.operacao = operacao;
	}
	public List<MovimentoVO> getLista(){		
		return lista;
	}	
}