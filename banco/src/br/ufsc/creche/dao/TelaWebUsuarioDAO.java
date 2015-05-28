package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.hibernate.transform.Transformers;

import br.ufsc.creche.VO.TelaWebVO;
import br.ufsc.creche.model.TelaWebUsuario;
import br.ufsc.creche.util.DAOException;

public class TelaWebUsuarioDAO extends DAO<TelaWebUsuario> {

	@Override
	public void salvar(TelaWebUsuario model) throws DAOException {
		sessao.saveOrUpdate(model);
	}

	@Override
	public void excluir(TelaWebUsuario model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public TelaWebUsuario obterPorId(TelaWebUsuario filtro) {
		return (TelaWebUsuario) sessao.get(TelaWebUsuario.class, filtro.getId().getTwuFrm());
	}

	@Override
	public List<TelaWebUsuario> pesquisar(TelaWebUsuario filtros) {
		Criteria criteria = sessao.createCriteria(TelaWebUsuario.class);
		criteria.addOrder(Order.asc("id.twuFrm"));
		return criteria.list();
	}

	public TelaWebUsuario obterPorDescricao(TelaWebUsuario filtro) {
		Criteria criteria = sessao.createCriteria(TelaWebUsuario.class);
		Criteria telaWeb = criteria.createCriteria("telaWeb", "telaWeb", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("id.twuFrm", filtro.getId().getTwuFrm()));
		return (TelaWebUsuario) criteria.uniqueResult();
	}

	public List<TelaWebVO> montarTabelaDefault(TelaWebUsuario filtros) {
		/*String  hql="select new list(twu.twuPos as twuPos, twu.id.twuCpo as twuCpo, tw.tlwDes as tlwDes, twu.twuPer as twuPer )  "
				  + "from TelaWebUsuario twu          "
				  + "left join  twu.telaWeb as tw "
				  + "union all   "
				  + "select CASE (select count(*) from  TelaWebUsuario where twuFrm = tlwFrm and twuUsu = 4) when 0 then tlwPos else 0 end as tlwPos, tlwCpo, tlwDes, tlwPer  "
				  + "from TelaWeb "
				  + "where (tlwObg = 'S' and (select count(*) from TelaWebUsuario where twuFrm = tlwFrm and twuCpo = tlwCpo and twuUsu = 4) = 0)    "
				  + "or (tlwPos > 0 and (select count(*) from TelaWebUsuario where twuFrm = tlwFrm AND twuUsu = 4) = 0) "
				  + "order by 1 ";

		Query hqlQ = sessao.createQuery(hql);
		 */

		String sql="select twu_Pos ,  twu_Cpo , tlw_Des ,  tlw_Obg , tlw_dis,  'S' as TLW_DEF , twu_fil	"
				+ "from TELAWEBUSUARIO 	"
				+ "left join TELAWEB TW1 on TW1.TLW_FRM = TWU_FRM and TW1.TLW_CPO = TWU_CPO 	"
				+ "where twu_frm='"+filtros.getTelaWeb().getId().getTlwFrm()+"'  AND TWU_USU = "+ filtros.getId().getTwuUsu() +" "
				+ "union all 		"
				+ "select CASE "
				+ "(select count(*) from telawebusuario where TWU_FRM = TLW_FRM AND TWU_USU = "+ filtros.getId().getTwuUsu() +") when 0 then TLW_POS else 0 end as TLW_POS, TLW_CPO, TLW_DES, TLW_OBG , TLW_dis, tlw_def	, TLW_FIL     "
				+ "from TELAWEB  "
				+ "where (     (TLW_OBG = 'S' and (select count(*) from telawebusuario where TWU_FRM = TLW_FRM AND TWU_CPO = TLW_CPO and twu_usu = "+ filtros.getId().getTwuUsu() +") = 0) 		   "
				+ "or (TLW_DEF = 'S' and (select count(*) from telawebusuario where TWU_FRM = TLW_FRM AND TWU_USU = "+ filtros.getId().getTwuUsu() +") = 0)  ) "
				+ "and (TLW_FRM='"+filtros.getTelaWeb().getId().getTlwFrm()+"' )   "
				+ "order by 1 	";

		List<TelaWebVO>  sqlQ = sessao.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(TelaWebVO.class)).list();

		return sqlQ;
	}

	public List<TelaWebVO> montarCamposDisponivel(TelaWebUsuario filtros) {

		String sql="select TLW_POS, TLW_CPO, TLW_DES, TLW_OBG , TLW_DIS	, TLW_FIL	"
				+ "from TELAWEB TLW   "
				+ "where "
				+ "tlw_dis='S' "
				+ " and  tlw_frm='"+filtros.getTelaWeb().getId().getTlwFrm()+"' "
				+ "and ((select Count(*) from TELAWEBUSUARIO where TWU_FRM = TLW.TLW_FRM and TWU_CPO = TLW.TLW_CPO AND TWU_USU = "+ filtros.getId().getTwuUsu() +") = 0) "
				+ "order by TLW_POS asc";

		List<TelaWebVO>  sqlQ = sessao.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(TelaWebVO.class)).list();

		return sqlQ;
	}

	public List<TelaWebVO> montarCampoSelecionado(TelaWebUsuario filtros) {
		String sql="select TWU_POS, TWU_CPO,  TLW_DES, TWU_FIL	"
				+ "from telawebusuario twu "
				+ "inner join telaweb tw on tw.tlw_frm= twu.twu_frm and tw.tlw_cpo = twu.twu_cpo "
				+ "where "
				+ "twu_frm='"+filtros.getId().getTwuFrm()+"' and TWU_USU = "+ filtros.getId().getTwuUsu() +" "
				+ "order by TWU_POS ";

		List<TelaWebVO>  sqlQ = sessao.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(TelaWebVO.class)).list();

		return sqlQ;
	}


	public List<TelaWebVO> montarTabelaFiltro(TelaWebUsuario filtros) {

		String sql="select twu_Pos ,  twu_Cpo , tlw_Des ,  tlw_Obg , tlw_dis,  'S' as TLW_DEF , twu_fil	"
				+ "from TELAWEBUSUARIO 	"
				+ "left join TELAWEB TW1 on TW1.TLW_FRM = TWU_FRM and TW1.TLW_CPO = TWU_CPO 	"
				+ "where twu_frm='"+filtros.getTelaWeb().getId().getTlwFrm()+"'  AND TWU_USU = "+ filtros.getId().getTwuUsu() +" "
				+ "union all 		"
				+ "select CASE "
				+ "(select count(*) from telawebusuario where TWU_FRM = TLW_FRM AND TWU_USU = "+ filtros.getId().getTwuUsu() +") when 0 then TLW_POS else 0 end as TLW_POS, TLW_CPO, TLW_DES, TLW_OBG , TLW_dis, tlw_def	, TLW_FIL     "
				+ "from TELAWEB  "
				+ "where (TLW_DEF = 'S' and (select count(*) from telawebusuario where TWU_FRM = TLW_FRM AND TWU_USU = "+ filtros.getId().getTwuUsu() +") = 0)   "
				+ "order by 1 	";

		List<TelaWebVO>  sqlQ = sessao.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(TelaWebVO.class)).list();

		return sqlQ;
	}

}
