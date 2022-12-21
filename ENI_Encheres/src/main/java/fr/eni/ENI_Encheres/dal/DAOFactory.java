package fr.eni.ENI_Encheres.dal;

import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Categorie;
import fr.eni.ENI_Encheres.bo.Encheres;
import fr.eni.ENI_Encheres.bo.Retrait;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.jdbcImpl.ArticleVenduJdbcImpl;
import fr.eni.ENI_Encheres.jdbcImpl.CategorieJdbcImpl;
import fr.eni.ENI_Encheres.jdbcImpl.EnchereJdbcImpl;
import fr.eni.ENI_Encheres.jdbcImpl.RetraitJdbcImpl;
import fr.eni.ENI_Encheres.jdbcImpl.UtilisateurDAOJdbcImpl;

public class DAOFactory {

	
	public static DAO<Utilisateur> getUtilisateurDAO(){
		return new UtilisateurDAOJdbcImpl();
	}
	public static DAO<ArticleVendu> getArticleDAO(){
		return new ArticleVenduJdbcImpl();
	}
	public static DAO<Categorie> getCategorieDAO(){
		return new CategorieJdbcImpl();
	}
	public static DAO<Encheres> getEnchereDAO(){
		return new EnchereJdbcImpl();
	}
	public static DAO<Retrait> getRetraitDAO(){
		return new RetraitJdbcImpl();
	}
	
}
