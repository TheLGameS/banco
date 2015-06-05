package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Query;

import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.util.DAOException;


public class AlunoDAO extends DAO<Aluno> {

	@Override
	public void salvar(Aluno model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(Aluno model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Aluno obterPorId(Aluno filtro) {
		Aluno col = (Aluno) sessao.get(Aluno.class, filtro.getCodigoAluno());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@Override
	public List<Aluno> pesquisar(Aluno filtros) {
		return sessao.createCriteria(Aluno.class).list();
	}

	   
}