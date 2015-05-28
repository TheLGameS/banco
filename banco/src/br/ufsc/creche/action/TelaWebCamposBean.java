package br.ufsc.creche.action;


import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.ReorderEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.model.TelaWebUsuario;
import br.ufsc.creche.model.TelaWebUsuarioId;
import br.ufsc.creche.negocio.TelaWebUsuarioRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@SessionScoped

/**
 *
 * @author Carlos Eduardo Vilarinho
 *
 */
public class TelaWebCamposBean extends ActionBean {

	private TelaWebUsuario telaWebUsuario = new TelaWebUsuario();
	private DualListModel<TelaWebVO> campos;
	private List<TelaWebVO> disponiveis;
	private List<TelaWebVO> selecionados;
	private String nomeJanela="";
	private TelaWebUsuarioRN telaWebUsuarioRN;
	private int tipoFiltro;



	public void init(){
		setTipoFiltro(0);
		telaWebUsuarioRN = new TelaWebUsuarioRN();
		selecionados = telaWebUsuarioRN.montarCampoSelecionado(telaWebUsuario);
		disponiveis = telaWebUsuarioRN.montarCamposDisponivel(telaWebUsuario);
		campos = new DualListModel<TelaWebVO>(disponiveis, selecionados);
	}

	public void onTransfer(TransferEvent event) {
		telaWebUsuarioRN = new TelaWebUsuarioRN();
		String campo="";
		Integer filtro=0;
		Integer posicao=0;
		TelaWebUsuario model;
		TelaWebUsuarioId modelId;

		StringBuilder builder = new StringBuilder();
		for(Object item :   event.getItems()) {
			campo = ((TelaWebVO)item).getTWU_CPO() !=null ? ((TelaWebVO)item).getTWU_CPO() : ((TelaWebVO)item).getTLW_CPO();
			posicao = ((TelaWebVO)item).getTWU_POS() !=null ? ((TelaWebVO)item).getTWU_POS() : ((TelaWebVO)item).getTLW_POS();
			filtro = ((TelaWebVO)item).getTWU_FIL() !=null ? ((TelaWebVO)item).getTWU_FIL() : ((TelaWebVO)item).getTLW_FIL();


			if(!event.isAdd()){
				try {
					model = new TelaWebUsuario();
					modelId = new TelaWebUsuarioId();

					modelId.setTwuCpo(campo);
					modelId.setTwuFrm(telaWebUsuario.getTelaWeb().getId().getTlwFrm());
					modelId.setTwuUsu(telaWebUsuario.getId().getTwuUsu());
					model.setId(modelId);
					model.setTwuPos(posicao);
					model.setTwuFil(filtro);


					telaWebUsuarioRN.excluir(model);

				} catch (RNException e1) {
					e1.printStackTrace();
				}
			}

		}

		FacesUtil.exibirMensagemSucesso(builder.toString());
	}

	public void onSelect(SelectEvent event) {
		tipoFiltro = ((TelaWebVO)event.getObject()).getTWU_FIL() !=null ? ((TelaWebVO)event.getObject()).getTWU_FIL() : ((TelaWebVO)event.getObject()).getTLW_FIL();
		FacesUtil.exibirMensagemSucesso(event.getObject().toString());
	}

	public void onUnselect(UnselectEvent event) {
		FacesUtil.exibirMensagemSucesso(event.getObject().toString());
	}

	public void onReorder(ReorderEvent event) {
		FacesUtil.exibirMensagemSucesso("List Reordered");
	}

	public void pickListSelecionadoDialog(Boolean grava) {
		if(grava){
			TelaWebUsuario novaTela;
			TelaWebUsuarioId novaTelaId;
			telaWebUsuarioRN = new TelaWebUsuarioRN();

			disponiveis = campos.getSource();
			selecionados = campos.getTarget();

			int i=1;
			for(Object value : selecionados){
				novaTela   = new TelaWebUsuario();
				novaTelaId = new TelaWebUsuarioId();
				novaTelaId.setTwuCpo( ((TelaWebVO)value).getTWU_CPO() !=null ? ((TelaWebVO)value).getTWU_CPO() : ((TelaWebVO)value).getTLW_CPO()) ;
				novaTelaId.setTwuFrm(nomeJanela);
				novaTelaId.setTwuUsu(telaWebUsuario.getId().getTwuUsu());
				novaTela.setTwuFil( ((TelaWebVO)value).getTWU_FIL() !=null ? ((TelaWebVO)value).getTWU_FIL() : ((TelaWebVO)value).getTLW_FIL()) ;
				novaTela.setTwuPos(i);
				novaTela.setId(novaTelaId);

				try {
					telaWebUsuarioRN.salvar(novaTela);
					i++;
				} catch (RNException e) {
					apresentarMenssagemDeErro(e);
					return;
				}
			}

			FacesUtil.closeDialog(grava);
		}else{
			FacesUtil.closeDialog(null);
		}
	}


	public TelaWebUsuario getTelaWebUsuario() {
		return telaWebUsuario;
	}

	public void setTelaWebUsuario(TelaWebUsuario telaWebUsuario) {
		this.telaWebUsuario = telaWebUsuario;
	}

	public DualListModel<TelaWebVO> getCampos() {
		return campos;
	}

	public void setCampos(DualListModel<TelaWebVO> campos) {
		this.campos = campos;
	}

	public List<TelaWebVO> getDisponiveis() {
		return disponiveis;
	}

	public void setDisponiveis(List<TelaWebVO> disponiveis) {
		this.disponiveis = disponiveis;
	}

	public List<TelaWebVO> getSelecionados() {
		return selecionados;
	}

	public void setSelecionados(List<TelaWebVO> selecionados) {
		this.selecionados = selecionados;
	}

	public String getNomeJanela() {
		return nomeJanela;
	}

	public void setNomeJanela(String nomeJanela) {
		this.nomeJanela = nomeJanela;
	}

	public int getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
}