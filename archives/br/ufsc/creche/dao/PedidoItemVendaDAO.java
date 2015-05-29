package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import br.ufsc.creche.model.PedidoItens;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.HibernateUtil;

public class PedidoItemVendaDAO extends DAO<PedidoItens> {

	@Override
	public void salvar(PedidoItens model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(PedidoItens model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

	}

	@Override
	public PedidoItens obterPorId(PedidoItens filtro) {
		return (PedidoItens) sessao.get(PedidoItens.class, filtro.getPeiCod());
	}

	@Override
	public List<PedidoItens> pesquisar(PedidoItens filtros)   {
		Criteria criteria = sessao.createCriteria(PedidoItens.class);
		criteria.addOrder(Order.asc("peiCod"));
		return criteria.list();
	}

	public Integer geraCodigoMax(){

		if (!sessao.isOpen()){
			sessao = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria criteria = sessao.createCriteria(PedidoItens.class);

		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.max("peiCod"));
		criteria.setProjection(projList);

		Number result = (Number) criteria.uniqueResult();

		int genId = result.intValue();

		genId++;

		return  genId;
	}
}