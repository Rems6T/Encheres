package fr.eni.ENI_Encheres.controleursAdmin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.Utilisateur;

/**
 * Servlet implementation class supprimer
 */
@WebServlet("/supprimer")
public class Supprimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{
			int id;
			id=Integer.parseInt(request.getParameter("id"));
			UtilisateurManager mger;
			try {
				mger = new UtilisateurManager();
				//on recupere l'utilisateur grace a son no_utilisateur
			Utilisateur u = mger.getUtilisateurById(id);
			mger.supprimerDefUtilisateur(u);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			response.sendRedirect("ProfilAdmin");
		}
	}

	
}
