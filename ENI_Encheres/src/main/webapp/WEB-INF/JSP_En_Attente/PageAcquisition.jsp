<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.ENI_Encheres.bo.Encheres"%>
<%@page import="fr.eni.ENI_Encheres.bo.Retrait"%>
<%@page import="fr.eni.ENI_Encheres.bo.Utilisateur"%>

<%@page import="fr.eni.ENI_Encheres.bo.ArticleVendu"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>




<body>


    
    <!--Head-->
    <div class="head">
      <h1>Vente Remportée</h1>
    </div>
    <%ArticleVendu article = (ArticleVendu)request.getAttribute("ArticleAffiche"); %>
    <% Encheres meilleureOffre = (Encheres)request.getAttribute("MeilleureOffre"); %>
	<% Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser"); %>
	<% Utilisateur gagnantEnchere = (Utilisateur) request.getAttribute("GagnantEnchere"); %>
			<% Retrait retraitArticle = (Retrait)request.getAttribute("retraitVente"); %>
	
	
    <div class="container-vente">
      <div class="encherisseur">
      <% if(connectedUser.getPseudo().equals(gagnantEnchere.getPseudo())) { %>
        <h1>Vous avez remporté la vente</h1>
       <% } else {%>
        <h1><%=gagnantEnchere.getPseudo() %> a remporté la vente</h1>
       <% } %>
      </div>
        
        <div class="top-container-vente">
          <div class="card-img-container">
              <img src="img/tournevis.jpeg" alt="">
          </div>
        </div>
        
        <table>
            <tr>
              <td class="td1"><p class="value-td1">Nom de l'article:</p></td>
              <td class="td2"><p class="value-td2"><%=article.getNomArticle() %></p></td>
            </tr>
            <tr>
                <td class="td1"><p class="value-td1">Description:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getDescription() %></p></td>
            </tr>
            
            
            <%if(article.getPrixVente() != 0) { %>
            <tr>
                <td class="td1"><p class="value-td1">Meilleure offre:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getPrixVente()%> pts par <%=meilleureOffre.getNoUtilisateur() %>  </p></td>
            </tr>
             <% } %>
            <tr>
                <td class="td1"><p class="value-td1">Mise à prix:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getMiseAPrix()%> pts </p></td>
            </tr> 
            <tr>
                <td class="td1"><p class="value-td1">Retrait:</p></td>
                <td class="td2"><p class="value-td2"><${retraitVente.rue }, 
                        ${retraitVente.code_postal }, ${retraitVente.ville }</p></td>
            </tr>
            <tr>
                <td class="td1"><p class="value-td1">Vendeur:</p></td>
                <td class="td2"><p class="value-td2">${article.NoUtilisateur}></p></td>
            </tr> 
            <tr>
              <td class="td1"><p class="value-td1">Téléphone:</p></td>
              <td class="td2"><p class="value-td2">${article.NoUtilisateur.telephone}></p></td>
          </tr> 
        </table>

        <div>
          <a href = "<%=request.getContextPath()%>/Accueil"><button class="btn-login" type="button"> 
            back
          </button></a>
        </div>

    </div>
</body>
</html>
