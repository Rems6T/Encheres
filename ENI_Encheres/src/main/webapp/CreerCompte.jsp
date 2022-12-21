<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>


<body class="container">

	<%@ include file="WEB-INF/fragments/header.html"%>

	<h1>Mon profil</h1>


	<form action="<%=request.getContextPath()%>/CreerCompte" method="post">

		<div class="row g-3">
			<div class="col">
				<label for="pseudo">Pseudo :</label> <input type="text" id="pseudo"
					name="pseudo" />

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
				<label for="nom">Nom :</label> <input type="text" id="nom"
							name="nom" /> <br>
			</div>
		</div>

		<div class="row g-3">
			<div class="col">
				<label for="prenom">prenom :</label> <input type="text" id="prenom"
					name="prenom" /> <br>
			</div>
			<div class="col">
<label for="mail">mail :</label> <input type="text" id="mail"
							name="mail" />
						<%
							if (request.getAttribute("emailUniqueOK")!=null){
								boolean emailUniqueOK = (boolean) request.getAttribute("emailUniqueOK");
								if (!emailUniqueOK){
						%>
						<div class="messageErreur">
							<a>Cet email est deja utilisé par un compte utilisateur</a>
						</div>
						<%
								}
							}
						%>
		</div></div>

		<div class="row g-3">
			<div class="col">
				<label for="telephone">telephone :</label> <input type="text"
					id="telephone" name="telephone" /> <br>
			</div>
			<div class="col">
				<label for="rue">Rue :</label> <input type="text" id="rue"
							name="rue" /> <br>
			</div>
		</div>

		<div class="row g-3">
			<div class="col">
				<label for="codePostal">Code Postal :</label> <input type="text"
							id="codePostal" name="codePostal" />
				<br>
			</div>
			<div class="col">
				<label for="ville">ville :</label> <input type="text" id="ville"
							name="ville" /> <br>
			</div>
		</div>

		<div class="row g-3">
			<div class="col">
				<label for="motDePasse"> Mot de passe : </label><input type="text" id="motDePasse" name="motDePasse">
				<br>
			</div>
			<div class="col">
				<label for="motDePasse_confirm"> Confirmation : </label><input type="text"
					name="motDePasse_confirm" id="motDePasse_confirm"> <br>
			</div>
		</div>


		<div class="row g-3">
		
						<div class="col">
						<input type="submit" value="CréerCmpte">
				</div>

				<div class="col">
					<a href="<%=request.getContextPath()%>/Accueil.jsp">
						<button type="button">Annuler</button>
					</a>
				</div>
		
			</div>

	</form>



</body>

<jsp:include page="/WEB-INF/fragments/footer.html"></jsp:include>

</html>