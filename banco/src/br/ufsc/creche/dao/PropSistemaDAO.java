package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import br.ufsc.creche.model.PropSistema;
import br.ufsc.creche.util.DAOException;

public class PropSistemaDAO extends DAO<PropSistema> {

	@Override
	public void salvar(PropSistema model) throws DAOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void excluir(PropSistema model) throws DAOException {
		// TODO Auto-generated method stub
	}

	@Override
	public PropSistema obterPorId(PropSistema filtro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropSistema> pesquisar(PropSistema filtros) {
		// TODO Auto-generated method stub
		return null;
	}

	public String obterGlic()   {
		Criteria criteria = sessao.createCriteria(PropSistema.class);
		ProjectionList projList = Projections.projectionList();
		String result ="";
		projList.add(Projections.property("id.prsGlic"));
		criteria.setProjection(projList);
		result = (String) criteria.uniqueResult(); 
		return result; 
	}
}
