package br.ufsc.creche.negocio;

import java.sql.SQLException;
import java.util.List;

import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;


/**
 * Interface que define as assinaturas padr�es das RNs do sistema
 * @param <T> - Entidade que ser� persistida/pesquisada
 */
public interface IRN<T> {

	/**
	 * Assinatura padr�o para os m�todos Inserir/Alterar
	 * @param model - entidade a ser inserida/alterada
	 * @throws RNException
	 */
	void salvar(T model) throws RNException;
	
	/**
	 * Assinatura padr�o para o m�todo Excluir
	 * @param model - entidade a ser excluida
	 * @throws RNException
	 * @throws DAOException 
	 * @throws SQLException 
	 */
	void excluir(T model) throws RNException;
	
	/**
	 * Assinatura padr�o para o que busca um registro pela PK
	 * @param model - entidade contendo a PK populada
	 * @throws RNException
	 */
	T obterPorId(T filtro);

	/**
	 * Assinatura padr�o para o que pesquisa N registros
	 * de acordo com os filtros informados no <T> model
	 * @param model - entidade contendo a PK populada
	 * @throws RNException
	 */
	List<T> pesquisar(T filtros);
}