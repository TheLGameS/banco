package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.AlunoDAO;
import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class AlunoRN  extends RN<Aluno>{

	public AlunoRN() {
		super(new AlunoDAO());
	}
	
	@Override
	public void salvar(Aluno model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(Aluno model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Aluno obterPorId(Aluno filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Aluno> pesquisar(Aluno filtros) {
		return dao.pesquisar(filtros);
	}
	
	
	public Aluno obterPorMatricula(Aluno filtro) {
		return ((AlunoDAO) dao).obterPorMatricula(filtro);
	}

}
