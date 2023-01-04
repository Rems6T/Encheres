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


<div class="row my-5">
			<div class="ms-auto col-3">
				<b>Recherche :</b> <br />
				<div>
					<input id="searchbar" type="text" name="search"
						placeholder="Le nom de l'article" /> <br /> <br /> Categorie :
					<select name="categorie" id="categorie">
						<option value="toutes">Toutes</option>
						<option value="informatique">Informatique</option>
						<option value="ameublement">Ameublement</option>
						<option value="vetement">Vêtements</option>
						<option value="sportloisirs">Sport & Loisirs</option>
					</select>
				</div>
			</div>
			<div class="me-auto col-3">
				
					<button type="submit" style="width: 100%; height: 100%" >Rechercher</button>
			</div>
		</div>
		<h1>Creer nouvelle vente</h1>
		<a href="CreerVente"><input type="button" class="btn" placeholder="Creer"></a>
		<h1>Mes ventes</h1>
		<!-- Affichage des articles -->

		<br /> <br /> <br />
		<div class="row d-flex justify-content-center">
			<c:forEach items="${articleList}" var="article">
			<div class="col-4 d-flex" style="border: solid black 2px">
				<div class="col-2 m-2"
					style="border: solid black 2px; height: 100px; width: 100px"></div>
				<div>
				
					<p>${article.nomArticle}</p>
					<p>Prix : ${article.miseAPrix} points</p>
					<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
					<a href="ModifierVente?id=${article.noArticle}">Modifier</a>
					
				</div>
			</div>
			<div class="col-1"></div>
			</c:forEach>
			</div>
</body>
</html>