<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/fragments/head.jsp"></jsp:include>
<body>
	<header>
		<div>

	<%@ include file="/WEB-INF/fragments/headerConnecte.jsp"%>
	</div>
	</header>

	
	<main>
    <div class="content">
        <h1>Bienvenue sur la boutique</h1>

        <h2>Il vous reste <c:out value="${ConnectedUser.credit }"/> points</h2> <br>
        
        <h2>Combien souhaitez-vous acheter ?</h2>
        <form action="${pageContext.request.contextPath}/boutique" method="post">
            <fieldset>
                <div class="inputField">
                    <label for="credit"></label>
                    <input type="text" name="newCredit" id="credit" placeholder="Combien de crédit?" 
                           required/>
                </div>
            </fieldset>
            <div class="submit">
                <button class="btn" type="submit">Acheter</button>
            </div>
        </form>
        <a href="${pageContext.request.contextPath}/Accueil"><button class="btn ">Retourner à l'Accueil
			</button></a>
    </div>
</main>
<jsp:include page="/WEB-INF/fragments/footer.jsp"></jsp:include>
</body>
</body>
</html>