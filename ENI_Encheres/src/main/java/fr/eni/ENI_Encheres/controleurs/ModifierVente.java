package fr.eni.ENI_Encheres.controleurs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Categorie;
import fr.eni.ENI_Encheres.bo.EtatVente;
import fr.eni.ENI_Encheres.bo.Retrait;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.CategorieJdbcImpl;
import fr.eni.ENI_Encheres.jdbcImpl.RetraitJdbcImpl;

/**
 * Servlet implementation class ModifierVente
 */
@WebServlet("/ModifierVente")
public class ModifierVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// On recupere la vente
		int noArt = Integer.valueOf(request.getParameter("id"));
		ArticleManager aMger = null;
		ArticleVendu a = null;
		try {
			aMger = new ArticleManager();
			a = aMger.getArticleById(noArt);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("articleAffiche", a);
		// ajout de la liste categorie
		CategorieJdbcImpl categorieJdbcImpl = new CategorieJdbcImpl();
		List<Categorie> categorieList = null;
		try {
			categorieList = categorieJdbcImpl.selectAll();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("categorieList", categorieList);
		// on ajoute le retrait
		RetraitJdbcImpl rMger = new RetraitJdbcImpl();
		Retrait retrait = null;
		try {
			retrait = rMger.selectById(noArt);
		} catch (DALException e) {

			e.printStackTrace();
		}
		request.setAttribute("retrait", retrait);
		// on redirige
		request.getRequestDispatcher("/ModifierVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//On modifie l'article
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		String nomArticle = request.getParameter("nom").trim();
		String description = request.getParameter("description").trim();
		LocalDateTime dateDebut = LocalDateTime.parse(request.getParameter("debutEnchere"));
		LocalDateTime dateFin = LocalDateTime.parse(request.getParameter("finEnchere"));
		int miseAprix = Integer.parseInt(request.getParameter("miseAPrix"));
		int prixVente = Integer.parseInt(request.getParameter("miseAPrix"));
		int noUtilisateur = Integer.parseInt(request.getParameter("noUtilisateur"));
		int  noCategorie = Integer.parseInt(request.getParameter("categorie"));
		EtatVente etatVente =EtatVente.CREE;
		
		ArticleVendu article = new ArticleVendu(noArticle,nomArticle,description, dateDebut, dateFin, miseAprix, prixVente, noUtilisateur,noCategorie,etatVente);
		//on le save en Bd
		try {
			ArticleManager mger = new ArticleManager();
			mger.modifierArticle(article);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//on modifie le retrait
		String rue =request.getParameter("rue").trim();
		String codePostal =request.getParameter("codePostal").trim();
		String ville =request.getParameter("ville").trim();
		Retrait retrait = new Retrait(noArticle, rue, codePostal, ville); 
		RetraitJdbcImpl retraitMger = new RetraitJdbcImpl();
		//on le modifie en bd
		try {
			retraitMger.update(retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/Vente").forward(request, response);
	}

}
