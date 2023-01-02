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
import fr.eni.ENI_Encheres.bo.EtatVente;
import fr.eni.ENI_Encheres.bo.Retrait;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer noArticle = Integer.valueOf(request.getParameter("noArticle"));

        request.getRequestDispatcher("/Encherir?no_article="+noArticle).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Integer> listeCodesErreur=new ArrayList<>();
            try {
				encherir(request, response, listeCodesErreur);
			} catch (ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BLLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DALException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}        
                try {
					preleverCredit(request, response, listeCodesErreur);
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}           

 updatePrixVenteArticle(request, response, listeCodesErreur);
            

            HttpSession session = request.getSession();
            session.setAttribute("succes","Enchère ajoutée avec succès");
            response.sendRedirect(request.getContextPath()+"/accueil");
        }


    private void encherir(HttpServletRequest request, HttpServletResponse response, List<Integer> listeCodesErreur) throws ServletException, IOException, BLLException, SQLException, DALException {
        Encheres enchere = creerEnchere(request, listeCodesErreur);
        isMeilleurEncherisseur(request, listeCodesErreur);

        EnchereManager enchereManager = new EnchereManager();

        //si la création d'enchère et isMeilleurEnchrisseur ont renvoyé des erreurs, on renvoi vers details article avec les erreurs
        if(listeCodesErreur.size() > 0) {
            request.setAttribute("listeCodesErreur", listeCodesErreur);
            this.doGet(request, response);
        } else {
            //selectMeilleurEncherisseur si null 0 enchères
            Integer prixVente = Integer.valueOf(request.getParameter("prixVente"));
            Integer noArticle = Integer.valueOf(request.getParameter("noArticle"));
            ArticleVendu article = null ;
            article.setNoArticle(noArticle);
            article.setPrixVente(prixVente);
            Utilisateur encherisseurPrecedent = enchereManager.selectMeilleurEncherisseur(article);
            //si il n'y a eu aucune enchère sur l'article on insère l'enchère en base
            if(encherisseurPrecedent == null) {
                EnchereManager.ajoutEnchere(enchere);
            } else {
                //s'il y a une autre enchère sur l'article on rend les crédit à l'enchérisseur précédent avant d'ajouter notre enchère

            	rendreCredit(encherisseurPrecedent, prixVente);

                EnchereManager.ajoutEnchere(enchere);
            }
        }
    }

    private Encheres creerEnchere(HttpServletRequest request, List<Integer> listeCodesErreur) {
        HttpSession session = request.getSession();
        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
        
        
        Integer prixVente = Integer.valueOf(request.getParameter("prixVente"));
        Integer noArticle = Integer.valueOf(request.getParameter("noArticle"));
        ArticleVendu article = null ;
        article.setNoArticle(noArticle);
        article.setPrixVente(prixVente);

        
        
        long miliseconds = System.currentTimeMillis();
        Date dateEnchere = new Date(miliseconds);
        
        
        int montant = Integer.parseInt(request.getParameter("montant"));


        Encheres enchere = new Encheres(utilisateurConnecte.getNo_utilisateur() , article.getNoArticle() , (java.sql.Date) dateEnchere, montant);

        return enchere;
    }

    private void isMeilleurEncherisseur(HttpServletRequest request, List<Integer> listeCodesErreur) throws BLLException, SQLException {
        HttpSession session = request.getSession();
        Utilisateur utilisateurConnecte = (Utilisateur) session.getAttribute("utilisateur");
        Integer prixVente = Integer.valueOf(request.getParameter("prixVente"));
        Integer noArticle = Integer.valueOf(request.getParameter("noArticle"));
        ArticleVendu article = null ;
        article.setNoArticle(noArticle);
        article.setPrixVente(prixVente);
        EnchereManager enchereManager = new EnchereManager();

        //On vérifie si l'utilisateur est déjà le meilleur encherisseur, si c'est le cas on ajoute une erreur
        boolean isMeilleurEncherisseur = enchereManager.isMeilleurEncherisseur(article.getNoArticle(), utilisateurConnecte.getNo_utilisateur());

    }

    private void preleverCredit (HttpServletRequest request, HttpServletResponse response, List<Integer> listeCodesErreur) throws BLLException, ServletException, IOException {
        HttpSession session = request.getSession();

        //récupérer utilisateur en session et soustraire à son crédit le montant de l'enchère
        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
        UtilisateurManager utilisateurManager = new UtilisateurManager();
       
        Integer prixVente = Integer.valueOf(request.getParameter("prixVente"));

        int montant = Integer.parseInt(request.getParameter("montant"));
        
        Integer noArticle = Integer.valueOf(request.getParameter("noArticle"));
        ArticleVendu article = null ;
        article.setNoArticle(noArticle);
        article.setPrixVente(prixVente);
        utilisateur.setCredit(utilisateur.getCredit() - article.getPrixVente());

        if(listeCodesErreur.size() > 0) {
            request.setAttribute("listeCodesErreur", listeCodesErreur);
            this.doGet(request, response);
        } else {
            //update l'utilisateur

            UtilisateurManager.modifierUtilisateur(utilisateur);

            //l'utilisateur est update en base, on le remet correctement dans la session
            session.setAttribute("utilisateur", utilisateur);
        }
    }

    private void rendreCredit (Utilisateur u, Integer prixVente) throws BLLException {
        UtilisateurManager utilisateurManager = new UtilisateurManager();

        Utilisateur encherisseurPrecedent = u;
        encherisseurPrecedent.setCredit(encherisseurPrecedent.getCredit() + prixVente);
        UtilisateurManager.modifierUtilisateur(encherisseurPrecedent);
    }

    private void updatePrixVenteArticle (HttpServletRequest request, HttpServletResponse response, List<Integer> listeCodesErreur) throws ServletException, IOException {
        Integer prixVente = Integer.valueOf(request.getParameter("prixVente"));
        Integer noArticle = Integer.valueOf(request.getParameter("noArticle"));
        ArticleVendu article ;
        article.setNoArticle(noArticle);
        article.setPrixVente(prixVente);

        //Récupérer l'article au complet
        ArticleManager articleManager = new ArticleManager();
        ArticleVendu fullArticle = ArticleManager.getArticleById(article.getNoArticle());

        //Set nouveau prix de vente
        fullArticle.setPrixVente =Integer.valueOf(request.getParameter("montant"));
            ArticleManager.modifierArticle(fullArticle);
        }
    

   
}
