package controller;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dal.MarcaDAO;
import model.Marca;

@SessionScoped
@ManagedBean(name = "mMarcaBean")
public class MMarcaBean {
	
	private Marca marca = new Marca();
	private ArrayList<Marca> marcas = new ArrayList<Marca>();
	
	public ArrayList<Marca> getmarcas() {
		return MarcaDAO.listarMarcas();
	}	
	
	public Marca getMarca() {
		return marca;
	}
	
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public String adicionarMarca (Marca m){
		MarcaDAO.cadastrarMarca(m);
		marca = new Marca();
		return "ListarMarcas.xhtml?faces-redirect=true";
	}
	
	public String removerMarca (Marca m){
		MarcaDAO.removerMarca(m);
		return "ListarMarcas.xhtml?faces-redirect=true";
	}
	
	public String enviarDadosParaAlterar (Marca m){
		this.marca = m;
		return "AlterarMarca.xhtml?faces-redirect=true";
	}
	
	public String alterarMarca (Marca m) {
		MarcaDAO.alterarMarca(m);
		marca = new Marca();
		return "ListarMarcas.xhtml?faces-redirect=true";
	}
}