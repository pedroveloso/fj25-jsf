package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@Model
public class ProdutoBean implements Serializable {
	private Produto produto = new Produto();
	
	public Produto getProduto(){
		return this.produto;
	}
	
	@Inject
	private ProdutoDao dao;
	
	@Transactional
	public String grava(){
		System.out.println("Será que vai passar por aqui?");
		
		if(produto.getId()==null){
			dao.adiciona(produto);
		}else{
			dao.atualiza(produto);
		}
		this.produto = new Produto();
		this.produtos = dao.listaTodos();
		return "produto?faces-redirect=true";
	}

	private List<Produto> produtos;
	
	public List<Produto> getProdutos(){
		if(produtos==null){
			System.out.println("Carregando produtos...");
			produtos = dao.listaTodos();
		}
	return produtos;
	}
	
	@Transactional
	public void remove(Produto produto){
		dao.remove(produto);
		this.produtos=dao.listaTodos();
	}
	
	public void setProduto(Produto produto){
		this.produto=produto;
	}

}
