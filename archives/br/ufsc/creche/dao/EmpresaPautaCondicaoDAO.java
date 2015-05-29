package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufsc.creche.model.EmpresaPautaCondicao;
import br.ufsc.creche.util.DAOException;

public class EmpresaPautaCondicaoDAO extends DAO<EmpresaPautaCondicao> {

	@Override
	public void salvar(EmpresaPautaCondicao model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(EmpresaPautaCondicao model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public EmpresaPautaCondicao obterPorId(EmpresaPautaCondicao filtro) {
		return (EmpresaPautaCondicao) sessao.get(EmpresaPautaCondicao.class, filtro.getEpcCod());
	}

	@Override
	public List<EmpresaPautaCondicao> pesquisar(EmpresaPautaCondicao filtros) {
		Criteria criteria = sessao.createCriteria(EmpresaPautaCondicao.class);
		criteria.add(Restrictions.eq("epcPat", filtros.getEmpresaPauta().getId().getEptPat()));
		criteria.addOrder(Order.asc("epcCod"));
		return criteria.list();
	}


}
