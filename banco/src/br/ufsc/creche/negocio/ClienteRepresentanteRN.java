package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.ClienteRepresentanteDAO;
import br.ufsc.creche.model.ClienteRepresentante;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;


public class ClienteRepresentanteRN extends RN<ClienteRepresentante> {

	public ClienteRepresentanteRN() {
		super(new ClienteRepresentanteDAO());
	}

	@Override
	public void salvar(ClienteRepresentante model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(ClienteRepresentante model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public ClienteRepresentante obterPorId(ClienteRepresentante filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<ClienteRepresentante> pesquisar(ClienteRepresentante filtros) {
		return dao.pesquisar(filtros);
	}

	public void alterar(ClienteRepresentante model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

}
