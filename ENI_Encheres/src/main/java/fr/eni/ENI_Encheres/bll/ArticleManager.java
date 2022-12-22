package fr.eni.ENI_Encheres.bll;

import java.util.List;

import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.dal.DAO;
import fr.eni.ENI_Encheres.dal.DAOFactory;

public class ArticleManager {
	
	private static DAO<ArticleVendu> daoArticle;

	public ArticleManager() throws BLLException{
			//Instancier le Data Access Object
		daoArticle =DAOFactory.getArticleDAO();
	}
	/**
	 * 
	 * Select All
	 * 
	 * @return
	 * @throws BLLException
	 */
	
	public List<ArticleVendu> getAllArticle() throws BLLException{
		List<ArticleVendu> articles=null;
		try {
			articles = daoArticle.selectAll();
		} catch (DALException e) {
			e.printStackTrace();
			throw new BLLException("Erreur récupération articles", e);
		}
		
		return articles;
	}
	/**
	 * Select by Id
	 * 
	 */
	public static ArticleVendu getArticleById(int id) throws BLLException{
		ArticleVendu article = null;
		try {
			article = daoArticle.selectById(id);
		} catch (DALException e) {
			
			e.printStackTrace();
			throw new BLLException("Erreur récupération de l'article id="+id, e);
		}
		return article;
	}
	
	/**
	 * Ajout d'un Utilisateur 
	 * @param newArticle
	 * @return index du nouvel utilisateur dans le catalogue
	 * @throws BLLException 
	 */
	public void ajoutArticle(ArticleVendu newArticle) throws BLLException {
		
		try {
			// TODO Faire une validation 
			//validerUtilisateur(newUtilisateur);
			daoArticle.insert(newArticle);
		} catch (DALException e) {
			throw new BLLException("Echec ajout Article", e);
		}
	}

	/**
	 * updateArticle : Modifier un article du catalogue
	 * @param article
	 * @throws BLLException
	 */
	public static void modifierArticle(ArticleVendu article) throws BLLException{
		try {
			//validerUtilisateur(utilisateur);
			daoArticle.update(article);
			
		} catch (DALException e) {
			throw new BLLException("Echec modifierArticle-article:"+article, e);
		}
	}
	
	
	
	
	/**
	 * Supprimer un article du catalogue
	 * @param index
	 * @throws BLLException
	 */
	public void supprimerArticle(ArticleVendu article) throws BLLException{
		try {
			daoArticle.delete(article);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression de l'article - ", e);
		}
		
	}
}
