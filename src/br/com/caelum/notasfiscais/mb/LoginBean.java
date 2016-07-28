package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.dao.UsuarioDao;
import br.com.caelum.notasfiscais.modelo.Usuario;

@Model
public class LoginBean implements Serializable {
	@Inject
	private UsuarioDao dao;
	@Inject
	private UsuarioLogadoBean usuarioLogado;
	
	@Inject
	private Event <Usuario> eventoLogin;
	
	private Usuario usuario = new Usuario();
	
	public String efetuaLogin(){
		boolean loginValido = dao.existe(this.usuario);
		if(loginValido){
			usuarioLogado.logar(usuario);
			eventoLogin.fire(usuario);
			return "produto?faces-redirect=true";
		}else{
			usuarioLogado.deslogar();
			this.usuario=new Usuario();
			return "login";
		}
	}
	
	public Usuario getUsuario(){
		return this.usuario;
	}
	
	public String logOut(){
		usuarioLogado.deslogar();
		return "login?faces-redirect=true";
	}
	
}
