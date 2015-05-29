package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import br.ufsc.creche.model.CondicaoPagto;
import br.ufsc.creche.util.DAOException;

public class CondicaoPagtoDAO extends DAO<CondicaoPagto> {

	@Override
	public void salvar(CondicaoPagto model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(CondicaoPagto model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public CondicaoPagto obterPorId(CondicaoPagto filtro) {
		return (CondicaoPagto) sessao.get(CondicaoPagto.class, filtro.getCdpCod());
	}

	@Override
	public List<CondicaoPagto> pesquisar(CondicaoPagto filtros) {
		Criteria criteria = sessao.createCriteria(CondicaoPagto.class);
		criteria.addOrder(Order.asc("cdpNom"));
		return criteria.list();
	}

}
