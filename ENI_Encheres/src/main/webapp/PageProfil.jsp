<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="fr.eni.ENI_Encheres.bo.Utilisateur"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>


<body>

	<%@ include file="/WEB-INF/fragments/HeaderAutre.jsp"%>
<main>
<section>
        <div class="profil " style="text-align: center;">
            
            	<%Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur"); %>

                    <p>Pseudo :   ${utilisateur.pseudo} </p>
                    <p>nom    :    ${utilisateur.nom}</p>
                    <p>prenom :   ${utilisateur.prenom }</p>
                    <p>email  :   ${utilisateur.email }</p>
                    <p>telephone: ${utilisateur.telephone }</p>
                    <p>rue  :     ${utilisateur.rue} </p>
                    <p>codePostal:${utilisateur.codePostal}</p>
                    <p>ville  :   ${utilisateur.ville }</p>
                            
            <% if (request.getAttribute("ok") != null){
           		boolean ok = (boolean) request.getAttribute("ok");
            	if(ok){%>
            	<a href="<%=request.getContextPath()%>/ModifierCompte">
            <button class="btn" type="submit" value="modifier" name="modifier" style="text-align: center;" >Modifier</button>					
            	</a>
			<%}
            	}%>	
        </div>
    </section>
</main>
</body>

<jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>
</html>