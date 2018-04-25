<%@page import="org.primefaces.event.diagram.ConnectEvent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
	String parametro = request.getParameter("mensagem");
	String mensagem = "";
	String conta = "";
	if (parametro != null) {
		if (parametro.equals("1")) {
			mensagem = "Administrador";
			conta = "ListarProdutos.xhtml?faces-redirect=true";
		} else {
			if (parametro.equals("2")) {
				mensagem = "Usuario";
				conta = "ListarProdutosUser.xhtml?faces-redirect=true";
			} else {
				mensagem = "Login invalido";
			}

		}
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
<title>Login</title>
<style type="text/css">
div.container {
	margin-top: 20px;
	margin-left: 10px;
}

legend {
	margin-left: 13px;
}
</style>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-primary"> <a
		class="navbar-brand" href="#">ARENA GAMES PS4</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarColor01" aria-controls="navbarColor01"
		aria-expanded="false" aria-label="Toggle navigation" style="">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarColor01">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="#"> <span
					class="sr-only">(current)</span></a></li>

		</ul>

	</div>
	</nav>


	<form class="form-horizontal" action="LoginServlet" method="get">
		<fieldset>
			<div class="container">
				<legend>Login</legend>
				<div class="form-group">
					<label class="col-lg-2 control-label">Login</label>
					<div class="container">
						<input type="text" class="form-control"
							placeholder="administrador/usuario" name="txtLogin">
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<input type="submit" value="Verificar" class="btn btn-primary"
							name="btnAcao" />
					</div>
				</div>
				<div class="form-group">
					<label > <%= mensagem %>
					</label>
				</div>
				<a href="<%=conta%>">Logar</a>


			</div>
		</fieldset>
	</form>


</body>
</html>