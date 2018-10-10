package com.sgi.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import com.sgi.utils.CPFUtils;

@FacesConverter(value = "CPFConverter")
public class CPFConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent cpn, String str) {
		if(str != null && !str.equals("")){
		 if (!StringUtils.isBlank(str)) {
			return CPFUtils.clean(str);
		 }
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent cpn, Object obj) {
		if(obj != null){
		 if (!((String) obj).equals("")) {
			return CPFUtils.format(obj.toString());
		 }else{
			 return null;
		 }
		}
		return null;
	}

}
