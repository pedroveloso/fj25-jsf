package br.com.caelum.notasfiscais.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
@Stereotype
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ViewModel {

}
