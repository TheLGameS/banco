package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.AtividadeDAO;
import br.ufsc.creche.model.Atividade;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class AtividadeRN  extends RN<Atividade>{

	public AtividadeRN() {
		super(new AtividadeDAO());
	}

	@Override
	public void salvar(Atividade model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(Atividade model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Atividade obterPorId(Atividade filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Atividade> pesquisar(Atividade filtros) {
		return dao.pesquisar(filtros);
	}



}
