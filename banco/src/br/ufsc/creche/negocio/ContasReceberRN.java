package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.AlunoDAO;
import br.ufsc.creche.dao.ContasReceberDAO;
import br.ufsc.creche.model.ContasReceber;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class ContasReceberRN  extends RN<ContasReceber>{

	public ContasReceberRN() {
		super(new ContasReceberDAO());
	}
	
	@Override
	public void salvar(ContasReceber model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public void excluir(ContasReceber model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			e.printStackTrace();
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public ContasReceber obterPorId(ContasReceber filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<ContasReceber> pesquisar(ContasReceber filtros) {
		return dao.pesquisar(filtros);
	}
	
	public List<ContasReceber> listaContasReceberPorAluno(ContasReceber filtros) {
		return ((ContasReceberDAO) dao).listaContasReceberPorAluno(filtros);
	}
	
	

}
