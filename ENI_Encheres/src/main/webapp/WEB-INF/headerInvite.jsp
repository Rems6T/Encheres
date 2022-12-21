<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.ENI_Encheres.bo.ArticleVendu"%>
<%@page import="fr.eni.ENI_Encheres.bo.Utilisateur"%>
<%@page import="fr.eni.ENI_Encheres.jdbcImpl.UtilisateurDAOJdbcImpl"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header>



		<div class="border border-primary ">
			<div class="row m-auto">
				<a href="<%=request.getContextPath()%>/Accueil.jsp" style="text-decoration-line: none;">ENI-Encheres</a>
				<div class="col ">
					<nav>
						<ul class="nav justify-content-end col-lg  ">

							<li class="nav-item"><a class="nav-link active"
								aria-current="page"
								href="<%=request.getContextPath()%>/CreerCompte.jsp">S'inscrire</a>
							</li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/Connexion.jsp">Se
									connecter</a></li>

						</ul>
					</nav>
				</div>

			</div>
		</div>	

	</header>
</body>
</html>