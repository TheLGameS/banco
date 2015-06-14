package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufsc.creche.model.Matricula;
import br.ufsc.creche.util.DAOException;


public class MatriculaDAO extends DAO<Matricula> {

	@Override
	public void salvar(Matricula model) throws DAOException {
		try {
			sessao.save(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(Matricula model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Matricula obterPorId(Matricula filtro) {
		Criteria c = sessao.createCriteria(Matricula.class);
		c.add(Restrictions.eq("id.turma.codigoTurma", filtro.getId().getTurma().getCodigoTurma()));
		c.add(Restrictions.eq("id.aluno.codigoAluno", filtro.getId().getAluno().getCodigoAluno()));

		Matricula col = (Matricula) c.uniqueResult();
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@Override
	public List<Matricula> pesquisar(Matricula filtros) {
		return sessao.createCriteria(Matricula.class).list();
	}



}