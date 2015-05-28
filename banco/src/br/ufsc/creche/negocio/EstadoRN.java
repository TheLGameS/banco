package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.EstadoDAO;
import br.ufsc.creche.model.Estado;
import br.ufsc.creche.util.RNException;

public class EstadoRN extends RN<Estado> {

	public EstadoRN() {
		super(new EstadoDAO());
	}

	@Override
	public void salvar(Estado model) throws RNException {
		// TODO Auto-generated method stub
	}

	@Override
	public void excluir(Estado model) throws RNException {
		// TODO Auto-generated method stub
	}

	@Override
	public Estado obterPorId(Estado filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Estado> pesquisar(Estado filtros) {
		return dao.pesquisar(filtros);
	}

}
