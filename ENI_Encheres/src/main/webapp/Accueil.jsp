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
	<header>
		<div>


			<%
			ArticleVendu article = (ArticleVendu) request.getAttribute("ArticleAffiche");
			%>

			<%
			if (request.getSession().getAttribute("ConnectedUser") == null) {
			%>
			<%@include file="WEB-INF/fragments/headerInvite.jsp"%>
			<%
			} else {
			%>
			<%@include file="WEB-INF/fragments/headerConnecte.jsp"%>
			<%
			}
			%>
		</div>
	</header>

	<main >

		<!-- Nom de la page -->

	
			<h1>Liste des enchères</h1>
	

		<!-- Espace de recherche des articles -->

		<div class="contenant2">
			<div class="contenu">
				<b>Recherche :</b> <br />
				<div>
					<input id="searchbar" type="text" name="search"
						placeholder="Le nom de l'article" /> <br /> <br /> Categorie :
					<select name="categorie" id="categorie">
						<option value="0">Toutes</option>
						<c:forEach items="${categorieList}" var="categorie">
							<option value="${categorie.noCategorie }">${categorie.libelle }</option>
						</c:forEach>
					</select>
				</div>
			
				<button class="btn" type="submit">Rechercher</button>
			</div>
			<br>
			<div class="trier">
			<fieldset >
				<legend>Trier par :</legend>
				<div class="trier2">
				<div class="trier3">
					<input type="radio" id="achat" name="choixTri" value="achat"
						${checkAchat }> <label for="achat">Achats</label>
					<div>
						<a href="lister?etatVente=CREE&id=0&check=1"><input
							type="checkbox" id="debut" name="choixEnchere" disabled="disabled"
							${check1 }><label for="debut">encheres non débuté</label></a>
					</div>
					<div>
						<a href="lister?etatVente=EN_COURS&id=0&check=2"><input
							type="checkbox" id="ouvertes" name="choixEnchere"
							disabled="disabled" ${check2 }><label for="ouvertes">encheres
								ouvertes</label></a>
					</div>
					<div>
						<a href="lister?etatVente=ENCHERES_TERMINEES&id=0&check=3"><input
							type="checkbox" id="termine" name="choixEnchere" disabled="disabled"
							${check3 }><label for="termine">encheres terminé</label></a>
					</div>
				</div>
				<c:if test="${ConnectedUser!=null }">
					<div class="trier3">
						<input type="radio" id="vente" name="choixTri" value="vente"
							${checkVente }> <label for="vente">Mes ventes</label>
						<div>
							<a
								href="lister?etatVente=CREE&id=${ConnectedUser.no_utilisateur }&check=4"><input
								type="checkbox" id="debutVente" name="debutVente"
								disabled="disabled" ${check4 }><label for="debutVente">Ventes
									non débuté</label></a>
						</div>

						<div>
							<a
								href="lister?etatVente=EN_COURS&id=${ConnectedUser.no_utilisateur }&check=5"><input
								type="checkbox" id="ouvertesVente" name="ouvertesVente" ${check5 }><label
								for="ouvertesVente">Ventes ouvertes</label></a>
						</div>
						<div>
							<a
								href="lister?etatVente=ENCHERES_TERMINEES&id=${ConnectedUser.no_utilisateur }&check=6"><input
								type="checkbox" id="termineVente" name="termineVente" ${check6 }><label
								for="termineVente">Ventes terminés</label></a>
						</div>
					</div>
				</c:if>
				</div>
			</fieldset>
			</div>
		</div>
		<script type="text/javascript">
			//fonstion pour Achats
			// Get your checkbox who determine the condition
			var determine1 = document.getElementById("achat");
			// Make a function who disabled or enabled your conditioned checkbox
			var disableCheckboxConditioned1 = function() {
				if (determine1.checked) {
					document.getElementById("debut").disabled = false;
					document.getElementById("ouvertes").disabled = false;
					document.getElementById("termine").disabled = false;
					//on desactives les radio de vente
					document.getElementById("debutVente").disabled = true;
					document.getElementById("ouvertesVente").disabled = true;
					document.getElementById("termineVente").disabled = true;
				} else {

					document.getElementById("debut").disabled = true;
					document.getElementById("ouvertes").disabled = true;
					document.getElementById("termine").disabled = true;
				}
			}
			// On click active your function
			determine1.onclick = disableCheckboxConditioned1;
			disableCheckboxConditioned1();
		</script>
		<script type="text/javascript">
			//fonstion pour Ventes
			// Get your checkbox who determine the condition
			var determine = document.getElementById("vente");
			// Make a function who disabled or enabled your conditioned checkbox
			var disableCheckboxConditioned = function() {
				if (determine.checked) {
					document.getElementById("debutVente").disabled = false;
					document.getElementById("ouvertesVente").disabled = false;
					document.getElementById("termineVente").disabled = false;
					//on desactive les radio de achats
					document.getElementById("debut").disabled = true;
					document.getElementById("ouvertes").disabled = true;
					document.getElementById("termine").disabled = true;
				} else {

					document.getElementById("debutVente").disabled = true;
					document.getElementById("ouvertesVente").disabled = true;
					document.getElementById("termineVente").disabled = true;
				}
			}
			// On click active your function
			determine.onclick = disableCheckboxConditioned;
			disableCheckboxConditioned();
		</script>
		<!-- Affichage des articles -->

		
		<div>
			<c:forEach items="${ArticleListU}" var="article" end="6">
				<div class="article">


						<p>
							Article : <a href="Encherir?noArticle=${article.noArticle}">${article.nomArticle}</a>
						</p>

						<p>Prix : ${article.prixVente} points</p>
						<p>Debut de l'enchère : ${article.dateDebutEncheres}</p>
						<p>Fin de l'enchère : ${article.dateFinEncheres}</p>
						<p>Vendeur : ${article.pseudoUtil }</p>
						<p>Statut de la vente : ${article.etatVente}</p>


					</div>
				
				
			</c:forEach>
		</div>

	</main>
	<%@include file="WEB-INF/fragments/footer.jsp"%>
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
