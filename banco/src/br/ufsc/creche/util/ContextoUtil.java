package br.ufsc.creche.util;

import br.ufsc.creche.action.ContextoBean;


public class ContextoUtil {

	/**
	 * Método que obtem a instancia de contextoBean do escopo de sessao
	 * @return
	 */
	public static ContextoBean getContextoBean() {
		// Primeiro pegamos o contexto do JSF
		//FacesContext context = FacesContext.getCurrentInstance();

		// Com o contexto do JSF, conseguimos ter acesso ao contexto externo
		//ExternalContext external = context.getExternalContext();


		// Com o contexto externo, temos acesso ao objeto session
		//HttpSession session = FacesUtil.getSession();

		// Da sessao obtemos o objeto que desejamos: contextoBean
		ContextoBean contextoBean = (ContextoBean) FacesUtil.getSession().getAttribute("contextoBean");
		return contextoBean;
	}
}
