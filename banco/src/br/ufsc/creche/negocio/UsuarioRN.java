package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.UsuarioDAO;
import br.ufsc.creche.model.Usuario;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;


public class UsuarioRN extends RN<Usuario> {

	public UsuarioRN() {
		// Aqui informamos qual instancia sera armazenada no atributo
		// genérico dao da classe pai RN.
		// NÃO ENTENDEU, da um F3 sob a palavra SUPER
		super(new UsuarioDAO());
	}

	@Override
	public void salvar(Usuario model) throws RNException {
		if(model.getCodigo() == null  || model.getCodigo() ==  0){
			try {

				Usuario usuarioExistenteComLogin = buscarPorLogin(model.getLogin());

				if(usuarioExistenteComLogin != null){
					throw new RNException("Já existe um usuário com o login informado.");
				}

				model.setPermissao("ROLE_USUARIO");
				dao.salvar(model);


			} catch (DAOException e) {
				throw new RNException("Não foi possível inserir o usuario. Erro: "+e.getMessage());
			}
		} else {
			try {
				// Transformamos a variavel GENERICA dao em uma variavel
				// mais especifica do tipo UsuarioDAO
				UsuarioDAO userDAO = (UsuarioDAO) dao;
				userDAO.atualizar(model);

				// versão reduzida dos comandos acima
				// ((UsuarioDAO) dao).atualizar(model);

			} catch (DAOException e) {
				throw new RNException("Não foi possível atualizar o usuario. Erro: "+e.getMessage());
			}
		}
	}

	@Override
	public void excluir(Usuario model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e) {
			throw new RNException("Não foi possível excluir o usuario. Erro: "+e.getMessage());
		}
	}

	@Override
	public Usuario obterPorId(Usuario filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<Usuario> pesquisar(Usuario filtros) {
		return dao.pesquisar(filtros);
	}

	public Usuario buscarPorLogin(String login){
		// DownCasting: dao é muito generico e nao sabe dos métodos
		//			    especificos de UsuarioDAO
		UsuarioDAO userDao = (UsuarioDAO) dao;
		return userDao.buscarPorLogin(login);
		//		return ((UsuarioDAO) dao).buscarPorLogin(login);
	}

}