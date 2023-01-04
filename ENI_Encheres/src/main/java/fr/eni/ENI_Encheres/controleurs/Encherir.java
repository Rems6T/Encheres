package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.EnchereManager;
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
import jakarta.servlet.http.HttpSession;

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
		

		// on redirige vers la page de l'enchere
		request.getRequestDispatcher("Encherir.jsp").forward(request, response);


	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//        //On encherie
//				try {
//					encherir(request, response);
//				} catch (ServletException | IOException | BLLException | SQLException | DALException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				try {
//					preleverCredit(request, response);
//				} catch (BLLException | ServletException | IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	try {
//		updatePrixVenteArticle(request, response);
//	} catch (ServletException | IOException | BLLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	
//    HttpSession session = request.getSession();
//            session.setAttribute("succes","Enchère ajoutée avec succès");
//            response.sendRedirect(request.getContextPath()+"/accueil");

		/*
		 * Nouvelle essai plus simple
		 * 
		 * 
		 * 
		 */
		
		UtilisateurManager uMger = null;
		try {
			uMger = new UtilisateurManager();
		} catch (BLLException e1) {
		
			e1.printStackTrace();
		}
		// On recupere le montant de la nouvelle enchere
		int newMontant = Integer.valueOf(request.getParameter("mPrix"));
		System.out.println("NewMontant :" + newMontant);
		// On recupere l'utlisateur connecte
		Utilisateur utilisateurConnecte = (Utilisateur) request.getSession().getAttribute("ConnectedUser");
		// Pas de check si il a le pognon c'est fait dans le formulaire

		// On recupere l'enchere
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));
		EnchereJdbcImpl enMger = new EnchereJdbcImpl();
		Encheres enchere = null;
		try {
			enchere = enMger.selectById(noArticle);
			System.out.println("enchere :" + enchere);
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
				System.out.println("Ancien encherisseur :" + ancienEncherisseur);
				// On lui rend l'argent
				int argentRendu = ancienEncherisseur.getCredit() + enchere.getMontantEnchere();
				ancienEncherisseur.setCredit(argentRendu);
				// et on le modifie en base de données
				uMger.modifierUtilisateur(ancienEncherisseur);
				System.out.println("Ancien encherisseur modifié :" + ancienEncherisseur);

			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		// On modifie l'enchere
		// On indique le nouvelle encherisseur
		enchere.setNoUtilisateur(utilisateurConnecte.getNo_utilisateur());
		// On indique le nouveau montant
		enchere.setMontantEnchere(newMontant);
		// On le change en base de données
		try {
			enMger.update(enchere);
			System.out.println("enchere modifié :" + enchere);
		} catch (DALException e) {
			e.printStackTrace();
		}
		// On enleve le pognon aux nouvelle encherisseur
		System.out.println("nouvelle encherisseur  :" + utilisateurConnecte);
		int argentPris = utilisateurConnecte.getCredit() - newMontant;
		utilisateurConnecte.setCredit(argentPris);
		// On le change en base de données
		try {
			uMger.modifierUtilisateur(utilisateurConnecte);
			System.out.println("Ancien encherisseur modifié :" + utilisateurConnecte);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// On recupere l'article et on modifie son prix de vente
		try {
			ArticleManager aMger = new ArticleManager();
			ArticleVendu article = aMger.getArticleById(noArticle);
			article.setPrixVente(newMontant);
			aMger.modifierArticle(article);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// On redirige vers la page d'accueil
		request.getRequestDispatcher("/Accueil").forward(request, response);

	}

	private void encherir(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, BLLException, SQLException, DALException {
		// On recupere l'enchere
		Encheres enchere = creerEnchere(request);
		isMeilleurEncherisseur(request);

		EnchereManager enchereManager = new EnchereManager();
		// On recupere l'article
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));

		Integer prixVente = Integer.valueOf(request.getParameter("prixVente"));

		ArticleVendu article = null;
		article.setNoArticle(noArticle);
		article.setPrixVente(prixVente);
		Utilisateur encherisseurPrecedent = enchereManager.selectMeilleurEncherisseur(article);
		// si il n'y a eu aucune enchère sur l'article on insère l'enchère en base
		if (encherisseurPrecedent == null) {
			EnchereManager.ajoutEnchere(enchere);
		} else {
			// s'il y a une autre enchère sur l'article on rend les crédit à l'enchérisseur
			// précédent avant d'ajouter notre enchère

			rendreCredit(encherisseurPrecedent, prixVente);

			EnchereManager.ajoutEnchere(enchere);
		}
	}

	private Encheres creerEnchere(HttpServletRequest request) {

		// Integer prixVente = Integer.valueOf(request.getParameter("prixVente"));

//        ArticleVendu article = null ;
//        article.setNoArticle(noArticle);
//        article.setPrixVente(prixVente);

		// On recupere l'utilisateur connecte
		HttpSession session = request.getSession();
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
		// On recupere le numero de l'article
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));
		LocalDateTime dateEnchere = LocalDateTime.now();
		

		int montant = Integer.parseInt(request.getParameter("montant"));

		Encheres enchere = new Encheres(utilisateurConnecte.getNo_utilisateur(), noArticle, dateEnchere,
				montant);

		return enchere;
	}

	private void isMeilleurEncherisseur(HttpServletRequest request) throws BLLException, SQLException {
		HttpSession session = request.getSession();
		Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
		Integer prixVente = Integer.valueOf(request.getParameter("prixVente"));
		Integer noArticle = Integer.valueOf(request.getParameter("noArticle"));
		ArticleVendu article = null;
		article.setNoArticle(noArticle);
		article.setPrixVente(prixVente);
		EnchereManager enchereManager = new EnchereManager();

		// On vérifie si l'utilisateur est déjà le meilleur encherisseur, si c'est le
		// cas on ajoute une erreur
		boolean isMeilleurEncherisseur = enchereManager.isMeilleurEncherisseur(article.getNoArticle(),
				utilisateurConnecte.getNo_utilisateur());

	}

	private void preleverCredit(HttpServletRequest request, HttpServletResponse response)
			throws BLLException, ServletException, IOException {
		HttpSession session = request.getSession();

		// récupérer utilisateur en session et soustraire à son crédit le montant de
		// l'enchère
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		Integer prixVente = Integer.valueOf(request.getParameter("prixVente"));

		int montant = Integer.parseInt(request.getParameter("montant"));

		Integer noArticle = Integer.valueOf(request.getParameter("noArticle"));
		ArticleVendu article = null;
		article.setNoArticle(noArticle);
		article.setPrixVente(prixVente);
		utilisateur.setCredit(utilisateur.getCredit() - article.getPrixVente());

		utilisateurManager.modifierUtilisateur(utilisateur);

		// l'utilisateur est update en base, on le remet correctement dans la session
		session.setAttribute("utilisateur", utilisateur);
	}

	private void rendreCredit(Utilisateur u, Integer prixVente) throws BLLException {
		UtilisateurManager utilisateurManager = new UtilisateurManager();

		Utilisateur encherisseurPrecedent = u;
		encherisseurPrecedent.setCredit(encherisseurPrecedent.getCredit() + prixVente);
		utilisateurManager.modifierUtilisateur(encherisseurPrecedent);
	}

	private void updatePrixVenteArticle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, BLLException {
		Integer prixVente = Integer.valueOf(request.getParameter("prixVente"));
		Integer noArticle = Integer.valueOf(request.getParameter("noArticle"));
		ArticleVendu article = null;
		article.setNoArticle(noArticle);
		article.setPrixVente(prixVente);

		// Récupérer l'article au complet
		ArticleManager articleManager = new ArticleManager();
		ArticleVendu fullArticle = articleManager.getArticleById(article.getNoArticle());

		// Set nouveau prix de vente
		fullArticle.setPrixVente(Integer.valueOf(request.getParameter("montant")));
		articleManager.modifierArticle(fullArticle);
	}

}
