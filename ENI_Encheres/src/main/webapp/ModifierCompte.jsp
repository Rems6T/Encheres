<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>


<body class="container">

	<%@ include file="WEB-INF/fragments/header.html"%>

	<h1>Mon profil</h1>


	<form action="<%=request.getContextPath()%>/ModifierCompte"
		method="post">
		<input type="hidden" name="id" value="${utilisateur.no_utilisateur }">
		<input type="hidden" name="credit" value="${utilisateur.credit }">

		<div class="row g-3">
			<div class="col">
				<label> Pseudo : </label><input type="text" name="pseudo"
					value="${utilisateur.pseudo }"> <br>
				<%
				if (request.getAttribute("pseudoUniqueOK") != null) {
					boolean pseudoUniqueOK = (boolean) request.getAttribute("pseudoUniqueOK");
					if (!pseudoUniqueOK) {
				%>
				<div class="messageErreur">
					<a>Ce pseudo n'est pas disponible</a>
				</div>
				<%
				}
				}
				%>
			</div>
			<div class="col">
				<label> Nom : </label><input type="text" name="nom"
					value="${utilisateur.nom }"> <br>
			</div>
		</div>

		<div class="row g-3">
			<div class="col">
				<label> Prénom : </label><input type="text" name="prenom"
					value="${utilisateur.prenom }"><br>
			</div>
			<div class="col">
				<label> Email : </label><input type="text" name="email"
					value="${utilisateur.email}"> <br>
				<%
				if (request.getAttribute("emailUniqueOK") != null) {
					boolean emailUniqueOK = (boolean) request.getAttribute("emailUniqueOK");
					if (!emailUniqueOK) {
				%>
				<div class="messageErreur">
					<a>Cet email est deja utilisé par un compte utilisateur</a>
				</div>
				<%
				}
				}
				%>
			</div>
		</div>

		<div class="row g-3">
			<div class="col">
				<label> Téléphone : </label><input type="text" name="telephone"
					value="${utilisateur.telephone }"> <br>
			</div>
			<div class="col">
				<label> Rue : </label><input type="text" name="rue"
					value="${utilisateur.rue }"> <br>
			</div>
		</div>

		<div class="row g-3">
			<div class="col">
				<label> Code Postal : </label><input type="text" name="codePostal"
					value="${utilisateur.codePostal }"> <br>
			</div>
			<div class="col">
				<label> Ville : </label><input type="text" name="ville"
					value="${utilisateur.ville}"> <br>
			</div>
		</div>

		<div class="row g-3">
			<div class="col">
				<label> Mot de passe : </label><input type="text" name="AncienmotDePasse"
					value="${utilisateur.motDePasse }"> <br>
			</div>
			<div class="col">
				<label> Confirmation : </label><input type="text"
					name="AncienmotDePasse" value="">
				<br>
			</div>
		</div>


		<div class="row g-3">
			<div class="col">

				<button type="submit" value="ModifierCompte" name="choix"
					style="text-align: center;">Modifier</button>
			</div>


			
		</div>
		
	</form>
	<form action="<%=request.getContextPath()%>/SupprimerCompte"
		method="post">
			<div class="col">

				<input type="checkbox" value="ModifierCompte" name="supprimer" required>
					<label>Cocher la case pour confirmer la suppression du compte</label>
			</div>
		<div class="col">
				<button type="submit" name="choix" value="supprimer">Supprimer</button>
			</div>
		</form>



</body>

<jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>
</html>