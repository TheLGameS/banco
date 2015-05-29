package br.ufsc.creche.action;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.SelectEvent;

import br.ufsc.creche.VO.ClienteVO;
import br.ufsc.creche.VO.ColaboradorVO;
import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.model.Cliente;
import br.ufsc.creche.model.ClienteId;
import br.ufsc.creche.model.ClienteRepresentante;
import br.ufsc.creche.model.ClienteRepresentanteId;
import br.ufsc.creche.model.Colaborador;
import br.ufsc.creche.model.ColaboradorEmpresa;
import br.ufsc.creche.model.ColaboradorEmpresaId;
import br.ufsc.creche.model.Municipio;
import br.ufsc.creche.model.TelaWeb;
import br.ufsc.creche.model.TelaWebId;
import br.ufsc.creche.model.TelaWebUsuario;
import br.ufsc.creche.model.TelaWebUsuarioId;
import br.ufsc.creche.negocio.ClienteRN;
import br.ufsc.creche.negocio.ClienteRepresentanteRN;
import br.ufsc.creche.negocio.ColaboradorEmpresaRN;
import br.ufsc.creche.negocio.ColaboradorRN;
import br.ufsc.creche.negocio.MunicipioRN;
import br.ufsc.creche.negocio.TelaWebRN;
import br.ufsc.creche.negocio.TelaWebUsuarioRN;
import br.ufsc.creche.parameter.ReceitaFederalPar;
import br.ufsc.creche.parameter.SintegraPar;
import br.ufsc.creche.util.CepWebService;
import br.ufsc.creche.util.ContextoUtil;
import br.ufsc.creche.util.Diversos;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;

@ManagedBean
@ViewScoped
public class ClienteBean extends ActionBean implements Serializable  {

	private static final long serialVersionUID = -6612593949221420383L;

	private Cliente cliente = new Cliente();
	private ClienteId clienteId = new ClienteId();
	private Colaborador colaborador = new Colaborador();
	private ClienteVO clienteVO = new ClienteVO();
	private ColaboradorVO colaboradorVO = new ColaboradorVO();
	private List<Cliente> lista;
	private List<ClienteVO> listaTelaWeb;
	private boolean podeAlterar=false;
	private boolean podeDigitarDocumento=false;
	private Municipio municipioExibidoDialog = new Municipio();

	private Municipio municipioCliEmu = new Municipio();
	private Municipio municipioCliCmu = new Municipio();

	private List<TelaWebVO> tela = new ArrayList<TelaWebVO>();
	private final String nomeJanela="frm_cad_cliente";
	private TelaWebUsuario telaWebUsuario = new TelaWebUsuario();
	private String documentoSemMascara ="";

	private int tipoLista=0;


	@PostConstruct
	public void init() {
		listaTelaWeb =null;
		TelaWebRN telaWebRN = new TelaWebRN();
		TelaWeb telaWeb = new TelaWeb();
		TelaWebId telaWebId = new TelaWebId();
		TelaWebUsuarioRN telaWebUsuarioRN = new TelaWebUsuarioRN();
		telaWebUsuario = new TelaWebUsuario();
		TelaWebUsuarioId telaWebUsuarioId = new TelaWebUsuarioId();

		// Setando Nome da Janela para TelaWeb
		telaWebId.setTlwFrm("frm_cad_cliente");
		telaWeb.setId(telaWebId);

		// Pegando Nome da Janela para TelaWebUsuario e Setando Usuario ATIVO
		telaWebUsuarioId.setTwuFrm(telaWeb.getId().getTlwFrm());
		telaWebUsuario.setUsuario(ContextoUtil.getContextoBean().getUsuarioLogado());
		telaWebUsuarioId.setTwuUsu(telaWebUsuario.getUsuario().getCdUsuario());
		telaWebUsuario.setId(telaWebUsuarioId);
		telaWebUsuario.setTelaWeb(telaWeb);

		tela = new ArrayList<TelaWebVO>();
		tela = telaWebUsuarioRN.montarTabelaDefault(telaWebUsuario);

	}

	@PreDestroy
	public void preDestroy() {
		init();
	}

