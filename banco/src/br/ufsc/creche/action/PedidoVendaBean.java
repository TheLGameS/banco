package br.ufsc.creche.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import br.ufsc.creche.VO.PedidoVO;
import br.ufsc.creche.VO.ProdutoVO;
import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.model.CategoriaFiscal;
import br.ufsc.creche.model.CategoriaFiscalId;
import br.ufsc.creche.model.Colaborador;
import br.ufsc.creche.model.ColaboradorEmpresa;
import br.ufsc.creche.model.ColaboradorEmpresaId;
import br.ufsc.creche.model.CondicaoPagto;
import br.ufsc.creche.model.Empresa;
import br.ufsc.creche.model.EmpresaPauta;
import br.ufsc.creche.model.EmpresaPautaId;
import br.ufsc.creche.model.Filial;
import br.ufsc.creche.model.FilialId;
import br.ufsc.creche.model.FormaPagto;
import br.ufsc.creche.model.Grade;
import br.ufsc.creche.model.Pedido;
import br.ufsc.creche.model.PedidoItens;
import br.ufsc.creche.model.Produto;
import br.ufsc.creche.model.Representante;
import br.ufsc.creche.model.RepresentanteId;
import br.ufsc.creche.model.TelaWeb;
import br.ufsc.creche.model.TelaWebId;
import br.ufsc.creche.model.TelaWebUsuario;
import br.ufsc.creche.model.TelaWebUsuarioId;
import br.ufsc.creche.model.Usuario;
import br.ufsc.creche.negocio.ColaboradorRN;
import br.ufsc.creche.negocio.CondicaoPagtoRN;
import br.ufsc.creche.negocio.GradeRN;
import br.ufsc.creche.negocio.PedidoItemVendaRN;
import br.ufsc.creche.negocio.PedidoVendaRN;
import br.ufsc.creche.negocio.ProdutoRN;
import br.ufsc.creche.negocio.TelaWebRN;
import br.ufsc.creche.negocio.TelaWebUsuarioRN;
import br.ufsc.creche.parameter.PedidoVendaParametro;
import br.ufsc.creche.util.ContextoUtil;
import br.ufsc.creche.util.Diversos;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;

@ManagedBean
@SessionScoped
public class PedidoVendaBean  extends ActionBean{

	private Pedido                  pedido = new Pedido();
	private PedidoItens         pedidoItem = new PedidoItens();
	private Colaborador  colaboradorDialog = new Colaborador();
	private CondicaoPagto   condicaoDialog = new CondicaoPagto();
	private Produto          produtoDialog = new Produto();
	private ProdutoVO            produtoVO = new ProdutoVO();
	private Grade              gradePedido = new Grade();

	private BigDecimal peiQtd = BigDecimal.ZERO;
	private BigDecimal totalPrecoProdutos = BigDecimal.ZERO;

	private String     valorTotalPedidoFormatado = "";
	private BigDecimal valorTotalPedido = BigDecimal.ZERO;
	private BigDecimal totalItensPedido = BigDecimal.ZERO;

	private Integer peiQ01 = 0;
	private Integer peiQ02 = 0;
	private Integer peiQ03 = 0;
	private Integer peiQ04 = 0;
	private Integer peiQ05 = 0;
	private Integer peiQ06 = 0;
	private Integer peiQ07 = 0;
	private Integer peiQ08 = 0;
	private Integer peiQ09 = 0;
	private Integer peiQ10 = 0;
	private Integer peiQ11 = 0;
	private Integer peiQ12 = 0;
	private Integer peiQ13 = 0;
	private Integer peiQ14 = 0;

	// Parametros de Pesquisa
	private boolean digitacao=true;
	private boolean   analise=true;
	private boolean  aprovado=true;
	private boolean reprovado=true;
	private boolean cancelado=true;
	private Date 	dataInicial = Diversos.PrimeiroDiaAno(new Date());
	private Date    dataFinal = new Date();
	private Colaborador clienteColaboradorPesquisa = new Colaborador();

	//Lista DataTable
	private List<Pedido> lista= null;


