package br.ufsc.creche.dao;

import java.util.List;

import br.ufsc.creche.model.Cardapio;
import br.ufsc.creche.util.DAOException;


public class CardapioDAO extends DAO<Cardapio> {

	@Override
	public void salvar(Cardapio model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(Cardapio model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Cardapio obterPorId(Cardapio filtro) {
		Cardapio col = (Cardapio) sessao.get(Cardapio.class, filtro.getCodigoCardapio());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@Override
	public List<Cardapio> pesquisar(Cardapio filtros) {
		return sessao.createCriteria(Cardapio.class).list();
	}



}