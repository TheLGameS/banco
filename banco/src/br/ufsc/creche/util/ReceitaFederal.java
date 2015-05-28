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
import java.util.List;

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
import org.jsoup.select.Elements;

import br.ufsc.creche.parameter.ReceitaFederalPar;
import sun.misc.BASE64Encoder;

public class ReceitaFederal {

	static DefaultHttpClient cliente = null;
	static HttpContext contexto = null;
	static HttpResponse resposta = null;
	static HttpEntity entidade = null;
	static InputStream entrada = null;
	static String linha=null;
	static BufferedReader bufferedReader = null;

	static ReceitaFederalPar resultadoConsulta = new ReceitaFederalPar();

	public static void solicitacao(){

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
		HttpGet requisição1 = new HttpGet("http://www.receita.fazenda.gov.br/pessoajuridica/cnpj/cnpjreva/cnpjreva_solicitacao2.asp");

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

		// Para cada linha
		/*try {
			while ((linha = bufferedReader.readLine()) != null) {
				// Escreva
				System.out.println(linha);
				acumulador += "\n" + linha;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/


		String html = null;
		try {
			html = EntityUtils.toString(entidade);
		} catch (ParseException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// Busco o documento estruturado
		HTMLDocument document = getHTMLDocument(html);

		// Busco todos os elementos em forma de iterador
		ElementIterator elementIterator = new ElementIterator(document);

		// Crio o elemento que vai recepcionar
		Element element;
		//Crio o viewstate para receber um valor para o método post futuro
		String viewstate = "";
		// Crio o imgcaptcha para receber um valor do link do captcha
		String imgcaptcha = "";
		// Enquanto existir próximo elemento
		while ((element = elementIterator.next()) != null) {
			// Se for um input
			if (element.getName().equals(HTML.Tag.INPUT.toString()) && ((String) element.getAttributes().getAttribute(HTML.Attribute.NAME)).equalsIgnoreCase("viewstate")) {
				// Passo para a variável o valor do viewstate
				viewstate = (String) element.getAttributes().getAttribute(HTML.Attribute.VALUE);
			}
			// Se for um img
			if (element.getName().equals(HTML.Tag.IMG.toString()) && ((String) element.getAttributes().getAttribute(HTML.Attribute.ID)).equalsIgnoreCase("imgcaptcha")) {
				// Passo para a variável o valor do imgcaptcha
				imgcaptcha = "http://www.receita.fazenda.gov.br" + ((String) element.getAttributes().getAttribute(HTML.Attribute.SRC)).replaceAll("amp", "");
			}
		}

		// Separador
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println();


		System.out.println(acumulador.replaceAll("amp;", ""));

		// Separador
		System.out.println();
		System.out.println("---------------------------------------------------------");
		System.out.println();

	}



	public static String gerarCaptcha(){

		HttpGet requisição2 = new HttpGet("http://www.receita.fazenda.gov.br/pessoajuridica/cnpj/cnpjreva/captcha/gerarCaptcha.asp");


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
		}
		 */

		String captchaBase64 = new BASE64Encoder().encode(response);

		return captchaBase64;

	}


	public static ReceitaFederalPar valida(String cnpj, String captcha){

		// String captcha = JOptionPane.showInputDialog("Entre com o captcha:");

		// Criando o método de acesso
		HttpPost requisição3 = new HttpPost("http://www.receita.fazenda.gov.br/pessoajuridica/cnpj/cnpjreva/valida.asp");

		// Lista de parâmetros
		List<NameValuePair> nameValuePairs = new ArrayList<>();

		// Adicionando os parâmetros
		nameValuePairs.add(new BasicNameValuePair("origem", "comprovante"));
		nameValuePairs.add(new BasicNameValuePair("cnpj", cnpj)); // 00000000000191   06079307000106
		nameValuePairs.add(new BasicNameValuePair("txtTexto_captcha_serpro_gov_br", captcha));
		nameValuePairs.add(new BasicNameValuePair("submit1", "Consultar"));
		nameValuePairs.add(new BasicNameValuePair("search_type", "cnpj"));

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
				//System.out.println(linha);  Sysout com o valor de Todo HTML
				brk.add(linha);
			}

