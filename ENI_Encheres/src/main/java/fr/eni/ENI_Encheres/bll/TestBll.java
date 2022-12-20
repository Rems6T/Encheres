package fr.eni.ENI_Encheres.bll;

import fr.eni.ENI_Encheres.bo.Utilisateur;

public class TestBll {
public static void main(String[] args) {
	Utilisateur utilisateur = new Utilisateur("man06",	"Manu", "Valance", "manu@orange.com", "0643063142", "rue", "06420", "St Sau", "mdp", 0, false);
	try {
		UtilisateurManager mger = new UtilisateurManager();
		mger.ajoutUtilisateur(utilisateur);
	} catch (BLLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
