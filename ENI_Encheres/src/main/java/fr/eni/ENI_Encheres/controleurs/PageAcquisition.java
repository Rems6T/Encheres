package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.EnchereManager;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Encheres;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageAcquisition
 */
public class PageAcquisition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Encheres> encheres = new ArrayList<>();
		int encherisseur = 0;
		Encheres meilleureOffre = null;
		
		int id = Integer.parseInt(request.getParameter("idArticle"));
		

		ArticleVendu articleAffiche = null;
		try {
			articleAffiche = ArticleManager.getArticleById(id);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				encheres = EnchereManager.elsectionnerEncheresParArticle(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (encheres.size() > 0) {		
				meilleureOffre = encheres.get(encheres.size()-1);
				encherisseur = meilleureOffre.getNoUtilisateur();
				int gagnantEnchere = encherisseur;
			}

		if (articleAffiche != null) {
			request.setAttribute("ArticleAffiche", articleAffiche);
			request.setAttribute("GagnantEnchere", encherisseur);
			request.setAttribute("MeilleureOffre", meilleureOffre);
			request.getRequestDispatcher("/PageAcquisition.jsp").forward(request, response);
		
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}