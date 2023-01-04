<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<body>
	<header>
		<%@include file="WEB-INF/fragments/headerAdmin.jsp"%>
	</header>
<body>
			<h3>Modifier categorie</h3>


	<form action="<%=request.getContextPath()%>/ModifierCategorie"
		method="post">
		<input type="hidden" name="id" value="${categorie.noCategorie }">
		<label for="libelle">Nom :</label> <input type="text" id="libelle"
			name="libelle" value="${categorie.libelle }" />
		<button type="submit" value="ModifierCategorie">Valider</button>
	</form>
</body>
</html>