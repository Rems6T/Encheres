package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Categorie;
import fr.eni.ENI_Encheres.bo.EtatVente;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.CategorieJdbcImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Accueil")
public class PageAccueil extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setAttribute("utilisateur", session.getAttribute("utilisateur"));
		ArticleManager artmger = null;
		try {
			artmger = new ArticleManager();

		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ArticleVendu> ArticleList = null;
		try {
			ArticleList = artmger.getAllArticle();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Pour chaque article on check si l'enchere est fini ou en cours
		for (ArticleVendu articleVendu : ArticleList) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime debutEnchere = articleVendu.getDateDebutEncheres();
			LocalDateTime finEnchere = articleVendu.getDateFinEncheres();
			// Si l'enchere est termine
			if (now.compareTo(finEnchere) > 0) {
				// on verifie si ce n'est pas deja fait

				if (articleVendu.getEtatVente().equals(EtatVente.ENCHERES_TERMINEES) == false) {
					articleVendu.setEtatVente(EtatVente.ENCHERES_TERMINEES);
					// on le save en Bd
					try {

						artmger.modifierArticle(articleVendu);

					} catch (BLLException e) {
						e.printStackTrace();
					}
				}

			} else {
				// verifie si l'enchere a debuté
				if (now.compareTo(debutEnchere) > 0) {
					// on verifie si pas deja fait
					if (articleVendu.getEtatVente().equals(EtatVente.EN_COURS) == false) {
						articleVendu.setEtatVente(EtatVente.EN_COURS);
						// on le save en bd
						try {
							artmger.modifierArticle(articleVendu);
						} catch (BLLException e) {
							e.printStackTrace();
						}
					}

				}
			}
		}

		// On retourne le tableau
		Collections.reverse(ArticleList);
		// Pour les 10 premiers on ajoute l'utilisateur
		List<ArticleVendu> ArticleListU = new ArrayList<ArticleVendu>();
		for (ArticleVendu articleVendu : ArticleList) {
			// on recupere l'utilisateur
			Utilisateur u = null;
			try {
				UtilisateurManager uMger = new UtilisateurManager();
				int noUt = articleVendu.getNoUtilisateur();
				u = uMger.getUtilisateurById(noUt);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String pseudoU = u.getPseudo();
			ArticleVendu a = new ArticleVendu(articleVendu.getNoArticle(), articleVendu.getNomArticle(), articleVendu.getDescription(),
					articleVendu.getDateDebutEncheres(), articleVendu.getDateFinEncheres(), articleVendu.getMiseAPrix(),
					articleVendu.getPrixVente(), articleVendu.getNoUtilisateur(), articleVendu.getNoCategorie(), articleVendu.getEtatVente(),
					pseudoU);
			ArticleListU.add(a);
		}

		request.setAttribute("ArticleListU", ArticleListU);
		//Attribut pour les check
		String checkAchat = "";
		String check1 = "";
		String check2 = "";
		String check3 = "";
		String checkVente = "";
		String check4 = "";
		String check5 = "";
		String check6 = "";
		// on envoie les huit attribut
				request.setAttribute("checkAchat", checkAchat);
				request.setAttribute("check1", check1);
				request.setAttribute("check2", check2);
				request.setAttribute("check3", check3);
				request.setAttribute("check4", check4);
				request.setAttribute("check5", check5);
				request.setAttribute("check6", check6);
				request.setAttribute("checkVente", checkVente);
				//ajout de la liste categorie
				CategorieJdbcImpl categorieJdbcImpl = new CategorieJdbcImpl();
				List<Categorie> categorieList = null ;
				try {
					categorieList = categorieJdbcImpl.selectAll();
				} catch (DALException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				request.setAttribute("categorieList", categorieList);		
		request.getRequestDispatcher("/Accueil.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			doGet(request, response);

	}

}
