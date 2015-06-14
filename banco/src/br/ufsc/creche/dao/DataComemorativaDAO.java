package br.ufsc.creche.dao;

import java.util.List;

import br.ufsc.creche.model.DatasComemorativa;
import br.ufsc.creche.util.DAOException;


public class DataComemorativaDAO extends DAO<DatasComemorativa> {

	@Override
	public void salvar(DatasComemorativa model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(DatasComemorativa model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public DatasComemorativa obterPorId(DatasComemorativa filtro) {
		DatasComemorativa col = (DatasComemorativa) sessao.get(DatasComemorativa.class, filtro.getCodigoDataComemorativa());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@Override
	public List<DatasComemorativa> pesquisar(DatasComemorativa filtros) {
		return sessao.createCriteria(DatasComemorativa.class).list();
	}



}