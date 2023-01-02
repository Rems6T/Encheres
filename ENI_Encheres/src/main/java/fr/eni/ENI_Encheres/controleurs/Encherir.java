package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;
import java.util.Date;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.EnchereManager;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Encheres;
import fr.eni.ENI_Encheres.bo.EtatVente;
import fr.eni.ENI_Encheres.bo.Retrait;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.RetraitJdbcImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Encherir
 */
@WebServlet("/EnchereArticle")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
		//on recupere un article 
		ArticleVendu articleAffiche = null;
		System.out.println(idArticle);
		try {
			articleAffiche = ArticleManager.getArticleById(idArticle);
			System.out.println(articleAffiche);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("articleAffiche", articleAffiche);
		//on recupere le retrait associe
		Retrait retraitVente = null;
		RetraitJdbcImpl retraitJdbcImpl = new RetraitJdbcImpl();
		try {
			retraitVente = retraitJdbcImpl.selectById(idArticle);
			System.out.println(retraitVente);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("retraitVente", retraitVente);
		// on recupere le vendeur grace aux no_utlisateur de l'article
		Utilisateur vendeur = null;
		System.out.println(articleAffiche.getNoUtilisateur());
		int idUtilisateur =articleAffiche.getNoUtilisateur();
		UtilisateurManager uMger = null;
		try {
			uMger = new UtilisateurManager();
			vendeur = uMger.getUtilisateurById(idUtilisateur);
			System.out.println(vendeur);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("vendeur", vendeur);
		//on envoie le user de la session
	Utilisateur userSession = (Utilisateur) request.getSession().getAttribute("ConnectedUser");
		request.setAttribute("userSession", userSession);
		
		request.getRequestDispatcher("/Encherir.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//		HttpSession session = request.getSession();
//		Utilisateur connectedUser = (Utilisateur) session.getAttribute("ConnectedUser");
//		int currentCredit = connectedUser.getCredit();
//		
//		int prixEnchere = Integer.parseInt(request.getParameter("mPrix"));
//		int idArticle = Integer.parseInt(request.getParameter("idArticle"));
//		
//		ArticleVendu articleAffiche = null;
//		
//			try {
//				articleAffiche = ArticleManager.getArticleById(idArticle);
//			} catch (BLLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		Date date = new Date();
//		if (articleAffiche != null && prixEnchere > articleAffiche.getMiseAPrix()
//				&& connectedUser.getCredit() >= prixEnchere) {
//			Encheres enchere = new Encheres(articleAffiche.getNoArticle() , connectedUser.getNo_utilisateur(), (java.sql.Date) date, prixEnchere);
//			
//			articleAffiche.setPrixVente(prixEnchere);
//			
//			
//			
//				try {
//					EnchereManager.ajoutEnchere(enchere);
//				} catch (DALException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				request.setAttribute("mPrix", prixEnchere);
//				request.setAttribute("meilleureEnchere", enchere);
//				try {
//					ArticleManager.modifierArticle(articleAffiche);
//				} catch (BLLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				connectedUser.setCredit(currentCredit - prixEnchere);
//				try {
//					UtilisateurManager.modifierUtilisateur(connectedUser);
//				} catch (BLLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		article.setEtatVente(EtatVente.EN_COURS);
//			request.getRequestDispatcher("/WEB-INF/jsp/accueilConnected.jsp").forward(request, response);
		}
		
	}


