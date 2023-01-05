package fr.eni.ENI_Encheres.controleurs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.ArticleVenduJdbcImpl;

/**
 * Servlet implementation class Lister
 */
@WebServlet("/lister")
public class Lister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ArticleVendu> ArticleList = null;
		ArticleVenduJdbcImpl aMger = new ArticleVenduJdbcImpl();
		try {
			ArticleList = aMger.selectAllBy(request.getParameter("etatVente"),
					Integer.parseInt(request.getParameter("id")));
		} catch (NumberFormatException | DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// On retourne le tableau

		Collections.reverse(ArticleList);
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
			ArticleVendu a = new ArticleVendu(articleVendu.getNoArticle(), articleVendu.getNomArticle(),
					articleVendu.getDescription(), articleVendu.getDateDebutEncheres(),
					articleVendu.getDateFinEncheres(), articleVendu.getMiseAPrix(), articleVendu.getPrixVente(),
					articleVendu.getNoUtilisateur(), articleVendu.getNoCategorie(), articleVendu.getEtatVente(),
					pseudoU);
			ArticleListU.add(a);
		}
		request.setAttribute("ArticleListU", ArticleListU);
		
		// On envoie les attribut pour les check
		int check = Integer.parseInt(request.getParameter("check"));
		String checkAchat = "";
		String check1 = "";
		String check2 = "";
		String check3 = "";
		String checkVente = "";
		String check4 = "";
		String check5 = "";
		String check6 = "";
		switch (check) {
		case 1:
			checkAchat = "checked";
			check1 = "checked";
			check2 = "";
			check3 = "";
			checkVente = "";
			check4 = "";
			check5 = "";
			check6 = "";
			break;
		case 2:
			checkAchat = "checked";
			check1 = "";
			check2 = "checked";
			check3 = "";
			checkVente = "";
			check4 = "";
			check5 = "";
			check6 = "";
			break;
		case 3:
			checkAchat = "checked";
			check1 = "";
			check2 = "";
			check3 = "checked";
			checkVente = "";
			check4 = "";
			check5 = "";
			check6 = "";
			break;
		case 4:
			checkAchat = "";
			check1 = "";
			check2 = "";
			check3 = "";
			checkVente = "checked";
			check4 = "checked";
			check5 = "";
			check6 = "";
			break;
		case 5:
			checkAchat = "";
			check1 = "";
			check2 = "";
			check3 = "";
			checkVente = "checked";
			check4 = "";
			check5 = "checked";
			check6 = "";
			break;
		case 6:
			checkAchat = "";
			check1 = "";
			check2 = "";
			check3 = "";
			checkVente = "checked";
			check4 = "";
			check5 = "";
			check6 = "checked";
			break;
		default:
			break;
		}
		// on envoie les huit attribut
		request.setAttribute("checkAchat", checkAchat);
		request.setAttribute("check1", check1);
		request.setAttribute("check2", check2);
		request.setAttribute("check3", check3);
		request.setAttribute("check4", check4);
		request.setAttribute("check5", check5);
		request.setAttribute("check6", check6);
		request.setAttribute("checkVente", checkVente);
		getServletContext().getRequestDispatcher("/Accueil.jsp").forward(request, response);
	}

}
