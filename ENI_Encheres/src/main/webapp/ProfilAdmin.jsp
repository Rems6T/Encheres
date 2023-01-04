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
			<th>pseudo</th>
			<th>nom</th>
			<th>prenom</th>
			<th>email</th>
			
			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${utilisateurList}" var="u">
		<c:choose>
		<c:when test="${u.pseudo == 'admin' }"></c:when>
		<c:otherwise>
			<tr>
				<td>${u.pseudo}</td>
				<td>${u.nom }</td>
				<td>${u.prenom }</td>
				<td>${u.email}</td>
				
				
				
				
			<td><a href="modifierVenteAdmin?id=${u.no_utilisateur}">Voir les ventes</a></td>		
			<td><a href="supprimer?id=${u.no_utilisateur}">supprimer</a></td>
						

			</tr>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			</table>
</body>
</html>