			if(brk.size()>1000){
				resultadoConsulta = new ReceitaFederalPar();
				retornoComIndexJsoup(brk);
				resultadoConsulta.setCaptcha("");
			}else if(brk.size()>130 && brk.size()<140){
				//sizee=133 erro cnpj invalido
				String erroCnpj = Diversos.removerHtmlReceita(brk.get(94));
				if(erroCnpj.contains("nmero") && erroCnpj.contains("vlido")){
					erroCnpj="O número do CNPJ é inválido.";
				}else{
					erroCnpj="Erro";
				}
				resultadoConsulta = new ReceitaFederalPar();
				resultadoConsulta.setMensagemErroConsulta(erroCnpj);
			}else if(brk.size()>300 && brk.size()<310){
				//size=301 erro captcha invalido
				String erroCaptcha = Diversos.removerHtmlReceita(brk.get(273));

				if(erroCaptcha.contains("Erro na Consulta")){
					erroCaptcha="Chave inválida.";
				}else{
					erroCaptcha="Erro";
				}
				resultadoConsulta = new ReceitaFederalPar();
				resultadoConsulta.setMensagemErroConsulta(erroCaptcha);
				resultadoConsulta.setCnpj(cnpj);
			}else{
				resultadoConsulta = new ReceitaFederalPar();
				resultadoConsulta.setCnpj(cnpj);
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

	private static void retornoComIndexJsoup(List<String> brk){

		Document html = Jsoup.parse(brk.toString());

		List<String> jsoup = new ArrayList<String>();
		Elements links = html.getElementsByTag("td");
		String elemento="";
		for (org.jsoup.nodes.Element link : links) {
			elemento = link.toString();
			if( (   elemento.contains("NÚMERO DE INSCRIÇÃO") ||
					elemento.contains("DATA DE ABERTURA") ||
					elemento.contains("NOME EMPRESARIAL") ||
					elemento.contains("TÍTULO DO ESTABELECIMENTO (NOME DE FANTASIA)") ||
					elemento.contains("CÓDIGO E DESCRIÇÃO DA ATIVIDADE ECONÔMICA PRINCIPAL") ||
					elemento.contains("CÓDIGO E DESCRIÇÃO DAS ATIVIDADES ECONÔMICAS SECUNDÁRIAS") ||
					elemento.contains("CÓDIGO E DESCRIÇÃO DA NATUREZA JURÍDICA") ||
					elemento.contains("LOGRADOURO") ||
					elemento.contains("NÚMERO") ||
					elemento.contains("COMPLEMENTO") ||
					elemento.contains("CEP") ||
					elemento.contains("BAIRRO/DISTRITO") ||
					elemento.contains("MUNICÍPIO") ||
					elemento.contains("UF") ||
					elemento.contains("ENDEREÇO ELETRÔNICO") ||
					elemento.contains("TELEFONE") ||
					elemento.contains("ENTE FEDERATIVO RESPONSÁVEL (EFR)") ||
					elemento.contains("SITUAÇÃO CADASTRAL") ||
					elemento.contains("DATA DA SITUAÇÃO CADASTRAL") ||
					elemento.contains("MOTIVO DE SITUAÇÃO CADASTRAL") ||
					elemento.contains("SITUAÇÃO ESPECIAL") ||
					elemento.contains("DATA DA SITUAÇÃO ESPECIAL") ) &&
					elemento.length() <2000   ) {

				jsoup.add(elemento);
			}

		}

		jsoup.remove(1); // Remove o indice com o Texto do cabeçalho "Comprovante de Inscricao e de situacao cadastral"

		String linha0 = jsoup.get(0);
		String[] quebra0 = linha0.split(",");
		String cnpj = Diversos.removerHtmlReceita(quebra0[15]);
		cnpj = Diversos.removerLetrasCaracteres(cnpj);
		resultadoConsulta.setCnpj(cnpj);
		String desc = Diversos.removerHtmlReceita(quebra0[17]);
		resultadoConsulta.setDescricao(desc);

		String linha1 = jsoup.get(1);
		String[] quebra1 = linha1.split(",");
		resultadoConsulta.setDataAbertura(Diversos.removerHtmlReceita(quebra1[15]));

		String linha2 = jsoup.get(2);
		String[] quebra2 = linha2.split(",");
		resultadoConsulta.setRazaoSocial(Diversos.removerHtmlReceita(quebra2[15]));

		String linha3 = jsoup.get(3);
		String[] quebra3 = linha3.split(",");
		resultadoConsulta.setNomeFantasia(Diversos.removerHtmlReceita(quebra3[15]));

		String linha4 = jsoup.get(4);
		String[] quebra4 = linha4.split(",");
		resultadoConsulta.setAtividadePrincipal(Diversos.removerHtmlReceita(quebra4[15]));

		String linha5 = jsoup.get(5);
		String[] quebra5 = linha5.split("<b>");

		switch (quebra5.length) {
		case 10:
			resultadoConsulta.setAtividadeSecundaria(Diversos.removerHtmlReceita(quebra5[1]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[2]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[3]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[4]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[5]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[6]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[7]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[8]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[9]));
			break;
		case 9:
			resultadoConsulta.setAtividadeSecundaria(Diversos.removerHtmlReceita(quebra5[1]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[2]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[3]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[4]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[5]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[6]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[7]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[8]));
			break;
		case 8:
			resultadoConsulta.setAtividadeSecundaria(Diversos.removerHtmlReceita(quebra5[1]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[2]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[3]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[4]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[5]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[6]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[7]));
			break;
		case 7:
			resultadoConsulta.setAtividadeSecundaria(Diversos.removerHtmlReceita(quebra5[1]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[2]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[3]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[4]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[5]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[6]));
			break;
		case 6:
			resultadoConsulta.setAtividadeSecundaria(Diversos.removerHtmlReceita(quebra5[1]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[2]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[3]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[4]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[5]));
			break;
		case 5:
			resultadoConsulta.setAtividadeSecundaria(Diversos.removerHtmlReceita(quebra5[1]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[2]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[3]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[4]));
			break;
		case 4:
			resultadoConsulta.setAtividadeSecundaria(Diversos.removerHtmlReceita(quebra5[1]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[2]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[3]));
			break;
		case 3:
			resultadoConsulta.setAtividadeSecundaria(Diversos.removerHtmlReceita(quebra5[1]));
			resultadoConsulta.setAtividadeSecundaria(resultadoConsulta.getAtividadeSecundaria()+"\n"+Diversos.removerHtmlReceita(quebra5[2]));
			break;
		case 2:
			resultadoConsulta.setAtividadeSecundaria(Diversos.removerHtmlReceita(quebra5[1]));
			break;
		case 1:
			resultadoConsulta.setAtividadeSecundaria("");
			break;
		default:
			resultadoConsulta.setAtividadeSecundaria("");
			break;
		}

		String linha6 = jsoup.get(6);
		String[] quebra6 = linha6.split(",");
		resultadoConsulta.setNaturezaJuridica(Diversos.removerHtmlReceita(quebra6[15]));

		String linha7 = jsoup.get(7);
		String[] quebra7 = linha7.split(",");
		resultadoConsulta.setLogradouro(Diversos.removerHtmlReceita(quebra7[15]));

		String linha8 = jsoup.get(8);
		String[] quebra8 = linha8.split(",");
		resultadoConsulta.setNumero(Diversos.removerHtmlReceita(quebra8[15]));

		String linha9 = jsoup.get(9);
		String[] quebra9 = linha9.split(",");
		resultadoConsulta.setComplemento(Diversos.removerHtmlReceita(quebra9[14]));

		String linha10 = jsoup.get(10);
		String[] quebra10 = linha10.split(",");
		resultadoConsulta.setCep(Diversos.removerHtmlReceita(quebra10[14]));

		String linha11 = jsoup.get(11);
		String[] quebra11 = linha11.split(",");
		resultadoConsulta.setBairro(Diversos.removerHtmlReceita(quebra11[14]));

		String linha12 = jsoup.get(12);
		String[] quebra12 = linha12.split(",");
		resultadoConsulta.setMunicipio(Diversos.removerHtmlReceita(quebra12[14]));

		String linha13 = jsoup.get(13);
		String[] quebra13 = linha13.split(",");
		resultadoConsulta.setUf(Diversos.removerHtmlReceita(quebra13[14]));

		String linha14 = jsoup.get(14);
		String[] quebra14 = linha14.split(",");
		resultadoConsulta.setEmail(Diversos.removerHtmlReceita(quebra14[14]));

		String linha15 = jsoup.get(15);
		String[] quebra15 = linha15.split(",");
		resultadoConsulta.setTelefone(Diversos.removerHtmlReceita(quebra15[14]));

		String linha16 = jsoup.get(16);
		String[] quebra16 = linha16.split(",");
		resultadoConsulta.setResponsavel(Diversos.removerHtmlReceita(quebra16[16]));

		String linha17 = jsoup.get(17);
		String[] quebra17 = linha17.split(",");
		resultadoConsulta.setSituacaoCadastral(Diversos.removerHtmlReceita(quebra17[15]));

		String linha18 = jsoup.get(18);
		String[] quebra18 = linha18.split(",");
		resultadoConsulta.setDataSituacaoCadastral(Diversos.removerHtmlReceita(quebra18[14]));

		String linha19 = jsoup.get(19);
		String[] quebra19 = linha19.split(",");
		resultadoConsulta.setMotivoSituacaoCadastral(Diversos.removerHtmlReceita(quebra19[15]));

		String linha20 = jsoup.get(20);
		String[] quebra20 = linha20.split(",");
		resultadoConsulta.setSituacaoEspecial(Diversos.removerHtmlReceita(quebra20[15]));

		String linha21 = jsoup.get(21);
		String[] quebra21 = linha21.split(",");
		resultadoConsulta.setDataSituacaoEspecial(Diversos.removerHtmlReceita(quebra21[14]));

	}

}