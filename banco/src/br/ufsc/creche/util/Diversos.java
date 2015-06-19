package br.ufsc.creche.util;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import br.gov.sintegra.ie.InscricaoEstadual;
import br.gov.sintegra.ie.InscricaoEstadualFactory;

public class Diversos {


	/**
	 * @param
	 * @return Retorna o primeiro dia do Mes da data informada no Parametro
	 */

	public static  Date PrimeiroDiaMes(Date data){
		LocalDate local =  toLocalDate(data);
		local = local.with(TemporalAdjusters.firstDayOfMonth());
		return toDate(local);
	}

	/**
	 * @param
	 * @return Retorna o ultimo dia do Mes da data informada no Parametro
	 */
	public static  Date UltimoDiaMes(Date data){
		LocalDate local =  toLocalDate(data);
		local = local.with(TemporalAdjusters.lastDayOfMonth());

		return toDate(local);
	}

	/**
	 * @param
	 * @return Retorna o primeiro dia do Ano da data informada no Parametro
	 */
	public static  Date PrimeiroDiaAno(Date data){
		LocalDate local =  toLocalDate(data);
		LocalDate aux  = LocalDate.of(local.getYear(), 1, 1);
		return toDate(aux);
	}

	/**
	 * @param
	 * @return Retorna o ultimo dia do Ano da data informada no Parametro
	 */
	public static  Date UltimoDiaAno(Date data){
		LocalDate local =  toLocalDate(data);
		LocalDate aux  = LocalDate.of(local.getYear(), 12, 31);
		return toDate(aux);
	}

	/**
	 * Converte LocalDate para Date
	 *
	 * @param datePicker
	 * @return date
	 */
	public static Date toDate(LocalDate datePicker) {
		Instant instant = datePicker.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		return date;
	}

	/**
	 * Converte Date para LocalDate
	 *
	 * @param d
	 * @return LocalDate
	 */
	public static LocalDate toLocalDate(Date d) {
		Instant instant = Instant.ofEpochMilli(d.getTime());
		LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		return localDate;
	}


	/**
	 *
	 * @param String palavra
	 * @param Boolean maiuscula
	 * @param Boolean espacoPorUnderline
	 * @return Retorna uma string sem acentos, podendo passar por parametro se o retorno será em Maiuscula e/ou transformar " " em "_"
	 */
	public static String removerAcentos(String str, boolean maiuscula, boolean espacoPorUnderline) {
		String aux="";
		if(maiuscula && !espacoPorUnderline){
			return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();
		}else if(maiuscula && espacoPorUnderline){
			aux = Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toUpperCase();

			aux = aux.replaceAll(" ", "_");

			return aux;
		}else if(espacoPorUnderline && !maiuscula){
			return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll(" ", "_");
		}else{
			return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
		}
	}


	/**
	 *
	 * @param String valor
	 * @return Retorna somente os números da string passada no parâmetro.
	 */
	public static String removerLetras(String value) {
		String inputValue=value;
		String ret=value;
		if (inputValue != null) {
			ret=Normalizer.normalize(inputValue,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]","");
			ret=ret.replaceAll("[^0-9./-]","");

		}
		return ret;
	}

	/**
	 * @param String value
	 * @return Retorna uma String sem sintaxe de HTML
	 */
	public static String removerHtmlReceita(String value){
		String aux=value;
		char aspasDuplas = '\"';
		String aspa = String.valueOf(aspasDuplas);

		aux = aux.replaceAll("<b>", "");
		aux = aux.replaceAll("</b>", "");
		aux = aux.replaceAll("\t\t", "");
		aux = aux.replaceAll("\t", "");
		aux = aux.replaceAll("<br>", "");
		aux = aux.replaceAll("</font></font></font>", "");
		aux = aux.replaceAll("</font></font>", "");
		aux = aux.replaceAll("</font>", "");
		aux = aux.replaceAll("</td>", "");
		aux = aux.replaceAll("</table>", "");
		aux = aux.replaceAll("<tr>", "");
		aux = aux.replaceAll("<td class=", "");

		// Análise de retorno utilizando conversão de 'aspa' foi implementado somente para PR e RS
		aux = aux.replaceAll(aspa, "");

		// Implementado para PR
		aux = aux.replaceAll("form_conteudo colspan=3>", "");
		aux = aux.replaceAll("form_conteudo colspan=5>", "");
		aux = aux.replaceAll("form_conteudo>", "");
		aux = aux.replaceAll("erro_msg_custom>", "");
		aux = aux.replaceAll("<font face=Verdana, Arial, Helvetica color=red>", "");
		aux = aux.replaceAll("<br />", "");

		// Implementado para RS
		aux = aux.replaceAll("&nbsp;", " ");

		// Implementado para Consulta na Receita Federal
		aux = aux.replaceAll(", , , , <font face=Arial style=font-size: 8pt>,", "");
		aux = aux.replaceAll(", , , , , ", "");

		aux = aux.trim();
		return aux;
	}

