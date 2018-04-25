package dal;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.ItemVenda;
import model.Produto;
import model.Venda;

public class ProdutoDAO {

	private static ArrayList<Produto> produtos = new ArrayList<Produto>();

	public static boolean cadastrarProduto(Produto produto) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.persist(produto);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	public static boolean removerProduto(Produto p) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		p = em.getReference(Produto.class, p.getId());
		em.remove(p);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	public static ArrayList<Produto> listarProdutos() {
		EntityManager em = Conexao.getEntityManager();
		Query q = em.createQuery("SELECT p FROM Produto p ORDER BY nome");
		ArrayList<Produto> lista = (ArrayList<Produto>) q.getResultList();
		em.close();
		return lista;
	}

	public static boolean alterarProduto(Produto p) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		Produto produto = em.find(Produto.class, p.getId());
		produto.setCategoria(p.getCategoria());
		produto.setDescricao(p.getDescricao());
		produto.setMarca(p.getMarca());
		produto.setNome(p.getNome());
		produto.setQuantidadeEstoque(p.getQuantidadeEstoque());
		produto.setValor(p.getValor());
		em.merge(produto);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	public static Produto buscarProdutoPorId(int idProduto) {
		EntityManager em = Conexao.getEntityManager();
		Produto p = em.find(Produto.class, idProduto);
		return p;
	}

	public static Produto buscarProdutoPorNome(Produto produto) {
		EntityManager em = Conexao.getEntityManager();
		Query q = em.createQuery("SELECT p FROM Produto p WHERE p.nome = :nomeProduto");
		q.setParameter("nomeProduto", produto.getNome());
		ArrayList<Produto> produtosBanco = (ArrayList<Produto>) q.getResultList();
		em.close();
		return produtosBanco.get(0);
	}

	public static void adicionarEstoque(Produto p, int quantidade) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		Produto produto = em.find(Produto.class, p.getId());
		produto.setQuantidadeEstoque(p.getQuantidadeEstoque() + quantidade);
		em.merge(produto);
		em.getTransaction().commit();
		em.close();

	}

}