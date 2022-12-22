<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="fr.eni.ENI_Encheres.bo.Utilisateur"%>
		<%@page import="fr.eni.ENI_Encheres.bo.ArticleVendu"%>
				<%@page import="fr.eni.ENI_Encheres.bo.Encheres"%>
								<%@page import="fr.eni.ENI_Encheres.bo.Retrait"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>


<body class="container">

<div class="head">
      <h1>Détail Vente</h1>
    </div>
    <% Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser"); %>
	<% ArticleVendu article = (ArticleVendu)request.getAttribute("ArticleAffiche"); %>
    <div class="container">
        <div>
            <img src="" alt="">
        </div>
        <table>
       		 <tr>
                <td class="td1"><p class="value-td1">Nom:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getNomArticle() %></p></td>
            </tr>
            <tr>
                <td class="td1"><p class="value-td1">Description:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getDescription() %></p></td>
            </tr>
            <%if(article.getPrixVente() != 0) { %>
            <tr>
                <td class="td1"><p class="value-td1">Meilleure offre:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getPrixVente()%> pts </p></td>
            </tr>
            <% } %>
            <tr>
                <td class="td1"><p class="value-td1">Mise à prix:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getMiseAPrix()  %> pts</p></td>
            </tr>  
            <tr>
                <td class="td1"><p class="value-td1">Fin de l'enchère:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getDateFinEncheres() %></p></td>
            </tr>
            <tr>
                <td class="td1"><p class="value-td1">Retrait:</p></td>
                <td class="td2"><p class="value-td2"><%=article.Retrait.getRue()  %>, 
                <%=article.retrait.getCodePostal()%>, <%=article.retrait.getVille() %></p></td>
            </tr>
            <tr>
                <td class="td1"><p class="value-td1">Vendeur:</p></td>
                <td class="td2"><p class="value-td2"><%=article.getNoUtilisateur() %></p></td>
            </tr> 
        </table>
        
        
        
        
	<% if(!article.getNoUtilisateur().getPseudo().equals(connectedUser.getPseudo())) { %>
		<form action="<%=request.getContextPath()%>/encherir" method="post">
			<div class="input-field">
				<label for="mPrix">Ma Proposition :</label>
				<input class="input" type="number" name="mPrix" id="mPrix" step="1" max= "10000" required>
			</div>
		    <div>
		        <button class="btn-login" type="submit">Enchérir</button>
		    </div>
	      <input value="<%=article.getNoArticle()%>" type="text" id="idArticle" name="idArticle" style="visibility:hidden;">
		</form>
		
		
	<% } else { %>
		<a href ="<%=request.getContextPath()%>/updateVente?idArticle=<%=article.getNoArticle()%>"><button class="btn" type="button"> 
        Modifier
     	 </button>
     	 </a>
     	 

	<% } %>
	
	
      <a href ="<%=request.getContextPath()%>/Accueil.jsp"><button class="btn" type="button"> <!--change that with index.html file location-->
        Back
      </button>
      </a>
    </div>
  </body>
</html>