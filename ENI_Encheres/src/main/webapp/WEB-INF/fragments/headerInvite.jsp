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
<title>Accueil</title>
</head>
<body>
		<div class="header1">

	<div class="logo">
<a href="<%=request.getContextPath()%>/Accueil"><img 
					src="<%=request.getContextPath()%>/Images/logo1.png"
					alt="logo" height=100px ></a> </div>
					
					
			<nav> 
				<ul>
					<li><a href="<%=request.getContextPath()%>/CreerCompte.jsp" >S'inscrire</a></li>
					<li><a href="<%=request.getContextPath()%>/Connexion.jsp" >Se connecter</a></li>
				</ul>
		</nav> 
		</div>


</body>
</html>