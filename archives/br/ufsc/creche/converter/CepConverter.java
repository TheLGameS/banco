package br.ufsc.creche.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("cepConverter")
public class CepConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		value = value.replace("-", "");
		return  value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String cep="";
		if(value != null){
			cep = value.toString();
			if(cep.trim().length()==8) {
				cep = cep.substring(0,5)+"-"+cep.substring(5,8);
			}
		}
		return cep;
	}
}