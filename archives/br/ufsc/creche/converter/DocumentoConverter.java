package br.ufsc.creche.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufsc.creche.util.Diversos;


@FacesConverter("documentoConverter")
public class DocumentoConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		value = value.replace("-", "");
		value = value.replace("/", "");
		value = value.replace(".", "");
		return  value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String doc="";
		if(value != null){
			doc = value.toString();
			doc = Diversos.mascaraDocumento(doc);
		}
		return doc;
	}
}