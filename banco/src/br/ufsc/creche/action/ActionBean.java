package br.ufsc.creche.action;

import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.RNException;


public abstract class ActionBean {

	protected final void apresentarMenssagemDeErro(RNException e){
		apresentarMenssagemDeErro(e.getMessage());
	}

	protected final void apresentarMenssagemDeErro(String msg){
		FacesUtil.exibirMensagemErro(msg);
	}

	//	 ALTER ROLE postgres PASSWORD 'novasenha';

	protected final void apresentarMenssagemDeErro(String msg, String tipo){
		switch (tipo) {
		case "info":
			FacesUtil.exibirMensagemSucesso(msg);
			break;
		case "fatal":
			FacesUtil.exibirMensagemErro(msg);
			break;
		case "warn":
			FacesUtil.exibirMensagemAlerta(msg);
			break;
		default:
			apresentarMenssagemDeErro(msg);
		}
	}

	public static String redirecionar() {
		String b = FacesUtil.getExternalContext().getApplicationContextPath();
		b = b+"/restrito/principal.jsf";
		return b;
	}


}
