package controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dal.CategoriaDAO;
import model.Categoria;

@SessionScoped
@ManagedBean(name = "mCategoriaBean")
public class MCategoriaBean {
	
	private Categoria categoria = new Categoria();
	private ArrayList<Categoria> categorias = new ArrayList<Categoria>();
	
	public ArrayList<Categoria> getCategorias() {
		return CategoriaDAO.listarCategorias();
	}	
	
	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String adicionarCategoria (Categoria c){
		CategoriaDAO.cadastrarCategoria(c);
		categoria = new Categoria();
		return "ListarCategorias.xhtml?faces-redirect=true";
	}
	
	public String removerCategoria (Categoria c){
		CategoriaDAO.removerCategoria(c);
		return "ListarCategorias.xhtml?faces-redirect=true";
	}
	
	public String enviarDadosParaAlterar (Categoria c){
		this.categoria = c;
		return "AlterarCategoria.xhtml?faces-redirect=true";
	}
	
	public String alterarCategoria (Categoria c){
		CategoriaDAO.alterarCategoria(c);
		categoria = new Categoria();
		return "ListarCategorias.xhtml?faces-redirect=true";
	}
}