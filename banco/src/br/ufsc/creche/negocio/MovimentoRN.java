package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.VO.MovimentoVO;
import br.ufsc.creche.dao.MovimentoDAO;
import br.ufsc.creche.model.Movimento;
import br.ufsc.creche.parameter.MovimentoPar;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class MovimentoRN extends RN<Movimento> {

	public MovimentoRN() {
		super(new MovimentoDAO());
	}

	@Override
	public void excluir(Movimento movimento) throws RNException {
		// TODO Auto-generated method stub
	}

	@Override
	public Movimento obterPorId(Movimento filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Movimento> pesquisar(Movimento filtros) {
		return dao.pesquisar(filtros);
	}

	public List<MovimentoVO> pesquisar(MovimentoPar filtros) throws RNException {
		try {
			if(filtros.getDataInicial().compareTo(filtros.getDataFinal())>0) {
				throw new RNException("Data Inicial não pode ser maior que Data Final.");
			}
			return ((MovimentoDAO)dao).pesquisar(filtros);
		} catch (DAOException e) {
			throw new  RNException(e);
		}
	}

	public int dataSize(MovimentoPar filtros) throws RNException {
		try {
			return ((MovimentoDAO)dao).dataSize((filtros));
		} catch (DAOException e) {
			throw new  RNException(e);
		}
	}

	@Override
	public void salvar(Movimento model) throws RNException {
		// TODO Auto-generated method stub

	}

}