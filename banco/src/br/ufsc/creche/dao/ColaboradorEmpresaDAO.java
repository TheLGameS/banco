package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import br.ufsc.creche.model.ColaboradorEmpresa;
import br.ufsc.creche.util.DAOException;

public class ColaboradorEmpresaDAO extends DAO<ColaboradorEmpresa> {

	@Override
	public void salvar(ColaboradorEmpresa model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(ColaboradorEmpresa model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public ColaboradorEmpresa obterPorId(ColaboradorEmpresa filtro) {
		ColaboradorEmpresa col = (ColaboradorEmpresa) sessao.get(ColaboradorEmpresa.class, filtro.getId());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@Override
	public List<ColaboradorEmpresa> pesquisar(ColaboradorEmpresa filtros)   {
		Criteria criteria = sessao.createCriteria(ColaboradorEmpresa.class);
		criteria.addOrder(Order.asc("id.clrCli"));
		return criteria.list();
	}
}