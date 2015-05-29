package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.GradeDAO;
import br.ufsc.creche.model.Grade;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class GradeRN extends RN<Grade> {

	public GradeRN() {
		super(new GradeDAO());
	}

	@Override
	public void salvar(Grade model) throws RNException {
		try {
			if(obterPorId(model) != null) {
				throw new RNException("Codigo Grade ja existe");
			}
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	@Override
	public void excluir(Grade model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Grade obterPorId(Grade filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Grade> pesquisar(Grade filtros) {
		return dao.pesquisar(filtros);
	}

	public void alterar(Grade model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

}
