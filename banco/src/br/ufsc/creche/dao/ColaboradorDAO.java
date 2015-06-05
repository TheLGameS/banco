/*package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.ufsc.creche.VO.ColaboradorVO;
import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.model.Colaborador;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.Diversos;
import br.ufsc.creche.util.HibernateUtil;

public class ColaboradorDAO extends DAO<Colaborador> {

	@Override
	public void salvar(Colaborador model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(Colaborador model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Colaborador obterPorId(Colaborador filtro) {
		Colaborador col = (Colaborador) sessao.get(Colaborador.class, filtro.getColCod());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	public Colaborador obterPorDocumento(Colaborador filtro) {
		Criteria criteria = sessao.createCriteria(Colaborador.class);
		criteria.add(Restrictions.eq("colInf", Diversos.removerLetrasCaracteres(filtro.getColInf())));
		criteria.add(Restrictions.eq("colTip", filtro.getColTip()));
		Colaborador colDoc = (Colaborador) criteria.uniqueResult();
		if(colDoc!=null) {
			sessao.setReadOnly(colDoc, true);
		}
		return colDoc;
	}

	public Colaborador obterPorCodigoVO(ColaboradorVO filtro) {
		Criteria criteria = sessao.createCriteria(Colaborador.class);
		criteria.add(Restrictions.eq("colCod", filtro.getColCod()));
		Colaborador colDoc = (Colaborador) criteria.uniqueResult();
		if(colDoc!=null) {
			sessao.setReadOnly(colDoc, true);
		}
		return colDoc;
	}

	@Override
	public List<Colaborador> pesquisar(Colaborador filtros)   {
		Criteria criteria = sessao.createCriteria(Colaborador.class);

		//ProjectionList projList = Projections.projectionList();
		//projList.add(Projections.property("colCod"));
		//projList.add(Projections.property("colRaz"));
		//criteria.setProjection(projList);
		//criteria.addOrder(Order.asc("cast(colRaz as varchar(50))"));
		criteria.addOrder(Order.asc("colRaz"));

		//criteria.setResultTransformer(Transformers.aliasToBean(ColaboradorVO.class));

		return criteria.list();
	}

	public Integer geraCodigo(){

		if (!sessao.isOpen()){
			sessao = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria criteria = sessao.createCriteria(Colaborador.class);
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.max("colCod"));
		criteria.setProjection(projList);

		Number result = (Number) criteria.uniqueResult();

		int genId = result.intValue();

		genId++;

		return  genId;
	}

	public List<ColaboradorVO> pesquisarTelaWeb(List<TelaWebVO> tela)   {
		Criteria criteria = sessao.createCriteria(Colaborador.class);

		Criteria criteriaMunicipio = criteria.createCriteria("municipio", "colMun");

		ProjectionList projList = Projections.projectionList();

		for(TelaWebVO o : tela){
			String campo="";
			campo=o.getTWU_CPO() !=null ? o.getTWU_CPO() : o.getTLW_CPO();
			montaProjecao(campo, projList);
		}

		criteria.setProjection(projList);
		criteria.addOrder(Order.asc("colRaz"));
		criteria.setResultTransformer(Transformers.aliasToBean(ColaboradorVO.class));

		return criteria.list();
	}

	private void montaProjecao(String campo, ProjectionList projList){
		if(campo.equals("colMun")){
			projList.add(Projections.property(campo+".munCod"),campo);
		}else if(campo.equals("colFon")){
			projList.add(Projections.property("colDdd"),"colDdd");
			projList.add(Projections.property(campo),campo);
		}else if(campo.equals("colFax")){
			projList.add(Projections.property("colDdf"),"colDdf");
			projList.add(Projections.property(campo),campo);
		}else if(campo.equals("colCel")){
			projList.add(Projections.property("colDdc"),"colDdc");
			projList.add(Projections.property(campo),campo);
		}else if(campo.equals("estSig")){
			projList.add(Projections.property("colMun.estado.estSig"),"estSig");
		}else if(campo.equals("munNom")){
			projList.add(Projections.property("colMun.munNom"),"munNom");
		}else{
			projList.add(Projections.property(campo),campo);
		}
	}
}*/