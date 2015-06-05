package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.negocio.AlunoRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class AlunoBean extends ActionBean {

	private Aluno aluno = new Aluno();
	private List<Aluno> lista;

	public void novo() {
		aluno = new Aluno();
	}


	public void excluir(){
		try {
			AlunoRN urn = new AlunoRN();
			urn.excluir(aluno);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir Aluno");
		}
	}

	public void salvar() {
		try {
			AlunoRN urn = new AlunoRN();
			urn.salvar(aluno);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar Aluno");
		}
	}

	
	public void verificaMat(){
		AlunoRN urn = new AlunoRN();
		Aluno auxMat = urn.obterPorMatricula(aluno);
		
		if(auxMat !=null){
			FacesUtil.exibirMensagemErro("Esta matricula já pertence a outro Aluno");
		}
	}

	public void obterPorId(){
		AlunoRN colRN = new AlunoRN();
		aluno = colRN.obterPorId(aluno);
	}
	
	public Aluno getAluno() {
		return aluno;
	}


	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}


	public List<Aluno> getLista() {
		if(lista == null){
			lista = new AlunoRN().pesquisar(null);
		}
		return lista;
	}


	public void setLista(List<Aluno> lista) {
		this.lista = lista;
	}


	 
	 

}
