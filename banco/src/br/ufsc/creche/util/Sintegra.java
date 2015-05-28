package br.ufsc.creche.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import br.ufsc.creche.parameter.SintegraPar;
import sun.misc.BASE64Encoder;

public class Sintegra {

	static DefaultHttpClient cliente = null;
	static HttpContext contexto = null;
	static HttpResponse resposta = null;
	static HttpEntity entidade = null;

	static InputStream entrada = null;
	static String linha=null;
	static BufferedReader bufferedReader = null;
	static String imgcaptcha = "";

	static HttpGet requisição1;
	static String view="";
	static String event="";
	static String paramBot="";


	static SintegraPar resultadoConsulta = new SintegraPar();

	public static void solicitacao(String estado){

		// Criando o cliente
		cliente = new DefaultHttpClient();

		// Adicionando um sistema de redireção
		cliente.setRedirectStrategy(new LaxRedirectStrategy());

		// Mantendo a conexão sempre ativa
		cliente.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());

		// Criando o container de cookies
		CookieStore cookie = new BasicCookieStore();

		// Criando o contexto de conexão
		contexto = new BasicHttpContext();

		// Adicionando o coockie store no contexto de conexão
		contexto.setAttribute(ClientContext.COOKIE_STORE, cookie);

		// Criando o método de acesso

		if(estado.equalsIgnoreCase("SC")){
			requisição1 = new HttpGet("http://sistemas3.sef.sc.gov.br/sintegra/consulta_empresa_pesquisa.aspx");
		}else if(estado.equalsIgnoreCase("SP")){
			requisição1 = new HttpGet("http://pfeserv1.fazenda.sp.gov.br/sintegrapfe/consultaSintegraServlet");
		}else if(estado.equalsIgnoreCase("PR")){
			requisição1 = new HttpGet("http://www.sintegra.fazenda.pr.gov.br/sintegra/");
		}else if(estado.equalsIgnoreCase("RS")){
			requisição1 = new HttpGet("http://sintegra.sefaz.rs.gov.br/sef_root/inf/Sintegra_Entrada.asp");
		}



		// Resposta

