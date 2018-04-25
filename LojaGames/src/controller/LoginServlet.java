package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("txtLogin");
		String verifica = request.getParameter("btnAcao");
		System.out.println(acao);
	
		if(verifica.equals("Verificar")){
			if(acao.equals("administrador")){
				response.sendRedirect("Login.jsp?mensagem=1");
			}else{
				if(acao.equals("usuario")){
					response.sendRedirect("Login.jsp?mensagem=2");
				}else{
					response.sendRedirect("Login.jsp?mensagem=3");
				}
			}
		}
			
	}
		
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
