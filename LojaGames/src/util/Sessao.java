package util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dal.VendaDAO;

public class Sessao {

	public static String getCarrinhoId(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		if(session.getAttribute("carrinhoId") == null){			
			session.setAttribute("carrinhoId", VendaDAO.gerarIdSessao());
		}
		return session.getAttribute("carrinhoId").toString();
	}
	
	public static void encerrarSessao(){
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(true);
		session.setAttribute("carrinhoId", VendaDAO.gerarIdSessao());
	}
	
}
