package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;

import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Connexion.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher("/Connexion.jsp");
		

		

		String identifiant = request.getParameter("pseudo");
		String password = request.getParameter("mdp");

		Utilisateur user;
		try {
			// Valider pseudo utilisateur, verification si il est bien dans la bdd
			user = UtilisateurManager.selectByPseudo(identifiant);
			// Si la connexion est reussie
			if (user != null && password.equals(user.getMotDePasse())) {
				request.getSession().setAttribute("ConnectedUser", user);

				//si admin se co redirection differente
				if(identifiant.equals("admin")) {
					request.getSession().setAttribute("ConnectedAdmin", user);
					this.getServletContext().getRequestDispatcher("/ProfilAdmin").forward(request, response);
				}else {
				//sinon redirection page d'accueil
				this.getServletContext().getRequestDispatcher("/Accueil.jsp").forward(request, response);
				}
			} else {
				String erreur ="pseudo et/ou mot de passe incorrect(s)! Veuillez ressaisir vos identifiants...";
				request.setAttribute("erreur", erreur);
				
				this.getServletContext().getRequestDispatcher("/Connexion.jsp").forward(request, response);
			}
		} catch (BLLException | DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
