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

<div class="vente">
	<h1>Modifier Vente</h1>
	
	<form action="<%=request.getContextPath()%>/ModifierVente"
		method="post">
		<input type="hidden" name="noArticle" value="${articleAffiche.noArticle }">
		<input type="hidden" name="noUtilisateur" value="${articleAffiche.noUtilisateur }">
	<label for="nom">Article : <input type="text" id="nom"
			name="nom" value="${articleAffiche.nomArticle }">
		</label> 
		<label for="description">Description : <input type="text"
			id="description" name="description" value="${articleAffiche.description }">
		</label>
		<label for="categorie"> Categorie : 
		<select name="categorie">
		<c:forEach items="${categorieList}" var="categorie">
		<option value="${categorie.noCategorie }" >${categorie.libelle }</option>
		</c:forEach>
		</select>
		</label>
		<label for="photo">Photo de l'article : <input type="file"
       id="photo" name="photo"
       accept="image/png, image/jpeg">
		</label>
		<label for="miseAPrix">Mise à Prix : <input type="number"
			id="miseAPrix" name="miseAPrix" value="${articleAffiche.miseAPrix }">
		</label>
		
		<label for="debutEnchere">Début de l'enchère : <input type="datetime-local"
			id="debutEnchere" name="debutEnchere" value="${articleAffiche.dateDebutEncheres }">
		</label>
		<label for="finEnchere">Fin de l'enchère : <input  type="datetime-local"
			id="finEnchere" name="finEnchere" value="${articleAffiche.dateFinEncheres }">
		</label>
		<fieldset>
    <legend>Retrait</legend>
    <label for="rue">Rue : <input type="text"
			id="rue" name="rue" value="${retrait.rue }">
		</label>
		<label for="codePostal">codePostal : <input type="text"
			id="codePostal" name="codePostal" value="${retrait.code_postal }">
		</label>
		<label for="ville">ville : <input type="text"
			id="ville" name="ville" value="${retrait.ville }">
		</label>
	</fieldset>
	<button class="btn" type="submit"  value="ModifierVente">Modifier Vente</button>
		</form>
	<br>
	<form action="<%=request.getContextPath()%>/SupprimerVente"
		method="post">
		<input type="hidden" name="noArticle" value="${articleAffiche.noArticle }">
		<label for="suppr">Cocher pour supprimer : <input type="checkbox"
			id="suppr" name="suppr" required>
		</label>
		<button class="btn" type="submit"  value="SupprimerVente">Supprimer Vente</button>
		</form></div>
</body>
</html>