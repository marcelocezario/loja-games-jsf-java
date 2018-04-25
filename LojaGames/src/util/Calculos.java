package util;

import model.ItemVenda;
import model.Produto;

public class Calculos {
	
	public static double calcularValorItem (ItemVenda i){
		return i.getProduto().getValor() * i.getQuantidade();
	}
	


	
}
