package br.ufsc.creche.util;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

public class FacesUtil {

	public static void exibirMensagemSucesso(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}

	public static void exibirMensagemAlerta(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_WARN, mensagem);
	}

	public static void exibirMensagemErro(String mensagem) {
		exibirMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
	}

	private static void exibirMensagem(FacesMessage.Severity severity, String mensagem) {
		FacesMessage facesMessage = new FacesMessage(severity, mensagem, mensagem);
		getFacesContext().addMessage(null, facesMessage);
	}

	public static String getRequestParameter(String name) {
		return getFacesContext().getExternalContext().getRequestParameterMap().get(name);
	}

	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	public static Map<String, Object> getSessionMap() {
		return getFacesContext().getExternalContext().getSessionMap();
	}

	public static ServletContext getServletContext() {
		return (ServletContext) getFacesContext().getExternalContext().getContext();
	}

	public static HttpServletRequest getServletRequest() {
		return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
	}

	public static HttpServletResponse getServletResponse() {
		return (HttpServletResponse) getFacesContext().getExternalContext().getResponse();
	}

	public static HttpSession getSession(){
		return (HttpSession) getExternalContext().getSession(true);
	}

	public static void invalidarSessao() {
		getFacesContext().getExternalContext().invalidateSession();
	}

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static UIComponent findComponent(String idComponente){
		return getFacesContext().getViewRoot().findComponent(idComponente);
	}

	public static void openDialog(String nomeJanela, Map<String, Object> parametrosDialog, Map<String, List<String>> mapList ){
		getRequestContext().openDialog(nomeJanela, parametrosDialog, mapList);
	}

	public static void closeDialog(Object object){
		getRequestContext().closeDialog(object);
	}

	private static RequestContext getRequestContext() {
		return RequestContext.getCurrentInstance();
	}

	public static String redirecionar() {
		return  "/restrito/principal.jsf" + "?faces-redirect=true";
	}
}
