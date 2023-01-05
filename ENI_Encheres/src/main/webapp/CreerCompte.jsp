<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>


<body>

	<%@ include file="/WEB-INF/fragments/HeaderAutre.jsp"%>
	<main>

	<h1>Mon profil</h1>


	<form action="<%=request.getContextPath()%>/CreerCompte" method="post">

		<div class="vente">
			<div>
				<label for="pseudo">Pseudo : <input type="text" id="pseudo"
					name="pseudo" pattern="[a-zA-Z0-9]{2,20}"/></label>

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
				%> </div>
			<div ><label for="nom">Nom :  <input type="text" id="nom"
							name="nom" required /></label> </div>
		

		
			<div><label for="prenom">prenom : <input type="text" id="prenom"
					name="prenom" required/></label>  </div>
					
					
			<div ><label for="mail">mail : <input type="text" id="mail"
							name="mail" required/></label>
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
						%></div>

		
			<div>
				<label for="telephone">telephone :  <input type="text"
					id="telephone" name="telephone" required/> </label>
			</div>
			<div>
				<label for="rue">Rue :  <input type="text" id="rue"
							name="rue" required/></label> 
			</div>
		

		
			<div>
				<label for="codePostal">Code Postal :  <input type="text"
							id="codePostal" name="codePostal" required/></label>
				
			</div>
			<div>
				<label for="ville">ville : <input type="text" id="ville"
							name="ville" required/> </label> 
			</div>
		

		
			<div>
				<label for="motDePasse"> Mot de passe : <input type="text" id="motDePasse" name="motDePasse" required></label>
			 	
			</div>
			<div>
				<label for="motDePasse_confirm"> Confirmation :<input type="text"
					name="motDePasse_confirm" id="motDePasse_confirm" required> </label> 
			</div>
		

						<div>
						<button class ="btn double" type="submit"  value="CréerCmpte">Valider</button>				
		
					<a href="<%=request.getContextPath()%>/Accueil">
						<button class="btn double " type="button">Annuler</button>
					</a>
				</div>
		
			</div>

	</form>


</main>
</body>

<jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>

</html>