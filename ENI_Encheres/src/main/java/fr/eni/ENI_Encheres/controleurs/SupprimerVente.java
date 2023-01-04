package fr.eni.ENI_Encheres.controleurs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Encheres;
import fr.eni.ENI_Encheres.bo.Retrait;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.EnchereJdbcImpl;
import fr.eni.ENI_Encheres.jdbcImpl.RetraitJdbcImpl;

/**
 * Servlet implementation class SupprimerVente
 */
@WebServlet("/SupprimerVente")
public class SupprimerVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int noArticle = Integer.valueOf(request.getParameter("noArticle"));
		
		//On recupere l'enchere
		
		Encheres e;
		try {
			EnchereJdbcImpl eMger = new EnchereJdbcImpl();
			e = eMger.selectById(noArticle);
			//On supprime l'enchere
			eMger.delete(e);
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//On recupere le retrait
		RetraitJdbcImpl rMger = new RetraitJdbcImpl();
		Retrait r;
		try {
			r = rMger.selectById(noArticle);
			//On supprime le retrait
			rMger.delete(r);
		} catch (DALException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//On recuper l'article
		
		ArticleManager aMger = null;
		ArticleVendu a ;
		try {
			aMger = new ArticleManager();
			a = aMger.getArticleById(noArticle);
			//On supprime l'article
			aMger.supprimerArticle(a);
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.getRequestDispatcher("/Vente").forward(request, response);
		
		
		
	}

}
