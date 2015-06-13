package br.ufsc.creche.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.SelectEvent;

import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.model.ContasReceber;
import br.ufsc.creche.model.Pagamento;
import br.ufsc.creche.negocio.AlunoRN;
import br.ufsc.creche.negocio.ContasReceberRN;
import br.ufsc.creche.negocio.PagamentoRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class PagamentoBean extends ActionBean {

	private Pagamento pagamento = new Pagamento();
	private ContasReceber contasReceber = new ContasReceber();
	private Aluno alunoPagamento = new Aluno();
	private List<Pagamento> lista;
	private List<ContasReceber> listaReceberAluno;

	public void novo() {
		pagamento = new Pagamento();
	}


	public void excluir(){
		try {
			PagamentoRN urn = new PagamentoRN();
			urn.excluir(pagamento);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir Pagamento");
		}
	}

	public void salvar() {
		try {
			PagamentoRN urn = new PagamentoRN();
			urn.salvar(pagamento);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar Pagamento");
		}
	}


	public void obterPorId(){
		PagamentoRN colRN = new PagamentoRN();
		pagamento = colRN.obterPorId(pagamento);
	}

	public void obterRecebimentoPorId(){
		ContasReceberRN colRN = new ContasReceberRN();
		contasReceber = colRN.obterPorId(contasReceber);
	}

	public Pagamento getPagamento() {
		return pagamento;
	}


	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}


	public List<Pagamento> getLista() {
		if(lista == null){
			lista = new PagamentoRN().pesquisar(null);
		}
		return lista;
	}


	public void setLista(List<Pagamento> lista) {
		this.lista = lista;
	}


	public ContasReceber getContasReceber() {
		return contasReceber;
	}


	public void setContasReceber(ContasReceber contasReceber) {
		this.contasReceber = contasReceber;
	}


	public List<ContasReceber> getListaReceberAluno() {
		if(listaReceberAluno == null && alunoPagamento.getCodigoAluno() != null ){
			listaReceberAluno = new ContasReceberRN().listaContasReceberPorAluno(alunoPagamento);
		}

		return listaReceberAluno;
	}


	public void setListaReceberAluno(List<ContasReceber> listaReceberAluno) {
		this.listaReceberAluno = listaReceberAluno;
	}


	public Aluno getAlunoPagamento() {
		return alunoPagamento;
	}


	public void setAlunoPagamento(Aluno alunoPagamento) {
		this.alunoPagamento = alunoPagamento;
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
			pagamento.setCodigoAluno(al);
			alunoPagamento = al;
			listaReceberAluno = null;
		}
	}

	public void buscaAluno(){
		AlunoRN alRN = new AlunoRN();
		Aluno al = new Aluno();

		al = alRN.obterPorId(alunoPagamento);
		alunoPagamento = al;
		if(al==null){
			alunoPagamento = new Aluno();
		}else{
			listaReceberAluno = null;
		}


	}





}
