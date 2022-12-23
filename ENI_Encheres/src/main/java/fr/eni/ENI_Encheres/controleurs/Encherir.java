package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;
import java.util.Date;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.EnchereManager;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Encheres;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Encherir
 */
@WebServlet("/Encherir")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.getRequestDispatcher("/Encherir.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser");
		int currentCredit = connectedUser.getCredit();
		
		int prixEnchere = Integer.parseInt(request.getParameter("mPrix"));
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		
		ArticleVendu articleAffiche = null;
		
			try {
				articleAffiche = ArticleManager.getArticleById(idArticle);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		Date date = new Date();
		if (articleAffiche != null && prixEnchere > articleAffiche.getMiseAPrix()
				&& connectedUser.getCredit() >= prixEnchere) {
			Encheres enchere = new Encheres(articleAffiche.getNoArticle() , connectedUser.getNo_utilisateur(), (java.sql.Date) date, prixEnchere);
			
			articleAffiche.setPrixVente(prixEnchere);
			
			
			
				try {
					EnchereManager.ajoutEnchere(enchere);
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("mPrix", prixEnchere);
				request.setAttribute("meilleureEnchere", enchere);
				try {
					ArticleManager.modifierArticle(articleAffiche);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				connectedUser.setCredit(currentCredit - prixEnchere);
				try {
					UtilisateurManager.modifierUtilisateur(connectedUser);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			request.getRequestDispatcher("/WEB-INF/jsp/accueilConnected.jsp").forward(request, response);
		}
		
	}

}
