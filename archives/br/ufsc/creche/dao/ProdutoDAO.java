package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

import br.ufsc.creche.VO.ProdutoVO;
import br.ufsc.creche.model.Produto;
import br.ufsc.creche.util.ContextoUtil;
import br.ufsc.creche.util.DAOException;

public class ProdutoDAO extends DAO<Produto> {

	@Override
	public void salvar(Produto model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(Produto model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Produto obterPorId(Produto filtro) {
		return (Produto) sessao.get(Produto.class, filtro.getId().getProCod());
	}

	@Override
	public List<Produto> pesquisar(Produto filtros) {
		Criteria criteria = sessao.createCriteria(Produto.class);
		criteria.add(Restrictions.eq("proSit", "A"));
		criteria.addOrder(Order.asc("proNom"));
		return criteria.list();
	}

	public List<ProdutoVO> pesquisarVO(Produto filtros)   {
		Criteria criteria = sessao.createCriteria(Produto.class);
		listaCriteriaVO(criteria, filtros);
		return criteria.list();
	}

	public ProdutoVO pesquisarProdutoVO(Produto filtros)   {
		Criteria criteria = sessao.createCriteria(Produto.class);
		listaCriteriaVO(criteria, filtros);
		return (ProdutoVO) criteria.uniqueResult();
	}

	public void listaCriteriaVO(Criteria criteria, Produto filtros){

		/*	join's	 */
		Criteria criteriaProdutoLocal = criteria.createCriteria("produtoLocal", "est", JoinType.LEFT_OUTER_JOIN);
		criteriaProdutoLocal.add(Restrictions.ne("est.prlSld", 0));
		criteriaProdutoLocal.add(Restrictions.eq("est.id.prlFil", 1));
		criteriaProdutoLocal.add(Restrictions.eq("est.id.prlLcd", 1));

		Criteria criteriaModelo = criteria.createCriteria("modelo", "modelo", JoinType.LEFT_OUTER_JOIN );
		Criteria criteriaEmpresaPautaModeloArtigo = criteriaModelo.createCriteria("empresaPautaModeloArtigos", "empresaPautaArtigo", JoinType.LEFT_OUTER_JOIN );

		criteriaEmpresaPautaModeloArtigo.add(Restrictions.eq("empresaPautaArtigo.id.epmPat", 1));

		Criteria criteriaCor = criteria.createCriteria("cor", "cores", JoinType.LEFT_OUTER_JOIN );

		/*	tuples 	 */
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.property("proCcp"), "codigo");
		projList.add(Projections.property("proNcm"),    "ncm");
		projList.add(Projections.property("proNom"),   "nome");
		projList.add(Projections.property("cores.corNom"),"cor");
		projList.add(Projections.property("est.prlSld"),   "prlSld");

		projList.add(Projections.property("est.prlQ01"),   "prlQ01");
		projList.add(Projections.property("est.prlQ02"),   "prlQ02");
		projList.add(Projections.property("est.prlQ03"),   "prlQ03");
		projList.add(Projections.property("est.prlQ04"),   "prlQ04");
		projList.add(Projections.property("est.prlQ05"),   "prlQ05");
		projList.add(Projections.property("est.prlQ06"),   "prlQ06");
		projList.add(Projections.property("est.prlQ07"),   "prlQ07");
		projList.add(Projections.property("est.prlQ08"),   "prlQ08");
		projList.add(Projections.property("est.prlQ09"),   "prlQ09");
		projList.add(Projections.property("est.prlQ10"),   "prlQ10");
		projList.add(Projections.property("est.prlQ11"),   "prlQ11");
		projList.add(Projections.property("est.prlQ12"),   "prlQ12");
		projList.add(Projections.property("est.prlQ13"),   "prlQ13");
		projList.add(Projections.property("est.prlQ14"),   "prlQ14");


		projList.add(Projections.property("empresaPautaArtigo.epmPrv"),   "precoVenda");
		projList.add(Projections.property("empresaPautaArtigo.epmPrp"),   "precoPromocional");


		criteria.setProjection(projList);

		/*	where	 */
		criteria.add(Restrictions.eq("proSit", "A"));
		criteria.add(Restrictions.eq("id.proEmp", 1));

		if(filtros!=null && filtros.getProCcp().trim().length()>0){
			criteria.add(Restrictions.eq("proCcp", filtros.getProCcp()));
		}


		/*	orderBy	 */
		criteria.addOrder(Order.asc("proCcp"));

		/* Transformar result para um ValuesObject*/
		criteria.setResultTransformer(Transformers.aliasToBean(ProdutoVO.class));

	}

	public Produto obterPorCcp(Produto produto) {
		Criteria criteria = sessao.createCriteria(Produto.class);
		criteria.add(Restrictions.eq("proCcp", produto.getProCcp()));
		//criteria.add(Restrictions.eq("empresa.empCod", ContextoUtil.getContextoBean().getFilialAtiva().getId().getFilEmp()));
		return (Produto) criteria.uniqueResult();
	}
}
