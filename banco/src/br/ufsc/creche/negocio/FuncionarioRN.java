package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.AlunoDAO;
import br.ufsc.creche.dao.FuncionarioDAO;
import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.model.Funcionario;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class FuncionarioRN  extends RN<Funcionario>{

	public FuncionarioRN() {
		super(new FuncionarioDAO());
	}
	
	@Override
	public void salvar(Funcionario model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(Funcionario model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Funcionario obterPorId(Funcionario filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Funcionario> pesquisar(Funcionario filtros) {
		return dao.pesquisar(filtros);
	}
	
	
	public Funcionario obterPorCpf(Funcionario filtro) {
		return ((FuncionarioDAO) dao).obterPorCpf(filtro);
	}

}
