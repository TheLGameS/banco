package br.ufsc.creche.action;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.ufsc.creche.util.CepWebService;
import br.ufsc.creche.util.Diversos;
import br.ufsc.creche.util.FacesUtil;

@ManagedBean
@RequestScoped
public class CepServiceBean implements Serializable {

	private static final long serialVersionUID = -8013345501568178833L;

	private String cep = null;

	private String tipoLogradouro;
	private String logradouro;
	private String estado;
	private String cidade;
	private String bairro;




	public void encontraCEP() {
		CepWebService cepWebService = new CepWebService(getCep());

		if (cepWebService.getResultado() != 0) {
			setTipoLogradouro(cepWebService.getTipoLogradouro());
			setLogradouro(cepWebService.getLogradouro().replaceFirst("- LADO PAR", "").replaceFirst("- LADO ÍMPAR", ""));
			setEstado(cepWebService.getEstado());
			setCidade(Diversos.removerAcentos(cepWebService.getCidade(),true,false));
			setBairro(cepWebService.getBairro());
		} else {
			FacesUtil.exibirMensagemErro("CEP não encontrado");
		}
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}
