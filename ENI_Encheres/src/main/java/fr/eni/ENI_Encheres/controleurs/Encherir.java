package fr.eni.ENI_Encheres.controleurs;

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
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Encherir
 */
@WebServlet("/Encherir")
public class Encherir extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int noArticle = Integer.valueOf(request.getParameter("noArticle"));
		// on recupere l'article a affiché
		ArticleVendu articleAffiche = null;
		try {
			ArticleManager aMger = new ArticleManager();
			articleAffiche = aMger.getArticleById(noArticle);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("articleAffiche", articleAffiche);
		// on recupere le retrait
		Retrait retrait = null;
		RetraitJdbcImpl rMger = new RetraitJdbcImpl();
		try {
			retrait = rMger.selectById(noArticle);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("retraitVente", retrait);
		// on recupere le vendeur
		Utilisateur vendeur = null;
		try {
			UtilisateurManager uMger = new UtilisateurManager();
			vendeur = uMger.getUtilisateurById(articleAffiche.getNoUtilisateur());
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("vendeur", vendeur);
		// on recupere l'enchere
		Encheres enchere = null;
		EnchereJdbcImpl eMger = new EnchereJdbcImpl();

		List<Encheres> enchereList;
		try {
			enchereList = eMger.selectAllByArticle(noArticle);

			enchere = enchereList.get(enchereList.size() - 1);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("enchere", enchere);
		// on recupere le pseudo pour l'enchere
		Utilisateur u = null;
		int noU = enchere.getNoUtilisateur();
		try {
			UtilisateurManager uMger = new UtilisateurManager();
			u = uMger.getUtilisateurById(noU);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("enchereU", u);

		// on redirige vers la page de l'enchere
		request.getRequestDispatcher("Encherir.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		EnchereJdbcImpl eMger = new EnchereJdbcImpl();
		UtilisateurManager uMger = null;
		try {
			uMger = new UtilisateurManager();
		} catch (BLLException e1) {

			e1.printStackTrace();
		}
		// On recupere le montant de la nouvelle enchere
		int newMontant = Integer.valueOf(request.getParameter("mPrix"));

		// On recupere l'utlisateur connecte
		Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("ConnectedUser");
		// Pas de check si il a le pognon c'est fait dans le formulaire
	
		// On recupere la derniere enchere
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));
		Encheres enchere = null;
		List<Encheres> enchereList;
		try {
			enchereList = eMger.selectAllByArticle(noArticle);
			enchere = enchereList.get(enchereList.size() - 1);
		} catch (DALException e) {

			e.printStackTrace();
		}
		// On rend l'argent a l'ancien encherisseur
		// Si ce n'est pas le vendeur
		// On recup le numero du vendeur
		int noVendeur = Integer.valueOf(request.getParameter("noVendeur"));

		if (enchere.getNoUtilisateur() != noVendeur) {
			// On recupere l'ancien encherisseur
			Utilisateur ancienEncherisseur;
			try {

				ancienEncherisseur = uMger.getUtilisateurById(enchere.getNoUtilisateur());

				// On lui rend l'argent
				int argentRendu = ancienEncherisseur.getCredit() + enchere.getMontantEnchere();
				ancienEncherisseur.setCredit(argentRendu);
				// et on le modifie en base de données
				uMger.modifierUtilisateur(ancienEncherisseur);

			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		// On modifie l'enchere
		// On indique le nouvelle encherisseur
		enchere.setNoUtilisateur(utilisateurConnecte.getNo_utilisateur());
		// On indique le nouveau montant
		enchere.setMontantEnchere(newMontant);

		// On regarde si il a deja fait une enchere
		
		Encheres enchereUtil = null;
		try {
			
			enchereUtil = eMger.selectByUtil(utilisateurConnecte.getNo_utilisateur());
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (enchereUtil!=null) {
			// si deja une enchere, On la supprime
			try {
				eMger.delete(enchereUtil);

			} catch (DALException e) {
				e.printStackTrace();
			}
		} 
		//On l'ajoute en base 
		try {
			eMger.insert(enchere);
		} catch (DALException e1) {

			e1.printStackTrace();
		}
		// On enleve le pognon aux nouvelle encherisseur

		int argentPris = utilisateurConnecte.getCredit() - newMontant;
		utilisateurConnecte.setCredit(argentPris);
		// On le change en base de données
		try {
			uMger.modifierUtilisateur(utilisateurConnecte);

		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// On recupere l'article et on modifie son prix de vente
		try {
			ArticleManager aMger = new ArticleManager();
			ArticleVendu article = aMger.getArticleById(noArticle);
			article.setPrixVente(newMontant);
			//On le save en base
			aMger.modifierArticle(article);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// On redirige vers la page d'accueil
		request.getRequestDispatcher("/Accueil").forward(request, response);

	}


}
