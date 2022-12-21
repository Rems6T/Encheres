<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.ENI_Encheres.bo.ArticleVendu"%>
<%@page import="fr.eni.ENI_Encheres.bo.Utilisateur"%>
<%@page import="fr.eni.ENI_Encheres.jdbcImpl.UtilisateurDAOJdbcImpl"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>

<body>
	<header class="p-3 row">
		<div class="col-3 h4">ENI-Encheres</div>
		<div class="col-2 d-flex ms-auto">
		<%  if (request.getSession().getAttribute("ConnectedUser")==null){ %>
			<a href="/Inscription">S'inscrire</a> &#160;-&#160; <a
				href="/Connexion">Se connecter</a>
				<%} %>
		</div>
	</header>

	<main class="ms-3">

		<!-- Nom de la page -->

		<div class="d-flex justify-content-center">
			<h1>Liste des enchères</h1>
		</div>

		<!-- Espace de recherche des articles -->

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
				<input type="submit" style="width: 100%; height: 100%" />
			</div>
		</div>

		<!-- Affichage des articles -->

		<br /> <br /> <br />
		<div class="row d-flex justify-content-center">
			<c:forEach items="${ArticleList}" var="article">
			<div class="col-4 d-flex" style="border: solid black 2px">
				<div class="col-2 m-2"
					style="border: solid black 2px; height: 100px; width: 100px"></div>
				<div>
				
					<p>${article.nomArticle}</p>
					<p>Prix : ${article.miseAPrix} points</p>
					<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
					<p>Vendeur : ${article.noUtilisateur}</p>
					
				</div>
			</div>
			<div class="col-1"></div>
			</c:forEach>
			</div>
			<!-- 
			<div class="col-4 d-flex" style="border: solid black 2px">
				<div class="col-2 m-2"
					style="border: solid black 2px; height: 100px; width: 100px"></div>
				<div class="">
					<p>
						<u>Article 2</u>
					</p>
					<p>Prix : X points</p>
					<p>Fin de l'enchère : XX/XX/XXXX</p>
					<p>Vendeur : nom_vendeur</p>
				</div>
			</div>
		</div> -->
	</main>

	<!-- Bootstrap JavaScript Libraries -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
		integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
		integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
		crossorigin="anonymous"></script>
</body>
</html>
