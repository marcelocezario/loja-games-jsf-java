package dal;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Categoria;

public class CategoriaDAO {
	
	public static boolean cadastrarCategoria(Categoria categoria) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.persist(categoria);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	public static boolean removerCategoria(Categoria c) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		c = em.getReference(Categoria.class, c.getId());
		em.remove(c);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	public static ArrayList<Categoria> listarCategorias() {
		EntityManager em = Conexao.getEntityManager();
		Query q = em.createQuery("SELECT c FROM Categoria c ORDER BY NOME");
		ArrayList<Categoria> lista = (ArrayList<Categoria>) q.getResultList();
		em.close();
		return lista;
	}
	
	public static boolean alterarCategoria(Categoria c) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		Categoria categoria = em.find(Categoria.class, c.getId());
		categoria.setDescricao(c.getDescricao());
		categoria.setNome(c.getNome());
		em.merge(categoria);
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
	public static Categoria buscarCategoriaPorId(int idCategoria) {
		EntityManager em = Conexao.getEntityManager();
		Categoria c = em.find(Categoria.class, idCategoria);
		return c;
	}

	public static Categoria buscarCategoriaPorNome(String nome, String descricao) {
		EntityManager em = Conexao.getEntityManager();
		Query query = em.createQuery("SELECT c FROM Categoria c WHERE c.nome = :nome AND c.descricao = " + ":descricao",
				Categoria.class);

		query.setParameter("nome", nome);
		query.setParameter("descricao", descricao);
		return (Categoria) query.getResultList().get(0);
	}

	

	

}
