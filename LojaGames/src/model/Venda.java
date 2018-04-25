package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Venda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String idSessao;
	private double valorDaVenda;
	private Calendar dataDaVenda;
	private String cliente;
	private String cpf;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}
	public double getValorDaVenda() {
		return valorDaVenda;
	}
	public void setValorDaVenda(double valorDaVenda) {
		this.valorDaVenda = valorDaVenda;
	}
	public Calendar getDataDaVenda() {
		return dataDaVenda;
	}
	public void setDataDaVenda(Calendar dataDaVenda) {
		this.dataDaVenda = dataDaVenda;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}

