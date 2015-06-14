package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.MatriculaDAO;
import br.ufsc.creche.model.Matricula;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class MatriculaRN  extends RN<Matricula>{

	public MatriculaRN() {
		super(new MatriculaDAO());
	}

	@Override
	public void salvar(Matricula model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(Matricula model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Matricula obterPorId(Matricula filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Matricula> pesquisar(Matricula filtros) {
		return dao.pesquisar(filtros);
	}



}
