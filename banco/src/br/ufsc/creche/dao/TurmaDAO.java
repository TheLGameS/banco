package br.ufsc.creche.dao;

import java.util.List;

import br.ufsc.creche.model.Turma;
import br.ufsc.creche.util.DAOException;


public class TurmaDAO extends DAO<Turma> {

	@Override
	public void salvar(Turma model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(Turma model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Turma obterPorId(Turma filtro) {
		Turma col = (Turma) sessao.get(Turma.class, filtro.getCodigoTurma());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@Override
	public List<Turma> pesquisar(Turma filtros) {
		return sessao.createCriteria(Turma.class).list();
	}



}