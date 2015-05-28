package br.ufsc.creche.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("dataConverter")
public class DataConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		value = value.replace("/", "");
		return  value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String data="";
		if(value != null){
			LocalDate d = LocalDate.parse(value.toString());
			DateTimeFormatter formatador = 	DateTimeFormatter.ofPattern("dd/MM/yyyy");
			data = d.format(formatador);
		}
		return data;
	}
}