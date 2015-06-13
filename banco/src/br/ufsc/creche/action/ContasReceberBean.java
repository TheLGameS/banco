package br.ufsc.creche.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.SelectEvent;

import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.model.ContasReceber;
import br.ufsc.creche.negocio.AlunoRN;
import br.ufsc.creche.negocio.ContasReceberRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class ContasReceberBean extends ActionBean {

	private ContasReceber contasReceber = new ContasReceber();
	private Aluno alunoPesquisa = new Aluno();
	private List<ContasReceber> lista;
	private List<ContasReceber> listaPorAluno;

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
			contasReceber.setCodigoAluno(alunoPesquisa);
			urn.salvar(contasReceber);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar ContasReceber");
		}
	}

	public void obterPorId(){
		ContasReceberRN colRN = new ContasReceberRN();
		contasReceber = colRN.obterPorId(contasReceber);

		AlunoRN alunoService = new AlunoRN();
		alunoPesquisa = alunoService.obterPorId(contasReceber.getCodigoAluno());


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
			contasReceber.setCodigoAluno(al);
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

}
