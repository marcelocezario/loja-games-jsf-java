package model;

import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItemVenda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Produto produto;
	private int quantidade;
	private double valorItem;
	private String idSessao;
	


	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		 this.idSessao = idSessao;
	}

	public double getValorItem() {
		return valorItem;
	}

	public void setValorItem(double valorItem) {
		this.valorItem = valorItem;
	}
	
	public ItemVenda(){
		produto = new Produto();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}


}