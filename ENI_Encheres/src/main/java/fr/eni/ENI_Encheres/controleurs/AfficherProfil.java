package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;

import fr.eni.ENI_Encheres.bo.Utilisateur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AfficherProfil
 */
public class AfficherProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageProfil.jsp");
		rd.forward(request, response); 
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //On récupère les infos de l'user connecté
		HttpSession sessionUser = request.getSession();
		Utilisateur user = (Utilisateur) sessionUser.getAttribute("utilisateur");
		request.setAttribute("user", true);
		request.setAttribute("profil", user);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/PageProfil.jsp");
		rd.forward(request, response);
	}
	}


