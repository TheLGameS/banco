package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Query;

import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.util.DAOException;


public class AlunoDAO extends DAO<Aluno> {

	@Override
	public void salvar(Aluno model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(Aluno model) throws DAOException {
		sessao.delete(model);
	}

	@Override
	public Aluno obterPorId(Aluno filtro) {
		return (Aluno) sessao.get(Aluno.class, filtro.getCodigoAluno());
	}

	@Override
	public List<Aluno> pesquisar(Aluno filtros) {
		return sessao.createCriteria(Aluno.class).list();
	}

	public Aluno buscarPorLogin(String login){
		String hql = "select u from Aluno u where u.login = :login";
		Query consulta = sessao.createQuery(hql);
		consulta.setString("login", login);

		return (Aluno) consulta.uniqueResult();
	}
}