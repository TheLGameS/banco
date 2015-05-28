package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufsc.creche.model.Filial;
import br.ufsc.creche.util.DAOException;


public class FilialDAO extends DAO<Filial> {

	@Override
	public void salvar(Filial model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(Filial model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Filial obterPorId(Filial filtro) {
		return (Filial) sessao.get(Filial.class, filtro.getId());
	}

	public Filial obterPorApelido(Filial filtro) {
		Criteria criteria = sessao.createCriteria(Filial.class);
		criteria.add(Restrictions.eq("filApe", filtro.getFilApe()));
		return (Filial) criteria.uniqueResult();
	}

	@Override
	public List<Filial> pesquisar(Filial filtros) {
		Criteria criteria = sessao.createCriteria(Filial.class);
		criteria.addOrder(Order.asc("filNmf"));
		return criteria.list();
	}


}
