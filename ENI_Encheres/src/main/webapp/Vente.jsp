<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<body>
	<header>
		<%@include file="WEB-INF/fragments/headerConnecte.jsp"%>
	</header>
<body>

<h1>Mes ventes</h1>

			<div class="contenant">


		
		<!-- Affichage des articles -->

		<br />
			<c:forEach items="${articleList }" var="article">
			<div class="article">

				
					<p>${article.nomArticle}</p>
					<p>Prix : ${article.miseAPrix} points</p>
					<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
					<c:if test="${article.etatVente == 'CREE' }">
					<a href="ModifierVente?id=${article.noArticle}">Modifier</a>
					</c:if>
				</div>

		
			</c:forEach>
			</div>
			
			<div class="contenant2">	
		<a href="CreerVente"><button class="btn" >Creer nouvelle vente</button></a> </div>
			
</body>
</html>