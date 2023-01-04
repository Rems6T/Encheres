package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;

import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boutique")
public class Boutique extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        request.getRequestDispatcher("/Boutique.jsp").forward(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 UtilisateurManager utilisateurManager = null;
		try {
			utilisateurManager = new UtilisateurManager();
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	        Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("ConnectedUser");

	        Integer credit = Integer.valueOf(request.getParameter("newCredit"));

	        utilisateur.setCredit(credit + utilisateur.getCredit());
	        
	            try {
					utilisateurManager.modifierUtilisateur(utilisateur);
					request.getRequestDispatcher("/Accueil").forward(request, response);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	            }		
	}


