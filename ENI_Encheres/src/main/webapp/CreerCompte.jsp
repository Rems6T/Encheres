<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>


<body class="container">

	<%@ include file="WEB-INF/fragments/header.html"%>

	<h1>Mon profil</h1>

	<form method="post"  action="<%= request.getContextPath() %>/CreerCompte" >
	
		<div class="row g-3">
			<div class="col">
				<label> Pseudo : </label><input type="text" name="pseudo"> <br>
			</div>
			<div class="col">
				<label> Nom : </label><input type="text" name="nom"> <br>
			</div>
		</div>

		<div class="row g-3">
			<div class="col">
				<label> Prénom : </label><input type="text" name="prenom"> <br>
			</div>
			<div class="col">
				<label> Email : </label><input type="text" name="email"> <br>
			</div> </div>
			
					<div class="row g-3">
			<div class="col">
				<label> Téléphone : </label><input type="text" name="telephone"> <br>
			</div>
			<div class="col">
				<label> Rue : </label><input type="text" name="rue"> <br>
			</div> </div>
			
								<div class="row g-3">
			<div class="col">
				<label> Code Postal : </label><input type="text" name="codePostal"> <br>
			</div>
			<div class="col">
				<label> Ville : </label><input type="text" name="ville"> <br>
			</div> </div>
			
			<div class="row g-3">
			<div class="col">
				<label> Mot de passe : </label><input type="text" name="motDePasse"> <br>
			</div>
			<div class="col">
				<label> Confirmation : </label><input type="text" name="motDePasse_confirm"> <br>
			</div> </div>
			
			
			<div class="row g-3">
			<div class="col">
    <button type="submit" class="btn btn-primary"name="action" value="/creer">Créer</button>
			</div>
			<div class="col">
    <button type="submit" class="btn btn-primary"name="action" value="/annuler">Annuler</button>
			</div> </div>
			
	</form>		



</body>

<jsp:include page="/WEB-INF/fragments/footer.html"></jsp:include>

</html>