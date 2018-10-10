package com.sgi.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author Cícero Régis in 28/09/2018
 *
 */

public class JsfUtils {

	public static boolean isPostback() {
		return getFacesContext().isPostback();
	}

	public static boolean isNotPostback() {
		return !isPostback();
	}

	public static void addInfoMessage(final String clientId, final String msg) {
		getFacesContext().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public static void addErrorMessage(String clientId, String msg) {
		getFacesContext().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
	}

	public static void addWarnMessage(String clientId, String msg) {
		getFacesContext().addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg));
	}

	public static FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	public static ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}

	public static ServletContext getServletContext() {
		return (ServletContext) getExternalContext().getContext();
	}

	public static HttpSession getHttpSession() {
		return (HttpSession) getExternalContext().getSession(false);
	}

	public static HttpServletRequest getHttpServletRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	public static HttpServletResponse getHttpServletResponse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	public static String getRequestParameter(String key) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
	}

}
