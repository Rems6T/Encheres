<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
 
       <nav class="navbar navbar-light  " >
            <a class="nav-link active m-2" aria-current="page"  >ENI Enchères</a>
            <ul class="nav justify-content-end "  > 
								
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/ProfilAdmin"> Profil </a>
                </li>
                
                 
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/CategorieAdmin"> Categories </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=request.getContextPath()%>/Logout">Deconnexion  </a>
                </li>
                
            </ul>
        </nav></header>
</body>
</html>