	//Atributos para DataTable Dynamic
	private List<PedidoVO> listaTelaWeb;
	private List<TelaWebVO> tela = new ArrayList<TelaWebVO>();
	private final String nomeJanela="frm_mov_pedido_venda";
	private TelaWebUsuario telaWebUsuario = new TelaWebUsuario();
	private PedidoVO pedidoVO = new PedidoVO();


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
		telaWebId.setTlwFrm("frm_mov_pedido_venda");
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

	public void parametros(){
		clienteColaboradorPesquisa = new Colaborador();
		dataInicial = Diversos.PrimeiroDiaAno(new Date());
		dataFinal = new Date();
		digitacao=true;
		analise=true;
		aprovado=true;
		reprovado=true;
		cancelado=true;
		aplicaFiltroDataTable();
	}

	public void novo() {
		PedidoVendaRN ped = new PedidoVendaRN();

		pedido = new Pedido();
		colaboradorDialog = new Colaborador();
		condicaoDialog = new CondicaoPagto();

		pedido.setPedSeq(ped.geraCodigoSequenciaMax());
		pedido.setPedCod(ped.geraCodigoMax());
		pedido.setPedDte(Diversos.DataHoje());
		pedido.setPedDtp(Diversos.DataHoje());
		pedido.setPedUdi(Diversos.DataHoje());
		pedido.setPedOri(1);
		pedido.setPedSfr("1");
		pedido.setPedSts("A");
		pedido.setPedMca("MARCA");

		pedido.setPedAcr(BigDecimal.ZERO);
		pedido.setPedDad(BigDecimal.ZERO);
		pedido.setPedDes(BigDecimal.ZERO);
		pedido.setPedDsp(BigDecimal.ZERO);
		pedido.setPedOut(BigDecimal.ZERO);
		pedido.setPedPac(BigDecimal.ZERO);
		pedido.setPedPcr(BigDecimal.ZERO);
		pedido.setPedPda(BigDecimal.ZERO);
		pedido.setPedPde(BigDecimal.ZERO);
		pedido.setPedPds(BigDecimal.ZERO);
		pedido.setPedPou(BigDecimal.ZERO);
		pedido.setPedPoa(BigDecimal.ZERO);
		pedido.setPedCba("N");
		pedido.setPedDpr("N");
		pedido.setPedNfi("N");

		pedido.setPedQtc(BigDecimal.ZERO);

		pedido.setPedQte(BigDecimal.ZERO);
		pedido.setPedQtp(BigDecimal.ZERO);
		pedido.setPedQtr(BigDecimal.ZERO);
		pedido.setPedQtd(BigDecimal.ZERO);
		pedido.setPedTod(BigDecimal.ZERO);
		pedido.setPedTop(BigDecimal.ZERO);
		pedido.setPedTot(BigDecimal.ZERO);
		pedido.setPedTpe(1);
		pedido.setPedTco(1);

	}

	public void salvar() {
		PedidoVendaRN ped = new PedidoVendaRN();
		PedidoItemVendaRN pedItem = new PedidoItemVendaRN();


		try {
			ped.salvar(pedido);

			if(pedido.getPedidoItenses().size() >0 && pedido.getPedidoItenses()!=null ){
				for(PedidoItens auxf : pedido.getPedidoItenses()){
					auxf.setPedido(pedido);
					auxf.setPeiCod(pedItem.geraCodigoMax());
					pedItem.salvar(auxf);
				}
			}

		} catch (RNException e) {
			apresentarMenssagemDeErro(e.getMessage());
			return;
		}
		pedido = null;

	}

	public void excluir()   {
		PedidoVendaRN ped = new PedidoVendaRN();
		try {
			pedido = ped.obterPorId(pedido);
			ped.excluir(pedido);
		} catch (RNException e) {
			apresentarMenssagemDeErro("Erro ao tentar excluir pedido","fatal");
			return;
		}
	}

	public void excluirItem(){
		pedido.getPedidoItenses().remove(pedidoItem);
		atualizaTotalPedido();
	}

