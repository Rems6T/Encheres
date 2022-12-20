package fr.eni.ENI_Encheres.dal;

import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.jdbcImpl.UtilisateurDAOJdbcImpl;

public class DAOFactory {

	
	public static DAO<Utilisateur> getUtilisateurDAO(){
		return new UtilisateurDAOJdbcImpl();
	}
}
