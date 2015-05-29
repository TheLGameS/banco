package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

import br.ufsc.creche.model.ClienteRepresentante;
import br.ufsc.creche.util.DAOException;

public class ClienteRepresentanteDAO extends DAO<ClienteRepresentante> {

	@Override
	public void salvar(ClienteRepresentante model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(ClienteRepresentante model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public ClienteRepresentante obterPorId(ClienteRepresentante filtro) {
		ClienteRepresentante col = (ClienteRepresentante) sessao.get(ClienteRepresentante.class, filtro.getId());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@Override
	public List<ClienteRepresentante> pesquisar(ClienteRepresentante filtros)   {
		Criteria criteria = sessao.createCriteria(ClienteRepresentante.class);
		criteria.addOrder(Order.asc("id.clrCli"));
		return criteria.list();
	}

}