	public void alteraItem(){
		ProdutoRN prodRN = new ProdutoRN();
		produtoDialog = pedidoItem.getProduto();
		totalPrecoProdutos=pedidoItem.getPeiTot();
		produtoVO = prodRN.pesquisarProdutoVO(produtoDialog);

		produtoVO.setPrecoVenda(pedidoItem.getPeiPru());

		peiQ01 = pedidoItem.getPeiQ01();
		peiQ02 = pedidoItem.getPeiQ02();
		peiQ03 = pedidoItem.getPeiQ03();
		peiQ04 = pedidoItem.getPeiQ04();
		peiQ05 = pedidoItem.getPeiQ05();
		peiQ06 = pedidoItem.getPeiQ06();
		peiQ07 = pedidoItem.getPeiQ07();
		peiQ08 = pedidoItem.getPeiQ08();
		peiQ09 = pedidoItem.getPeiQ09();
		peiQ10 = pedidoItem.getPeiQ10();
		peiQ11 = pedidoItem.getPeiQ11();
		peiQ12 = pedidoItem.getPeiQ12();
		peiQ13 = pedidoItem.getPeiQ13();
		peiQ14 = pedidoItem.getPeiQ14();
		peiQtd = pedidoItem.getPeiQtd();


	}


	public void editar() {
		PedidoVendaRN ped = new PedidoVendaRN();
		try {
			ped.alterar(pedido);
		} catch (RNException e) {
			apresentarMenssagemDeErro(e.getMessage());
			return;
		}
		pedido = null;
	}

	public void obterPorId(){
		PedidoVendaRN ped = new PedidoVendaRN();
		ped.obterPorId(pedido);
	}


	public void buscaClientePesquisa(){
		ColaboradorRN cliRN = new ColaboradorRN();
		Colaborador cli = new Colaborador();

		if(clienteColaboradorPesquisa.getColCod()!=null){
			cli = cliRN.obterPorId(clienteColaboradorPesquisa);
			clienteColaboradorPesquisa = cli;
		}else{
			parametros();
		}
	}


