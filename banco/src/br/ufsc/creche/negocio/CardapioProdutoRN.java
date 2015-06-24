package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.VO.GraficoDietaVO;
import br.ufsc.creche.dao.CardapioProdutoDAO;
import br.ufsc.creche.model.Cardapio;
import br.ufsc.creche.model.CardapioProduto;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class CardapioProdutoRN  extends RN<CardapioProduto>{

	public CardapioProdutoRN() {
		super(new CardapioProdutoDAO());
	}

	@Override
	public void salvar(CardapioProduto model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(CardapioProduto model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public CardapioProduto obterPorId(CardapioProduto filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<CardapioProduto> pesquisar(CardapioProduto filtros) {
		return dao.pesquisar(filtros);
	}

	public List<CardapioProduto> pesquisarProduto(Cardapio filtros) {
		return ((CardapioProdutoDAO) dao).pesquisarProduto(filtros);
	}

	public List<GraficoDietaVO> montarGrafico(Cardapio c) {
		return ((CardapioProdutoDAO) dao).montarGrafico(c);
	}

}