		try {
			resposta = cliente.execute(requisição1, contexto);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Escrever informações
		System.out.println("Status Line: " + resposta.getStatusLine());

		// Separador
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println();

		// Buscando a entidade
		entidade = resposta.getEntity();


		// Escrever informações
		System.out.println("Encoding: " + entidade.getContentEncoding());
		System.out.println("Tamanho: " + entidade.getContentLength());
		System.out.println("Tipo: " + entidade.getContentType());

		// Separador
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println();

		// Baixar o stream

		try {
			entrada = entidade.getContent();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Cria um stream de leitura
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(entrada, "LATIN1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// Cria o receptor de linha


		// Cria o acumulador
		String acumulador = "";

		// Somente cria acumulador para o Estado de SP, caso crie para SC não irá funcionar
		if(estado.equals("SP") || estado.equals("PR") || estado.equals("RS")){
			try {
				while ((linha = bufferedReader.readLine()) != null) {
					// Escreva
					//System.out.println(linha);
					acumulador += "\n" + linha;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String html = null;
		HTMLDocument document=null;
		ElementIterator elementIterator=null;
		// Estado do RS nao popula HTMLDocument
		if(!estado.equals("RS")){
			try {
				html = EntityUtils.toString(entidade);
			} catch (ParseException e2) {
				e2.printStackTrace();
			} catch (IOException e2) {
				e2.printStackTrace();
			}

			// Busco o documento estruturado
			document = getHTMLDocument(html);

			// Busco todos os elementos em forma de iterador
			elementIterator = new ElementIterator(document);
		}



		// Crio o elemento que vai recepcionar
		Element element;
		//Crio o viewstate para receber um valor para o método post futuro
		String viewstate = "";
		// Crio o imgcaptcha para receber um valor do link do captcha

		// Enquanto existir próximo elemento

		if(estado.equalsIgnoreCase("SC")){
			// So itera sobre o while para o estado de SC
			while ((element = elementIterator.next()) != null) {
				if (element.getName().equals(HTML.Tag.INPUT.toString()) && ((String) element.getAttributes().getAttribute(HTML.Attribute.NAME)).equalsIgnoreCase("__viewstate")) {
					viewstate = (String) element.getAttributes().getAttribute(HTML.Attribute.VALUE);
					view = viewstate;
				}
				if (element.getName().equals(HTML.Tag.INPUT.toString()) && ((String) element.getAttributes().getAttribute(HTML.Attribute.NAME)).equalsIgnoreCase("__EVENTVALIDATION")) {
					event = (String) element.getAttributes().getAttribute(HTML.Attribute.VALUE);
				}
				if (element.getName().equals(HTML.Tag.IMG.toString()) && ((String) element.getAttributes().getAttribute(HTML.Attribute.SRC)).contains("CaptchaImage.axd?guid=")) {
					imgcaptcha = "http://sistemas3.sef.sc.gov.br/sintegra/" + ((String) element.getAttributes().getAttribute(HTML.Attribute.SRC)).replaceAll("amp", "");
				}
			}
		}else if(estado.equalsIgnoreCase("SP")){
			paramBot = acumulador.substring(acumulador.indexOf("paramBot"));
			paramBot = paramBot.substring(paramBot.indexOf("value"), 42);
			paramBot = paramBot.substring(7, 31);

			acumulador = acumulador.substring(acumulador.indexOf("/sintegrapfe/imageGenerator?keycheck="));
			acumulador = acumulador.substring(0, acumulador.indexOf("alt"));

			int tam = acumulador.length();

			acumulador = acumulador.substring(0, tam-2);

			imgcaptcha = "http://pfeserv1.fazenda.sp.gov.br"+acumulador;

		}else if(estado.equalsIgnoreCase("PR")){
			Number number = Math.random() * 2;
			imgcaptcha = "http://www.sintegra.fazenda.pr.gov.br/sintegra/captcha?"+number;
		}
		else if(estado.equalsIgnoreCase("RS")){

			String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			int key_length = 32;
			String key = "";
			Double n;
			for (int i=0; i<key_length; i++) {
				n  = Math.floor(Math.random() * (chars.length()-1));
				int conv = n.intValue();
				key +=  chars.substring(conv,conv+1);
			}

			paramBot=key;
			imgcaptcha = "http://sintegra.sefaz.rs.gov.br/captchaweb/prCaptcha.aspx?f=getimage&rld=0&rdn="+key;

		}

		// Separador
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println();

	}


	public static void rsSolicitacaoSintegra2(String cnpj){
		HttpPost requisição4 =null;
		requisição4 = new HttpPost("http://sintegra.sefaz.rs.gov.br/sef_root/inf/SEF_SINTEGRA_2.asp");

		List<NameValuePair> nameValuePairs = new ArrayList<>();

		//RS
		nameValuePairs.add(new BasicNameValuePair("cgcmf", cnpj));
		nameValuePairs.add(new BasicNameValuePair("cgcte", ""));
		nameValuePairs.add(new BasicNameValuePair("LOCAL", "SINTEGRA"));
		nameValuePairs.add(new BasicNameValuePair("SEQ", "1"));

		UrlEncodedFormEntity urlEncodedFormEntity = null;
		try {
			urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		requisição4.setEntity(urlEncodedFormEntity);

		try {
			resposta = cliente.execute(requisição4, contexto);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		entidade = resposta.getEntity();

		try {
			entrada = entidade.getContent();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		bufferedReader = new BufferedReader(new InputStreamReader(entrada));

		List<String> listaRs = new ArrayList<String>();

		try {
			while ((linha = bufferedReader.readLine()) != null) {
				//System.out.println(linha);
				listaRs.add(linha);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static String gerarCaptcha(){

		HttpGet requisição2 = new HttpGet(imgcaptcha);


		// Resposta
		try {
			resposta = cliente.execute(requisição2, contexto);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Escrever informações
		System.out.println("Status Line: " + resposta.getStatusLine());

		// Separador
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println();

		// Buscando a entidade
		entidade = resposta.getEntity();

		// Escrever informações
		System.out.println("Encoding: " + entidade.getContentEncoding());
		System.out.println("Tamanho: " + entidade.getContentLength());
		System.out.println("Tipo: " + entidade.getContentType());

		// Separador
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println();

		// Baixar o stream
		try {
			entrada = entidade.getContent();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ByteArrayOutputStream out = null;
		try (InputStream in = new BufferedInputStream(entrada)) {
			out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int n = 0;
			try {
				while (-1 != (n = in.read(buf))) {
					out.write(buf, 0, n);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		byte[] response = out.toByteArray();

		/*try (FileOutputStream fos = new FileOutputStream("c://web//captcha.png")) {
			fos.write(response);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		String captchaBase64 = new BASE64Encoder().encode(response);

		return captchaBase64;

	}


	public static SintegraPar valida(String cnpj, String captcha, String estado){

		// Criando o método de acesso

		HttpPost requisição3 =null;

		if(estado.equalsIgnoreCase("SC")){
			requisição3 = new HttpPost("http://sistemas3.sef.sc.gov.br/sintegra/consulta_empresa_pesquisa.aspx");
		}else if(estado.equalsIgnoreCase("SP")){
			requisição3 = new HttpPost("http://pfeserv1.fazenda.sp.gov.br/sintegrapfe/sintegra");
		}else if(estado.equalsIgnoreCase("PR")){
			requisição3 = new HttpPost("http://www.sintegra.fazenda.pr.gov.br/sintegra/");
		}else if(estado.equalsIgnoreCase("RS")){
			requisição3 = new HttpPost("http://sintegra.sefaz.rs.gov.br/sef_root/inf/SEF_SINTEGRA_3.asp");
		}

		// Lista de parâmetros
		List<NameValuePair> nameValuePairs = new ArrayList<>();

		// SC
		nameValuePairs.add(new BasicNameValuePair("txt_CPFCNPJ", cnpj)); // 00000000000191   06079307000106
		nameValuePairs.add(new BasicNameValuePair("txtCodigoCaptcha", captcha));
		nameValuePairs.add(new BasicNameValuePair("btnEnviar", "Pesquisar"));
		nameValuePairs.add(new BasicNameValuePair("opt_pessoa", "2"));
		nameValuePairs.add(new BasicNameValuePair("__EVENTVALIDATION", event));
		nameValuePairs.add(new BasicNameValuePair("__VIEWSTATE", view));


		// SP
		nameValuePairs.add(new BasicNameValuePair("Key", captcha));
		nameValuePairs.add(new BasicNameValuePair("botao", "Consulta por CNPJ"));
		nameValuePairs.add(new BasicNameValuePair("cnpj", cnpj));
		nameValuePairs.add(new BasicNameValuePair("hidFlag", "1"));
		nameValuePairs.add(new BasicNameValuePair("paramBot", paramBot));
		nameValuePairs.add(new BasicNameValuePair("servico", "cnpj"));
		nameValuePairs.add(new BasicNameValuePair("ie", ""));


		// PR
		nameValuePairs.add(new BasicNameValuePair("_method", "POST"));
		nameValuePairs.add(new BasicNameValuePair("data[Sintegra1][Cnpj]", Diversos.mascaraDocumento(cnpj)));
		nameValuePairs.add(new BasicNameValuePair("data[Sintegra1][CodImage]", captcha));
		nameValuePairs.add(new BasicNameValuePair("empresa", "Consultar Empresa"));


		//RS
		nameValuePairs.add(new BasicNameValuePair("btOK", "Avançar"));
		nameValuePairs.add(new BasicNameValuePair("captchaCode", captcha));
		nameValuePairs.add(new BasicNameValuePair("captchaRdnKey", paramBot));
		nameValuePairs.add(new BasicNameValuePair("cgcmf", cnpj));
		nameValuePairs.add(new BasicNameValuePair("cgcte", ""));
		nameValuePairs.add(new BasicNameValuePair("LOCAL", "SINTEGRA"));
		nameValuePairs.add(new BasicNameValuePair("SEQ", "1"));



		// Encapsulando
		UrlEncodedFormEntity urlEncodedFormEntity = null;
		try {
			urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// A adição dos parâmetros
		requisição3.setEntity(urlEncodedFormEntity);

		// Resposta
		try {
			resposta = cliente.execute(requisição3, contexto);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Escrever informações
		System.out.println("Status Line: " + resposta.getStatusLine());

		// Separador
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println();

		// Buscando a entidade
		entidade = resposta.getEntity();

		// Escrever informações
		System.out.println("Encoding: " + entidade.getContentEncoding());
		System.out.println("Tamanho: " + entidade.getContentLength());
		System.out.println("Tipo: " + entidade.getContentType());

		// Separador
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println();

		// Baixar o stream
		try {
			entrada = entidade.getContent();
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Cria um stream de leitura
		bufferedReader = new BufferedReader(new InputStreamReader(entrada));

		List<String> brk = new ArrayList<String>();

		// Para cada linha
		try {
			while ((linha = bufferedReader.readLine()) != null) {
				// Escreva
				//System.out.println(linha);
				brk.add(linha);
			}

			if(estado.equals("SC")){
				if(brk.size() > 370){
					retornoSC(brk);
					resultadoConsulta.setCaptcha("");
					resultadoConsulta.setMensagemErroConsulta(null);
				}else{
					montaErro(brk, estado);
				}
			}else if(estado.equals("SP")){
				if(brk.size() > 350){
					retornoSP(brk);
					resultadoConsulta.setCaptcha("");
					resultadoConsulta.setMensagemErroConsulta(null);
				}else{
					montaErro(brk, estado);
				}
			}else if(estado.equals("PR")){
				if(brk.size() > 140){
					retornoPR(brk);
					resultadoConsulta.setCaptcha("");
					resultadoConsulta.setMensagemErroConsulta(null);
				}else{
					montaErro(brk, estado);
				}
			}else if(estado.equals("RS")){
				if(brk.size() > 45){
					retornoRS(brk);
					resultadoConsulta.setCaptcha("");
					resultadoConsulta.setMensagemErroConsulta(null);
				}else{
					montaErro(brk, estado);
				}
			}




		} catch (IOException e) {
			e.printStackTrace();
		}

		// Separador
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println();

		return resultadoConsulta;
	}

	private static void retornoRS(List<String> lista) {
		resultadoConsulta = new SintegraPar();

		String conteudoOriginal = lista.get(40);
		Document doc = Jsoup.parse(conteudoOriginal);

		List<String> atividades = new ArrayList<String>();
		Map<String,Object> retornoFormatado = new HashMap<String,Object>();

		Elements links = doc.getElementsByTag("td");
		String elemento="";
		String itemElemento="";
		for (org.jsoup.nodes.Element link : links) {

			elemento = link.toString();

			if(elemento.contains("CAD ICMS")){
				itemElemento = link.nextElementSibling().text();
				itemElemento = Diversos.removerLetrasCaracteres(itemElemento);
				retornoFormatado.put("ie", itemElemento);
			}

			else if(elemento.contains("Inscrição Única")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("inscricaoUnica", itemElemento);
			}

			else if(elemento.contains("CNPJ")){
				itemElemento = link.nextElementSibling().text();
				itemElemento = Diversos.removerLetrasCaracteres(itemElemento);
				retornoFormatado.put("cnpj", itemElemento);
			}

			else if(elemento.contains("Razão Social")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("razaoSocial", itemElemento);
			}

			else if(elemento.contains("Nome Fantasia")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("nomeFantasia", itemElemento);
			}

			else if(elemento.contains("Logradouro")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("logradouro", itemElemento);
			}

			else if(elemento.contains("Complemento")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("complemento", itemElemento);
			}

			else if(elemento.contains("Número")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("numero", itemElemento);
			}

			else if(elemento.contains("Bairro")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("bairro", itemElemento);
			}

			else if(elemento.contains("Município")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("municipio", itemElemento);
			}

			else if(elemento.contains("UF")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("uf", itemElemento);
			}

			else if(elemento.contains("CEP")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("cep", itemElemento);
			}

			else if(elemento.contains("Telefone")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("telefone", itemElemento);
			}

			else if(elemento.contains("Enquadramento Empresa")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("enquadramentoEmpresa", itemElemento);
			}

			else if(elemento.contains("Delegacia Fazendária")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("delegaciaFazenda", itemElemento);
			}

			else if(elemento.contains("Natureza Jurídica")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("naturezaJuridica", itemElemento);
			}

			else if(elemento.contains("CNAE Fiscal")){
				itemElemento = link.nextElementSibling().text();
				atividades.add(itemElemento);
				retornoFormatado.put("atividades", atividades);
			}

			else if(elemento.contains("Data Abertura")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("dataAbertura", itemElemento);
			}

			else if(elemento.contains("Motivo Inclusão")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("motivoInclusao", itemElemento);
			}

			else if(elemento.contains("Data Baixa")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("dataBaixa", itemElemento);
			}

			else if(elemento.contains("Motivo Baixa")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("motivoBaixa", itemElemento);
			}

			else if(elemento.contains("Situação Cadastral Vigente")){
				itemElemento = link.nextElementSibling().text();
				if(!retornoFormatado.containsKey("situacaoCadastral")){
					retornoFormatado.put("situacaoCadastral", itemElemento);
				}
			}

			else if(elemento.contains("Data desta Situação")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("dataSituacao", itemElemento);
			}

			else if(elemento.contains("Nota Fiscal Eletrônica")){
				itemElemento = link.nextElementSibling().text();
				retornoFormatado.put("notaFiscalEletronica", itemElemento);
			}

		}


		// IDENTIFICAÇÃO
		resultadoConsulta.setInscricaoEstadual(retornoFormatado.get("ie").toString());
		resultadoConsulta.setInscricaoUnica(retornoFormatado.get("inscricaoUnica").toString());
		resultadoConsulta.setCnpj(retornoFormatado.get("cnpj").toString());
		resultadoConsulta.setRazaoSocial(retornoFormatado.get("razaoSocial").toString());
		resultadoConsulta.setNomeFantasia(retornoFormatado.get("nomeFantasia").toString());


		// ENDEREÇO
		resultadoConsulta.setLogradouro(retornoFormatado.get("logradouro").toString());
		resultadoConsulta.setNumero(retornoFormatado.get("numero").toString());
		resultadoConsulta.setComplemento(retornoFormatado.get("complemento").toString());
		resultadoConsulta.setBairro(retornoFormatado.get("bairro").toString());
		resultadoConsulta.setMunicipio(retornoFormatado.get("municipio").toString());
		resultadoConsulta.setUf(retornoFormatado.get("uf").toString());
		resultadoConsulta.setCep(retornoFormatado.get("cep").toString());
		resultadoConsulta.setTelefone(retornoFormatado.get("telefone").toString());


		// INFORMAÇÕES COMPLEMENTARES
		resultadoConsulta.setRegimeApuracaoIcms(retornoFormatado.get("enquadramentoEmpresa").toString());
		resultadoConsulta.setDelegaciaFazendaria(retornoFormatado.get("delegaciaFazenda").toString());
		resultadoConsulta.setEnquadramentoFiscal(retornoFormatado.get("naturezaJuridica").toString());
		resultadoConsulta.setDataAbertura(retornoFormatado.get("dataAbertura").toString());
		resultadoConsulta.setMotivoInclusao(retornoFormatado.get("motivoInclusao").toString());
		resultadoConsulta.setDataBaixa(retornoFormatado.get("dataBaixa").toString());
		resultadoConsulta.setMotivoBaixa(retornoFormatado.get("motivoBaixa").toString());
		resultadoConsulta.setSituacaoCadastral(retornoFormatado.get("situacaoCadastral").toString());
		resultadoConsulta.setDataSituacaoCadastral(retornoFormatado.get("dataSituacao").toString());

		if(retornoFormatado.containsKey("notaFiscalEletronica")){
			resultadoConsulta.setDocumentosEletronicos(retornoFormatado.get("notaFiscalEletronica").toString());
		}

		List<String> atv = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) retornoFormatado.get("atividades");
		atv = list;

		resultadoConsulta.setAtividadePrincipal(atv.get(0));

		if(atv.size() > 0){
			resultadoConsulta.setAtividadeSecundaria("");
			for(int i=1; i<atv.size(); i++){
				resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+atv.get(i)+"\n");
			}

		}

	}


	private static void retornoPR(List<String> lista) {

		resultadoConsulta = new SintegraPar();

		// IDENTIFICAÇÃO
		String doc= Diversos.removerHtmlReceita(lista.get(40));
		doc = Diversos.removerLetrasCaracteres(doc);
		resultadoConsulta.setCnpj(doc);


		String ie= Diversos.removerHtmlReceita(lista.get(42));
		ie = Diversos.removerLetrasCaracteres(ie);
		resultadoConsulta.setInscricaoEstadual(ie);

		resultadoConsulta.setRazaoSocial(Diversos.removerHtmlReceita(lista.get(46)));

		resultadoConsulta.setLogradouro(Diversos.removerHtmlReceita(lista.get(58)));

		resultadoConsulta.setNumero(Diversos.removerHtmlReceita(lista.get(62)));

		resultadoConsulta.setComplemento(Diversos.removerHtmlReceita(lista.get(64)));

		resultadoConsulta.setBairro(Diversos.removerHtmlReceita(lista.get(68)));

		resultadoConsulta.setMunicipio(Diversos.removerHtmlReceita(lista.get(72)));

		resultadoConsulta.setUf(Diversos.removerHtmlReceita(lista.get(74)));

		resultadoConsulta.setCep(Diversos.removerHtmlReceita(lista.get(78)));

		resultadoConsulta.setTelefone(Diversos.removerHtmlReceita(lista.get(80)));

		resultadoConsulta.setEmail(Diversos.removerHtmlReceita(lista.get(84)));

		resultadoConsulta.setAtividadePrincipal(Diversos.removerHtmlReceita(lista.get(96)));

		resultadoConsulta.setAtividadeSecundaria(Diversos.removerHtmlReceita(lista.get(101)));

		resultadoConsulta.setDataAbertura(Diversos.removerHtmlReceita(lista.get(106)));

		String linha = Diversos.removerHtmlReceita(lista.get(112));
		String situacao = linha.substring(0, linha.indexOf("DESDE"));
		situacao = situacao.replaceAll("-","").trim();
		resultadoConsulta.setSituacaoCadastral(situacao);

		String dataSituacao = linha.substring(linha.indexOf("DESDE"));
		dataSituacao = dataSituacao.replaceAll("DESDE", "").trim();
		resultadoConsulta.setDataSituacaoCadastral(dataSituacao);


	}



	private static void retornoSP(List<String> lista) {

		resultadoConsulta = new SintegraPar();

		// IDENTIFICACAO
		String linha211 = lista.get(211);
		resultadoConsulta.setCnpj(Diversos.removerLetrasCaracteres(linha211.substring(162, 180)));
		resultadoConsulta.setInscricaoEstadual(Diversos.removerLetrasCaracteres(linha211.substring(370, 385)));

		String linha218 = lista.get(218);
		String linha218a = linha218.substring(linha218.indexOf("Social:</B></FONT></TD><TD BGCOLOR="));
		int tamLinha218a = linha218a.length();
		String linha218b = linha218a.substring(80, tamLinha218a-12);
		resultadoConsulta.setRazaoSocial(linha218b);


		//ENDEREÇO
		String linha235 = lista.get(235);
		String linha235a = linha235.substring(linha235.indexOf("Logradouro:</B></FONT></TD><TD BGCOLOR="));
		int tamLinha235a = linha235a.length();
		String linha235b = linha235a.substring(84, tamLinha235a-12);
		resultadoConsulta.setLogradouro(linha235b);

		String linha242 = lista.get(242);
		String linha242a = linha242.substring(linha242.indexOf("mero:</B></FONT></TD><TD BGCOLOR="));
		String linha242b = linha242a.substring(0, linha242a.indexOf("#F1F1B1"));
		int tamLinha242a = linha242b.length();
		String linha242c = linha242a.substring(90, tamLinha242a-25);
		resultadoConsulta.setNumero(linha242c);

		String linha242d = 	linha242.substring(linha242.indexOf("Complemento:</B></FONT></TD><TD BGCOLOR="));
		int tamLinha242b = linha242d.length();
		String linha242e = linha242d.substring(85, tamLinha242b-12);
		resultadoConsulta.setComplemento(linha242e);

		String linha249 = lista.get(249);
		String linha249a = linha249.substring(linha249.indexOf("Bairro:</B></FONT></TD><TD BGCOLOR="));
		int tamLinha249a = linha249a.length();
		String linha249b = linha249a.substring(80, tamLinha249a-12);
		resultadoConsulta.setBairro(linha249b);

		String linha256 = lista.get(256);
		String linha256a = linha256.substring(linha256.indexOf("pio:</B></FONT></TD><TD BGCOLOR="));
		String linha256b = linha256a.substring(0, linha256a.indexOf("#F1F1B1"));
		int tamLinha256a = linha256b.length();
		String linha256c = linha256a.substring(89, tamLinha256a-25);
		resultadoConsulta.setMunicipio(linha256c);

		String linha256d = 	linha256.substring(linha256.indexOf("UF:</B></FONT></TD><TD BGCOLOR="));
		int tamLinha256b = linha256d.length();
		String linha256e = linha256d.substring(76, tamLinha256b-12);
		resultadoConsulta.setUf(linha256e);

		String linha263 = lista.get(263);
		String linha263a = linha263.substring(linha263.indexOf("CEP:</B></FONT></TD><TD BGCOLOR="));
		int tamLinha263a = linha263a.length();
		String linha263b = linha263a.substring(77, tamLinha263a-12);
		resultadoConsulta.setCep(linha263b);


		//INFORMACOES COMPLEMENTARES
		String linha280 = lista.get(280);
		String linha280a = linha280.substring(linha280.indexOf("mica:</B></FONT></TD><TD BGCOLOR="));
		int tamLinha280a = linha280a.length();
		String linha280b = linha280a.substring(78, tamLinha280a-12);
		resultadoConsulta.setAtividadePrincipal(linha280b);

		String linha287 = lista.get(287);
		String linha287a = linha287.substring(linha287.indexOf("Cadastral Vigente:</B></FONT></TD><TD BGCOLOR="));
		String linha287b = linha287a.substring(linha287a.indexOf("</FONT></TD><TD><FONT FACE="));
		int tamLinha287b = linha287b.length();
		String linha287c = linha287b.substring(51, tamLinha287b-12);
		resultadoConsulta.setSituacaoCadastral(linha287c.toUpperCase());

		String linha294 = lista.get(294);
		resultadoConsulta.setDataSituacaoCadastral(linha294.substring(189, 199));

		String linha301 = lista.get(301);
		String linha301a = linha301.substring(linha301.indexOf("o:</B></FONT></TD><TD BGCOLOR="));
		int tamLinha301a = linha301a.length();
		String linha301b = linha301a.substring(75, tamLinha301a-12);
		resultadoConsulta.setRegimeApuracaoIcms(linha301b);

		String linha308 = lista.get(308);
		if(linha308.length()>200){
			linha308 = "Data de Credenciamento como emissor de NF-e: "+linha308.substring(189,199)+"\n";
			resultadoConsulta.setDocumentosEletronicos(linha308);
		}

		String linha315 = lista.get(315);
		if(linha315.length()>200){
			String linha315a = linha315.substring(linha315.indexOf("NF-e:</B></FONT></TD><TD BGCOLOR="));
			int tamLinha315a = linha315a.length();
			String linha315b = linha315a.substring(78, tamLinha315a-12);
			linha315b = "Indicador de Obrigatoriedade de NF-e: "+linha315b+"\n";
			resultadoConsulta.setDocumentosEletronicos( resultadoConsulta.getDocumentosEletronicos()+linha315b );
		}

		String linha322 = lista.get(322);
		if(linha322.length()>200){
			linha322 = "Data de Início da Obrigatoriedade de NF-e: "+linha322.substring(194,204)+"\n";
			resultadoConsulta.setDocumentosEletronicos( resultadoConsulta.getDocumentosEletronicos()+linha322 );
		}
	}

	private static void retornoSC(List<String> brk){

		String doc = Diversos.removerHtmlReceita(brk.get(89));
		doc = Diversos.removerLetrasCaracteres(doc);
		resultadoConsulta.setCnpj(doc);

		resultadoConsulta.setInscricaoEstadual(Diversos.removerHtmlReceita(brk.get(99)));

		String razao = Diversos.removerHtmlReceita(brk.get(111));
		razao = Diversos.removerAcentos(razao, true, false);
		resultadoConsulta.setRazaoSocial(razao);

		String tipo = Diversos.removerHtmlReceita(brk.get(130));
		String logradouro = Diversos.removerHtmlReceita(brk.get(131));
		logradouro = Diversos.removerAcentos(logradouro, true, false);
		resultadoConsulta.setLogradouro(tipo+""+logradouro);

		resultadoConsulta.setNumero(Diversos.removerHtmlReceita(brk.get(142)));

		String comp = Diversos.removerHtmlReceita(brk.get(149));
		comp = Diversos.removerAcentos(comp, true, false);
		resultadoConsulta.setComplemento(comp);

		String bairro = Diversos.removerHtmlReceita(brk.get(156));
		bairro = Diversos.removerAcentos(bairro, true, false);
		resultadoConsulta.setBairro(bairro);

		resultadoConsulta.setUf(Diversos.removerHtmlReceita(brk.get(167)));

		String mun = Diversos.removerHtmlReceita(brk.get(174));
		mun = Diversos.removerAcentos(mun, true, false);
		resultadoConsulta.setMunicipio(mun);

		resultadoConsulta.setCep(Diversos.removerHtmlReceita(brk.get(181)));

		resultadoConsulta.setEmail(Diversos.removerHtmlReceita(brk.get(192)));

		resultadoConsulta.setTelefone(Diversos.removerHtmlReceita(brk.get(199)));

		resultadoConsulta.setDataAbertura(Diversos.removerHtmlReceita(brk.get(219)));

		resultadoConsulta.setSituacaoCadastral(Diversos.removerHtmlReceita(brk.get(230)));

		resultadoConsulta.setDataSituacaoCadastral(Diversos.removerHtmlReceita(brk.get(237)));

		resultadoConsulta.setObservacao("");

		resultadoConsulta.setRegimeApuracaoIcms(Diversos.removerHtmlReceita(brk.get(258)));

		resultadoConsulta.setEnquadramentoFiscal(Diversos.removerHtmlReceita(brk.get(266)));

		String atvPri = Diversos.removerHtmlReceita(brk.get(277));
		atvPri = Diversos.removerAcentos(atvPri, true, false);
		resultadoConsulta.setAtividadePrincipal(atvPri);

		// Mapeado ate 2 Documentos Eletronicos, tem influencia direta no Indice de Atividades Secundárias
		String permiteDoc = Diversos.removerHtmlReceita(brk.get(297));
		permiteDoc = Diversos.removerAcentos(permiteDoc, false, false);
		permiteDoc = permiteDoc+"\n";
		permiteDoc = permiteDoc + Diversos.removerHtmlReceita(brk.get(306));
		permiteDoc = Diversos.removerAcentos(permiteDoc, false, false);
		resultadoConsulta.setDocumentosEletronicos(permiteDoc);

		String atvSecCod = Diversos.removerHtmlReceita(brk.get(318));
		String atvSec = Diversos.removerHtmlReceita(brk.get(320));
		atvSec = Diversos.removerAcentos(atvSec, true, false);
		atvSec = atvSecCod+" - "+atvSec;
		atvSec = atvSec+"\n";
		atvSec = atvSec + Diversos.removerHtmlReceita(brk.get(327));
		atvSec = Diversos.removerAcentos(atvSec, true, false);
		resultadoConsulta.setAtividadeSecundaria(atvSec);

	}

	private static HTMLDocument getHTMLDocument(String html) {

		Reader stringReader = new StringReader(html);
		HTMLEditorKit htmlKit = new HTMLEditorKit();
		HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
		try {
			htmlKit.read(stringReader, htmlDoc, 0);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		return htmlDoc;
	}

	private static void montaErro(List<String> html, String estado){
		String erro="";

		if(html.size() >115 && html.size() <120 && estado.equals("SC")){
			// Erro de Capctcha
			erro = Diversos.removerHtmlReceita(html.get(47));
		}else if(html.size() >74 && html.size() <78 && estado.equals("SC") ){
			// Erro de CNPJ invalido
			erro = Diversos.removerHtmlReceita(html.get(51));
		}else if(html.size() >215 && html.size() <225 && estado.equals("SP") ){
			String campo = html.get(193);
			String campoAux1 = campo.substring(77);
			String campoAux2 = campoAux1.substring(0, campoAux1.indexOf("</B></FONT></TD>"));
			erro = campoAux2;
			resultadoConsulta.setMensagemErroConsulta(erro);
			resultadoConsulta.setCaptcha("");
		}else if(html.size() >170 && html.size() <180 && estado.equals("SP") ){
			String campo = Diversos.removerHtmlReceita(html.get(152));
			erro = campo;
			resultadoConsulta.setMensagemErroConsulta(erro);
			resultadoConsulta.setCaptcha("");
		}else if(html.size() >45 && html.size() <75 && estado.equals("PR") ){
			String campo =  Diversos.removerHtmlReceita(html.get(36));
			erro = campo;
			resultadoConsulta.setMensagemErroConsulta(erro);
			resultadoConsulta.setCaptcha("");
		}else if(estado.equals("RS") ){

			Document doc = Jsoup.parse(html.get(0));

			List<Node> jsoup = new ArrayList<Node>();
			Elements links = doc.getElementsByTag("script");
			for (org.jsoup.nodes.Element link : links) {
				jsoup.addAll(link.childNodes());
			}

			String linha = jsoup.toString();
			String linhaA = linha.substring(0, linha.indexOf("location.href"));
			String linhaB = linhaA.substring(linhaA.indexOf("('"));
			linhaB = linhaB.replace("'", "");
			linhaB = linhaB.replace("(", "");
			linhaB = linhaB.replace(")", "");
			linhaB = linhaB.replace(";", "");
			linhaB = linhaB.replace(",", "");

			erro = linhaB;
			resultadoConsulta.setMensagemErroConsulta(erro);
			resultadoConsulta.setCaptcha("");
		}else{
			resultadoConsulta = new SintegraPar();
		}

		erro = Diversos.removerAcentos(erro, false, false);

		if(erro.contains("SequAncia de caracteres incorreta.")){
			erro = "Sequência de caracteres incorreta.";
			resultadoConsulta.setMensagemErroConsulta(erro);
			resultadoConsulta.setCaptcha("");
		}else if(erro.contains("O CNPJ Informado") && erro.contains("consta na base de dados")){
			erro= "O CNPJ Informado não consta na base de dados.";
			resultadoConsulta.setMensagemErroConsulta(erro);
			resultadoConsulta.setCaptcha("");
		}else if(erro.contains("foi encontrado contribuinte cadastrado") && erro.contains("no Estado de") && erro.contains("Paulo com o CNPJ informado.")){
			erro= "Não foi encontrado contribuinte cadastrado no Estado de São Paulo com o CNPJ informado.";
			resultadoConsulta.setMensagemErroConsulta(erro);
			resultadoConsulta.setCaptcha("");
		}


	}

	public static void main(String[] args) {
		/*
		solicitacao("RS");
		gerarCaptcha();
		rsSolicitacaoSintegra2("92646231000195"); // apenas para o RS
		String captcha = JOptionPane.showInputDialog("Entre com o captcha:");
		// 06079307000106  SC
		// 51669307000110    43248764000103   10738871000160 SP
		// 02970678000122   PR
		// 92646231000195    01443622000157    RS
		valida("92646231000195", captcha, "RS");
		 */
	}
}




