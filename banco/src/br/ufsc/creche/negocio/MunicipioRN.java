package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.MunicipioDAO;
import br.ufsc.creche.model.Municipio;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class MunicipioRN extends RN<Municipio> {

	public MunicipioRN() {
		super(new MunicipioDAO());
	}

	@Override
	public void salvar(Municipio model) throws RNException {
		try {
			if(obterPorId(model) != null) {
				throw new RNException("Codigo Municipio ja existe");
			}
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	@Override
	public void excluir(Municipio model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Municipio obterPorId(Municipio filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Municipio> pesquisar(Municipio filtros) {
		return dao.pesquisar(filtros);
	}

	public Municipio obterPorDescricao(Municipio filtro) {
		return ((MunicipioDAO) dao).obterPorDescricao(filtro);
	}

}
