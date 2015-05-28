package br.ufsc.creche.dao;

import java.sql.SQLException;
import java.util.List;

import br.ufsc.creche.util.DAOException;

/**
 * Interface que define as assinaturas padrões das DAOs do sistema
 * @param <T> - Entidade que será persistida/pesquisada
 */
public interface IDAO<T> {

	/**
	 * Assinatura padrão para os métodos Inserir/Alterar
	 * @param model - entidade a ser inserida/alterada
	 * @throws DAOException
	 */
	void salvar(T model) throws DAOException;
	
	/**
	 * Assinatura padrão para o método Excluir
	 * @param model - entidade a ser excluida
	 * @throws DAOException
	 * @throws SQLException 
	 */
	void excluir(T model) throws DAOException;
	
	/**
	 * Assinatura padrão para o que busca um registro pela PK
	 * @param model - entidade contendo a PK populada
	 * @throws DAOException
	 */
	T obterPorId(T filtro);

	/**
	 * Assinatura padrão para o que pesquisa N registros
	 * de acordo com os filtros informados no <T> model
	 * @param model - entidade contendo a PK populada
	 * @throws DAOException
	 */
	List<T> pesquisar(T filtros);
}
