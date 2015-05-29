package br.ufsc.creche.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufsc.creche.model.Usuario;
import br.ufsc.creche.negocio.UsuarioRN;
import br.ufsc.creche.util.FacesUtil;


/**
 * Objetivo desta classe é conter os dados do usuario logado
 * armazendo em um único objeto tudo que possa ser necessario,
 * o qual estará em escopo de sessão.
 */
@ManagedBean(name = "contextoBean")
@SessionScoped
public class ContextoBean {

	private Usuario	usuarioLogado = null;

	public Usuario getUsuarioLogado() {
		//FacesContext context = FacesContext.getCurrentInstance();
		//ExternalContext external = context.getExternalContext();
		String login = FacesUtil.getExternalContext().getRemoteUser();
		if (this.usuarioLogado == null || !login.equals(String.valueOf(usuarioLogado.getCdUsuario()))) {
			if (login != null) {
				UsuarioRN usuarioRN = new UsuarioRN();
				usuarioLogado = new Usuario();
				usuarioLogado.setCdUsuario(Integer.valueOf(login));
				this.usuarioLogado = usuarioRN.obterPorId(usuarioLogado);
			}
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuario) {
		this.usuarioLogado = usuario;
	}

	public String logout() {
		FacesUtil.invalidarSessao();
		return "publico/login";
	}

}