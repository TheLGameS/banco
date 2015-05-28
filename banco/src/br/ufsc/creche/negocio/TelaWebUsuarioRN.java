package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.dao.TelaWebUsuarioDAO;
import br.ufsc.creche.model.TelaWebUsuario;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;


public class TelaWebUsuarioRN extends RN<TelaWebUsuario> {

	public TelaWebUsuarioRN() {
		super(new TelaWebUsuarioDAO());
	}

	@Override
	public void salvar(TelaWebUsuario model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	@Override
	public void excluir(TelaWebUsuario model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public TelaWebUsuario obterPorId(TelaWebUsuario filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<TelaWebUsuario> pesquisar(TelaWebUsuario filtros) {
		return dao.pesquisar(filtros);
	}

	public TelaWebUsuario obterPorDescricao(TelaWebUsuario filtro) {
		return ((TelaWebUsuarioDAO) dao).obterPorDescricao(filtro);
	}

	public List<TelaWebVO> montarTabelaDefault(TelaWebUsuario filtros) {
		return ((TelaWebUsuarioDAO) dao).montarTabelaDefault(filtros);
	}

	public List<TelaWebVO> montarTabelaFiltro(TelaWebUsuario filtros) {
		return ((TelaWebUsuarioDAO) dao).montarTabelaFiltro(filtros);
	}

	public List<TelaWebVO> montarCamposDisponivel(TelaWebUsuario filtros) {
		return ((TelaWebUsuarioDAO) dao).montarCamposDisponivel(filtros);
	}

	public List<TelaWebVO> montarCampoSelecionado(TelaWebUsuario filtros) {
		return ((TelaWebUsuarioDAO) dao).montarCampoSelecionado(filtros);
	}

}
