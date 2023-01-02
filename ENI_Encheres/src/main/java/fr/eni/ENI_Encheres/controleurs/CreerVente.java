package fr.eni.ENI_Encheres.controleurs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fr.eni.ENI_Encheres.bll.ArticleManager;
import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Categorie;
import fr.eni.ENI_Encheres.bo.Encheres;
import fr.eni.ENI_Encheres.bo.EtatVente;
import fr.eni.ENI_Encheres.bo.Retrait;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.CategorieJdbcImpl;
import fr.eni.ENI_Encheres.jdbcImpl.EnchereJdbcImpl;
import fr.eni.ENI_Encheres.jdbcImpl.RetraitJdbcImpl;

/**
 * Servlet implementation class CreerVente
 */
@WebServlet("/CreerVente")
public class CreerVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
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
		request.getRequestDispatcher("/CreerVente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Utilisateur u = (Utilisateur) request.getSession().getAttribute("ConnectedUser");
		
		
		
		//on ajoute l'article
		String nomArticle = request.getParameter("nom").trim();
		String description = request.getParameter("description").trim();
		
		
		String dateDebut=request.getParameter("debutEnchere");
		String dateFin=request.getParameter("finEnchere");
		
		java.util.Date utilDateDebut=null;
		java.util.Date utilDateFin=null;
		
        try {
			utilDateDebut= new SimpleDateFormat("yyyy-mm-dd").parse(dateDebut);
			 utilDateFin= new SimpleDateFormat("yyyy-mm-dd").parse(dateFin);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        java.sql.Date sqlDateDebut = new java.sql.Date(utilDateDebut.getTime());	
        java.sql.Date sqlDateFin = new java.sql.Date(utilDateFin.getTime());	
		
		int miseAprix = Integer.parseInt(request.getParameter("miseAPrix"));
		int prixVente = 0;
		int noUtilisateur = u.getNo_utilisateur();
		int  noCategorie = Integer.parseInt(request.getParameter("categorie"));

		
		ArticleVendu article = new ArticleVendu(nomArticle,description, sqlDateDebut, sqlDateFin, miseAprix, prixVente, noUtilisateur,noCategorie);

		int idArticle=0;
		try {
			ArticleManager mger = new ArticleManager();
			mger.ajoutArticle(article);
			article.setEtatVente(EtatVente.CREE);
			//on recupere l'id de l'objet créer
			 idArticle = mger.getIdArticle(article);
			 System.out.println("idArticle "+idArticle);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//on ajoute le retrait
		String rue =request.getParameter("rue").trim();
		String codePostal =request.getParameter("codePostal").trim();
		String ville =request.getParameter("ville").trim();
		Retrait retrait = new Retrait(idArticle, rue, codePostal, ville); 
		RetraitJdbcImpl retraitMger = new RetraitJdbcImpl();
		try {
			retraitMger.insert(retrait);
			System.out.println(retrait);
			System.out.println("retrait ajouté");
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//on ajoute l'encheres
		Encheres enchere = new Encheres(noUtilisateur, idArticle, sqlDateDebut, miseAprix);
		EnchereJdbcImpl encImpl = new EnchereJdbcImpl();
		try {
			encImpl.insert(enchere);
			System.out.println("enchere ajouté");
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("Vente");
	}

}
