package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufsc.creche.model.Municipio;
import br.ufsc.creche.util.DAOException;

public class MunicipioDAO extends DAO<Municipio> {

	@Override
	public void salvar(Municipio model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(Municipio model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Municipio obterPorId(Municipio filtro) {
		return (Municipio) sessao.get(Municipio.class, filtro.getMunCod());
	}

	@Override
	public List<Municipio> pesquisar(Municipio filtros) {
		Criteria criteria = sessao.createCriteria(Municipio.class);

		if(filtros!=null) {
			criteria.add(Restrictions.eq("estado.estSig", filtros.getEstado().getEstSig()));
		}
		criteria.addOrder(Order.asc("munNom"));

		return criteria.list();
	}

	public Municipio obterPorDescricao(Municipio filtro) {
		Criteria criteria = sessao.createCriteria(Municipio.class);
		criteria.add(Restrictions.eq("munNom", filtro.getMunNom()));
		return (Municipio) criteria.uniqueResult();
	}

}
