package br.ufsc.creche.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufsc.creche.util.Diversos;


@FacesConverter("somenteNumeroConverter")
public class NumberConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null){
			value = Diversos.removerLetras( ((String)value));
		}
		return ((String) value)  ;
	}

}
