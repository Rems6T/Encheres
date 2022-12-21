package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;
import java.io.PrintWriter;

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
import jakarta.servlet.http.HttpSession;

@WebServlet("/Connexion")

public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Connexion.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Connexion.jsp");
		PrintWriter out= response.getWriter();
		
		HttpSession session = request.getSession();
		
		String erreur = null;
		
		String identifiant = request.getParameter("pseudo");
		String password = request.getParameter("mdp");
		
		
		if(identifiant.length()==0 || identifiant.isEmpty()){
				
			//cr�ation de l'erreur
			request.setAttribute("erreur", "pseudo non renseigné. Veuillez le saisir...");
			erreur = (String) session.getAttribute("erreur");
			out.println(erreur);
			
			//redirection vers la page de connexion pour saisir le login
			this.getServletContext().getRequestDispatcher("/Connexion").forward(request, response);
			
			
		}else if(password.length()==0 || password.isEmpty()) {
				
			//cr�ation de l'erreur
			request.setAttribute("erreur", "mot de passe non renseign�. Veuillez le saisir...");
			erreur = (String) session.getAttribute("erreur");
			out.println(erreur);
			//redirection vers la page de connexion pour saisir le login
			this.getServletContext().getRequestDispatcher("/Connexion").forward(request, response);
			
		}else {
			
			try {
		
				//Valider pseudo utilisateur, verification si il est bien dans la bdd
				Utilisateur user = UtilisateurManager.selectByPseudo(identifiant);
				//Si la connexion est reussie
				if(user!= null && password.equals(user.getMotDePasse())) {
					request.getSession().setAttribute("ConnectedUser", user);
					
					this.getServletContext().getRequestDispatcher("/Accueil").forward(request, response);
					
				} else {
					request.setAttribute("erreur", "pseudo et/ou mot de passe incorrect(s)! Veuillez ressaisir vos identifiants...");
					erreur = (String) session.getAttribute("erreur");
					out.println(erreur);
					this.getServletContext().getRequestDispatcher("/Connexion").forward(request, response);
				}
			} catch (BLLException | DALException e) {
				request.setAttribute("erreur", e);
				this.getServletContext().getRequestDispatcher("/ServletErreurPage").forward(request, response);
			}
		
		}

	}
}
