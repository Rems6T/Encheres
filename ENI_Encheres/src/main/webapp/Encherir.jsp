<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni.ENI_Encheres.bo.Utilisateur"%>
<%@page import="fr.eni.ENI_Encheres.bo.ArticleVendu"%>
<%@page import="fr.eni.ENI_Encheres.bo.Encheres"%>
<%@page import="fr.eni.ENI_Encheres.bo.Retrait"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>


<body >
<header>
		<div>
		

					<% ArticleVendu article = (ArticleVendu)request.getAttribute("ArticleAffiche"); %>
				
				<%  if (request.getSession().getAttribute("ConnectedUser")==null){ %>		
				<%@include file="WEB-INF/headerInvite.jsp"%>
	<%} else {%>
		<%@include file="WEB-INF/headerConnecte.jsp"%>
	<%} %>
		</div>
	</header>
	<main class="ms-3">
	<div class="head">
		<h1>Détail Vente</h1>
	</div>
	

	<div class="container">
		<div>
			<img src="" alt="">
		</div>
		<table>
			<tr>
				<td class="td1"><p class="value-td1">Nom:</p></td>
				<td class="td2"><p class="value-td2">${articleAffiche.nomArticle }</p></td>
			</tr>
			<tr>
				<td class="td1"><p class="value-td1">Description:</p></td>
				<td class="td2"><p class="value-td2">${articleAffiche.description }</p></td>
			</tr>

			<tr>
				<td class="td1"><p class="value-td1">Meilleure offre:</p></td>
				<td class="td2"><p class="value-td2">${articleAffiche.prixVente }
						pts</p></td>
			</tr>

			<tr>
				<td class="td1"><p class="value-td1">Mise à prix:</p></td>
				<td class="td2"><p class="value-td2">${articleAffiche.miseAPrix }
						pts</p></td>
			</tr>
			<tr>
				<td class="td1"><p class="value-td1">Fin de l'enchère:</p></td>
				<td class="td2"><p class="value-td2">${articleAffiche.dateFinEncheres }</p></td>
			</tr>
			<tr>
				<td class="td1"><p class="value-td1">Retrait:</p></td>
				<td class="td2"><p class="value-td2">${retraitVente.rue },
						${retraitVente.code_postal }, ${retraitVente.ville }</p></td>
			</tr>
			<tr>
				<td class="td1"><p class="value-td1">Vendeur:</p></td>
				<td class="td2"><p class="value-td2">${vendeur.pseudo}</p></td>
			</tr>
		</table>




		<c:choose>

			<c:when test="${ConnectedUser == null}"></c:when>
			<c:when
				test="${vendeur.no_utilisateur == ConnectedUser.no_utilisateur} ">
				<p>Vous etes le vendeur</p>
			</c:when>
			<c:otherwise>
				<form action="<%=request.getContextPath()%>/Encherir" method="post">
					<div class="input-field">
						<label for="mPrix">Ma Proposition :</label> <input class="input"
							type="number" name="mPrix" id="mPrix" step="1"
							max="${ConnectedUser.credit }" min="${articleAffiche.prixVente+1}"
							required>
							 <input value="${articleAffiche.noArticle }"
							type="hidden" id="noArticle" name="noArticle"> <input
							value="${articleAffiche.noUtilisateur }" type="hidden"
							id="noVendeur" name="noVendeur">
					</div>
					<div>
						<button class="btn-login" type="submit">Enchérir</button>
					</div>

				</form>


			</c:otherwise>
		</c:choose>
		<a href="Accueil"><button class="btn" type="button">
				<!--change that with index.html file location-->
				Back
			</button> </a>
	</div>
	</main>
</body>
</html>