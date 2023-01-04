<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>

<body>
	<header>
		<div>

	<%@ include file="/WEB-INF/fragments/HeaderAutre.jsp"%>
	</div>
	</header>

	<!-- Formulaire de connexion -->

	<main>
		<div class="connection">
			<form class="login" action="<%=request.getContextPath() %>/Connexion" method="post">
				
					<label for="pseudo" >Identifiant : </label> <input
						type="text"  name="pseudo" required/>
			
				<br />
			
					<label for="motDePasse"> Mot de passe : </label> <input
						type="password"  name="mdp" required/>
				
				<%if (request.getAttribute("erreur")!=null){ 
				%>
				<p >pseudo et/ou mot de passe incorrect(s)! Veuillez ressaisir vos identifiants...</p>
				<%} %>
				<br />
				
				
						<input type="checkbox" name="remember" id="remember" /> <label
							for="remember">Se souvenir de moi</label> <br />  
			
					<br />
						<button class="btn" type="submit"  >Connexion</button>
					
					
							
							<a href="EmailMdpOublier"><button class="btn" >Mot
							de passe oublié</button></a>
							
							
	
					
				
			</form></div>
	
		
					<div class="connection">
			
				<a href="${pageContext.request.contextPath}/CreerCompte"><button class="btn large ">Créer un compte
			</button> </a>
				
		</div>
	</main>

</body>

<jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>

</html>
