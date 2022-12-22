package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;
import java.util.List;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
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
		
		request.setAttribute("ArticleList", ArticleList);

		request.getRequestDispatcher("Accueil.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
		
		request.setAttribute("ArticleList", ArticleList);

		request.getRequestDispatcher("Accueil.jsp").forward(request, response);

	}

}




