package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.AlunoDAO;
import br.ufsc.creche.dao.PagamentoDAO;
import br.ufsc.creche.model.Pagamento;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class PagamentoRN  extends RN<Pagamento>{

	public PagamentoRN() {
		super(new PagamentoDAO());
	}
	
	@Override
	public void salvar(Pagamento model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(Pagamento model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public Pagamento obterPorId(Pagamento filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Pagamento> pesquisar(Pagamento filtros) {
		return dao.pesquisar(filtros);
	}

}
