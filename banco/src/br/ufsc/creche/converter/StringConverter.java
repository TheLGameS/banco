package br.ufsc.creche.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("toUpperCaseConverter")
public class StringConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return (String) value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return (value != null) ? ((String) value).toUpperCase() : null;
	}

}
