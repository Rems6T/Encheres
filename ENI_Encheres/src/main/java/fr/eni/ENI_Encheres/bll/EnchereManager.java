package fr.eni.ENI_Encheres.bll;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Encheres;
import fr.eni.ENI_Encheres.bo.EtatVente;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.EnchereJdbcImpl;

public class EnchereManager {

	private static EnchereJdbcImpl enchere = new EnchereJdbcImpl();

	
	public EnchereManager() {
		
	}
	
	public static Encheres ajoutEnchere (Encheres enchere) throws DALException {
			EnchereJdbcImpl enchereJdbcImpl = new EnchereJdbcImpl();
			enchereJdbcImpl.insert(enchere);

		return enchere;
		
	}

	public static Encheres selectionnerEnchereById(int id) throws BLLException, DALException
	{
		return enchere.selectById(id);
	}

	public static List<Encheres> selectionnerToutesLesEncheres() throws BLLException, DALException
	{
		return enchere.selectAll();
	}
	

	
	
	public static List<Encheres> elsectionnerEncheresParArticle(int id) throws SQLException 
	{
		
		return enchere.getAllByArticle(id);
		
	}
	
	public static Boolean enchereTerminee (ArticleVendu article) {
		Boolean encheretermniee;
	       long miliseconds = System.currentTimeMillis();
	        Date date = new Date(miliseconds);
		if (article.getDateFinEncheres().before(date)) {
		return encheretermniee  =true ;
	}else 
		return encheretermniee= false;
	
	}
	
    private  EnchereJdbcImpl enchereDAO;

    public boolean isMeilleurEncherisseur(int noArticle, int noUtilisateur) throws BLLException, SQLException {
        return this.enchereDAO.isMeilleurEncherisseur(noArticle, noUtilisateur);
    }

    public Utilisateur selectMeilleurEncherisseur(ArticleVendu article) throws BLLException, SQLException {
        return this.enchereDAO.selectMeilleurEncherisseur(article);
    }
	}
	

