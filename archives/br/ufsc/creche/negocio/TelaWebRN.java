package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.TelaWebDAO;
import br.ufsc.creche.model.TelaWeb;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.RNException;

public class TelaWebRN extends RN<TelaWeb> {

	public TelaWebRN() {
		super(new TelaWebDAO());
	}

	@Override
	public void salvar(TelaWeb model) throws RNException {
		try {
			dao.salvar(model);
		} catch (DAOException e) {
			throw new RNException(e);
		}
	}

	@Override
	public void excluir(TelaWeb model) throws RNException {
		try {
			dao.excluir(model);
		} catch (DAOException e ) {
			throw new RNException(e.getMessage());
		}
	}

	@Override
	public TelaWeb obterPorId(TelaWeb filtro) {
		return dao.obterPorId(filtro);
	}

	@Override
	public List<TelaWeb> pesquisar(TelaWeb filtros) {
		return dao.pesquisar(filtros);
	}

	public TelaWeb obterPorDescricao(TelaWeb filtro) {
		return ((TelaWebDAO) dao).obterPorDescricao(filtro);
	}

	public List<TelaWeb> pesquisarPorNomeJanela(TelaWeb filtros) {
		return ((TelaWebDAO) dao).pesquisarPorNomeJanela(filtros);
	}

	public TelaWeb pesquisarPorNomeJanelaNomeCampo(TelaWeb filtros) {
		return ((TelaWebDAO) dao).pesquisarPorNomeJanelaNomeCampo(filtros);
	}

	public List<TelaWeb> pesquisarPorNomeJanelaSomenteDisponivel(TelaWeb filtros) {
		return ((TelaWebDAO) dao).pesquisarPorNomeJanelaSomenteDisponivel(filtros);
	}
}
