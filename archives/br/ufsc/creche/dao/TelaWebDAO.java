package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufsc.creche.model.TelaWeb;
import br.ufsc.creche.util.DAOException;

public class TelaWebDAO extends DAO<TelaWeb> {

	@Override
	public void salvar(TelaWeb model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(TelaWeb model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public TelaWeb obterPorId(TelaWeb filtro) {
		return (TelaWeb) sessao.get(TelaWeb.class, filtro.getId().getTlwFrm());
	}

	@Override
	public List<TelaWeb> pesquisar(TelaWeb filtros) {
		Criteria criteria = sessao.createCriteria(TelaWeb.class);

		criteria.addOrder(Order.asc("id.tlwFrm"));

		return criteria.list();
	}

	public TelaWeb obterPorDescricao(TelaWeb filtro) {
		Criteria criteria = sessao.createCriteria(TelaWeb.class);
		criteria.add(Restrictions.eq("id.tlwFrm", filtro.getId().getTlwFrm()));
		return (TelaWeb) criteria.uniqueResult();
	}

	public List<TelaWeb> pesquisarPorNomeJanela(TelaWeb filtros) {
		Criteria criteria = sessao.createCriteria(TelaWeb.class);
		criteria.add(Restrictions.eq("id.tlwFrm", filtros.getId().getTlwFrm()));
		return criteria.list();
	}

	public TelaWeb pesquisarPorNomeJanelaNomeCampo(TelaWeb filtros) {
		Criteria criteria = sessao.createCriteria(TelaWeb.class);
		criteria.add(Restrictions.eq("id.tlwFrm", filtros.getId().getTlwFrm()));
		criteria.add(Restrictions.eq("id.tlwCpo", filtros.getId().getTlwCpo()));
		return (TelaWeb) criteria.uniqueResult();
	}

	public List<TelaWeb> pesquisarPorNomeJanelaSomenteDisponivel(TelaWeb filtros) {
		Criteria criteria = sessao.createCriteria(TelaWeb.class);
		criteria.add(Restrictions.eq("id.tlwFrm", filtros.getId().getTlwFrm()));
		criteria.add(Restrictions.eq("tlwDis", "S"));
		return criteria.list();
	}


}
