package br.ufsc.creche.negocio;

import java.util.List;

import br.ufsc.creche.dao.PropSistemaDAO;
import br.ufsc.creche.model.PropSistema;
import br.ufsc.creche.util.RNException;

public class PropSistemaRN extends RN<PropSistema> {

	public PropSistemaRN() {
		super(new PropSistemaDAO());
	}

	@Override
	public void salvar(PropSistema model) throws RNException {
		// TODO Auto-generated method stub
	}

	@Override
	public void excluir(PropSistema model) throws RNException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<PropSistema> pesquisar(PropSistema filtros) {
		return dao.pesquisar(filtros);
	}

	@Override
	public PropSistema obterPorId(PropSistema filtro) {
		return null;
	}

	public String obterGlic()  {
		return ((PropSistemaDAO)dao).obterGlic();
	}

}
