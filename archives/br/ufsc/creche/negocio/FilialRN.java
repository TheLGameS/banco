package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.FilialDAO;
import br.ufsc.creche.model.Filial;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;


public class FilialRN extends RN<Filial> {

	public FilialRN() {
		super(new FilialDAO());		
	}

	@Override
	public void salvar(Filial model) throws RNException {
		try {		
			if(obterPorId(model) != null)
				throw new RNException("Codigo Filial ja existe");
			dao.salvar(model);			
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	@Override
	public void excluir(Filial model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			throw new RNException(e.getMessage());
		}		
	}

	@Override
	public Filial obterPorId(Filial filtro) {
		return dao.obterPorId(filtro);
	}
	
	public Filial obterPorApelido(Filial filtro) {
		return ((FilialDAO) dao).obterPorApelido(filtro);
	}

	@Override
	public List<Filial> pesquisar(Filial filtros) {		
		return dao.pesquisar(filtros);
	}

}
