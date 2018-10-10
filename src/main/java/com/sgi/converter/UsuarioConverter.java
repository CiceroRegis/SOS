package com.sgi.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.sgi.constantes.SEXO;
import com.sgi.model.Cliente;
import com.sgi.model.Usuario;
import com.sgi.rn.ClienteRN;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoString) {
		
		if(codigoString != null && codigoString.trim().length() > 0) {
			Integer id = Integer.valueOf(codigoString);
			Usuario usuario = new Usuario();
			return usuario.getSexo();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object clienteObjeto) {
		if(clienteObjeto != null) {
			Cliente cliente =  (Cliente) clienteObjeto;
		return cliente.getId().toString();
	}
		return null;
	}
}
