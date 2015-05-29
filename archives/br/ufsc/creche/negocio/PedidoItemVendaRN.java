package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.PedidoItemVendaDAO;
import br.ufsc.creche.model.PedidoItens;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class PedidoItemVendaRN extends RN<PedidoItens> {

	public PedidoItemVendaRN() {
		super(new PedidoItemVendaDAO());
	}

	@Override
	public void salvar(PedidoItens model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	@Override
	public void excluir(PedidoItens model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public PedidoItens obterPorId(PedidoItens filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<PedidoItens> pesquisar(PedidoItens filtros) {
		return dao.pesquisar(filtros);
	}

	public void alterar(PedidoItens model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	public Integer geraCodigoMax(){
		return ((PedidoItemVendaDAO) dao).geraCodigoMax();
	}

}
