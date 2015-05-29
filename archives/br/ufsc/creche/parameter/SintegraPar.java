package br.ufsc.creche.parameter;


public class SintegraPar {

	// Atributos para Consulta
	private String cnpj;
	private String captcha;
	private String captchaBase64;
	private String logoSintegraBase64="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";
	private String logoEstadoScBase64="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";
	private String logoEstadoSpBase64="iVBORw0KGgoAAAANSUhEUgAAAHAAAABQCAMAAAD2kgFPAAAAclBMVEX///8AAAD/AAAAAP//Zmb/mZn/MzOPkv9vdP+/wf//zMz/f3//Ly//7+9QUFA/Rf//T0//Hx/v7/8PF/9/g///X1//398fJv//j48vNv/f4P/P0P//v78QEBCgsP8zMzP/r69PVf+fov9fZP8gICBAQED1wGVLAAAByklEQVRo3u2a23KCMBRFo4QAwtRkEERQipf+/y82FxICvrSdkzx0zn4y0cni3LZxRvKxi6ovstuvxWipxMVmH4qogMvZomuJ1YEv28DAzhI5WelQ2ccABVby6E4f3JGtmnm7FZARcpJqXqkZxbP3iEy9kRIBmlJKS3Vsowl9nYxDll0sVWU75Q0o0FSQqePr8yXRqgfiiAK8S3Vj6PaccUlyGY8PA5zmhiIwckBTwDrxdM8KV0Z4oAqwOCcb5XI3DQLUE/jGk7Wc+wYcSNUilxndQnubU2Dg1czEacjfc1qG6NKre4Tx6PFOJDyQkMzxjiQKsHfDcddrGgKY+oV1SX0sDg4NpD7Q+s3ZLKsQwMoHDoZYF2a5D+Kl7YoocZn9vgjjNOucSs/J3esqDNAPsZd2M5J1gPBAr4pFvQTYigBAY5aNt/1cJ5SBAgWbONsSbXyKx/jExP4F1qVCDj1/nw19T9T5rA5kAr21Na4xxPqiSOf6TcC3tkZ4d2zncW1p20VQ9QHowfd+XGhtf1oAAiNL9mlUIRCBfwFGHwsEIhC9FIHopTj4CETzRqdBIJo3AhH4A+ArLu9GUKhfK/I/FT7Raf4B8BbZ2r4B7cUkqwc1BgcAAAAASUVORK5CYII=";
	private String logoEstadoPrBase64="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";
	private String logoEstadoRsBase64="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";

	//Atributos do Retorno
	private String razaoSocial;
	private String dataAbertura;
	private String atividadePrincipal;
	private String atividadeSecundaria;
	private String logradouro;
	private String numero;
	private String complemento;
	private String cep;
	private String bairro;
	private String municipio;
	private String uf;
	private String email;
	private String telefone;
	private String situacaoCadastral;
	private String dataSituacaoCadastral;
	private String mensagemErroConsulta;
	private String inscricaoEstadual;
	private String observacao;
	private String regimeApuracaoIcms;
	private String enquadramentoFiscal;
	private String documentosEletronicos;

	//Atributos Especificos do RS
	private String inscricaoUnica;
	private String nomeFantasia;
	private String delegaciaFazendaria;
	private String motivoInclusao;
	private String dataBaixa;
	private String motivoBaixa;

	public String getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public String getAtividadePrincipal() {
		return atividadePrincipal;
	}
	public void setAtividadePrincipal(String atividadePrincipal) {
		this.atividadePrincipal = atividadePrincipal;
	}
	public String getAtividadeSecundaria() {
		return atividadeSecundaria;
	}
	public void setAtividadeSecundaria(String atividadeSecundaria) {
		this.atividadeSecundaria = atividadeSecundaria;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getSituacaoCadastral() {
		return situacaoCadastral;
	}
	public void setSituacaoCadastral(String situacaoCadastral) {
		this.situacaoCadastral = situacaoCadastral;
	}
	public String getDataSituacaoCadastral() {
		return dataSituacaoCadastral;
	}
	public void setDataSituacaoCadastral(String dataSituacaoCadastral) {
		this.dataSituacaoCadastral = dataSituacaoCadastral;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getCaptchaBase64() {
		return captchaBase64;
	}
	public void setCaptchaBase64(String captchaBase64) {
		this.captchaBase64 = captchaBase64;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getMensagemErroConsulta() {
		return mensagemErroConsulta;
	}
	public void setMensagemErroConsulta(String mensagemErroConsulta) {
		this.mensagemErroConsulta = mensagemErroConsulta;
	}
	public String getLogoSintegraBase64() {
		return logoSintegraBase64;
	}
	public void setLogoSintegraBase64(String logoSintegraBase64) {
		this.logoSintegraBase64 = logoSintegraBase64;
	}
	public String getLogoEstadoScBase64() {
		return logoEstadoScBase64;
	}
	public void setLogoEstadoScBase64(String logoEstadoScBase64) {
		this.logoEstadoScBase64 = logoEstadoScBase64;
	}
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}
	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getRegimeApuracaoIcms() {
		return regimeApuracaoIcms;
	}
	public void setRegimeApuracaoIcms(String regimeApuracaoIcms) {
		this.regimeApuracaoIcms = regimeApuracaoIcms;
	}
	public String getEnquadramentoFiscal() {
		return enquadramentoFiscal;
	}
	public void setEnquadramentoFiscal(String enquadramentoFiscal) {
		this.enquadramentoFiscal = enquadramentoFiscal;
	}
	public String getDocumentosEletronicos() {
		return documentosEletronicos;
	}
	public void setDocumentosEletronicos(String documentosEletronicos) {
		this.documentosEletronicos = documentosEletronicos;
	}
	public String getLogoEstadoSpBase64() {
		return logoEstadoSpBase64;
	}
	public void setLogoEstadoSpBase64(String logoEstadoSpBase64) {
		this.logoEstadoSpBase64 = logoEstadoSpBase64;
	}
	public String getLogoEstadoPrBase64() {
		return logoEstadoPrBase64;
	}
	public void setLogoEstadoPrBase64(String logoEstadoPrBase64) {
		this.logoEstadoPrBase64 = logoEstadoPrBase64;
	}
	public String getLogoEstadoRsBase64() {
		return logoEstadoRsBase64;
	}
	public void setLogoEstadoRsBase64(String logoEstadoRsBase64) {
		this.logoEstadoRsBase64 = logoEstadoRsBase64;
	}
	public String getInscricaoUnica() {
		return inscricaoUnica;
	}
	public void setInscricaoUnica(String inscricaoUnica) {
		this.inscricaoUnica = inscricaoUnica;
	}
	public String getNomeFantasia() {
		return nomeFantasia;
	}
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	public String getDelegaciaFazendaria() {
		return delegaciaFazendaria;
	}
	public void setDelegaciaFazendaria(String delegaciaFazendaria) {
		this.delegaciaFazendaria = delegaciaFazendaria;
	}
	public String getMotivoInclusao() {
		return motivoInclusao;
	}
	public void setMotivoInclusao(String motivoInclusao) {
		this.motivoInclusao = motivoInclusao;
	}
	public String getDataBaixa() {
		return dataBaixa;
	}
	public void setDataBaixa(String dataBaixa) {
		this.dataBaixa = dataBaixa;
	}
	public String getMotivoBaixa() {
		return motivoBaixa;
	}
	public void setMotivoBaixa(String motivoBaixa) {
		this.motivoBaixa = motivoBaixa;
	}
}