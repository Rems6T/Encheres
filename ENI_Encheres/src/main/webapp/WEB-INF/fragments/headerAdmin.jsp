<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="header1">

	<div class="logo">
<a href="<%=request.getContextPath()%>/Accueil"><img 
					src="<%=request.getContextPath()%>/Images/logo1.png"
					alt="logo" height=100px ></a> </div>
			<nav>
				<ul>
				<li><a  href="<%=request.getContextPath()%>/ProfilAdmin"> Profil </a>
                <li><a  href="<%=request.getContextPath()%>/CategorieAdmin"> Categories </a> </li>
                <li ><a href="<%=request.getContextPath()%>/Logout">Deconnexion  </a></li>
                
                				</ul>
			</nav> 
</div>
 
       
</body>
</html>