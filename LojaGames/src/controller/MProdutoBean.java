package controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dal.CategoriaDAO;
import dal.MarcaDAO;
import dal.ProdutoDAO;
import model.Categoria;
import model.Marca;
import model.Produto;

@SessionScoped
@ManagedBean(name = "mProdutoBean")
public class MProdutoBean {
	
	private Produto produto = new Produto();
	private ArrayList<Produto> produtos = new ArrayList<Produto>();
	private int idCategoria;
	private int idMarca;
	private int quantidadeEntradaEstoque;
	
	public int getQuantidadeEntradaEstoque() {
		return quantidadeEntradaEstoque;
	}
	public void setQuantidadeEntradaEstoque(int quantidadeEntradaEstoque) {
		this.quantidadeEntradaEstoque = quantidadeEntradaEstoque;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public int getIdMarca() {
		return idMarca;
	}
	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}
	public ArrayList<Produto> getProdutos(){
		return ProdutoDAO.listarProdutos();
	}
	
	public Produto getProduto(){
		return produto;
	}
	
	public void setProduto (Produto produto){
		this.produto = produto;
	}
	
	public String adicionarProduto (Produto p){
		Categoria c = CategoriaDAO.buscarCategoriaPorId(idCategoria);
		p.setCategoria(c);
		Marca m = MarcaDAO.buscarMarcaPorId(idMarca);
		p.setMarca(m);
		ProdutoDAO.cadastrarProduto(p);
		produto = new Produto();
		return "ListarProdutos.xhtml?faces-redirect=true";
	}
	
	public String removerProduto (Produto p){
		ProdutoDAO.removerProduto(p);
		return "ListarProdutos.xhtml?faces-redirect=true";
	}
	
	public String enviarDadosParaAlterar (Produto p){
		this.produto = p;
		return "AlterarProduto.xhtml?faces-redirect=true";
	}
	
	public String alterarProduto (Produto p){
		ProdutoDAO.alterarProduto(p);
		produto = new Produto();
		return "ListarProdutos.xhtml?faces-redirect=true";
	}
	
	public String adicionarEstoque (int idProduto, int quantidade){
		Produto p = ProdutoDAO.buscarProdutoPorId(idProduto);
		ProdutoDAO.adicionarEstoque(p, quantidade);
		return "ListarProdutos.xhtml?faces-redirect=true";
	}

}