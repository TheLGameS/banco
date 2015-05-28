package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.VO.ClienteVO;
import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.dao.ClienteDAO;
import br.ufsc.creche.model.Cliente;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;


public class ClienteRN extends RN<Cliente> {

	public ClienteRN() {
		super(new ClienteDAO());
	}

	@Override
	public void salvar(Cliente model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(Cliente model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Cliente obterPorId(Cliente filtro) {
		return dao.obterPorId(filtro);
	}

	public Cliente obterPorCodigoVO(ClienteVO filtro) {
		return ((ClienteDAO) dao).obterPorCodigoVO(filtro);
	}

	@Override
	public List<Cliente> pesquisar(Cliente filtros) {
		return dao.pesquisar(filtros);
	}

	public List<ClienteVO> pesquisarTelaWeb(List<TelaWebVO> tela, int tipoLista) {
		return ((ClienteDAO) dao).pesquisarTelaWeb(tela,tipoLista);
	}

	public void alterar(Cliente model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

}
