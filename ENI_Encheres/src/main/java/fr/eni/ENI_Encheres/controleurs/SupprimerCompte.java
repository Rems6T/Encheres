package fr.eni.ENI_Encheres.controleurs;

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
 * Servlet implementation class SupprimerCompte
 */
@WebServlet("/SupprimerCompte")
public class SupprimerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimerCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On recuper l'utilisateur connecté sur la session
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("ConnectedUser");
	
		//On supprime en Bll l'utilisateur
		try {
			UtilisateurManager mger = new UtilisateurManager();
			mger.supprimerUtilisateur(utilisateur);
			System.out.println("utilisateur supprimé :"+utilisateur);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//On retire l'utilisateur de la session
		request.getSession().removeAttribute("ConnectedUser");
		//On redirige vers la page d'accueil
		request.getRequestDispatcher("/Accueil").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
