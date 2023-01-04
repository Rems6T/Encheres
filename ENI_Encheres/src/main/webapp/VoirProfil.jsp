<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.ENI_Encheres.bo.ArticleVendu"%>
<%@page import="fr.eni.ENI_Encheres.bo.Utilisateur"%>
<%@page import="fr.eni.ENI_Encheres.jdbcImpl.UtilisateurDAOJdbcImpl"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>

<body>
	<header>
		<div>
		
<!--  -->
					<% ArticleVendu article = (ArticleVendu)request.getAttribute("ArticleAffiche"); %>
				
				<%  if (request.getSession().getAttribute("ConnectedUser")==null){ %>		
				<%@include file="WEB-INF/fragments/headerInvite.jsp"%>
	<%} else {%>
		<%@include file="WEB-INF/fragments/headerConnecte.jsp"%>
	<%} %>
		</div>
	</header>
	<div class="profil " style="text-align: center;">
            
            		<h2>Profil de l'utilisateur</h2>

                    <p>Pseudo :   ${utilisateur.pseudo} </p>
                    <p>nom    :    ${utilisateur.nom}</p>
                    <p>prenom :   ${utilisateur.prenom }</p>
                    <p>email  :   ${utilisateur.email }</p>
                    <p>telephone: ${utilisateur.telephone }</p>
                    <p>rue  :     ${utilisateur.rue} </p>
                    <p>codePostal:${utilisateur.codePostal}</p>
                    <p>ville  :   ${utilisateur.ville }</p>
                            
           
        </div>

</body>
</html>