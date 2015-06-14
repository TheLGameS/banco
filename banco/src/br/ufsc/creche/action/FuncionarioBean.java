package br.ufsc.creche.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.model.Funcionario;
import br.ufsc.creche.negocio.FuncionarioRN;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


@ManagedBean
@RequestScoped
public class FuncionarioBean extends ActionBean {

	private Funcionario funcionario = new Funcionario();
	private List<Funcionario> lista;

	public void novo() {
		funcionario = new Funcionario();
	}


	public void excluir(){
		try {
			FuncionarioRN urn = new FuncionarioRN();
			urn.excluir(funcionario);
			lista = null;
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Excluir Funcionario");
		}
	}

	public void salvar() {
		try {
			FuncionarioRN urn = new FuncionarioRN();
			urn.salvar(funcionario);
		} catch (RNException e) {
			FacesUtil.exibirMensagemErro("Erro ao tentar Salvar Funcionario");
		}
	}


	public void verificaCpf(){
		FuncionarioRN urn = new FuncionarioRN();
		Funcionario auxMat = urn.obterPorCpf(funcionario);

		if(auxMat !=null){
			FacesUtil.exibirMensagemErro("Este cpf já pertence a outro Funcionario");
		}
	}

	public void obterPorId(){
		FuncionarioRN colRN = new FuncionarioRN();
		funcionario = colRN.obterPorId(funcionario);
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public List<Funcionario> getLista() {
		if(lista == null){
			lista = new FuncionarioRN().pesquisar(null);
		}
		return lista;
	}


	public void setLista(List<Funcionario> lista) {
		this.lista = lista;
	}




	public void funcionarioSelecionadoDialog(Funcionario f) {
		FacesUtil.closeDialog(f);
	}





}
