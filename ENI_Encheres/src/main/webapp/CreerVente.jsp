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
	<h1>Nouvelle Vente</h1>
	
	<form action="<%=request.getContextPath()%>/CreerVente" method="post">


		
		<label for="nom">Article : <input type="text" id="nom"
			name="nom">
		</label> 
		<label for="description">Description : <input type="text"
			id="description" name="description">
		</label>
		<label for="categorie"> Categorie : 
		<select name="categorie">
		<c:forEach items="${categorieList}" var="categorie">
		<option value="${categorie.noCategorie }">${categorie.libelle }</option>
		</c:forEach>
		</select>
		</label>
		<label for="photo">Photo de l'article : <input type="file"
       id="photo" name="photo"
       accept="image/png, image/jpeg">
		</label>
		<label for="miseAPrix">Mise à Prix : <input type="number"
			id="miseAPrix" name="miseAPrix">
		</label>
		
		<label for="debutEnchere">Début de l'enchère : <input type="datetime-local"
			id="debutEnchere" name="debutEnchere" min="${tempsDebut }" value="${tempsDebut }">
		</label>
		<label for="finEnchere">Fin de l'enchère : <input  type="datetime-local"
			id="finEnchere" name="finEnchere" min="${tempsFin }" value="${tempsFin }">
		</label>
		<fieldset>
    <legend>Retrait</legend>
    <label for="rue">Rue : <input type="text"
			id="rue" name="rue" value="${ConnectedUser.rue }">
		</label>
		<label for="codePostal">codePostal : <input type="text"
			id="codePostal" name="codePostal" value="${ConnectedUser.codePostal }">
		</label>
		<label for="ville">ville : <input type="text"
			id="ville" name="ville" value="${ConnectedUser.ville }">
		</label>
	</fieldset>
	<button class="btn" type="submit"  value="CréerVente">Valider</button>
	</form></div>
</body>
</html>