<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<body>
	<header>
		<%@include file="WEB-INF/fragments/headerConnecte.jsp"%>
	</header>
<body>

<h1>Mot de passe oublié</h1>
<form action="<%=request.getContextPath()%>/EmailMdpOublier" method="post">
	<label for="email">Adresse mail : <input type="text" id="email"
			name="email">
		</label> 
		<button type="submit"  value="MdpOublier">Valider</button>

</form>
</body>
</html>