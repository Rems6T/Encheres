package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.EnchereManager;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Encheres;
import fr.eni.ENI_Encheres.bo.EtatVente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/PageAcquisition")

public class PageAcquisition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVendu articleAffiche = null;
		List<Encheres> encheres = new ArrayList<>();
		int encherisseur = 0;
		Encheres meilleureOffre = null;

		LocalDateTime date = LocalDateTime.now();
		
	
		
		
		int id = Integer.parseInt(request.getParameter("idArticle"));


		try {
			ArticleManager aMger = new ArticleManager();
			articleAffiche = aMger.getArticleById(id);
			encheres = EnchereManager.elsectionnerEncheresParArticle(id);
			if (articleAffiche.getDateFinEncheres().isBefore(date)) {
	
			if (encheres.size() > 0) {		
				meilleureOffre = encheres.get(encheres.size()-1);
				encherisseur = meilleureOffre.getNoUtilisateur();
				meilleureOffre.setRemporte(true);
			}
			}} catch (BLLException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (articleAffiche != null) {
			request.setAttribute("ArticleAffiche", articleAffiche);
			request.setAttribute("GagnantEnchere", encherisseur);
			request.setAttribute("MeilleureOffre", meilleureOffre);
			articleAffiche.setEtatVente(EtatVente.ENCHERES_TERMINEES);
			request.getRequestDispatcher("PageAcquisition.jsp").forward(request, response);
		}
		else  { 			request.getRequestDispatcher("Encherir.jsp").forward(request, response);

		
	}}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}