	/**
	 * @param String value
	 * @return Retorna uma String numérica sem caracteres especiais e sem Letras
	 */
	public static String removerLetrasCaracteres(String value) {
		String inputValue=value.trim();
		String ret=value;
		if (inputValue != null) {
			ret=Normalizer.normalize(inputValue,Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]","");
			ret=ret.replaceAll("[^0-9]","");
		}
		return ret;
	}

	/**
	 * @return Retorna a data de Hoje
	 */
	public static  Date DataHoje(){
		LocalDate local = LocalDate.now();
		return toDate(local);
	}

	/**
	 * @return Retorna um BigDecimal de 100. Ex: (100,00)
	 */
	public static BigDecimal BigCem(){
		return  new BigDecimal(100);
	}

	/**
	 * @param String Documento
	 * @return Retorna um Documento mascarado podendo ser CNPJ ou CPF
	 */
	public static String mascaraDocumento(String value) {
		String doc = Diversos.removerLetrasCaracteres(value);

		StringBuilder ret = new StringBuilder();
		String ponto= ".";
		String traco= "-";
		String barra= "/";

		if(doc.trim().length()==14){
			ret.append(doc.substring(0,2));
			ret.append(ponto);
			ret.append(doc.substring(2,5));
			ret.append(ponto);
			ret.append(doc.substring(5,8));
			ret.append(barra);
			ret.append(doc.substring(8,12));
			ret.append(traco);
			ret.append(doc.substring(12,14));
		}else if(doc.trim().length()==11){
			ret.append(doc.substring(0,3));
			ret.append(ponto);
			ret.append(doc.substring(3,6));
			ret.append(ponto);
			ret.append(doc.substring(6,9));
			ret.append(traco);
			ret.append(doc.substring(9,11));
		}else{
			ret.append(doc);
		}

		return ret.toString();
	}

	/**
	 * Prepara uma String de DDD + Telefone para ser convertida pela classe TelefoneConverter
	 * @param
	 * @param
	 * @return String formatada em (xx) xxxx-xxxx
	 */
	public static String mascaraTelefone(String ddd, String tel){

		String auxDdd = ddd;
		String auxTel = tel;

		if(auxDdd==null ){
			auxDdd="";
		}
		if(auxDdd.trim().length()<2){
			int l = auxDdd.trim().length();
			for(int m=l ;m<2; m++){
				auxDdd="x"+auxDdd;
			}
		}

		if(auxTel==null){
			auxTel="";
		}
		if(auxTel.trim().length()<8){
			int i = auxTel.trim().length();
			for(int j=i ;j<8; j++){
				auxTel="x"+auxTel;
			}
		}

		if(auxDdd.trim().length()==0 && auxTel.trim().length()==0){
			return null;
		}else if(auxTel.contains("xx")){
			return null;
		}else if(auxTel.trim().length()==0 && auxDdd.trim().length()<=2  ){
			return null;
		}else{
			return auxDdd+auxTel;
		}

	}

