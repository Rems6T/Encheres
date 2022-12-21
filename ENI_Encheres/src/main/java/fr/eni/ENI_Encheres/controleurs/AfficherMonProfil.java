package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;

import fr.eni.ENI_Encheres.bo.Utilisateur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AfficherProfil
 */
@WebServlet("/AfficherMonProfil")
public class AfficherMonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute("ConnectedUser");
		request.setAttribute("utilisateur", user);
		boolean ok = true;
		request.setAttribute("ok", ok);
		RequestDispatcher rd = request.getRequestDispatcher("/PageProfil.jsp");
		rd.forward(request, response); 
		
		

	}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	}}


