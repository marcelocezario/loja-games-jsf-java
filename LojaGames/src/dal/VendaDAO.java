package dal;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import model.ItemVenda;
import model.Produto;
import model.Venda;
import util.Calculos;
import util.Sessao;
import util.Validacao;

public class VendaDAO {

	private static ArrayList<Venda> vendas = new ArrayList<Venda>();

	public static boolean cadastrarItemVenda(ItemVenda item) {
		item.setValorItem(Calculos.calcularValorItem(item));		
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
	public static final String gerarIdSessao (){
		String idSessao;
		idSessao = UUID.randomUUID().toString();
		return idSessao;
	}
	
	public static boolean cadastrarVenda(Venda venda) {
		if(Validacao.validarEstoqueVenda(venda)){
			baixarEstoque(venda);
			venda.setDataDaVenda(Calendar.getInstance());
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			em.persist(venda);
			em.getTransaction().commit();
			em.close();
			Sessao.encerrarSessao();
			return true;
		} else {
			return false;
		}
		
	}

	public static ArrayList<Venda> listarVendas() {
		EntityManager em = Conexao.getEntityManager();
		Query q = em.createQuery("SELECT v FROM Venda v");
		ArrayList<Venda> vendaBanco = (ArrayList<Venda>) q.getResultList();
		em.close();
		return vendaBanco;
	}
	
	public static ArrayList<ItemVenda> listarItensDeVendaPorIdSessao(String idSessao) {
		EntityManager em = Conexao.getEntityManager();
		Query q = em.createQuery("SELECT i FROM ItemVenda i WHERE i.idSessao = :idSessao");
		q.setParameter("idSessao", idSessao);
		ArrayList<ItemVenda> itensDaVendaBanco = (ArrayList<ItemVenda>) q.getResultList();
		em.close();
		return itensDaVendaBanco;
	}
	
	public static double valorTotalCarrinho (String idSessao){
		ArrayList<ItemVenda> carrinho = new ArrayList<ItemVenda>();
		carrinho = listarItensDeVendaPorIdSessao(idSessao);
		double valorTotalCarrinho = 0;
		for (ItemVenda itemVenda : carrinho) {
			valorTotalCarrinho += itemVenda.getValorItem();
		}
		return valorTotalCarrinho;
	}
	
	public static int quantidadeTotalCarrinho (String idSessao){
		ArrayList<ItemVenda> carrinho = new ArrayList<ItemVenda>();
		carrinho = listarItensDeVendaPorIdSessao(idSessao);
		int quantidadeTotalCarrinho = 0;
		for (ItemVenda itemVenda : carrinho) {
			quantidadeTotalCarrinho += itemVenda.getQuantidade();
		}
		return quantidadeTotalCarrinho;
	}
	
	public static void baixarEstoque (Venda venda){
		ArrayList<ItemVenda> itensVenda = new ArrayList<ItemVenda>();
		itensVenda=VendaDAO.listarItensDeVendaPorIdSessao(venda.getIdSessao());
		for (ItemVenda item : itensVenda) {
			EntityManager em = Conexao.getEntityManager();
			em.getTransaction().begin();
			Produto produto = em.find(Produto.class, item.getProduto().getId());
			produto.setQuantidadeEstoque(item.getProduto().getQuantidadeEstoque() - item.getQuantidade());
			em.merge(produto);
			em.getTransaction().commit();
			em.close();			
		}
	}
	
	public static void removerItemCarrinho (ItemVenda i){
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		ItemVenda itemVenda = em.find(ItemVenda.class, i.getId());
		itemVenda.setIdSessao("Produto removido carrinho");
		em.merge(itemVenda);
		em.getTransaction().commit();
		em.close();		
	}
	

}
