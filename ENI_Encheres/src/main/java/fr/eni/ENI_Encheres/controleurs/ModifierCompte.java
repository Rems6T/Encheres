package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;

import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Modifier
 */
@WebServlet("/ModifierCompte")
public class ModifierCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		int id;
		Utilisateur user;
		id=Integer.parseInt(request.getParameter("id"));
		user=UtilisateurManager.getInstance().findById(id);
		request.setAttribute("utilisateur", user);
		getServletContext().getRequestDispatcher("/WEB-INF/ModifierCompte.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 			int id;
	Utilisateur user;
	id=Integer.parseInt(request.getParameter("id"));
	user=UtilisateurManager.getInstance().findById(id);	
		
		if(request.getServletPath().equals("/enregistrer"))
	{
			user.setPseudo(request.getParameter("pseudo"));
			user.setNom(request.getParameter("nom"));
			user.setPrenom(request.getParameter("prenom"));
			user.setEmail(request.getParameter("email"));
			user.setTelephone(request.getParameter("telephone"));
			user.setRue(request.getParameter("rue"));
			user.setCodePostal(request.getParameter("codePostal"));
			user.setVille(request.getParameter("ville"));
			user.setMotDePasse(request.getParameter("motDePasse"));
			UtilisateurManager.getInstance().modifier(user);
			response.sendRedirect("Accueil"); }

		
		else if(request.getServletPath().equals("/supprimer")) {
			UtilisateurManager.getInstance().supprimer(user);
			response.sendRedirect("Accueil");
		}
		
		
	}
		
	

{
