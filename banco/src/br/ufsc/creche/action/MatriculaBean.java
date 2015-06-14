package br.ufsc.creche.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.model.Matricula;
import br.ufsc.creche.model.MatriculaId;
import br.ufsc.creche.model.Turma;
import br.ufsc.creche.negocio.AlunoRN;
import br.ufsc.creche.negocio.MatriculaRN;
import br.ufsc.creche.negocio.TurmaRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@ViewScoped
public class MatriculaBean extends ActionBean {

	private Matricula matricula = new Matricula();
	private Turma turma = new Turma();
	private Aluno aluno = new Aluno();
	private List<Matricula> lista;


	public void novo() {
		matricula = new Matricula();
		turma = new Turma();
		aluno = new Aluno();
	}


	public void excluir(){
		try {
			MatriculaRN urn = new MatriculaRN();
			urn.excluir(matricula);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir Matricula");
		}
	}

	public void salvar() {
		try {
			MatriculaRN urn = new MatriculaRN();
			TurmaRN   turmaService = new TurmaRN();
			AlunoRN alunoService = new AlunoRN();
			aluno = alunoService.obterPorId(aluno);
			matricula.setNomeAluno(aluno.getNome());
			turma = turmaService.obterPorId(turma);
			matricula.setNomeTurma(turma.getDescricao());
			MatriculaId id = new MatriculaId();
			id.setAluno(aluno);
			id.setTurma(turma);
			matricula.setId(id);
			urn.salvar(matricula);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar Matricula");
		}
	}

	public void obterPorId(){
		MatriculaRN colRN = new MatriculaRN();
		Matricula m = new Matricula();
		MatriculaId id = new MatriculaId();
		id.setAluno(aluno);
		id.setTurma(turma);
		m.setId(id);

		m = colRN.obterPorId(m);

		if(m != null){
			FacesUtil.exibirMensagemErro("O aluno "+aluno.getNome()+" já esta matriculado na turma "+turma.getDescricao());
		}

	}

	public Matricula getMatricula() {
		return matricula;
	}


	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}


	public List<Matricula> getLista() {
		if(lista == null){
			lista = new MatriculaRN().pesquisar(null);
		}
		return lista;
	}

	public void setLista(List<Matricula> lista) {
		this.lista = lista;
	}


	public Turma getTurma() {
		return turma;
	}


	public void setTurma(Turma turma) {
		this.turma = turma;
	}


	public Aluno getAluno() {
		return aluno;
	}


	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
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
			aluno = al;
			obterPorId();
		}
	}

	public void buscaAluno(){
		AlunoRN alRN = new AlunoRN();
		Aluno al = new Aluno();
		al = alRN.obterPorId(aluno);
		aluno = al;
		if(al==null){
			aluno = new Aluno();
		}

		obterPorId();

	}

	public void escolhaDeTurma() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 400); // vert
		options.put("contentWidth", 600);// horizon
		options.put("closeOnEscape", true);
		FacesUtil.openDialog("frame_busca_turma", options, null);
	}

	public void retornoTurmaSelecionado(SelectEvent event) {
		if(event.getObject()!=null){
			Turma ta = (Turma) event.getObject();
			turma= ta;
		}
	}

	public void buscaTurma(){
		TurmaRN tRN = new TurmaRN();
		Turma t = new Turma();
		t = tRN.obterPorId(turma);
		turma = t;
		if(t==null){
			turma = new Turma();
		}
	}



}
