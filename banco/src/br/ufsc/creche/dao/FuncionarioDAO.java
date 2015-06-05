package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.model.Funcionario;
import br.ufsc.creche.util.DAOException;


public class FuncionarioDAO extends DAO<Funcionario> {

	@Override
	public void salvar(Funcionario model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(Funcionario model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Funcionario obterPorId(Funcionario filtro) {
		Funcionario col = (Funcionario) sessao.get(Funcionario.class, filtro.getCodigoFuncionario());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@Override
	public List<Funcionario> pesquisar(Funcionario filtros) {
		return sessao.createCriteria(Funcionario.class).list();
	}

	public Funcionario obterPorCpf(Funcionario filtro) {
		Criteria criteria = sessao.createCriteria(Funcionario.class);
		criteria.add(Restrictions.eq("cpf", filtro.getCpf()));
		Funcionario colDoc = (Funcionario) criteria.uniqueResult();
		if(colDoc!=null) {
			sessao.setReadOnly(colDoc, true);
		}
		return colDoc;
	}
	   
}