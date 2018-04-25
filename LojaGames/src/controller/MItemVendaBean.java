package controller;

import java.util.ArrayList;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.CloseEvent;


import dal.ProdutoDAO;
import dal.VendaDAO;
import model.ItemVenda;
import model.Produto;
import util.Sessao;

@SessionScoped
@ManagedBean(name = "mItemVendaBean")
public class MItemVendaBean {
	
	private ItemVenda item = new ItemVenda();
	private ArrayList<ItemVenda> itens = new ArrayList<ItemVenda>();
	private int idProduto;
	private double valorTotalCarrinho;
	private int quantidadeTotalCarrinho;
	
	public int getQuantidadeTotalCarrinho() {
		return VendaDAO.quantidadeTotalCarrinho(Sessao.getCarrinhoId());
	}

	public void setQuantidadeTotalCarrinho(int quantidadeTotalCarrinho) {
		this.quantidadeTotalCarrinho = quantidadeTotalCarrinho;
	}

	public double getValorTotalCarrinho() {
		return VendaDAO.valorTotalCarrinho(Sessao.getCarrinhoId());
	}

	public void setValorTotalCarrinho(double valorTotalCarrinho) {
		this.valorTotalCarrinho = valorTotalCarrinho;
	}

	public ArrayList<ItemVenda> getItens() {
		return VendaDAO.listarItensDeVendaPorIdSessao(Sessao.getCarrinhoId());
	}

	public void setItens(ArrayList<ItemVenda> itens) {
		this.itens = itens;
	}

	public ItemVenda getItem() {
		return item;
	}

	public void setItem(ItemVenda item) {
		this.item = item;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public String adicionarItemVenda(ItemVenda i) {
		Produto p = ProdutoDAO.buscarProdutoPorId(idProduto);
		i.setProduto(p);
		i.setIdSessao(Sessao.getCarrinhoId());
		VendaDAO.cadastrarItemVenda(i);
		item = new ItemVenda();
		return "CarrinhoDeCompras.xhtml?faces-redirect=true";
	}
	
	public String removerItem(ItemVenda i){
		VendaDAO.removerItemCarrinho(i);
		return "ListarProdutos.xhtml?faces-redirect=true";
	}
	
	
	public void mensagemConfirmacao() {
        addMessage("System Error", "Please try again later.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
	
	
	
}