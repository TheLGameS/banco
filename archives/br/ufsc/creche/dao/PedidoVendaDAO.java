package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.ufsc.creche.VO.PedidoVO;
import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.model.Pedido;
import br.ufsc.creche.parameter.PedidoVendaParametro;
import br.ufsc.creche.util.ContextoUtil;
import br.ufsc.creche.util.DAOException;
import br.ufsc.creche.util.HibernateUtil;

public class PedidoVendaDAO extends DAO<Pedido> {

	@Override
	public void salvar(Pedido model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(Pedido model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public Pedido obterPorId(Pedido filtro) {
		return (Pedido) sessao.get(Pedido.class, filtro.getPedSeq());
	}

	@Override
	public List<Pedido> pesquisar(Pedido filtros)   {
		Criteria criteria = sessao.createCriteria(Pedido.class);
		criteria.addOrder(Order.asc("pedSeq"));
		return criteria.list();
	}

	public List<Pedido> listaTabelaParametro(PedidoVendaParametro filtros)   {
		Criteria criteria = sessao.createCriteria(Pedido.class);

		if(filtros.getCodigoCliente()!=null){
			criteria.add(Restrictions.eq("pedCol", filtros.getCodigoCliente().intValue()));
		}

		filtros.getSituacao().forEach((String element) -> System.out.println("forLambda: "+element));

		if(filtros.getSituacao().size() >0) {
			criteria.add(Restrictions.in("pedSts",filtros.getSituacao()));
		}

		//criteria.add(Restrictions.eq("pedRep", ContextoUtil.getContextoBean().getUsuarioLogado().getUsuCol()));
		//criteria.add(Restrictions.eq("categoriaFiscal.id.cgfEmp", ContextoUtil.getContextoBean().getFilialAtiva().getId().getFilEmp()));


		criteria.add(Restrictions.between("pedDte", filtros.getDataInicial(), filtros.getDataFinal()));

		criteria.addOrder(Order.asc("pedSeq"));
		return criteria.list();
	}

	public List<PedidoVO> pesquisarTelaWeb(List<TelaWebVO> tela, PedidoVendaParametro filtros)   {
		Criteria criteria = sessao.createCriteria(Pedido.class);

		if(filtros.getCodigoCliente()!=null){
			criteria.add(Restrictions.eq("pedCol", filtros.getCodigoCliente().intValue()));
		}

		filtros.getSituacao().forEach((String element) -> System.out.println("forLambda: "+element));

		if(filtros.getSituacao().size() >0) {
			criteria.add(Restrictions.in("pedSts",filtros.getSituacao()));
		}

		//criteria.add(Restrictions.eq("pedRep", ContextoUtil.getContextoBean().getUsuarioLogado().getUsuCol()));
		//criteria.add(Restrictions.eq("categoriaFiscal.id.cgfEmp", ContextoUtil.getContextoBean().getFilialAtiva().getId().getFilEmp()));


		criteria.add(Restrictions.between("pedDte", filtros.getDataInicial(), filtros.getDataFinal()));



		ProjectionList projList = Projections.projectionList();

		for(TelaWebVO o : tela){
			String campo="";
			campo=o.getTWU_CPO() !=null ? o.getTWU_CPO() : o.getTLW_CPO();
			montaProjecao(campo, projList);
		}

		criteria.setProjection(projList);

		criteria.addOrder(Order.asc("pedSeq"));
		criteria.setResultTransformer(Transformers.aliasToBean(PedidoVO.class));

		return criteria.list();
	}

	public Integer geraCodigoSequenciaMax(){

		if (!sessao.isOpen()){
			sessao = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria criteria = sessao.createCriteria(Pedido.class);

		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.max("pedSeq"));
		criteria.setProjection(projList);

		Number result = (Number) criteria.uniqueResult();

		int genId = result.intValue();

		genId++;

		return  genId;
	}

	public Integer geraCodigoMax(){

		if (!sessao.isOpen()){
			sessao = HibernateUtil.getSessionFactory().getCurrentSession();
		}
		Criteria criteria = sessao.createCriteria(Pedido.class);

		ProjectionList projList = Projections.projectionList();
		projList.add(Projections.max("pedCod"));
		criteria.setProjection(projList);

		Number result = (Number) criteria.uniqueResult();

		int genId = result.intValue();

		genId++;

		return  genId;
	}


	private void montaProjecao(String campo, ProjectionList projList){
		if(campo.equals("cliCol")){
			projList.add(Projections.property("id."+campo),campo);

		}else if(campo.equals("colFax")){
			projList.add(Projections.property("colaborador.colDdf"),"colDdf");
			projList.add(Projections.property("colaborador."+campo),campo);

		}else{
			projList.add(Projections.property(campo),campo);
		}

	}

}