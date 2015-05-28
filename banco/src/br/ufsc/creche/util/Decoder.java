package br.ufsc.creche.util;

import java.nio.charset.Charset;

public  class Decoder {

	public static String DecodePWDEx(String prsGlic){

		String securityString = "HD2904Z8XSMART35";
		String str = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz+/";

		String str5 = "";
		if (securityString.length() < 0x10){
			return str5;
		}
		for (int i = 0; i < securityString.length(); i++){
			if (securityString.substring(i + 1).indexOf(securityString.charAt(i)) > 0){
				return str5;
			}
			if (str.indexOf(securityString.charAt(i)) < 0){
				return str5;
			}
		}
		String str3 = "";
		String str4 = securityString;
		for (int j = 0; j < prsGlic.length(); j++){
			if (str4.indexOf(prsGlic.charAt(j)) >= 0){
				str3 = str3 + prsGlic.charAt(j);
			}
		}
		prsGlic = str3;
		str3 = "";
		if ((prsGlic.length() % 2) != 0){
			return str5;
		}
		for (int k = 0; k <= ((prsGlic.length() / 2) - 1); k++){
			int index = str4.indexOf(prsGlic.charAt(((k * 2) + 1) - 1));

			if (index < 0){
				return str5;
			}
			str4 = str4.substring(str4.length() - 1) + str4.substring(0, str4.length() - 1);
			int num2 = str4.indexOf(prsGlic.charAt(((k * 2) + 2) - 1 ));
			if (num2 < 0){
				return str5;
			}
			index += num2 * 0x10;
			byte[] bytes = new byte[] { (byte) index };


			if(k==34) {
				str3 = str3+"/20";
			}

			if(k==38) {
				str3 = str3+"20";
			}

			str3 = str3 + new String(bytes, Charset.defaultCharset());

			str4 = str4.substring(str4.length() - 1) + str4.substring(0, str4.length() - 1);
		}

		return str3;
	}
}