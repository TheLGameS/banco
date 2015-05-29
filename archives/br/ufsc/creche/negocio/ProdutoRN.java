package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.VO.ProdutoVO;
import br.ufsc.creche.dao.ProdutoDAO;
import br.ufsc.creche.model.Produto;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class ProdutoRN extends RN<Produto> {

	public ProdutoRN() {
		super(new ProdutoDAO());
	}

	@Override
	public void salvar(Produto model) throws RNException {
		try {
			if(obterPorId(model) != null) {
				throw new RNException("Codigo Produto ja existe");
			}
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	@Override
	public void excluir(Produto model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Produto obterPorId(Produto filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Produto> pesquisar(Produto filtros) {
		return dao.pesquisar(filtros);
	}

	public List<ProdutoVO> pesquisarVO(Produto filtros)    {
		return ((ProdutoDAO)dao).pesquisarVO(filtros);
	}

	public ProdutoVO pesquisarProdutoVO(Produto filtros)    {
		return ((ProdutoDAO)dao).pesquisarProdutoVO(filtros);
	}

	public Produto obterPorCcp(Produto produto) {
		return ((ProdutoDAO)dao).obterPorCcp(produto);
	}

}