	public List<Cliente> getLista() {
		if (lista == null){
			lista = new ClienteRN().pesquisar(null);
		}
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	public List<ClienteVO> getListaTelaWeb() {
		if (listaTelaWeb == null){
			listaTelaWeb = new ClienteRN().pesquisarTelaWeb(tela, tipoLista);
		}
		return listaTelaWeb;
	}

	public void setListaTelaWeb(List<ClienteVO> listaTelaWeb) {
		this.listaTelaWeb = listaTelaWeb;
	}


	public void consulta(){
		listaTelaWeb = null;
		getListaTelaWeb();
	}

	public void novo() {
		documentoSemMascara="";
		ColaboradorRN col = new ColaboradorRN();
		colaborador = new Colaborador();
		colaboradorVO = new ColaboradorVO();
		cliente = new Cliente();
		clienteId = new ClienteId();
		clienteVO = new ClienteVO();
		municipioExibidoDialog = new Municipio();
		municipioCliEmu = new Municipio();
		municipioCliCmu = new Municipio();
		colaborador.setColCod(col.geraCodigoMax());
		clienteId.setCliCol(colaborador.getColCod());
		cliente.setId(clienteId);
		colaborador.setColIcm("S");
		cliente.setCliCfd(0);
	}

	public void salvar() {
		identificaTipoDocumento();

		ColaboradorRN col;
		ColaboradorEmpresaRN colaboradorEmpresaService;
		ClienteRN clienteService;
		ClienteRepresentanteRN clienteRepresentanteService;


		ClienteRepresentante cliRep = new ClienteRepresentante();
		ClienteRepresentanteId cliRepId = new ClienteRepresentanteId();

		cliRepId.setClrCli(colaborador.getColCod());
		cliRep.setId(cliRepId);
		cliRep.setClrPer(BigDecimal.ZERO);
		cliRep.setClrFpr(0);

		ColaboradorEmpresa colEmp = new ColaboradorEmpresa();
		ColaboradorEmpresaId colEmpId = new ColaboradorEmpresaId();

		colEmpId.setCoeCol(colaborador.getColCod());

		colEmp.setId(colEmpId);

		cliente.setCliCtf(1);
		///cliente.setColaboradorEmpresa(colEmp);
		cliente.setCliDti(Diversos.DataHoje());

		colaborador.setColDtu(new Date());
		colaborador.setColPai(1);
		colaborador.setColCep(colaborador.getColCep().replaceAll("-", ""));
		colaborador.setColInf(Diversos.removerLetrasCaracteres(colaborador.getColInf()));

		if(colaborador.getColInf().trim().length()>14){
			colaborador.setColInf(colaborador.getColInf().substring(0,14));
		}

		if(colaboradorVO.getColFon()!=null){
			colaborador.setColDdd(Diversos.removerLetrasCaracteres(colaboradorVO.getColFon()).substring(0,2));
			colaborador.setColFon(Diversos.removerLetrasCaracteres(colaboradorVO.getColFon()).substring(2,10));
		}
		if(colaboradorVO.getColFax()!=null){
			colaborador.setColDdf(Diversos.removerLetrasCaracteres(colaboradorVO.getColFax()).substring(0,2));
			colaborador.setColFax(Diversos.removerLetrasCaracteres(colaboradorVO.getColFax()).substring(2,10));
		}
		if(colaboradorVO.getColCel()!=null){
			colaborador.setColDdc(Diversos.removerLetrasCaracteres(colaboradorVO.getColCel()).substring(0,2));
			colaborador.setColCel(Diversos.removerLetrasCaracteres(colaboradorVO.getColCel()).substring(2,10));
		}

		colaborador.setMunicipio(municipioExibidoDialog);


		if(municipioCliCmu.getMunCod()!=null){
			cliente.setMunicipioByCliCmu(municipioCliCmu);
		}
		if(municipioCliEmu.getMunCod()!=null){
			cliente.setMunicipioByCliEmu(municipioCliEmu);
		}
		cliente.setCliEce(cliente.getCliEce().replaceAll("-", ""));
		cliente.setCliCce(cliente.getCliCce().replaceAll("-", ""));

		clienteService = new ClienteRN();
		colaboradorEmpresaService = new ColaboradorEmpresaRN();
		col = new ColaboradorRN();
		clienteRepresentanteService = new ClienteRepresentanteRN();
		try {

			col.salvar(colaborador);
			colaboradorEmpresaService.salvar(colEmp);
			clienteService.salvar(cliente);
			clienteRepresentanteService.salvar(cliRep);

			listaTelaWeb = null;
		} catch (RNException e) {
			apresentarMenssagemDeErro(e.getMessage());
			return;
		}

		colaborador = null;
		cliente = null;
		clienteId = null;

	}


	public void excluir()   {
		ClienteRN cliRN = new ClienteRN();
		try {
			cliente = cliRN.obterPorCodigoVO(clienteVO);
			cliRN.excluir(cliente);
			listaTelaWeb = null;
		} catch (RNException e) {
			e.printStackTrace();
			apresentarMenssagemDeErro("Erro ao tentar excluir Cliente","fatal");
			return;
		}

	}

	/*	public void editar() {
		ClienteRN cliRN = new ClienteRN();
		ColaboradorRN colRN = new ColaboradorRN();
		try {
			cliRN.alterar(cliente);
			colRN.alterar(colaborador);
		} catch (RNException e) {
			apresentarMenssagemDeErro(e.getMessage());
			return;
		}
		cliente = null;
		colaborador = null;
	}*/

	public void obterPorId(){

		cliente = new Cliente();
		colaborador = new Colaborador();

		ColaboradorRN colRN = new ColaboradorRN();
		ClienteRN cliRN = new ClienteRN();
		cliente = cliRN.obterPorCodigoVO(clienteVO);

		colaborador.setColCod(clienteVO.getCliCol().intValue());

		colaborador = colRN.obterPorId(colaborador);


		if(colaborador.getColDti()!=null){
			String abc = "1899-12-30";
			String d = String.valueOf(colaborador.getColDti());
			if(abc.equals(d) ){
				colaborador.setColDti(null);
			}
		}

		colaboradorVO.setColDdd(colaborador.getColDdd());
		colaboradorVO.setColFon(colaborador.getColFon());

		colaboradorVO.setColDdf(colaborador.getColDdf());
		colaboradorVO.setColFax(colaborador.getColFax());

		colaboradorVO.setColDdc(colaborador.getColDdc());
		colaboradorVO.setColCel(colaborador.getColCel());

		identificaTipoDocumento();

		if(Diversos.removerLetrasCaracteres(colaborador.getColInf()).length() ==14  ) {
			documentoSemMascara=Diversos.removerLetrasCaracteres(colaborador.getColInf());
		}else{
			documentoSemMascara="";
		}

		municipioExibidoDialog = null;
		municipioCliCmu = null;
		municipioCliEmu = null;

		municipioExibidoDialog = colaborador.getMunicipio();

		if(cliente.getMunicipioByCliCmu() !=null){
			municipioCliCmu = cliente.getMunicipioByCliCmu();
		}else{
			municipioCliCmu = new Municipio();
		}

		if(cliente.getMunicipioByCliEmu() != null){
			municipioCliEmu = cliente.getMunicipioByCliEmu();
		}else{
			municipioCliEmu = new Municipio();
		}



	}

	public void encontraCEP(boolean cepCliente, boolean cepEntrega, boolean cepCobranca) {
		CepWebService cepWebService = null;

		if(cepCliente){
			if(colaborador.getColCep().replaceAll("_","").trim().length() >7){
				cepWebService = new CepWebService(colaborador.getColCep());
			}

			if(cepWebService != null){
				if (cepWebService.getResultado() != 0) {
					MunicipioRN munRN = new MunicipioRN();
					municipioExibidoDialog = new Municipio();
					municipioExibidoDialog.setMunNom(cepWebService.getCidade());
					municipioExibidoDialog = munRN.obterPorDescricao(municipioExibidoDialog);

					colaborador.setColEnd(cepWebService.getTipoLogradouro()+" "+cepWebService.getLogradouro());
					colaborador.setColBai(cepWebService.getBairro());
					colaborador.setMunicipio(municipioExibidoDialog);
				} else {
					FacesUtil.exibirMensagemErro("CEP não encontrado");
				}
			}

		}else if(cepEntrega){

			if(cliente.getCliEce().replaceAll("_","").trim().length() >7){
				cepWebService = new CepWebService(cliente.getCliEce());
			}

			if(cepWebService != null){
				if (cepWebService.getResultado() != 0) {
					MunicipioRN munRN = new MunicipioRN();
					municipioCliEmu = new Municipio();
					municipioCliEmu.setMunNom(cepWebService.getCidade());
					municipioCliEmu = munRN.obterPorDescricao(municipioCliEmu);

					cliente.setCliEen(cepWebService.getTipoLogradouro()+" "+cepWebService.getLogradouro());
					cliente.setCliEba(cepWebService.getBairro());
					cliente.setMunicipioByCliEmu(municipioCliEmu);
				} else {
					FacesUtil.exibirMensagemErro("CEP não encontrado");
				}
			}

		}else{

			if(cliente.getCliCce().replaceAll("_","").trim().length() >7){
				cepWebService = new CepWebService(cliente.getCliCce());
			}

			if(cepWebService != null){
				if (cepWebService.getResultado() != 0) {
					MunicipioRN munRN = new MunicipioRN();
					municipioCliCmu = new Municipio();
					municipioCliCmu.setMunNom(cepWebService.getCidade());
					municipioCliCmu = munRN.obterPorDescricao(municipioCliCmu);

					cliente.setCliCen(cepWebService.getTipoLogradouro()+" "+cepWebService.getLogradouro());
					cliente.setCliCba(cepWebService.getBairro());
					cliente.setMunicipioByCliCmu(municipioCliCmu);
				} else {
					FacesUtil.exibirMensagemErro("CEP não encontrado");
				}
			}

		}




	}

	public void escolhaDeMunicipio() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 545); // vert
		options.put("contentWidth", 660);// horizon
		options.put("closeOnEscape", true);
		FacesUtil.openDialog("frm_busca_municipio", options, null);
	}

	public void retornoMunicipioSelecionado(SelectEvent event) {
		if(event.getObject()!=null){
			Municipio mun = (Municipio) event.getObject();
			colaborador.setMunicipio(mun);
			municipioExibidoDialog = mun;
		}
	}


	public void retornoMunicipioCliEmu(SelectEvent event) {
		if(event.getObject()!=null){
			Municipio mun = (Municipio) event.getObject();
			cliente.setMunicipioByCliEmu(mun);
			municipioCliEmu = mun;
		}
	}

	public void retornoMunicipioCliCmu(SelectEvent event) {
		if(event.getObject()!=null){
			Municipio mun = (Municipio) event.getObject();
			cliente.setMunicipioByCliCmu(mun);
			municipioCliCmu= mun;
		}
	}
	public void buscaMunicipio(boolean cepCliente, boolean cepEntrega, boolean cepCobranca){
		MunicipioRN munRN = new MunicipioRN();
		Municipio mun = new Municipio();

		if(cepCliente){
			mun = munRN.obterPorId(municipioExibidoDialog);
			municipioExibidoDialog = mun;
			if(mun==null){
				municipioExibidoDialog = new Municipio();
				CommandButton btn = (CommandButton) FacesUtil.findComponent(":dlg:tabExterna:botaoConsulta");
				System.out.println("nmBtn: "+btn.getValue());
			}
		}else if(cepEntrega){
			mun = munRN.obterPorId(municipioCliEmu);
			municipioCliEmu = mun;
			if(mun==null){
				municipioCliEmu = new Municipio();
				CommandButton btn = (CommandButton) FacesUtil.findComponent(":dlg:tabExterna:botaoConsultaEntrega");
				System.out.println("nmBtn: "+btn.getValue());
			}
		}else{
			mun = munRN.obterPorId(municipioCliCmu);
			municipioCliCmu = mun;
			if(mun==null){
				municipioCliCmu = new Municipio();
				CommandButton btn = (CommandButton) FacesUtil.findComponent(":dlg:tabExterna:botaoConsultaCobranca");
				System.out.println("nmBtn: "+btn.getValue());
			}
		}




	}

	public void buscaColaborador(){
		ColaboradorRN colaboradorService = new ColaboradorRN();
		Colaborador entidadeColaborador = new Colaborador();

		identificaTipoDocumento();

		entidadeColaborador = colaboradorService.obterPorDocumento(colaborador);

		if(entidadeColaborador != null ){
			colaborador = entidadeColaborador;
			colaborador.setColInf(Diversos.mascaraDocumento(colaborador.getColInf()));
			municipioExibidoDialog = colaborador.getMunicipio();

			colaboradorVO.setColDdd(colaborador.getColDdd());
			colaboradorVO.setColFon(colaborador.getColFon());

			colaboradorVO.setColDdf(colaborador.getColDdf());
			colaboradorVO.setColFax(colaborador.getColFax());

			colaboradorVO.setColDdc(colaborador.getColDdc());
			colaboradorVO.setColCel(colaborador.getColCel());

			clienteId.setCliCol(colaborador.getColCod());
			cliente.setId(clienteId);

		}

	}

	public void identificaTipoDocumento(){
		String documento =Diversos.removerLetrasCaracteres(colaborador.getColInf());
		boolean validou=false;

		switch (documento.length()) {
		case 14:
			validou = Diversos.validaCnpj(documento);
			documentoSemMascara=documento;

			if(!validou) {
				colaborador.setColTip("EX");
				colaborador.setColInf("");
				FacesUtil.exibirMensagemErro("CNPJ inválido");
			}else{
				colaborador.setColTip("CNPJ");
				colaborador.setColInf(Diversos.mascaraDocumento(documento));
			}

			break;
		case 11:
			validou = Diversos.validaCpf(documento);

			documentoSemMascara="";

			if(!validou) {
				colaborador.setColTip("EX");
				colaborador.setColInf("");
				FacesUtil.exibirMensagemErro("CPF inválido");
			}else{
				colaborador.setColTip("CPF");
				colaborador.setColInf(Diversos.mascaraDocumento(documento));
				colaborador.setColIcm("N");
			}


			break;
		default:
			colaborador.setColTip("EX");

			documentoSemMascara="";

			break;
		}
	}


	public void validaIe(){
		boolean valida=true;

		if(colaborador.getColIne()!=null){

			if( colaborador.getColIne().equalsIgnoreCase("isento")){
				valida =true;
			}else if(colaborador.getColIcm().equals("S")  &&  municipioExibidoDialog.getEstado().getEstSig().trim().length() >1 ){
				valida = Diversos.validaIE(municipioExibidoDialog.getEstado().getEstSig(), colaborador.getColIne());
			}
		}

		if(!valida){
			colaborador.setColIne("");
			FacesUtil.exibirMensagemErro("IE inválida");
		}
	}

	public void escolhaCamposTabela() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("closable", false);
		options.put("contentHeight", 340);// vert
		options.put("contentWidth", 760);//  horizon
		options.put("closeOnEscape", false);
		FacesUtil.openDialog("frm_campos_tabela", options, null);
	}

	public void escolhaTipoFiltro() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("closable", false);
		options.put("contentHeight", 340);// vert
		options.put("contentWidth", 320);//  horizon
		options.put("closeOnEscape", false);
		FacesUtil.openDialog("frm_tipo_filtro", options, null);
	}

	public void frmReceitaFederal() {
		if( documentoSemMascara.trim().length()==14 ){
			Map<String, Object> options = new HashMap<String, Object>();
			options.put("modal", true);
			options.put("draggable", false);
			options.put("resizable", false);
			options.put("closable", false);
			options.put("contentHeight", 500);// vert
			options.put("contentWidth", 1200);//  horizon
			options.put("closeOnEscape", false);
			FacesUtil.openDialog("frm_receita_federal", options, null);
		}
	}

	public void retornoFrmReceitaFederal(SelectEvent event) {
		if(event.getObject()!=null){
			ReceitaFederalPar receita =  (ReceitaFederalPar) event.getObject();

			if(receita.getEmail().length() >50){
				colaborador.setColEma(receita.getEmail().substring(0,49));
			}else{
				colaborador.setColEma(receita.getEmail());
			}

			if(receita.getRazaoSocial().length() >50){
				colaborador.setColRaz(receita.getRazaoSocial().substring(0,49));
			}else{
				colaborador.setColRaz(receita.getRazaoSocial());
			}

			if(receita.getNomeFantasia().length() >50){
				colaborador.setColNmf(receita.getNomeFantasia().substring(0,49));
			}else{
				colaborador.setColNmf(receita.getNomeFantasia());
			}

			if(receita.getLogradouro().length() >40){
				colaborador.setColEnd(receita.getLogradouro().substring(0,39));
			}else{
				colaborador.setColEnd(receita.getLogradouro());
			}

			if(!receita.getNumero().equals("SN")){
				colaborador.setColNro(Integer.valueOf(receita.getNumero()));
			}

			if(receita.getComplemento().length() >20){
				colaborador.setColCpl(receita.getComplemento().substring(0,19));
			}else{
				colaborador.setColCpl(receita.getComplemento());
			}

			if(receita.getBairro().length() >20){
				colaborador.setColBai(receita.getBairro().substring(0,19));
			}else{
				colaborador.setColBai(receita.getBairro());
			}

			colaborador.setColCep(receita.getCep());

			MunicipioRN munRN = new MunicipioRN();
			municipioExibidoDialog = new Municipio();
			municipioExibidoDialog.setMunNom(Diversos.removerAcentos(receita.getMunicipio(),true,false));
			municipioExibidoDialog = munRN.obterPorDescricao(municipioExibidoDialog);
			colaborador.setMunicipio(municipioExibidoDialog);

		}
	}

	public void frmSintegra() {
		if( documentoSemMascara.trim().length() ==14 && municipioExibidoDialog.getEstado() !=null){
			if(	municipioExibidoDialog.getEstado().getEstSig().equals("RS") ||
					municipioExibidoDialog.getEstado().getEstSig().equals("SC") ||
					municipioExibidoDialog.getEstado().getEstSig().equals("PR") ||
					municipioExibidoDialog.getEstado().getEstSig().equals("SP") ){

				Map<String, Object> options = new HashMap<String, Object>();
				options.put("modal", true);
				options.put("draggable", false);
				options.put("resizable", false);
				options.put("closable", false);
				options.put("contentHeight", 500);// vert
				options.put("contentWidth", 1160);//  horizon
				options.put("closeOnEscape", false);
				FacesUtil.openDialog("frm_sintegra", options, null);

			}
		}
	}

	public void retornoFrmSintegra(SelectEvent event) {
		if(event.getObject()!=null){
			SintegraPar sintegra =  (SintegraPar) event.getObject();
			colaborador.setColIne(sintegra.getInscricaoEstadual());
		}
	}


	public void reiniciaTabela(){
		DataTable btn = (DataTable) FacesUtil.findComponent(":cadCliente:cliente");
		btn.setSortOrder(null);         // reset sortOrder
		btn.setFirst(0);                // reset page
		btn.setFilteredValue(null);     // reset filter
		btn.setFilters(null);
		btn.setSortBy(null);
		btn.setValueExpression("sortBy", null);
		preDestroy();
	}

	public void validateEmail(FacesContext context,	UIComponent toValidate, Object value) {
		String email = (String) value;

		if (email.indexOf('@') == -1) {
			((UIInput)toValidate).setValid(false);
			FacesUtil.exibirMensagemErro("Email inválido");
		}
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public boolean isPodeAlterar() {
		return podeAlterar;
	}

	public void setPodeAlterar(boolean podeAlterar) {
		this.podeAlterar = podeAlterar;
	}

	public Municipio getMunicipioExibidoDialog() {
		return municipioExibidoDialog;
	}

	public void setMunicipioExibidoDialog(Municipio municipioExibidoDialog) {
		this.municipioExibidoDialog = municipioExibidoDialog;
	}

	public void colaboradorSelecionadoDialog(Colaborador col) {
		FacesUtil.closeDialog(col);
	}

	public List<TelaWebVO> getTela() {
		return tela;
	}

	public void setTela(List<TelaWebVO> tela) {
		this.tela = tela;
	}

	public String getNomeJanela() {
		return nomeJanela;
	}

	public TelaWebUsuario getTelaWebUsuario() {
		return telaWebUsuario;
	}

	public void setTelaWebUsuario(TelaWebUsuario telaWebUsuario) {
		this.telaWebUsuario = telaWebUsuario;
	}

	public ClienteVO getClienteVO() {
		return clienteVO;
	}

	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}

	public boolean isPodeDigitarDocumento() {
		return podeDigitarDocumento;
	}

	public void setPodeDigitarDocumento(boolean podeDigitarDocumento) {
		this.podeDigitarDocumento = podeDigitarDocumento;
	}

	public String getDocumentoSemMascara() {
		return documentoSemMascara;
	}

	public void setDocumentoSemMascara(String documentoSemMascara) {
		this.documentoSemMascara = documentoSemMascara;
	}

	public ColaboradorVO getColaboradorVO() {
		return colaboradorVO;
	}

	public void setColaboradorVO(ColaboradorVO colaboradorVO) {
		this.colaboradorVO = colaboradorVO;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Municipio getMunicipioCliEmu() {
		return municipioCliEmu;
	}

	public void setMunicipioCliEmu(Municipio municipioCliEmu) {
		this.municipioCliEmu = municipioCliEmu;
	}

	public Municipio getMunicipioCliCmu() {
		return municipioCliCmu;
	}

	public void setMunicipioCliCmu(Municipio municipioCliCmu) {
		this.municipioCliCmu = municipioCliCmu;
	}

	public int getTipoLista() {
		return tipoLista;
	}

	public void setTipoLista(int tipoLista) {
		this.tipoLista = tipoLista;
	}




}
