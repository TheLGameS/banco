package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.model.ContasReceber;
import br.ufsc.creche.util.DAOException;


public class ContasReceberDAO extends DAO<ContasReceber> {

	@Override
	public void salvar(ContasReceber model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(ContasReceber model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public ContasReceber obterPorId(ContasReceber filtro) {
		ContasReceber col = (ContasReceber) sessao.get(ContasReceber.class, filtro.getCodigoContasReceber());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContasReceber> pesquisar(ContasReceber filtros) {
		return sessao.createCriteria(ContasReceber.class).add(Restrictions.isNull("dataPagamento")).addOrder(Order.asc("dataVencimento")).list();
	}


	@SuppressWarnings("unchecked")
	public List<ContasReceber> listaContasReceberPorAluno(Aluno filtros) {
		Criteria criteria = sessao.createCriteria(ContasReceber.class);
		criteria.add(Restrictions.eq("aluno.codigoAluno", filtros.getCodigoAluno()));
		criteria.add(Restrictions.isNull("dataPagamento"));
		criteria.addOrder(Order.asc("dataVencimento"));
		return criteria.list();
	}

}