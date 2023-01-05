<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<body>
	<header>
		<%@include file="WEB-INF/fragments/headerAdmin.jsp"%>
	</header>
<h1>Liste des ventes</h1>
<h2>${u.pseudo}</h2>
<div class="row d-flex justify-content-center">
			<c:forEach items="${ArticleList}" var="article">
			<div class="col-4 d-flex" style="border: solid black 2px">
				<div class="col-2 m-2"
					style="border: solid black 2px; height: 100px; width: 100px"></div>
				<div>
				
					
					<p >Article : ${article.nomArticle}</p>
					
					<p>Prix : ${article.prixVente} points</p>
					<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
					
					
					
					
				</div>
			</div>
			<div class="col-1"></div>
			</c:forEach>
			<form action="<%=request.getContextPath()%>/modifierVenteAdmin" method="post">
			<label for="valid">Etes-vous sur de vouloir desactiver ses ventes</label> <input type="checkbox" id="valid"
					name="valid" required />
					<input value="${u.no_utilisateur }"
							type="hidden" id="noUtil" name="noUtil">
			<button type="submit"  value="Desactiver">Desactiver</button>
			
			</form>
			<br>
			<a href="ProfilAdmin">Retour</a>
</body>
</html>