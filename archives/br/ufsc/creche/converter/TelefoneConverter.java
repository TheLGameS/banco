package br.ufsc.creche.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		value = value.replace("-", "");
		value = value.replace(" ", "");
		value = value.replace("(", "");
		value = value.replace(")", "");
		return  value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String tel="";
		if(value != null){
			tel = value.toString();
			tel = "("+tel.substring(0,2)+") "+tel.substring(2,6)+"-"+tel.substring(6,10);
		}
		return tel;
	}
}