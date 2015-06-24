package br.ufsc.creche.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import br.ufsc.creche.VO.GraficoDietaVO;
import br.ufsc.creche.model.Cardapio;
import br.ufsc.creche.model.CardapioProduto;
import br.ufsc.creche.util.DAOException;


public class CardapioProdutoDAO extends DAO<CardapioProduto> {

	@Override
	public void salvar(CardapioProduto model) throws DAOException {
		try {
			sessao.saveOrUpdate(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public void excluir(CardapioProduto model) throws DAOException {
		try {
			sessao.delete(model);
			sessao.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}

	@Override
	public CardapioProduto obterPorId(CardapioProduto filtro) {
		CardapioProduto col = (CardapioProduto) sessao.get(CardapioProduto.class, filtro.getCodigoCardapioProduto());
		if(col!=null) {
			sessao.setReadOnly(col, true);
		}
		return col;
	}

	@Override
	public List<CardapioProduto> pesquisar(CardapioProduto filtros) {
		return sessao.createCriteria(CardapioProduto.class).list();
	}

	public List<CardapioProduto> pesquisarProduto(Cardapio filtros) {
		return sessao.createCriteria(CardapioProduto.class).add(Restrictions.eq("cardapio.codigoCardapio", filtros.getCodigoCardapio())).list();
	}

	public List<GraficoDietaVO> montarGrafico(Cardapio cardapio) {
		String sql="select "
				+" prod.descricao ,  cp.cd_produto as produto, sum(cp.quantidade) as quantidade "
				+" from cardapioproduto  cp "
				+" left outer join produto prod on prod.cd_produto = cp.cd_produto "
				+" where cp.cd_cardapio="+cardapio.getCodigoCardapio()
				+" group by 1,2 ";
		List<GraficoDietaVO>  sqlQ = sessao.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(GraficoDietaVO.class)).list();

		return sqlQ;
	}

}