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
 
       <nav class="navbar navbar-light  " >
            <a class="nav-link active m-2" aria-current="page"  href="<%=request.getContextPath()%>/Accueil.jsp">ENI Enchères</a>
            <ul class="nav justify-content-end "  > 
								
                <li class="nav-item ">
                <a class="nav-link" href="<%=request.getContextPath()%>/Vente">Vendre un article</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/PageProfil.jsp">Mon Profil </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/Logout">Deconnexion  </a>
                </li>
                
            </ul>
        </nav></header>
</body>
</html>