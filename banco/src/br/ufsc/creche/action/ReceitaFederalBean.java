package br.ufsc.creche.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.ufsc.creche.parameter.ReceitaFederalPar;
import br.ufsc.creche.util.FacesUtil;
import br.ufsc.creche.util.ReceitaFederal;


@ManagedBean
@SessionScoped
public class ReceitaFederalBean extends ActionBean {

	private ReceitaFederalPar rec = new ReceitaFederalPar();
	private boolean parametros=true;
	private boolean resultado=false;
	private String documentoSemMascara ="";


	public  String obtemImagem(){
		String cpt="";
		ReceitaFederal.solicitacao();
		cpt = ReceitaFederal.gerarCaptcha();

		rec.setCaptchaBase64(cpt);
		rec.setCnpj(documentoSemMascara);

		return rec.getCaptchaBase64();
	}

	public ReceitaFederalPar getRec() {
		return rec;
	}

	public void setRec(ReceitaFederalPar rec) {
		this.rec = rec;
	}

	public void valida(){
		if (rec.getCnpj().trim().length() ==14) {
			rec = ReceitaFederal.valida(rec.getCnpj(), rec.getCaptcha());
			if(rec.getMensagemErroConsulta()==null){
				parametros=false;
				resultado=true;
			}else{
				FacesUtil.exibirMensagemErro(rec.getMensagemErroConsulta());
			}
		}else{
			FacesUtil.exibirMensagemErro("CNPJ inválido");
		}
	}

	public void dialogReturn(boolean capturaDados){

		ReceitaFederalPar aux = new ReceitaFederalPar();
		aux = rec;

		rec = new ReceitaFederalPar();
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

}
