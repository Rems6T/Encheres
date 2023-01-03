package fr.eni.ENI_Encheres.controleursAdmin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Encheres;
import fr.eni.ENI_Encheres.bo.Retrait;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.EnchereJdbcImpl;
import fr.eni.ENI_Encheres.jdbcImpl.RetraitJdbcImpl;

/**
 * Servlet implementation class modifierVenteAdmin
 */
@WebServlet("/modifierVenteAdmin")
public class modifierVenteAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// on recupere l'utilisateur
		int noUtil = Integer.valueOf(request.getParameter("id"));
		Utilisateur u = null;
		try {
			UtilisateurManager uMger = new UtilisateurManager();
			u = uMger.getUtilisateurById(noUtil);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		// on recupere tous les articles vendu par cet utilisateur
		List<ArticleVendu> ArticleList = null;
		try {
			ArticleManager aMger = new ArticleManager();
			ArticleList = aMger.getAllArticlebyUtilisateur(u);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		// on envoie l'utilisateur et sa liste d'article vendu
		request.setAttribute("ArticleList", ArticleList);
		request.setAttribute("u", u);
		// on redirige vers modifVenteAdmin
		request.getRequestDispatcher("ModifierVenteAdmin.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// on recupere l'utilisateur
		int noUtil = Integer.valueOf(request.getParameter("noUtil"));
		Utilisateur u = null;
		UtilisateurManager uMger = null;
		ArticleManager aMger = null;
		try {
			uMger = new UtilisateurManager();
			u = uMger.getUtilisateurById(noUtil);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		// on recupere tous les articles vendu par cet utilisateur
		List<ArticleVendu> ArticleList = null;
		try {
			 aMger = new ArticleManager();
			ArticleList = aMger.getAllArticlebyUtilisateur(u);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		// Pour Chaque article on recupere l'enchere
		EnchereJdbcImpl eMger = new EnchereJdbcImpl();
		for (ArticleVendu articleVendu : ArticleList) {
			try {
				Encheres enchere = eMger.selectById(articleVendu.getNoArticle());
				// On recupere l'utilisateur associé si ce n'est pas le vendeur
				if (articleVendu.getNoUtilisateur() != enchere.getNoUtilisateur()) {
					Utilisateur encherisseur = uMger.getUtilisateurById(enchere.getNoUtilisateur());
					// On lui rend l'argent
					int argentRendu = encherisseur.getCredit() + enchere.getMontantEnchere();
					encherisseur.setCredit(argentRendu);
					// on le save en bd
					uMger.modifierUtilisateur(encherisseur);
				}
				//on supprime l'enchere
				eMger.delete(enchere);
				//on recupere le retrait
				RetraitJdbcImpl rMger = new RetraitJdbcImpl();
				Retrait retrait = rMger.selectById(articleVendu.getNoArticle());
				//on le supprime
				rMger.delete(retrait);
				//Enfin on supprime l'article
				aMger.supprimerArticle(articleVendu);
			} catch (DALException | BLLException e) {
				e.printStackTrace();
			}
		}
		// on redirige vers ProfilAdmin
		request.getRequestDispatcher("ProfilAdmin").forward(request, response);
	}

}
