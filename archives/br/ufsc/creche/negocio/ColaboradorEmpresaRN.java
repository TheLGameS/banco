package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.ColaboradorEmpresaDAO;
import br.ufsc.creche.model.ColaboradorEmpresa;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;


public class ColaboradorEmpresaRN extends RN<ColaboradorEmpresa> {

	public ColaboradorEmpresaRN() {
		super(new ColaboradorEmpresaDAO());
	}

	@Override
	public void salvar(ColaboradorEmpresa model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(ColaboradorEmpresa model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public ColaboradorEmpresa obterPorId(ColaboradorEmpresa filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<ColaboradorEmpresa> pesquisar(ColaboradorEmpresa filtros) {
		return dao.pesquisar(filtros);
	}

	public void alterar(ColaboradorEmpresa model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

}
