package br.ufsc.creche.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.SelectEvent;

import br.ufsc.creche.model.Funcionario;
import br.ufsc.creche.model.Turma;
import br.ufsc.creche.negocio.FuncionarioRN;
import br.ufsc.creche.negocio.TurmaRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class TurmaBean extends ActionBean {

	private Turma turma = new Turma();
	private List<Turma> lista;
	private Funcionario funcionarioPesquisa = new Funcionario();

	public void novo() {
		turma = new Turma();
	}


	public void excluir(){
		try {
			TurmaRN urn = new TurmaRN();
			urn.excluir(turma);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir Turma");
		}
	}

	public void salvar() {
		try {
			TurmaRN urn = new TurmaRN();
			FuncionarioRN funcionarioService = new FuncionarioRN();
			turma.setFuncionario(funcionarioPesquisa);
			funcionarioPesquisa = funcionarioService.obterPorId(funcionarioPesquisa);
			turma.setNomeFuncionario(funcionarioPesquisa.getNome());
			urn.salvar(turma);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar Turma");
		}
	}

	public void obterPorId(){
		TurmaRN colRN = new TurmaRN();
		turma = colRN.obterPorId(turma);
	}

	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}


	public List<Turma> getLista() {
		if(lista == null){
			lista = new TurmaRN().pesquisar(null);
		}
		return lista;
	}


	public void setLista(List<Turma> lista) {
		this.lista = lista;
	}

	public void turmaSelecionadoDialog(Turma t) {
		FacesUtil.closeDialog(t);
	}


	public Funcionario getFuncionarioPesquisa() {
		return funcionarioPesquisa;
	}


	public void setFuncionarioPesquisa(Funcionario funcionarioPesquisa) {
		this.funcionarioPesquisa = funcionarioPesquisa;
	}

	public void escolhaDeFuncionario() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 400); // vert
		options.put("contentWidth", 600);// horizon
		options.put("closeOnEscape", true);
		FacesUtil.openDialog("frame_busca_funcionario", options, null);
	}

	public void retornoFuncionarioSelecionado(SelectEvent event) {
		if(event.getObject()!=null){
			Funcionario al = (Funcionario) event.getObject();
			turma.setFuncionario(al);
			funcionarioPesquisa = al;
		}
	}

	public void buscaFuncionario(){
		FuncionarioRN alRN = new FuncionarioRN();
		Funcionario al = new Funcionario();

		al = alRN.obterPorId(funcionarioPesquisa);
		funcionarioPesquisa = al;
		if(al==null){
			funcionarioPesquisa   = new Funcionario();
		}
	}
}
