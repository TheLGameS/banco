package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.EmpresaPautaCondicaoDAO;
import br.ufsc.creche.model.EmpresaPautaCondicao;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class EmpresaPautaCondicaoRN extends RN<EmpresaPautaCondicao> {

	public EmpresaPautaCondicaoRN() {
		super(new EmpresaPautaCondicaoDAO());		
	}

	@Override
	public void salvar(EmpresaPautaCondicao model) throws RNException {
		try {		
			if(obterPorId(model) != null)
				throw new RNException("Codigo EmpresaPautaCondicao ja existe");
			dao.salvar(model);			
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	@Override
	public void excluir(EmpresaPautaCondicao model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			throw new RNException(e.getMessage());
		}		
	}

	@Override
	public EmpresaPautaCondicao obterPorId(EmpresaPautaCondicao filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<EmpresaPautaCondicao> pesquisar(EmpresaPautaCondicao filtros) {		
		return dao.pesquisar(filtros);
	}

}
