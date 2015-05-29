package br.ufsc.creche.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.SessionFactory;

import br.ufsc.creche.util.HibernateUtil;


/**
 * Classe que interceptara TODAS as requisições
 * criando uma sessao com o hibernate,
 * @author Administrador
 */
@WebFilter({ "*.jsf", "/webservice/*" })
public class ConexaoHibernateFilter implements Filter {

	private SessionFactory sf;

	@Override
	public void destroy(){
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			// inicializa a transacao
			sf.getCurrentSession().beginTransaction();
			// segue o "baile" faz o que foi solicitado na requisicao
			chain.doFilter(request, response);

			// após concluir requisição, senao ocorrer erro, comita
			sf.getCurrentSession().getTransaction().commit();
		}catch(Throwable t){
			// caso de erro, rollback (desfaz tudo)

			if(sf.getCurrentSession().getTransaction().isActive()){
				sf.getCurrentSession().getTransaction().rollback();
			}

		} finally{
			sf.getCurrentSession().close();
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		sf = HibernateUtil.getSessionFactory();
	}

}
