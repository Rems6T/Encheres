<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="fr.eni.ENI_Encheres.bo.Encheres"%>
<%@page import="fr.eni.ENI_Encheres.bo.Utilisateur"%>

<%@page import="fr.eni.ENI_Encheres.bo.ArticleVendu"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>




<body class="container">

	<%@ include file="/WEB-INF/fragments/header.html"%>


    <div class="container-vente">
      <div class="encherisseur">
          <% Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser"); %>
      <% if(connectedUser.getPseudo().equals(gagnantEnchere.getPseudo())) { %>
        <h1>Vous avez remporté la vente</h1>
       <% } else {%>
        <h1><%=gagnantEnchere.getPseudo() %> a remporté la vente</h1>
       <% } %>
      </div>


        <div class="photo"></div>
        <div class="corpsVente">
          <p>${article.nomArticle}</p>
          <div class="left">
            <div>Description :</div>
            <div>Meilleure offre :</div>
            <div>Mise à prix :</div>
            <div>Fin de l'enchère :</div>
            <div>Retrait :</div>
            <div>Vendeur :</div>
          </div>
          <div class="right">
            <div>${article.description}</div>
            <div>${article.prixVentes} pts par ${article.acheteur.pseudo}</div>
            <div>${article.prixInitial}</div>
            <div>${article.dateFinEncheres}</div>
            <div>
              ${article.retrait.rue}<br />
              ${article.retrait.codePostal} ${article.retrait.ville}
            </div>
            <div>${article.vendeur.pseudo}</div>
            <div>${article.vendeur.telephone}</div>
          </div>
          <div class="bouton">
            <a href="#"><button>Retrait effectué</button></a>
            <a href="#"><button>Back</button></a>
          </div>
        </div>
     

    </div>
  </body>
</html>
