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

	<table border="1">
		<tr>
			<th>Nom catégorie</th>
			
			
			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${categorieList}" var="cat">

			<tr>
				<td>${cat.libelle}</td>
				
				
				
				
				
					
			<td><a href="SupprimerCategorie?id=${cat.noCategorie}">supprimer</a></td>
			<td><a href="ModifierCategorie?id=${cat.noCategorie}">modifier</a></td>			

					
					
					
				

			</tr>
			</c:forEach>
			</table>
			<form action="<%=request.getContextPath()%>/CreerCategorie" method="post">
			<h3>Creer une nouvelle categorie</h3>
			<label for="libelle">Nom :</label> <input type="text" id="libelle"
					name="libelle" />
			<button type="submit"  value="CreerCategorie">Valider</button>
			</form>
</body>
</html>