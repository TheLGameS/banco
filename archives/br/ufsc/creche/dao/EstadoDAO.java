package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.criterion.Order;

import br.ufsc.creche.model.Estado;
import br.ufsc.creche.util.DAOException;

public class EstadoDAO extends DAO<Estado> {

	@Override
	public void salvar(Estado model) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Estado model) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public Estado obterPorId(Estado filtro) {
		return (Estado) sessao.get(Estado.class, filtro.getEstSig());
	}

	@Override
	public List<Estado> pesquisar(Estado filtros) {
		return sessao.createCriteria(Estado.class).addOrder(Order.asc("estSig")).list();
	}

}
