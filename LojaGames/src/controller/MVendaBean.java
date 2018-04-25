package controller;

import java.util.ArrayList;
import java.util.UUID;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dal.ProdutoDAO;
import dal.VendaDAO;
import model.ItemVenda;
import model.Produto;
import model.Venda;
import util.Sessao;

@SessionScoped
@ManagedBean(name = "mVendaBean")
public class MVendaBean {
	
	private Venda venda = new Venda();
	private ItemVenda itemVenda = new ItemVenda();
	private ArrayList<Venda> vendas = new ArrayList<Venda>();
	private ArrayList<ItemVenda> itens = new ArrayList<ItemVenda>();
	private Venda vendaSelecionada;
	private ArrayList<ItemVenda> itensVendaDetalhado = new ArrayList<ItemVenda>();
	
	
	public ItemVenda getItemVenda() {
		return itemVenda;
	}
	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}
	public ArrayList<ItemVenda> getItensVendaDetalhado() {
		return VendaDAO.listarItensDeVendaPorIdSessao(venda.getIdSessao());
	}
	public void setItensVendaDetalhado(ArrayList<ItemVenda> itensVendaDetalhado) {
		this.itensVendaDetalhado = itensVendaDetalhado;
	}
	public Venda getVendaSelecionada() {
		return vendaSelecionada;
	}
	public void setVendaSelecionada(Venda vendaSelecionada) {
		this.vendaSelecionada = vendaSelecionada;
	}
	public ArrayList<ItemVenda> getItens() {
		return VendaDAO.listarItensDeVendaPorIdSessao(Sessao.getCarrinhoId());
	}
	public void setItens(ArrayList<ItemVenda> itens) {
		this.itens = itens;
	}
	private String idSessao;
	
	public String getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public ArrayList<Venda> getVendas() {
		return VendaDAO.listarVendas();
	}
	public void setVendas(ArrayList<Venda> vendas) {
		this.vendas = vendas;
	}

	
	public String adicionarVenda (Venda v){
		v.setIdSessao(Sessao.getCarrinhoId());
		VendaDAO.cadastrarVenda(v);
		venda = new Venda ();
		return "ListarVendas.xhtml?faces-redirect=true";
	}
	
	public void enviarDadosParaListar (Venda v){
		this.venda = v;
	}
	
	
	public ArrayList<ItemVenda> listarItensDaVenda (Venda v){
		ArrayList<ItemVenda> lista = VendaDAO.listarItensDeVendaPorIdSessao(v.getIdSessao());
		venda = new Venda();
		return lista;
	}

}
