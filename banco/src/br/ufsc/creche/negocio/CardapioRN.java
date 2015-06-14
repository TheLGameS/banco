package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.CardapioDAO;
import br.ufsc.creche.model.Cardapio;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class CardapioRN  extends RN<Cardapio>{

	public CardapioRN() {
		super(new CardapioDAO());
	}

	@Override
	public void salvar(Cardapio model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(Cardapio model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Cardapio obterPorId(Cardapio filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Cardapio> pesquisar(Cardapio filtros) {
		return dao.pesquisar(filtros);
	}




}