	public void escolhaClientePesquisa() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 545); // vert
		options.put("contentWidth", 660);// horizon
		options.put("closeOnEscape", true);
		FacesUtil.openDialog("frm_cad_cliente", options, null);
	}

	public void retornoClientePesquisa(SelectEvent event) {
		if(event.getObject()!=null){
			Colaborador col = (Colaborador) event.getObject();
			clienteColaboradorPesquisa = col;
		}
	}


	public void aplicaFiltroDataTable(){
		lista =null;
		getLista();
	}


	public void escolhaDeColaborador() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 600);
		options.put("contentWidth", 800);
		options.put("closeOnEscape", true);
		FacesUtil.openDialog("frm_util_colaborador", options, null);
	}

	public void retornoColaboradorSelecionado(SelectEvent event) {
		Colaborador col = (Colaborador) event.getObject();
		colaboradorDialog = col;

		//----------    Empresa -----------------
		Empresa emp = new Empresa();
		emp.setEmpCod(1);

		//----------    Colaborador -----------------
		ColaboradorEmpresa colEmp = new ColaboradorEmpresa();
		ColaboradorEmpresaId colId = new ColaboradorEmpresaId();
		colId.setCoeCol(col.getColCod());
		colId.setCoeEmp(emp.getEmpCod());
		colEmp.setId(colId);
		colEmp.setColaborador(col);
		colEmp.setEmpresa(emp);
		pedido.setColaboradorEmpresa(colEmp);
		pedido.setPedCol(col.getColCod());

		//----------    Pauta -----------------
		EmpresaPauta empPauta = new EmpresaPauta();
		EmpresaPautaId empId =  new EmpresaPautaId();
		empId.setEptPat(1);
		empId.setEptEmp(emp.getEmpCod());
		empPauta.setId(empId);
		empPauta.setEmpresa(emp);
		pedido.setEmpresaPauta(empPauta);
		pedido.setPedEpt(empPauta.getId().getEptPat());

		//  ----------    Filial -----------------
		Filial fil = new Filial();
		FilialId filId = new FilialId();
		filId.setFilCod(3);
		filId.setFilEmp(emp.getEmpCod());
		fil.setId(filId);
		fil.setEmpresa(emp);
		fil.setEmpresaPauta(empPauta);
		pedido.setFilialByFkPedFil(fil);

		//  ----------    Usuario     -----------------
		ContextoBean cb =   ContextoUtil.getContextoBean();
		Usuario logado = new Usuario();
		logado = cb.getUsuarioLogado();
		pedido.setUsuario(logado);

		//  ----------   Representante -----------------
		Representante rep = new Representante();
		RepresentanteId repId = new RepresentanteId();
		repId.setRepEmp(emp.getEmpCod());
		rep.setId(repId);
		rep.setColaboradorEmpresa(colEmp);
		rep.setFilial(fil);
		pedido.setRepresentante(rep);
		pedido.setPedRep(rep.getId().getRepCol());

		//  ----------   Categoria Fiscal -----------------
		CategoriaFiscal catFis = new CategoriaFiscal();
		CategoriaFiscalId catFisId = new CategoriaFiscalId();
		catFisId.setCgfEmp(emp.getEmpCod());
		catFisId.setCgfCod("A");
		catFis.setEmpresa(emp);
		catFis.setId(catFisId);
		pedido.setCategoriaFiscal(catFis);
		pedido.setPedCgf(catFis.getId().getCgfCod());

	}

	public void buscaColaborador(){
		ColaboradorRN colRN = new ColaboradorRN();
		Colaborador col = new Colaborador();

		try {
			col = colRN.obterPorId(colaboradorDialog);
			colaboradorDialog = col;
		} catch (Exception e) {
			colaboradorDialog = new Colaborador();
		}
	}


	public void escolhaDeCondicaoPagto() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 470);
		options.put("contentWidth", 800);
		options.put("closeOnEscape", true);
		options.put("closable", true);
		options.put("showHeader", false);
		FacesUtil.openDialog("frm_util_condicao_pagto", options, null);
	}

	public void retornoCondicaoPagtoSelecionado(SelectEvent event) {
		CondicaoPagto cond = (CondicaoPagto) event.getObject();

		FormaPagto fp = new FormaPagto();
		fp.setFpaCod(3);

		pedido.setCondicaoPagtoByPedCdp(cond);
		pedido.setFormaPagtoByPedFpa(fp);

		condicaoDialog = cond;
	}

	public void buscaCondicao(){
		CondicaoPagtoRN condRN = new CondicaoPagtoRN();
		CondicaoPagto cond = new CondicaoPagto();

		FormaPagto fp = new FormaPagto();
		fp.setFpaCod(3);

		try {
			cond = condRN.obterPorId(condicaoDialog);
			condicaoDialog = cond;
			pedido.setCondicaoPagtoByPedCdp(cond);
			pedido.setFormaPagtoByPedFpa(fp);
		} catch (Exception e) {
			condicaoDialog = new CondicaoPagto();
		}
	}

	public void escolhaDePedidoItem() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 490);
		options.put("contentWidth", 820);
		options.put("closable", true);
		FacesUtil.openDialog("frm_util_produto", options, null);
	}

	public void retornoPedidoItemSelecionado(SelectEvent event) {
		ProdutoRN prodRN = new ProdutoRN();
		produtoVO        = new ProdutoVO();
		pedidoItem       = new PedidoItens();
		produtoDialog    = new Produto();
		Produto prod     = new Produto();

		produtoVO = (ProdutoVO) event.getObject();
		prod.setProCcp(produtoVO.getCodigo());
		prod = prodRN.obterPorCcp(prod);
		pedidoItem.setProduto(prod);
		produtoDialog = prod;
		initDescGrade();

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

	public void buscaProduto(){
		ProdutoRN prodRN = new ProdutoRN();
		Produto prod     = new Produto();
		pedidoItem       = new PedidoItens();
		produtoVO        = new ProdutoVO();

		try {
			prod = prodRN.obterPorCcp(produtoDialog);
			produtoVO = prodRN.pesquisarProdutoVO(prod);
			pedidoItem.setProduto(prod);
			produtoDialog = prod;
			initDescGrade();
		} catch (Exception e) {
			produtoDialog = new Produto();
		}

	}


	public void adicionarPedidoItem(boolean gravar){
		pedidoItem = new PedidoItens();

		if(gravar){
			pedidoItem.setProduto(produtoDialog);

			// quantidade
			pedidoItem.setPeiQ01(peiQ01);
			pedidoItem.setPeiQ02(peiQ02);
			pedidoItem.setPeiQ03(peiQ03);
			pedidoItem.setPeiQ04(peiQ04);
			pedidoItem.setPeiQ05(peiQ05);
			pedidoItem.setPeiQ06(peiQ06);
			pedidoItem.setPeiQ07(peiQ07);
			pedidoItem.setPeiQ08(peiQ08);
			pedidoItem.setPeiQ09(peiQ09);
			pedidoItem.setPeiQ10(peiQ10);
			pedidoItem.setPeiQ11(peiQ11);
			pedidoItem.setPeiQ12(peiQ12);
			pedidoItem.setPeiQ13(peiQ13);
			pedidoItem.setPeiQ14(peiQ14);
			pedidoItem.setPeiQtd(peiQtd);

			// quantidade empacotada
			pedidoItem.setPeiC01(0);
			pedidoItem.setPeiC02(0);
			pedidoItem.setPeiC03(0);
			pedidoItem.setPeiC04(0);
			pedidoItem.setPeiC05(0);
			pedidoItem.setPeiC06(0);
			pedidoItem.setPeiC07(0);
			pedidoItem.setPeiC08(0);
			pedidoItem.setPeiC09(0);
			pedidoItem.setPeiC10(0);
			pedidoItem.setPeiC11(0);
			pedidoItem.setPeiC12(0);
			pedidoItem.setPeiC13(0);
			pedidoItem.setPeiC14(0);
			pedidoItem.setPeiQtc(BigDecimal.ZERO);

			// quantidade entregada
			pedidoItem.setPeiE01(0);
			pedidoItem.setPeiE02(0);
			pedidoItem.setPeiE03(0);
			pedidoItem.setPeiE04(0);
			pedidoItem.setPeiE05(0);
			pedidoItem.setPeiE06(0);
			pedidoItem.setPeiE07(0);
			pedidoItem.setPeiE08(0);
			pedidoItem.setPeiE09(0);
			pedidoItem.setPeiE10(0);
			pedidoItem.setPeiE11(0);
			pedidoItem.setPeiE12(0);
			pedidoItem.setPeiE13(0);
			pedidoItem.setPeiE14(0);
			pedidoItem.setPeiQte(BigDecimal.ZERO);

			// quantidade produzida
			pedidoItem.setPeiP01(0);
			pedidoItem.setPeiP02(0);
			pedidoItem.setPeiP03(0);
			pedidoItem.setPeiP04(0);
			pedidoItem.setPeiP05(0);
			pedidoItem.setPeiP06(0);
			pedidoItem.setPeiP07(0);
			pedidoItem.setPeiP08(0);
			pedidoItem.setPeiP09(0);
			pedidoItem.setPeiP10(0);
			pedidoItem.setPeiP11(0);
			pedidoItem.setPeiP12(0);
			pedidoItem.setPeiP13(0);
			pedidoItem.setPeiP14(0);
			pedidoItem.setPeiQtp(BigDecimal.ZERO);

			// quantidade reservada
			pedidoItem.setPeiR01(0);
			pedidoItem.setPeiR02(0);
			pedidoItem.setPeiR03(0);
			pedidoItem.setPeiR04(0);
			pedidoItem.setPeiR05(0);
			pedidoItem.setPeiR06(0);
			pedidoItem.setPeiR07(0);
			pedidoItem.setPeiR08(0);
			pedidoItem.setPeiR09(0);
			pedidoItem.setPeiR10(0);
			pedidoItem.setPeiR11(0);
			pedidoItem.setPeiR12(0);
			pedidoItem.setPeiR13(0);
			pedidoItem.setPeiR14(0);
			pedidoItem.setPeiQtr(BigDecimal.ZERO);

			pedidoItem.setPeiTot(totalPrecoProdutos);
			pedidoItem.setPeiTod(BigDecimal.ZERO);
			pedidoItem.setPeiGrd(gradePedido.getGrdCod());
			BigDecimal preco = new BigDecimal(produtoVO.getPrecoVenda().doubleValue());
			pedidoItem.setPeiPru(preco);
			pedidoItem.setPeiPnf(BigDecimal.ZERO);

			boolean encontrou = false;

			if(pedido.getPedidoItenses().size() >0 ){
				for(PedidoItens auxFor : pedido.getPedidoItenses()){
					if(auxFor.getProduto().getProCcp() == pedidoItem.getProduto().getProCcp()){
						auxFor.setPeiQtd(pedidoItem.getPeiQtd());

						auxFor.setPeiQ01(pedidoItem.getPeiQ01());
						auxFor.setPeiQ02(pedidoItem.getPeiQ02());
						auxFor.setPeiQ03(pedidoItem.getPeiQ03());
						auxFor.setPeiQ04(pedidoItem.getPeiQ04());
						auxFor.setPeiQ05(pedidoItem.getPeiQ05());
						auxFor.setPeiQ06(pedidoItem.getPeiQ06());
						auxFor.setPeiQ07(pedidoItem.getPeiQ07());
						auxFor.setPeiQ08(pedidoItem.getPeiQ08());
						auxFor.setPeiQ09(pedidoItem.getPeiQ09());
						auxFor.setPeiQ10(pedidoItem.getPeiQ10());
						auxFor.setPeiQ11(pedidoItem.getPeiQ11());
						auxFor.setPeiQ12(pedidoItem.getPeiQ12());
						auxFor.setPeiQ13(pedidoItem.getPeiQ13());
						auxFor.setPeiQ14(pedidoItem.getPeiQ14());
						auxFor.setPeiTot(pedidoItem.getPeiTot());
						auxFor.setPeiPru(pedidoItem.getPeiPru());
						encontrou = true;

						break;
					}

					if(auxFor.getProduto().getProCcp().equals(pedidoItem.getProduto().getProCcp())){

						auxFor.setPeiQtd( auxFor.getPeiQtd().add(pedidoItem.getPeiQtd()));

						auxFor.setPeiQ01(auxFor.getPeiQ01()+pedidoItem.getPeiQ01());
						auxFor.setPeiQ02(auxFor.getPeiQ02()+pedidoItem.getPeiQ02());
						auxFor.setPeiQ03(auxFor.getPeiQ03()+pedidoItem.getPeiQ03());
						auxFor.setPeiQ04(auxFor.getPeiQ04()+pedidoItem.getPeiQ04());
						auxFor.setPeiQ05(auxFor.getPeiQ05()+pedidoItem.getPeiQ05());
						auxFor.setPeiQ06(auxFor.getPeiQ06()+pedidoItem.getPeiQ06());
						auxFor.setPeiQ07(auxFor.getPeiQ07()+pedidoItem.getPeiQ07());
						auxFor.setPeiQ08(auxFor.getPeiQ08()+pedidoItem.getPeiQ08());
						auxFor.setPeiQ09(auxFor.getPeiQ09()+pedidoItem.getPeiQ09());
						auxFor.setPeiQ10(auxFor.getPeiQ10()+pedidoItem.getPeiQ10());
						auxFor.setPeiQ11(auxFor.getPeiQ11()+pedidoItem.getPeiQ11());
						auxFor.setPeiQ12(auxFor.getPeiQ12()+pedidoItem.getPeiQ12());
						auxFor.setPeiQ13(auxFor.getPeiQ13()+pedidoItem.getPeiQ13());
						auxFor.setPeiQ14(auxFor.getPeiQ14()+pedidoItem.getPeiQ14());

						auxFor.setPeiTot(auxFor.getPeiTot().add(pedidoItem.getPeiTot()));
						auxFor.setPeiPru(pedidoItem.getPeiPru());

						encontrou = true;

						break;
					}

				}
				if(!encontrou) {
					pedido.getPedidoItenses().add(pedidoItem);
				}
			}else{
				pedido.getPedidoItenses().add(pedidoItem);
			}

			formDlgIncluiProduto();
		}else{
			formDlgIncluiProduto();
		}
	}

	public void atualizaTotalQuantidade(){
		int  total = 0;
		BigDecimal preco = new BigDecimal(produtoVO.getPrecoVenda().doubleValue());
		total = total+peiQ01+peiQ02+peiQ03+peiQ04+peiQ05+peiQ06+peiQ07+peiQ08+peiQ09+peiQ10+peiQ11+peiQ12+peiQ13+peiQ14;
		peiQtd = new BigDecimal(total);
		totalPrecoProdutos = peiQtd.multiply(preco);
	}

	public void atualizaTotalPedido(){
		valorTotalPedido = BigDecimal.ZERO;
		totalItensPedido = BigDecimal.ZERO;
		valorTotalPedidoFormatado = "";
		if(pedido.getPedidoItenses().size()>0){
			for(PedidoItens itens : pedido.getPedidoItenses()){
				valorTotalPedido = valorTotalPedido.add((itens.getPeiTot())).setScale(2, 4);
				totalItensPedido = totalItensPedido.add(itens.getPeiQtd());
			}
			valorTotalPedidoFormatado = valorTotalPedido+"";
			valorTotalPedidoFormatado = valorTotalPedidoFormatado.replace(".", ",");

		}


	}

	public void initDescGrade(){
		GradeRN gradeRN = new GradeRN();
		gradePedido.setGrdCod(produtoDialog.getGrade().getGrdCod());
		gradePedido = gradeRN.obterPorId(gradePedido);
	}

	public void formDlgIncluiProduto(){
		pedidoItem = new PedidoItens();
		produtoDialog = new Produto();
		produtoVO = new ProdutoVO();
		peiQtd = BigDecimal.ZERO;
		totalPrecoProdutos = BigDecimal.ZERO;
		gradePedido = new Grade();
		peiQ01 = 0;
		peiQ02 = 0;
		peiQ03 = 0;
		peiQ04 = 0;
		peiQ05 = 0;
		peiQ06 = 0;
		peiQ07 = 0;
		peiQ08 = 0;
		peiQ09 = 0;
		peiQ10 = 0;
		peiQ11 = 0;
		peiQ12 = 0;
		peiQ13 = 0;
		peiQ14 = 0;
		atualizaTotalPedido();
	}

	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Colaborador getColaboradorDialog() {
		return colaboradorDialog;
	}

	public void setColaboradorDialog(Colaborador colaboradorDialog) {
		this.colaboradorDialog = colaboradorDialog;
	}

	public CondicaoPagto getCondicaoDialog() {
		return condicaoDialog;
	}

	public void setCondicaoDialog(CondicaoPagto condicaoDialog) {
		this.condicaoDialog = condicaoDialog;
	}

	public PedidoItens getPedidoItem() {
		return pedidoItem;
	}

	public void setPedidoItem(PedidoItens pedidoItem) {
		this.pedidoItem = pedidoItem;
	}

	public Produto getProdutoDialog() {
		return produtoDialog;
	}

	public void setProdutoDialog(Produto produtoDialog) {
		this.produtoDialog = produtoDialog;
	}

	public BigDecimal getPeiQtd() {
		return peiQtd;
	}

	public void setPeiQtd(BigDecimal peiQtd) {
		this.peiQtd = peiQtd;
	}

	public Integer getPeiQ01() {
		return peiQ01;
	}

	public void setPeiQ01(Integer peiQ01) {
		this.peiQ01 = peiQ01;
	}

	public Integer getPeiQ02() {
		return peiQ02;
	}

	public void setPeiQ02(Integer peiQ02) {
		this.peiQ02 = peiQ02;
	}

	public Integer getPeiQ03() {
		return peiQ03;
	}

	public void setPeiQ03(Integer peiQ03) {
		this.peiQ03 = peiQ03;
	}

	public Integer getPeiQ04() {
		return peiQ04;
	}

	public void setPeiQ04(Integer peiQ04) {
		this.peiQ04 = peiQ04;
	}

	public Integer getPeiQ05() {
		return peiQ05;
	}

	public void setPeiQ05(Integer peiQ05) {
		this.peiQ05 = peiQ05;
	}

	public Integer getPeiQ06() {
		return peiQ06;
	}

	public void setPeiQ06(Integer peiQ06) {
		this.peiQ06 = peiQ06;
	}

	public Integer getPeiQ07() {
		return peiQ07;
	}

	public void setPeiQ07(Integer peiQ07) {
		this.peiQ07 = peiQ07;
	}

	public Integer getPeiQ08() {
		return peiQ08;
	}

	public void setPeiQ08(Integer peiQ08) {
		this.peiQ08 = peiQ08;
	}

	public Integer getPeiQ09() {
		return peiQ09;
	}

	public void setPeiQ09(Integer peiQ09) {
		this.peiQ09 = peiQ09;
	}

	public Integer getPeiQ10() {
		return peiQ10;
	}

	public void setPeiQ10(Integer peiQ10) {
		this.peiQ10 = peiQ10;
	}

	public Integer getPeiQ11() {
		return peiQ11;
	}

	public void setPeiQ11(Integer peiQ11) {
		this.peiQ11 = peiQ11;
	}

	public Integer getPeiQ12() {
		return peiQ12;
	}

	public void setPeiQ12(Integer peiQ12) {
		this.peiQ12 = peiQ12;
	}

	public Integer getPeiQ13() {
		return peiQ13;
	}

	public void setPeiQ13(Integer peiQ13) {
		this.peiQ13 = peiQ13;
	}

	public Integer getPeiQ14() {
		return peiQ14;
	}

	public void setPeiQ14(Integer peiQ14) {
		this.peiQ14 = peiQ14;
	}

	public Grade getGradePedido() {
		return gradePedido;
	}

	public void setGradePedido(Grade gradePedido) {
		this.gradePedido = gradePedido;
	}

	public ProdutoVO getProdutoVO() {
		return produtoVO;
	}

	public void setProdutoVO(ProdutoVO produtoVO) {
		this.produtoVO = produtoVO;
	}

	public BigDecimal getTotalPrecoProdutos() {
		return totalPrecoProdutos;
	}

	public void setTotalPrecoProdutos(BigDecimal totalPrecoProdutos) {
		this.totalPrecoProdutos = totalPrecoProdutos;
	}

	public BigDecimal getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(BigDecimal valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public BigDecimal getTotalItensPedido() {
		return totalItensPedido;
	}

	public void setTotalItensPedido(BigDecimal totalItensPedido) {
		this.totalItensPedido = totalItensPedido;
	}

	public String getValorTotalPedidoFormatado() {
		return valorTotalPedidoFormatado;
	}

	public void setValorTotalPedidoFormatado(String valorTotalPedidoFormatado) {
		this.valorTotalPedidoFormatado = valorTotalPedidoFormatado;
	}

	public boolean isDigitacao() {
		return digitacao;
	}

	public void setDigitacao(boolean digitacao) {
		this.digitacao = digitacao;
	}

	public boolean isAnalise() {
		return analise;
	}

	public void setAnalise(boolean analise) {
		this.analise = analise;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public boolean isReprovado() {
		return reprovado;
	}

	public void setReprovado(boolean reprovado) {
		this.reprovado = reprovado;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
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

	/**
	 * @return the clienteColaboradorPesquisa
	 */
	public Colaborador getClienteColaboradorPesquisa() {
		return clienteColaboradorPesquisa;
	}

	/**
	 * @param clienteColaboradorPesquisa the clienteColaboradorPesquisa to set
	 */
	public void setClienteColaboradorPesquisa(Colaborador clienteColaboradorPesquisa) {
		this.clienteColaboradorPesquisa = clienteColaboradorPesquisa;
	}

	/**
	 * @return the lista
	 */
	public List<Pedido> getLista() {

		PedidoVendaParametro par = new PedidoVendaParametro();
		par.setCodigoCliente(clienteColaboradorPesquisa.getColCod());
		par.setDataInicial(dataInicial);
		par.setDataFinal(dataFinal);
		par.setAnalise(analise);
		par.setAprovado(aprovado);
		par.setCancelado(cancelado);
		par.setDigitacao(digitacao);
		par.setReprovado(reprovado);

		if (lista == null){
			lista = new PedidoVendaRN().listaTabelaParametro(par);
		}

		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<Pedido> lista) {
		this.lista = lista;
	}

	public List<PedidoVO> getListaTelaWeb() {

		PedidoVendaParametro par = new PedidoVendaParametro();
		par.setCodigoCliente(clienteColaboradorPesquisa.getColCod());
		par.setDataInicial(dataInicial);
		par.setDataFinal(dataFinal);
		par.setAnalise(analise);
		par.setAprovado(aprovado);
		par.setCancelado(cancelado);
		par.setDigitacao(digitacao);
		par.setReprovado(reprovado);

		if (listaTelaWeb == null){
			listaTelaWeb = new PedidoVendaRN().pesquisarTelaWeb(tela, par);
		}
		return listaTelaWeb;
	}

	public void setListaTelaWeb(List<PedidoVO> listaTelaWeb) {
		this.listaTelaWeb = listaTelaWeb;
	}

	public List<TelaWebVO> getTela() {
		return tela;
	}

	public void setTela(List<TelaWebVO> tela) {
		this.tela = tela;
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

	public PedidoVO getPedidoVO() {
		return pedidoVO;
	}

	public void setPedidoVO(PedidoVO pedidoVO) {
		this.pedidoVO = pedidoVO;
	}



}

