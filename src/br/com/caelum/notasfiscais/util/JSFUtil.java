package br.com.caelum.notasfiscais.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;

public class JSFUtil {
	
	@Produces
	@RequestScoped
	public FacesContext getFC(){
		return FacesContext.getCurrentInstance();
	}
	
	@Produces
	@RequestScoped
	public NavigationHandler getNH(){
		return getFC().getApplication().getNavigationHandler();
	}

}
