package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.Diversos;


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

	public Aluno obterPorMatricula(Aluno filtro) {
		Criteria criteria = sessao.createCriteria(Aluno.class);
		criteria.add(Restrictions.eq("matricula", filtro.getMatricula()));
		Aluno colDoc = (Aluno) criteria.uniqueResult();
		if(colDoc!=null) {
			sessao.setReadOnly(colDoc, true);
		}
		return colDoc;
	}
	   
}