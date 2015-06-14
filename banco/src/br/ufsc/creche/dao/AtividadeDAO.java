package br.ufsc.creche.dao;

import java.util.List;

import br.ufsc.creche.model.Atividade;
import br.ufsc.creche.util.DAOException;


public class AtividadeDAO extends DAO<Atividade> {

	@Override
	public void salvar(Atividade model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(Atividade model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Atividade obterPorId(Atividade filtro) {
		Atividade col = (Atividade) sessao.get(Atividade.class, filtro.getCodigoAtividade());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@Override
	public List<Atividade> pesquisar(Atividade filtros) {
		return sessao.createCriteria(Atividade.class).list();
	}


}