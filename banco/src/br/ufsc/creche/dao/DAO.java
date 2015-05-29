package br.ufsc.creche.dao;

import org.hibernate.Session;

import br.ufsc.creche.util.HibernateUtil;

public abstract class DAO<T> implements IDAO<T> {

	/**
	 * Como todas as DAOS ir�o possuir uma sessao, criaremos a mesma
	 * na classe pai, sendo ela HERDADA pelas filhas....
	 */
	protected Session sessao;
	
	/**
	 * M�todo construtor de DAO para INICIALIZAR a sessao
	 * do hibernate
	 */
	public DAO() {
		sessao = HibernateUtil.getSessionFactory().getCurrentSession();
		
	}


}


















