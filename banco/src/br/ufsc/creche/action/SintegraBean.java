package br.ufsc.creche.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufsc.creche.parameter.SintegraPar;
import br.ufsc.creche.util.FacesUtil;


@ManagedBean
@SessionScoped
public class SintegraBean extends ActionBean {

	private SintegraPar sintegra = new SintegraPar();
	private boolean parametros=true;
	private boolean resultado=false;
	private String documentoSemMascara ="";
	private String estado="";


	public  String obtemImagem(){
		String cpt="";
	/*	Sintegra.solicitacao(estado);
		cpt = Sintegra.gerarCaptcha();

		if(estado.equals("RS")){
			Sintegra.rsSolicitacaoSintegra2(sintegra.getCnpj());
		}*/

		sintegra.setCaptchaBase64(cpt);
		sintegra.setCnpj(documentoSemMascara);

		return sintegra.getCaptchaBase64();
	}

	public  String obtemLogoEstado(){

		if(estado.equals("SC")){
			return sintegra.getLogoEstadoScBase64();
		}else if (estado.equals("SP")) {
			return sintegra.getLogoEstadoSpBase64();
		}else if (estado.equals("PR")) {
			return sintegra.getLogoEstadoPrBase64();
		}else if (estado.equals("RS")) {
			return sintegra.getLogoEstadoRsBase64();
		}else{
			return sintegra.getLogoSintegraBase64();
		}

	}



	public void valida(){
		if (sintegra.getCnpj().trim().length() ==14) {
			//sintegra = Sintegra.valida(sintegra.getCnpj(), sintegra.getCaptcha(), estado);
			if(sintegra.getMensagemErroConsulta()==null || sintegra.getMensagemErroConsulta().trim().length()<5){
				parametros=false;
				resultado=true;
			}else{
				FacesUtil.exibirMensagemErro(sintegra.getMensagemErroConsulta());
			}
		}else{
			FacesUtil.exibirMensagemErro("CNPJ inválido");
		}
	}

	public void dialogReturn(boolean capturaDados){

		SintegraPar aux = new SintegraPar();
		aux = sintegra;

		sintegra = new SintegraPar();
		parametros=true;
		resultado=false;

		if (capturaDados) {
			FacesUtil.closeDialog(aux);
		}else{
			FacesUtil.closeDialog(null);
		}

	}

	public boolean isParametros() {
		return parametros;
	}

	public void setParametros(boolean parametros) {
		this.parametros = parametros;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public String getDocumentoSemMascara() {
		return documentoSemMascara;
	}

	public void setDocumentoSemMascara(String documentoSemMascara) {
		this.documentoSemMascara = documentoSemMascara;
	}


	public SintegraPar getSintegra() {
		return sintegra;
	}


	public void setSintegra(SintegraPar sintegra) {
		this.sintegra = sintegra;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}

}
