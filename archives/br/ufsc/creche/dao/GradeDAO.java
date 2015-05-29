package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import br.ufsc.creche.model.Grade;
import br.ufsc.creche.util.DAOException;

public class GradeDAO extends DAO<Grade> {

	@Override
	public void salvar(Grade model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(Grade model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Grade obterPorId(Grade filtro) {
		return (Grade) sessao.get(Grade.class, filtro.getGrdCod());
	}

	@Override
	public List<Grade> pesquisar(Grade filtros)   {
		Criteria criteria = sessao.createCriteria(Grade.class);
		criteria.addOrder(Order.asc("grdCod"));
		return criteria.list();
	}

}