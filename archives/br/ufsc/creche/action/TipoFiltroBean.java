package br.ufsc.creche.action;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.model.TelaWebUsuario;
import br.ufsc.creche.model.TelaWebUsuarioId;
import br.ufsc.creche.negocio.TelaWebUsuarioRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@SessionScoped
public class TipoFiltroBean extends ActionBean {

	private TelaWebUsuario telaWebUsuario = new TelaWebUsuario();
	private List<TelaWebVO> tabelaAtiva;
	private String nomeJanela="";
	private TelaWebUsuarioRN telaWebUsuarioRN;


	public void init(){
		tabelaAtiva = new ArrayList<TelaWebVO>();
		telaWebUsuarioRN = new TelaWebUsuarioRN();
		tabelaAtiva = telaWebUsuarioRN.montarTabelaFiltro(telaWebUsuario);
	}

	public void onRowEdit(RowEditEvent event) {
		FacesUtil.exibirMensagemSucesso(((TelaWebVO) event.getObject()).getTLW_DES());
	}

	public void onRowCancel(RowEditEvent event) {
		FacesUtil.exibirMensagemSucesso(((TelaWebVO) event.getObject()).getTLW_DES());
	}


	public void onCellEdit(CellEditEvent event) {
		tabelaAtiva.get(event.getRowIndex()).setTWU_FIL( (Integer)event.getNewValue());
	}


	public void salvar(boolean salvar) {
		if(salvar) {

			telaWebUsuarioRN = new TelaWebUsuarioRN();
			TelaWebUsuario novaTela;
			TelaWebUsuarioId novaTelaId;

			for(Object value : tabelaAtiva){
				novaTela   = new TelaWebUsuario();
				novaTelaId = new TelaWebUsuarioId();
				novaTelaId.setTwuCpo( ((TelaWebVO)value).getTWU_CPO()) ;
				novaTelaId.setTwuFrm(nomeJanela);
				novaTelaId.setTwuUsu(telaWebUsuario.getId().getTwuUsu());
				novaTela.setTwuFil( ((TelaWebVO)value).getTWU_FIL()) ;
				novaTela.setId(novaTelaId);
				novaTela.setTwuPos( ((TelaWebVO)value).getTWU_POS());

				try {
					telaWebUsuarioRN.salvar(novaTela);
				} catch (RNException e) {
					apresentarMenssagemDeErro(e);
					return;
				}


			}



			FacesUtil.closeDialog(telaWebUsuario);
		} else {
			FacesUtil.closeDialog(null);
		}
	}


	public TelaWebUsuario getTelaWebUsuario() {
		return telaWebUsuario;
	}

	public void setTelaWebUsuario(TelaWebUsuario telaWebUsuario) {
		this.telaWebUsuario = telaWebUsuario;
	}

	public String getNomeJanela() {
		return nomeJanela;
	}

	public void setNomeJanela(String nomeJanela) {
		this.nomeJanela = nomeJanela;
	}

	public List<TelaWebVO> getTabelaAtiva() {
		return tabelaAtiva;
	}

	public void setTabelaAtiva(List<TelaWebVO> tabelaAtiva) {
		this.tabelaAtiva = tabelaAtiva;
	}


}