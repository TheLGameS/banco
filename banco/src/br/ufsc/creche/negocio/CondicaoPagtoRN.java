package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.CondicaoPagtoDAO;
import br.ufsc.creche.model.CondicaoPagto;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;


public class CondicaoPagtoRN extends RN<CondicaoPagto> {

	public CondicaoPagtoRN() {
		super(new CondicaoPagtoDAO());		
	}

	@Override
	public void salvar(CondicaoPagto model) throws RNException {
		try {		
			if(obterPorId(model) != null)
				throw new RNException("Codigo CondicaoPagto ja existe");
			dao.salvar(model);			
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	@Override
	public void excluir(CondicaoPagto model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			throw new RNException(e.getMessage());
		}		
	}

	@Override
	public CondicaoPagto obterPorId(CondicaoPagto filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<CondicaoPagto> pesquisar(CondicaoPagto filtros) {		
		return dao.pesquisar(filtros);
	}

}
