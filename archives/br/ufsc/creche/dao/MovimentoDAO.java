package br.ufsc.creche.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import br.ufsc.creche.VO.MovimentoVO;
import br.ufsc.creche.model.Movimento;
import br.ufsc.creche.parameter.MovimentoPar;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.Diversos;
import br.ufsc.creche.util.HibernateUtil;


public class MovimentoDAO extends DAO<Movimento> {

	@Override
	public void salvar(Movimento model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(Movimento model) throws DAOException {
		sessao.delete(model);
	}

	@Override
	public Movimento obterPorId(Movimento filtro){
		return (Movimento) sessao.get(Movimento.class, filtro.getMovCod());
	}

	@Override
	public List<Movimento> pesquisar(Movimento filtros) {
		return null;
	}

	public List<MovimentoVO> pesquisar(MovimentoPar filtros) throws DAOException {
		if (!sessao.isOpen()){
			sessao = HibernateUtil.getSessionFactory().getCurrentSession();
		}

		Criteria criteria = sessao.createCriteria(Movimento.class);

		switch (filtros.getConsulta()) {
		case 0:
			CriteriaDiario(filtros, criteria);
			criteria.setResultTransformer(Transformers.aliasToBean(MovimentoVO.class));
			break;
		case 1:
			CriteriaMensal(filtros, criteria);
			criteria.setResultTransformer(Transformers.aliasToBean(MovimentoVO.class));
			break;
		case 2:
			CriteriaAnual(filtros, criteria);
			criteria.setResultTransformer(Transformers.aliasToBean(MovimentoVO.class));
			break;
		case 3:
			CriteriaCliente(filtros, criteria);
			criteria.setResultTransformer(Transformers.aliasToBean(MovimentoVO.class));
			break;
		case 4:
			CriteriaFilial(filtros, criteria);
			criteria.setResultTransformer(Transformers.aliasToBean(MovimentoVO.class));
			break;
		case 5:
			CriteriaMunicipio(filtros, criteria);
			criteria.setResultTransformer(Transformers.aliasToBean(MovimentoVO.class));
			break;
		case 6:
			CriteriaMunicipio(filtros, criteria);
			criteria.setResultTransformer(Transformers.aliasToBean(MovimentoVO.class));
			break;
		case 7:
			CriteriaGuia(filtros, criteria);
			criteria.setResultTransformer(Transformers.aliasToBean(MovimentoVO.class));
			break;
		case 8:
			CriteriaRepresentante();
			break;
		default:
			return null;
		}

		return criteria.list();
	}


	private void CriteriaRepresentante(){
		String hql="select new list(mov.filial.id.filCod as filCod , mov.movGui as guiCod, guia.guiRaz )  from Movimento  mov  ,  Guia guia         left join  mov.movimentoTipo as tp left join mov.filial.empresa as empresa  where   guia.id.guiCod=mov.movGui   and guia.id.guiEmp = mov.filial.empresa.empCod and guia.id.guiFil=mov.filial.id.filCod    and    mov.movDtf  between '01.11.2014' and '31.12.2015' and mov.movSit in ('S','E')  and tp.motCod in ('4','5', '6')  order by filCod asc ";
		// colocar ,Guia guia no from ele gera cross join ..
		Query consulta = sessao.createQuery(hql);
	}

	private void CriteriaDiario(MovimentoPar filtros, Criteria criteria) {
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.groupProperty("movDtf"), "dataMovimento");

		projList.add(Projections.sum("movAcr").as("acrescimo"));

		projList.add(Projections.sqlProjection(" sum(this_.MOV_DES + this_.mov_dsp + this_.mov_dad) as desc  ",  new String[] { "desc" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"desconto");


		if(filtros.getOperacao()==2){
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOT else MOV_TOT end ) as movTot  ",  new String[] { "movTot" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"total");
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOP else MOV_TOP end ) as movTop  ",  new String[] { "movTop" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"totalProdutos");
		}else{
			projList.add(Projections.sum("movTot").as("total"));
			projList.add(Projections.sum("movTop").as("totalProdutos"));
		}

		criteria.setFirstResult(filtros.getPrimeriroRegistro());
		criteria.setMaxResults(filtros.getQtdeRegistro());
		criteria.setProjection(projList);

		CriteriaFiltros(filtros, criteria);

		criteria.addOrder(Order.asc("movDtf"));
	}

	private void CriteriaFilial(MovimentoPar filtros, Criteria criteria) {
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.groupProperty("fil.id.filCod"),"idFilial");
		projList.add(Projections.groupProperty("fil.filRaz"),"razaoSocial");
		projList.add(Projections.groupProperty("fil.filApe"),"apelido");

