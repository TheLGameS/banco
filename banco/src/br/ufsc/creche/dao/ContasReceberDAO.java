package br.ufsc.creche.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.text.DateFormatter;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import br.ufsc.creche.VO.GraficoVO;
import br.ufsc.creche.model.Aluno;
import br.ufsc.creche.model.ContasReceber;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.Diversos;


public class ContasReceberDAO extends DAO<ContasReceber> {

	@Override
	public void salvar(ContasReceber model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(ContasReceber model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public ContasReceber obterPorId(ContasReceber filtro) {
		ContasReceber col = (ContasReceber) sessao.get(ContasReceber.class, filtro.getCodigoContasReceber());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContasReceber> pesquisar(ContasReceber filtros) {
		return sessao.createCriteria(ContasReceber.class).add(Restrictions.isNull("dataPagamento")).addOrder(Order.asc("dataVencimento")).list();
	}


	@SuppressWarnings("unchecked")
	public List<ContasReceber> listaContasReceberPorAluno(Aluno filtros) {
		Criteria criteria = sessao.createCriteria(ContasReceber.class);
		criteria.add(Restrictions.eq("aluno.codigoAluno", filtros.getCodigoAluno()));
		criteria.add(Restrictions.isNull("dataPagamento"));
		criteria.addOrder(Order.asc("dataVencimento"));
		return criteria.list();
	}


	public List<ContasReceber> pesquisaPeriodo(Date ini, Date fim) {
		Criteria criteria = sessao.createCriteria(ContasReceber.class);

		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.sqlGroupProjection("  cast ( '01.'||extract(month from this_.data_vencimento )||'.'|| extract(year from this_.data_vencimento )   as date ) as data_vencimento  ", "data_vencimento", new String[] { "data_vencimento" }, new Type[] { StandardBasicTypes.DATE }));

		projList.add(Projections.sum("valor"));
		criteria.setProjection(projList);

		criteria.add(Restrictions.between("dataVencimento", Diversos.PrimeiroDiaMes(ini), Diversos.UltimoDiaMes(fim)));
		criteria.add(Restrictions.isNull("dataPagamento"));
		criteria.addOrder(Order.asc("1"));
		return criteria.list();
	}

	public List<GraficoVO> montarGrafico(Date ini, Date fim) {
		String sql="select "
				+"cast ( '01.'||extract(month from this_.data_vencimento )||'.'|| extract(year from this_.data_vencimento )   as date ) as data , "
				+"sum(this_.valor) as valor "
				+ "from  public.contasreceber this_ "
				+ "where  this_.data_vencimento between '"
				+ Diversos.dataSql(ini) +"' and '" + Diversos.dataSql(fim) + "' and this_.data_pagamento is null  "   
				+"group by cast ( '01.'||extract(month from this_.data_vencimento )||'.'|| extract(year from this_.data_vencimento )   as date ) "
				+ "order by 1 	";

		List<GraficoVO>  sqlQ = sessao.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(GraficoVO.class)).list();

		return sqlQ;
	}
	
	public List<GraficoVO> montarGraficoPagamento(Date ini, Date fim) {
		String sql="select "
				+"cast ( '01.'||extract(month from this_.data_vencimento )||'.'|| extract(year from this_.data_vencimento )   as date ) as data , "
				+"sum(this_.valor) as valor "
				+ "from  public.contasreceber this_ "
				+ "where  this_.data_vencimento between '"
				+ Diversos.dataSql(ini) +"' and '" + Diversos.dataSql(fim) + "' and this_.data_pagamento is not null  "   
				+"group by cast ( '01.'||extract(month from this_.data_vencimento )||'.'|| extract(year from this_.data_vencimento )   as date ) "
				+ "order by 1 	";

		List<GraficoVO>  sqlQ = sessao.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(GraficoVO.class)).list();

		return sqlQ;
	}

}