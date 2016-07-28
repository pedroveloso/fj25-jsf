package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;


@Named
@ConversationScoped
@Transactional
public class NotaFiscalBean implements Serializable {
	
	@Inject
	private Conversation conversation;
	
	private Item item = new Item();
	private Long idProduto;
	
	@Inject
	private ProdutoDao produtoDao;
	
	public Long getIdProduto(){
		return this.idProduto;
	}
	
	public void setIdProduto(Long idProduto){
		this.idProduto=idProduto;
	}
	
	public Item getItem(){
		return this.item;
	}
	
	public void setItem(Item item){
		this.item=item;
	}
	
	private NotaFiscal notaFiscal = new NotaFiscal();
	
	@Inject
	private NotaFiscalDao notaFiscalDao;
	
	public String avancar(){
		if(conversation.isTransient()){
		conversation.begin();
		}
		return "item?faces-redirect=true";
	}
	
	@Transactional
	public String gravar(){
		notaFiscalDao.adiciona(notaFiscal);
		conversation.end();
		this.notaFiscal = new NotaFiscal();
		return "notafiscal?faces-redirect=true";
	}
	
	public NotaFiscal getNotaFiscal(){
		return notaFiscal;
	}
	
	public void guardaItem(){
		Produto produto = produtoDao.buscaPorId(idProduto);
		
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		
		notaFiscal.getItens().add(item);
		item.setNotaFiscal(notaFiscal);
		
		item=new Item();
		idProduto= null;
	}
	
}