		if(filtros.getOperacao()==2){
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOT else MOV_TOT end ) as movTot  ",  new String[] { "movTot" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"total");
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOP else MOV_TOP end ) as movTop  ",  new String[] { "movTop" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"totalProdutos");
		}else{
			projList.add(Projections.sum("movTot").as("total"));
			projList.add(Projections.sum("movTop").as("totalProdutos"));
		}

		projList.add(Projections.sum("movAcr").as("acrescimo"));
		projList.add(Projections.sum("movTop").as("totalProdutos"));
		projList.add(Projections.sqlProjection(" sum(this_.MOV_DES + this_.mov_dsp + this_.mov_dad) as desc  ",  new String[] { "desc" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"desconto");
		projList.add(Projections.sqlProjection("  sum((select sum(MOI_QTD) from MOVIMENTOITENS where MOI_MOV = this_.MOV_COD and MOI_SIT <> 'C') )as QUANTIDADE ",  new String[] { "QUANTIDADE" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"qtdadeProdutos");

		criteria.setProjection(projList);

		CriteriaFiltros(filtros, criteria,true);

		criteria.addOrder(Order.desc("total"));
	}

	private void CriteriaCliente(MovimentoPar filtros, Criteria criteria) {
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.groupProperty("col.colCod"),"idColaborador");
		projList.add(Projections.groupProperty("col.colRaz"),"razaoSocial");


		if(filtros.getColCod() !=null){
			criteria.add(Restrictions.eq("col.colCod",filtros.getColCod()));
		}


		if(filtros.getOperacao()==2){
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOT else MOV_TOT end ) as movTot  ",  new String[] { "movTot" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"total");
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOP else MOV_TOP end ) as movTop  ",  new String[] { "movTop" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"totalProdutos");
			projList.add(Projections.sqlProjection("  sum((select case when MOV_MOT = 9 then sum(-mvi.MOI_QTD) else Sum(mvi.MOI_QTD) end from  MOVIMENTOITENS mvi where mvi.MOI_MOV = this_.MOV_COD and mvi.MOI_SIT <> 'C') )as QUANTIDADE ",  new String[] { "QUANTIDADE" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"qtdadeProdutos");
		}else{
			projList.add(Projections.sum("movTot").as("total"));
			projList.add(Projections.sum("movTop").as("totalProdutos"));
			projList.add(Projections.sqlProjection("  sum((select sum(mvi.MOI_QTD) from MOVIMENTOITENS mvi where mvi.MOI_MOV = this_.MOV_COD and mvi.MOI_SIT <> 'C') ) as QUANTIDADE ",  new String[] { "QUANTIDADE" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"qtdadeProdutos");
		}

		projList.add(Projections.sum("movAcr").as("acrescimo"));
		projList.add(Projections.sqlProjection(" sum(this_.MOV_DES + this_.mov_dsp + this_.mov_dad) as desc  ",  new String[] { "desc" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"desconto");

		projList.add(Projections.sqlProjection(" Sum((case when (MOV_MOT in (4,5)) then 1 else 0 end)) as TOTAL_VENDAS ",  new String[] { "TOTAL_VENDAS" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"qtdadeVendas");

		criteria.setProjection(projList);

		CriteriaFiltros(filtros, criteria,true);

		criteria.addOrder(Order.desc("total"));
	}

	private void CriteriaMensal(MovimentoPar filtros, Criteria criteria) {
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.sqlGroupProjection("  cast ( '01.'||extract(month from this_.MOV_DTF )||'.'|| extract(year from this_.MOV_DTF )   as date ) as movDtf  ", "movDtf", new String[] { "movDtf" }, new Type[] { StandardBasicTypes.DATE }),"dataMovimento");

		projList.add(Projections.sum("movAcr").as("acrescimo"));
		projList.add(Projections.sum("movTop").as("totalProdutos"));
		projList.add(Projections.sqlProjection(" sum(this_.MOV_DES + this_.mov_dsp + this_.mov_dad) as desc  ",  new String[] { "desc" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"desconto");

		if(filtros.getOperacao()==2){
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOT else MOV_TOT end ) as movTot  ",  new String[] { "movTot" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"total");
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOP else MOV_TOP end ) as movTop  ",  new String[] { "movTop" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"totalProdutos");
		}else{
			projList.add(Projections.sum("movTot").as("total"));
			projList.add(Projections.sum("movTop").as("totalProdutos"));
		}

		criteria.setProjection(projList);
		CriteriaFiltros(filtros, criteria, true);
	}

	private void CriteriaMunicipio(MovimentoPar filtros, Criteria criteria) {
		ProjectionList projList = Projections.projectionList();

		if(filtros.getConsulta()==5){
			projList.add(Projections.groupProperty("mun.munCod"),"idMunicipio");
			projList.add(Projections.groupProperty("mun.munNom"),"municipio");
		}

		projList.add(Projections.groupProperty("mun.estado.estSig"),"uf");

		projList.add(Projections.sum("movAcr").as("acrescimo"));
		projList.add(Projections.sqlProjection(" sum(this_.MOV_DES + this_.mov_dsp + this_.mov_dad) as desc  ",  new String[] { "desc" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"desconto");

		if(filtros.getOperacao()==2){
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOT else MOV_TOT end ) as movTot  ",  new String[] { "movTot" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"total");
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOP else MOV_TOP end ) as movTop  ",  new String[] { "movTop" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"totalProdutos");
		}else{
			projList.add(Projections.sum("movTot").as("total"));
			projList.add(Projections.sum("movTop").as("totalProdutos"));
		}
		criteria.setProjection(projList);

		criteria.addOrder(Order.desc("total"));

		CriteriaFiltros(filtros, criteria);
	}

	private void CriteriaGuia(MovimentoPar filtros, Criteria criteria) {
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.groupProperty("fil.id.filCod"),"idFilial");

		projList.add(Projections.groupProperty("movGui"), "idGuia");

		projList.add(Projections.sum("movAcr").as("acrescimo"));
		projList.add(Projections.sqlProjection(" sum(this_.MOV_DES + this_.mov_dsp + this_.mov_dad) as desc  ",  new String[] { "desc" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"desconto");

		if(filtros.getOperacao()==2){
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOT else MOV_TOT end ) as movTot  ",  new String[] { "movTot" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"total");
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOP else MOV_TOP end ) as movTop  ",  new String[] { "movTop" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"totalProdutos");
		}else{
			projList.add(Projections.sum("movTot").as("total"));
			projList.add(Projections.sum("movTop").as("totalProdutos"));
		}

		criteria.setProjection(projList);

		criteria.addOrder(Order.desc("total"));

		CriteriaFiltros(filtros, criteria);
	}

	private void CriteriaAnual(MovimentoPar filtros, Criteria criteria) {
		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.sqlGroupProjection("  cast ( '01.'||'01.'|| extract(year from this_.MOV_DTF )   as date ) as movDtf  ", "movDtf", new String[] { "movDtf" }, new Type[] { StandardBasicTypes.DATE }),"dataMovimento");

		projList.add(Projections.sum("movAcr").as("acrescimo"));
		projList.add(Projections.sum("movTop").as("totalProdutos"));
		projList.add(Projections.sqlProjection(" sum(this_.MOV_DES + this_.mov_dsp + this_.mov_dad) as desc  ",  new String[] { "desc" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"desconto");

		if(filtros.getOperacao()==2){
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOT else MOV_TOT end ) as movTot  ",  new String[] { "movTot" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"total");
			projList.add(Projections.sqlProjection(" sum(case when(MOV_MOT = 9) then -MOV_TOP else MOV_TOP end ) as movTop  ",  new String[] { "movTop" }, new Type[] { StandardBasicTypes.BIG_DECIMAL }),"totalProdutos");
		}else{
			projList.add(Projections.sum("movTot").as("total"));
			projList.add(Projections.sum("movTop").as("totalProdutos"));
		}
		criteria.setProjection(projList);

		CriteriaFiltros(filtros, criteria, false, true);
	}

	private void CriteriaFiltros(MovimentoPar filtros, Criteria criteria, boolean converteMes, boolean converteAno) {
		Criteria critFilial = criteria.createCriteria("filial", "fil");
		Criteria critEmpresa = critFilial.createCriteria("empresa");
		critEmpresa.add(Restrictions.eq("empCod",filtros.getEmpresa()));

		Criteria critMovTipo = criteria.createCriteria("movimentoTipo");
		critMovTipo.add(Restrictions.in("motCod",filtros.getMovimentoTipo()));

		Criteria critColaboradorEmpresa = criteria.createCriteria("colaboradorEmpresaByFkMovCoe","cli");
		Criteria critColaborador = critColaboradorEmpresa.createCriteria("colaborador","col");
		Criteria critMunicipio = critColaborador.createCriteria("municipio","mun");

		criteria.add(Restrictions.in("movSit",filtros.getSituacao()));

		if(converteMes){
			criteria.add(Restrictions.between("movDtf", Diversos.PrimeiroDiaMes(filtros.getDataInicial()), Diversos.UltimoDiaMes(filtros.getDataFinal())));
		}else if(converteAno){
			criteria.add(Restrictions.between("movDtf", Diversos.PrimeiroDiaAno(filtros.getDataInicial()), Diversos.UltimoDiaAno(filtros.getDataFinal())));
		}else{
			criteria.add(Restrictions.between("movDtf", filtros.getDataInicial(), filtros.getDataFinal()));
		}
	}

	public int dataSize(MovimentoPar filtros) throws DAOException {
		if (!sessao.isOpen()){
			sessao = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria criteria = sessao.createCriteria(Movimento.class);

		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.groupProperty("movDtf"), "dataMovimento");
		criteria.setProjection(projList);

		CriteriaFiltros(filtros, criteria);

		Number result = criteria.list().size();
		return result.intValue();
	}

	private void CriteriaFiltros(MovimentoPar filtros, Criteria criteria) {
		CriteriaFiltros(filtros, criteria, false, false);
	}

	private void CriteriaFiltros(MovimentoPar filtros, Criteria criteria, boolean mes) {
		CriteriaFiltros(filtros, criteria, true, false);
	}

}