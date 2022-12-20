<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>


<body class="container">

	<%@ include file="./fragments/header.html"%>

	    <c:if test="${user}">   
	   <h1>Mon profil</h1> 
    </c:if>


	<p>
		Pseudo : ${utilisateur.pseudo }<br> 
		</p>
		
		    <c:if test="${user}">   
	<p>	Nom : ${utilisateur.nom }"> <br>
		Prénom :${utilisateur.prenom } <br> 
		Téléphone : ${utilisateur.telephone } <br> 
		Rue : ${utilisateur.rue } <br>
		Code Postal : ${utilisateur.codePostal } <br> 
		Ville : ${utilisateur.ville} <br> </p>  
	

	<div>
		<a class="btn" href="${pageContext.request.contextPath}/ModifierCompte"
			title="Modifier votre profil"><i class="material-icons">Modifier</i></a>
	</div>
</c:if>



</body>

<jsp:include page="/WEB-INF/fragments/footer.html"></jsp:include>
</html>