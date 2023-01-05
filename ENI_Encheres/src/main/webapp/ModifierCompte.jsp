<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>


<body>

	<%@ include file="WEB-INF/fragments/HeaderAutre.jsp"%>

	<h1>Mon profil</h1>


	<form action="<%=request.getContextPath()%>/ModifierCompte"
		method="post">
		<input type="hidden" name="id" value="${utilisateur.no_utilisateur }">
		<input type="hidden" name="credit" value="${utilisateur.credit }">
<div class="vente">
		
			<div>
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
			
			<div>
				<label> Nom : </label><input type="text" name="nom"
					value="${utilisateur.nom }"> <br>
			</div>
		

		
			<div>
				<label> Prénom : </label><input type="text" name="prenom"
					value="${utilisateur.prenom }"><br>
			</div>
			<div>
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
		

		
			<div>
				<label> Téléphone : </label><input type="text" name="telephone"
					value="${utilisateur.telephone }"> <br>
			</div>
			
			<div>
				<label> Rue : </label><input type="text" name="rue"
					value="${utilisateur.rue }"> <br>
			</div>
		

		
			<div>
				<label> Code Postal : </label><input type="text" name="codePostal"
					value="${utilisateur.codePostal }"> <br>
			</div>
			
			<div>
				<label> Ville : </label><input type="text" name="ville"
					value="${utilisateur.ville}"> <br>
			</div>
		

		<div>
				<label> Mot de passe : </label><input type="text" name="AncienmotDePasse"
					value="${utilisateur.motDePasse }"> <br>
			</div>
			
			<div>
				<label> Confirmation : </label><input type="text"
					name="AncienmotDePasse" value="">
				<br>
			</div>



			<div>

				<button class="btn" type="submit" value="ModifierCompte" name="choix"
					">Modifier</button>
			</div>


			
		</div>
		
	</form>
	
	
	<div class="vente">
	<form action="<%=request.getContextPath()%>/SupprimerCompte"
		method="post">
			<div>

				<input type="checkbox" value="ModifierCompte" name="supprimer" required>
					<label>Cocher la case pour confirmer la suppression du compte</label>
			</div>
			
		<div>
				<button class="btn" type="submit" name="choix" value="supprimer">Supprimer</button>
			</div>
			
			
		</form>

</div>

</body>

<jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>
</html>