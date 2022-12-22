package fr.eni.ENI_Encheres.controleursAdmin;

import java.io.IOException;
import java.util.List;

import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ProfilAdmin
 */
@WebServlet("/ProfilAdmin")
public class ProfilAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("utilisateur", session.getAttribute("utilisateur"));
		UtilisateurManager mger = null;
		try {
			mger = new UtilisateurManager();
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Utilisateur> utilisateurList = null;
		try {
			utilisateurList = mger.getAllUtilisateur();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("utilisateurList", utilisateurList);

		request.getRequestDispatcher("ProfilAdmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("utilisateur", session.getAttribute("utilisateur"));
		UtilisateurManager mger = null;
		try {
			mger = new UtilisateurManager();
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Utilisateur> utilisateurList = null;
		try {
			utilisateurList = mger.getAllUtilisateur();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("utilisateurList", utilisateurList);

		request.getRequestDispatcher("ProfilAdmin.jsp").forward(request, response);
	}

}
