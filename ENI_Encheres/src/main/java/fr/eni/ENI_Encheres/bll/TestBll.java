package fr.eni.ENI_Encheres.bll;

import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;

public class TestBll {
public static void main(String[] args) throws DALException {
	Utilisateur utilisateur = new Utilisateur("man06",	"Manu", "Valance", "manu@orange.com", "0643063142", "rue", "06420", "St Sau", "mdp", 0, false);
	try {
		UtilisateurManager mger = new UtilisateurManager();
		boolean ok = mger.verifUniqueMail(utilisateur.getEmail());
		System.out.println(ok);
	} catch (BLLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
