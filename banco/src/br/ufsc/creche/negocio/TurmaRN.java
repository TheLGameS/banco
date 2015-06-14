package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.TurmaDAO;
import br.ufsc.creche.model.Turma;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class TurmaRN  extends RN<Turma>{

	public TurmaRN() {
		super(new TurmaDAO());
	}

	@Override
	public void salvar(Turma model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(Turma model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Turma obterPorId(Turma filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Turma> pesquisar(Turma filtros) {
		return dao.pesquisar(filtros);
	}



}
