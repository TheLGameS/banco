package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.VO.PedidoVO;
import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.dao.PedidoVendaDAO;
import br.ufsc.creche.model.Pedido;
import br.ufsc.creche.parameter.PedidoVendaParametro;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class PedidoVendaRN extends RN<Pedido> {

	public PedidoVendaRN() {
		super(new PedidoVendaDAO());
	}

	@Override
	public void salvar(Pedido model) throws RNException {
		try {
			if(obterPorId(model) != null) {
				throw new RNException("Codigo Pedido ja existe");
			}
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	@Override
	public void excluir(Pedido model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Pedido obterPorId(Pedido filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Pedido> pesquisar(Pedido filtros) {
		return dao.pesquisar(filtros);
	}

	public List<Pedido> listaTabelaParametro(PedidoVendaParametro filtros) {
		return ((PedidoVendaDAO) dao).listaTabelaParametro(filtros);
	}

	public List<PedidoVO> pesquisarTelaWeb(List<TelaWebVO> tela, PedidoVendaParametro filtros) {
		return ((PedidoVendaDAO) dao).pesquisarTelaWeb(tela,filtros);
	}

	public void alterar(Pedido model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	public Integer geraCodigoSequenciaMax(){
		return ((PedidoVendaDAO) dao).geraCodigoSequenciaMax();
	}

	public Integer geraCodigoMax(){
		return ((PedidoVendaDAO) dao).geraCodigoMax();
	}
}
