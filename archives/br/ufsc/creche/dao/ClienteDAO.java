package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

import br.ufsc.creche.VO.ClienteVO;
import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.model.Cliente;
import br.ufsc.creche.util.ContextoUtil;
import br.ufsc.creche.util.DAOException;

public class ClienteDAO extends DAO<Cliente> {

	@Override
	public void salvar(Cliente model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(Cliente model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}

	}

	@Override
	public Cliente obterPorId(Cliente filtro) {
		Cliente col = (Cliente) sessao.get(Cliente.class, filtro.getId().getCliCol());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	public Cliente obterPorCodigoVO(ClienteVO filtro) {
		Criteria criteria = sessao.createCriteria(Cliente.class);
		criteria.add(Restrictions.eq("id.cliCol", filtro.getCliCol().intValue()));
		criteria.add(Restrictions.eq("id.cliEmp", filtro.getCliEmp().intValue()));

		Cliente colDoc = (Cliente) criteria.uniqueResult();

		if(colDoc!=null) {
			sessao.setReadOnly(colDoc, true);
		}

		return colDoc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> pesquisar(Cliente filtros)   {
		Criteria criteria = sessao.createCriteria(Cliente.class);
		criteria.addOrder(Order.asc("id.cliCol"));
		return criteria.list();
	}

	@SuppressWarnings({ "unused", "unchecked" })
	public List<ClienteVO> pesquisarTelaWeb(List<TelaWebVO> tela, int tipoLista)   {
		Criteria criteria = sessao.createCriteria(Cliente.class);

		Criteria criteriaClienteRepresentante = criteria.createCriteria("clienteRepresentantes", "cliRep" , JoinType.LEFT_OUTER_JOIN);

		Criteria criteriaColaboradorEmpresa = criteria.createCriteria("colaboradorEmpresas", "colaboradorEmpresa" , JoinType.LEFT_OUTER_JOIN);

		Criteria criteriaColaborador = criteria.createCriteria("colaboradorEmpresas.colaborador", "colaborador" , JoinType.LEFT_OUTER_JOIN);

		Criteria criteriaMunicipio = criteria.createCriteria("colaborador.municipio", "municipio" , JoinType.LEFT_OUTER_JOIN);

		Criteria criteriaClienteMunicipioCobranca = criteria.createCriteria("municipioByCliCmu", "municipioByCliCmu" , JoinType.LEFT_OUTER_JOIN);

		Criteria criteriaClienteMunicipioEntrega = criteria.createCriteria("municipioByCliEmu", "municipioByCliEmu" , JoinType.LEFT_OUTER_JOIN );

		ProjectionList projList = Projections.projectionList();

		for(TelaWebVO o : tela){
			String campo="";
			campo=o.getTWU_CPO() !=null ? o.getTWU_CPO() : o.getTLW_CPO();
			montaProjecao(campo, projList);
		}

		criteria.setProjection(projList);
		//criteria.add(Restrictions.eq("cliRep.id.clrRep",ContextoUtil.getContextoBean().getUsuarioLogado().getUsuCol()));

		switch (tipoLista) {
		case 0:
			System.out.println("Ativos");
			criteria.add(Restrictions.isNull("cliDtf"));
			break;
		case 1:
			System.out.println("Inativos");
			criteria.add(Restrictions.isNotNull("cliDtf"));
			break;
		case 2:
			System.out.println("Ambos");
			break;
		default:
			break;
		}

		criteria.addOrder(Order.asc("id.cliCol"));
		criteria.setResultTransformer(Transformers.aliasToBean(ClienteVO.class));

		return criteria.list();
	}

	private void montaProjecao(String campo, ProjectionList projList){
		if(campo.equals("cliCol")){
			projList.add(Projections.property("id."+campo),campo);
		}else if(campo.equals("cliCtf")){
			projList.add(Projections.property("categoriaFinanceira.id.ctfCod"),campo);
		}else if(campo.equals("cliEmp")){
			projList.add(Projections.property("id."+campo),campo);
		}else if(campo.equals("cliEmu")){
			projList.add(Projections.property("municipioByCliEmu.munCod"),campo);
		}else if(campo.equals("cliEmun")){
			projList.add(Projections.property("municipioByCliEmu.munNom"),campo);
		}else if(campo.equals("cliEsig")){
			projList.add(Projections.property("municipioByCliEmu.estado.estSig"),campo);
		}else if(campo.equals("cliCmu")){
			projList.add(Projections.property("municipioByCliCmu.munCod"),campo);
		}else if(campo.equals("cliCmun")){
			projList.add(Projections.property("municipioByCliCmu.munNom"),campo);
		}else if(campo.equals("cliCsig")){
			projList.add(Projections.property("municipioByCliCmu.estado.estSig"),campo);
		}else if(campo.equals("colRaz")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colFon")){
			projList.add(Projections.property("colaborador.colDdd"),"colDdd");
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colFax")){
			projList.add(Projections.property("colaborador.colDdf"),"colDdf");
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colCel")){
			projList.add(Projections.property("colaborador.colDdc"),"colDdc");
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colTip")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colInf")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colNmf")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colApe")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colInm")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colIne")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colDti")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colEnd")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colNro")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colCpl")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colBai")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colCep")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colCxp")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colEma")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colWeb")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colCon")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("colIcm")){
			projList.add(Projections.property("colaborador."+campo),campo);
		}else if(campo.equals("estSig")){
			projList.add(Projections.property("municipio.estado."+campo),campo);
		}else if(campo.equals("munNom")){
			projList.add(Projections.property("municipio."+campo),campo);
		}else if(campo.equals("colMun")){
			projList.add(Projections.property("municipio.munCod"),campo);
		}else{
			projList.add(Projections.property(campo),campo);
		}

	}
}