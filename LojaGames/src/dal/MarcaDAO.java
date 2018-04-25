package dal;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Categoria;
import model.Marca;

public class MarcaDAO {

	public static boolean cadastrarMarca(Marca marca) {
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		em.persist(marca);
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
	public static boolean removerMarca (Marca m){
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		m = em.getReference(Marca.class, m.getId());
		em.remove(m);
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
	public static ArrayList<Marca> listarMarcas(){
		EntityManager em = Conexao.getEntityManager();
		Query q = em.createQuery("SELECT m FROM Marca m ORDER BY NOME");
		ArrayList<Marca> lista = (ArrayList<Marca>) q.getResultList();
		em.close();
		return lista;
	}
	
	public static boolean alterarMarca (Marca m){
		EntityManager em = Conexao.getEntityManager();
		em.getTransaction().begin();
		Marca marca = em.find(Marca.class, m.getId());
		marca.setNome(m.getNome());
		marca.setEndereco(m.getEndereco());
		em.merge(marca);
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
	public static Marca buscarMarcaPorId (int idMarca){
		EntityManager em = Conexao.getEntityManager();
		Marca m = em.find(Marca.class, idMarca);
		return m;
	}

	public static Marca buscarMarcaPorNome (Marca marca) {
		EntityManager em = Conexao.getEntityManager();
		Query q = em.createQuery("SELECT m FROM Marca m WHERE m.nome = :nomeMarca");
		q.setParameter("nomeMarca", marca.getNome());
		ArrayList<Marca> marcasBanco = (ArrayList<Marca>) q.getResultList();
		em.close();
		return marcasBanco.get(0);
	}
	
	
	
	
	
	

}