	/**
	 * @param Recebe uma string Cnpj
	 * @return Boolean se o CNPJ é valido ou não
	 */
	public static boolean validaCnpj( String str_cnpj ){
		int soma = 0, aux, dig;
		String cnpj_calc = str_cnpj.substring(0,12);

		if ( str_cnpj.length() != 14 ) {
			return false;
		}

		char[] chr_cnpj = str_cnpj.toCharArray();

		/* Primeira parte */
		for( int i = 0; i < 4; i++ ) {
			if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 ) {
				soma += (chr_cnpj[i] - 48 ) * (6 - (i + 1)) ;
			}
		}
		for( int i = 0; i < 8; i++ ) {
			if ( chr_cnpj[i+4]-48 >=0 && chr_cnpj[i+4]-48 <=9 ) {
				soma += (chr_cnpj[i+4] - 48 ) * (10 - (i + 1)) ;
			}
		}
		dig = 11 - (soma % 11);

		cnpj_calc += ( dig == 10 || dig == 11 ) ?
				"0" : Integer.toString(dig);

		/* Segunda parte */
		soma = 0;
		for ( int i = 0; i < 5; i++ ) {
			if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 ) {
				soma += (chr_cnpj[i] - 48 ) * (7 - (i + 1)) ;
			}
		}
		for ( int i = 0; i < 8; i++ ) {
			if ( chr_cnpj[i+5]-48 >=0 && chr_cnpj[i+5]-48 <=9 ) {
				soma += (chr_cnpj[i+5] - 48 ) * (10 - (i + 1)) ;
			}
		}
		dig = 11 - (soma % 11);
		cnpj_calc += ( dig == 10 || dig == 11 ) ?
				"0" : Integer.toString(dig);

		return str_cnpj.equals(cnpj_calc);
	}

	/**
	 * @param Recebe uma string Cpf
	 * @return Boolean se o CPF é valido ou não
	 */
	public static boolean validaCpf(String cpf) {
		boolean ret = false;
		String base = "000000000";
		String digitos = "00";

		if(cpf.length()<11) {
			return false;
		}

		if( cpf.equals("00000000000") ||
				cpf.equals("11111111111") ||
				cpf.equals("22222222222") ||
				cpf.equals("33333333333") ||
				cpf.equals("44444444444") ||
				cpf.equals("55555555555") ||
				cpf.equals("66666666666") ||
				cpf.equals("77777777777") ||
				cpf.equals("88888888888") ||
				cpf.equals("99999999999") ) {
			return false;
		}


		if (cpf.length() <= 11) {
			if (cpf.length() < 11) {
				cpf = base.substring(0, 11 - cpf.length()) + cpf;
				base = cpf.substring(0, 9);
			}
			base = cpf.substring(0, 9);
			digitos = cpf.substring(9, 11);
			int soma = 0, mult = 11;
			int[] var = new int[11];
			// Recebe os números e realiza a multiplicação e soma.
			for (int i = 0; i < 9; i++) {
				var[i] = Integer.parseInt("" + cpf.charAt(i));
				if (i < 9) {
					soma += (var[i] * --mult);
				}
			}
			// Cria o primeiro dígito verificador.
			int resto = soma % 11;
			if (resto < 2) {
				var[9] = 0;
			} else {
				var[9] = 11 - resto;
			}
			// Reinicia os valores.
			soma = 0;
			mult = 11;
			// Realiza a multiplicação e soma do segundo dígito.
			for (int i = 0; i < 10; i++) {
				soma += var[i] * mult--;
			}
			// Cria o segundo dígito verificador.
			resto = soma % 11;
			if (resto < 2) {
				var[10] = 0;
			} else {
				var[10] = 11 - resto;
			}
			if ((digitos.substring(0, 1).equalsIgnoreCase(new Integer(var[9])
			.toString()))
			&& (digitos.substring(1, 2).equalsIgnoreCase(new Integer(
					var[10]).toString()))) {
				ret = true;
			}
		}

		return ret;
	}



	/**
	 * @param String com sigla do estado
	 * @param String com o numero da IE
	 * @return Boolean inscrição estadual valida.
	 */
	public static boolean validaIE(String estado, String ie){
		boolean result=false;

		InscricaoEstadual ins = InscricaoEstadualFactory.getInstance(estado);
		result = ins.validar(ie);

		return result;
	}
	
	/**
	 * 
	 * @param Date por parametro
	 * @return String com formato para consulta SQL
	 */
	public static String dataSql(Date d){
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		String aux = dt.format(d);
		return aux;
	}

}
