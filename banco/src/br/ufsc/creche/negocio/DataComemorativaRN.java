package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.DataComemorativaDAO;
import br.ufsc.creche.model.DatasComemorativa;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class DataComemorativaRN  extends RN<DatasComemorativa>{

	public DataComemorativaRN() {
		super(new DataComemorativaDAO());
	}

	@Override
	public void salvar(DatasComemorativa model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(DatasComemorativa model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public DatasComemorativa obterPorId(DatasComemorativa filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<DatasComemorativa> pesquisar(DatasComemorativa filtros) {
		return dao.pesquisar(filtros);
	}



}
