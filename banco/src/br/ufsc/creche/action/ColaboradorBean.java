/*package br.ufsc.creche.action;

import java.io.Serializable;
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

import br.ufsc.creche.VO.ColaboradorVO;
import br.ufsc.creche.VO.MovimentoVO;
import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.model.Colaborador;
import br.ufsc.creche.model.Municipio;
import br.ufsc.creche.model.TelaWeb;
import br.ufsc.creche.model.TelaWebId;
import br.ufsc.creche.model.TelaWebUsuario;
import br.ufsc.creche.model.TelaWebUsuarioId;
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
public class ColaboradorBean extends ActionBean implements Serializable  {

	private static final long serialVersionUID = -6612593949221420383L;

	private Colaborador colaborador = new Colaborador();
	private ColaboradorVO colaboradorVO = new ColaboradorVO();
	private List<Colaborador> lista;
	private List<ColaboradorVO> listaTelaWeb;
	private boolean podeAlterar=false;
	private boolean podeDigitarDocumento=false;
	private Municipio municipioExibidoDialog = new Municipio();
	private List<TelaWebVO> tela = new ArrayList<TelaWebVO>();
	private final String nomeJanela="frm_cad_colaborador";
	private TelaWebUsuario telaWebUsuario = new TelaWebUsuario();
	private String documentoSemMascara ="";

	private List<MovimentoVO> listaMovimento;

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
		telaWebId.setTlwFrm("frm_cad_colaborador");
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

	public List<Colaborador> getLista() {
		if (lista == null){
			lista = new ColaboradorRN().pesquisar(null);
		}
		return lista;
	}

	public void setLista(List<Colaborador> lista) {
		this.lista = lista;
	}

	public List<ColaboradorVO> getListaTelaWeb() {
		if (listaTelaWeb == null){
			listaTelaWeb = new ColaboradorRN().pesquisarTelaWeb(tela);
		}
		return listaTelaWeb;
	}

	public void setListaTelaWeb(List<ColaboradorVO> listaTelaWeb) {
		this.listaTelaWeb = listaTelaWeb;
	}

	public void novo() {
		listaMovimento=null;
		documentoSemMascara="";
		ColaboradorRN col = new ColaboradorRN();
		colaborador = new Colaborador();
		colaboradorVO = new ColaboradorVO();
		municipioExibidoDialog = new Municipio();
		colaborador.setColCod(col.geraCodigoMax());
		colaborador.setColIcm("S");
	}

	public void salvar() {
		identificaTipoDocumento();
		ColaboradorRN col = new ColaboradorRN();

		colaborador.setColDtu(new Date());
		colaborador.setColPai(1);
		colaborador.setColCep(colaborador.getColCep().replaceAll("-", ""));
		colaborador.setColInf(Diversos.removerLetrasCaracteres(colaborador.getColInf()));

		if(colaborador.getColInf().trim().length()>14){
			colaborador.setColInf(colaborador.getColInf().substring(0,14));
		}


		System.out.println("A "+colaboradorVO.getColFon());
		System.out.println("B "+colaboradorVO.getColFax());
		System.out.println("C "+colaboradorVO.getColCel());

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

		try {
			col.salvar(colaborador);
			listaTelaWeb = null;
		} catch (RNException e) {
			apresentarMenssagemDeErro(e.getMessage());
			return;
		}

		colaborador = null;

	}

	public void excluir()   {
		ColaboradorRN colRN = new ColaboradorRN();
		try {
			colaborador = colRN.obterPorCodigoVO(colaboradorVO);
			colRN.excluir(colaborador);
			listaTelaWeb = null;
		} catch (RNException e) {
			e.printStackTrace();
			apresentarMenssagemDeErro("Erro ao tentar excluir Colaborador","fatal");
			return;
		}

	}

	public void editar() {
		ColaboradorRN colRN = new ColaboradorRN();
		try {
			colRN.alterar(colaborador);
		} catch (RNException e) {
			apresentarMenssagemDeErro(e.getMessage());
			return;
		}
		colaborador = null;
	}

	public void obterPorId(){

		ColaboradorRN colRN = new ColaboradorRN();
		colaborador = colRN.obterPorCodigoVO(colaboradorVO);


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
		municipioExibidoDialog = colaborador.getMunicipio();
	}

	public void encontraCEP() {
		CepWebService cepWebService = null;


		if(colaborador.getColCep().replaceAll("_","").trim().length() >7){
			cepWebService = new CepWebService(colaborador.getColCep());
		}

		if(cepWebService != null){
			if (cepWebService.getResultado() != 0) {
				MunicipioRN munRN = new MunicipioRN();
				municipioExibidoDialog = new Municipio();

				cepWebService.setCidade(Diversos.removerAcentos(cepWebService.getCidade(),true,false));

				municipioExibidoDialog.setMunNom(cepWebService.getCidade());

				municipioExibidoDialog = munRN.obterPorDescricao(municipioExibidoDialog);

				colaborador.setColEnd(cepWebService.getTipoLogradouro()+" "+cepWebService.getLogradouro().replaceFirst("- LADO PAR", "").replaceFirst("- LADO ÍMPAR", ""));
				colaborador.setColBai(cepWebService.getBairro());
				colaborador.setMunicipio(municipioExibidoDialog);
			} else {
				FacesUtil.exibirMensagemErro("CEP não encontrado");
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

	public void buscaMunicipio(){

		MunicipioRN munRN = new MunicipioRN();
		Municipio mun = new Municipio();

		mun = munRN.obterPorId(municipioExibidoDialog);
		municipioExibidoDialog = mun;
		if(mun==null){
			municipioExibidoDialog = new Municipio();

			CommandButton btn = (CommandButton) FacesUtil.findComponent(":dlg:botaoConsulta");
			System.out.println("nmBtn: "+btn.getValue());
		}
	}

	public void identificaTipoDocumento(){
		String documento =Diversos.removerLetrasCaracteres(colaborador.getColInf());
		boolean validou=false;

		ColaboradorRN verRn = new ColaboradorRN();
		Colaborador verificaDocumento = new Colaborador();

		switch (documento.length()) {
		case 14:
			validou = Diversos.validaCnpj(documento);

			documentoSemMascara=documento;

			verificaDocumento.setColInf(documento);
			verificaDocumento.setColTip("CNPJ");


			if (podeDigitarDocumento) {
				verificaDocumento = verRn.obterPorDocumento(verificaDocumento);

				if(verificaDocumento!=null) {
					colaborador.setColTip("EX");
					colaborador.setColInf("");
					FacesUtil.exibirMensagemErro("Já existe um colaborador com este CNPJ.");
					break;
				}
			}


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

			verificaDocumento.setColInf(documento);
			verificaDocumento.setColTip("CPF");

			if (podeDigitarDocumento) {
				verificaDocumento = verRn.obterPorDocumento(verificaDocumento);

				if(verificaDocumento!=null) {
					colaborador.setColTip("EX");
					colaborador.setColInf("");
					FacesUtil.exibirMensagemErro("Já existe um colaborador com este CPF.");
					break;
				}

			}


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
			verificaDocumento.setColInf(documento);
			verificaDocumento.setColTip("EX");
			if (podeDigitarDocumento) {
				verificaDocumento = verRn.obterPorDocumento(verificaDocumento);

				if(verificaDocumento!=null) {
					colaborador.setColInf("");
					FacesUtil.exibirMensagemErro("Já existe um colaborador com este EX");
					break;
				}
			}



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

	public void sort(){
		System.out.println("deu Sort");
	}

	public void filter(){
		System.out.println("deu Filter");
	}

	public void reiniciaTabela(){
		DataTable btn = (DataTable) FacesUtil.findComponent(":cadColaborador:colaborador");
		btn.setSortOrder(btn.getSortOrder());  // reset sortOrder
		btn.setFirst(0);                // reset page
		btn.setFilteredValue(btn.getFilteredValue());     // reset filter
		btn.setFilters(btn.getFilters());
		btn.setSortBy(btn.getSortBy());


		btn.setSortOrder(null);         // reset sortOrder
		btn.setFirst(0);                // reset page
		btn.setFilteredValue(null);     // reset filter
		btn.setFilters(null);
		btn.setSortBy(null);
		btn.setValueExpression("sortBy", null);

		preDestroy();


				Map<String, String> filters =  new HashMap<String, String>();

		for (org.primefaces.component.api.UIColumn column : btn.getColumns()) {
			ValueExpression filterBy = column.getValueExpression("filterBy");
			if (filterBy == null) {
				continue;
			}

			String property = getProperty(filterBy.getExpressionString());
			if (!filters.containsKey(property)) {
				continue;
			}

			FacesContext context = FacesContext.getCurrentInstance();
			char separator = UINamingContainer.getSeparatorChar(context);

			String key = column.getContainerClientId(context) + separator+ "filter";

			System.out.println(key);


		}

		 
	}



	private String getProperty(String expression) {
		String content = expression.substring(2, expression.length() - 1);
		String[] tokens = content.split("\\.");
		if (tokens.length == 2) {
			return tokens[1];
		}
		throw new IllegalArgumentException("Invalid expression: " + expression);
	}


	public void page(){
		System.out.println("mudou pagina");
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

	public ColaboradorVO getColaboradorVO() {
		return colaboradorVO;
	}

	public void setColaboradorVO(ColaboradorVO colaboradorVO) {
		this.colaboradorVO = colaboradorVO;
	}

	public boolean isPodeDigitarDocumento() {
		return podeDigitarDocumento;
	}

	public void setPodeDigitarDocumento(boolean podeDigitarDocumento) {
		this.podeDigitarDocumento = podeDigitarDocumento;
	}

	public List<MovimentoVO> getListaMovimento() {
		return listaMovimento;
	}

	public void setListaMovimento(List<MovimentoVO> listaMovimento) {
		this.listaMovimento = listaMovimento;
	}

	public String getDocumentoSemMascara() {
		return documentoSemMascara;
	}

	public void setDocumentoSemMascara(String documentoSemMascara) {
		this.documentoSemMascara = documentoSemMascara;
	}


}
*/