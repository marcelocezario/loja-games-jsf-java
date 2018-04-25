package dal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexao {
	
	private static final String NOME_DA_CONEXAO = "GamesDB";
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(NOME_DA_CONEXAO);
	
	public static EntityManager getEntityManager(){
		return EMF.createEntityManager();
	}

}
