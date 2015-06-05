package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import br.ufsc.creche.model.Pagamento;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.Diversos;


public class PagamentoDAO extends DAO<Pagamento> {

	@Override
	public void salvar(Pagamento model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(Pagamento model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Pagamento obterPorId(Pagamento filtro) {
		Pagamento col = (Pagamento) sessao.get(Pagamento.class, filtro.getCodigoAluno());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pagamento> pesquisar(Pagamento filtros) {
		return sessao.createCriteria(Pagamento.class).list();
	}
	   
}