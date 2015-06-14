package br.ufsc.creche.dao;

import java.util.List;

import br.ufsc.creche.model.Produto;
import br.ufsc.creche.util.DAOException;


public class ProdutoDAO extends DAO<Produto> {

	@Override
	public void salvar(Produto model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(Produto model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Produto obterPorId(Produto filtro) {
		Produto col = (Produto) sessao.get(Produto.class, filtro.getCodigoProduto());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@Override
	public List<Produto> pesquisar(Produto filtros) {
		return sessao.createCriteria(Produto.class).list();
	}



}