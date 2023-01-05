package fr.eni.ENI_Encheres.bll;

import java.time.LocalDateTime;

import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.EtatVente;
import fr.eni.ENI_Encheres.bo.Retrait;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.RetraitJdbcImpl;

public class TestBll {
	public static void main(String[] args) {
//	Utilisateur utilisateur = new Utilisateur("man06",	"Manu", "Valance", "manu@orange.com", "0643063142", "rue", "06420", "St Sau", "mdp", 0, false);
//	try {
//		UtilisateurManager mger = new UtilisateurManager();
//		List<Utilisateur> articles = mger.selectBy("email","Jean@email.com");
//		for (Utilisateur utilisateur2 : articles) {
//			System.out.println(utilisateur2);
//		}
//		System.out.println(articles);
//	} catch (BLLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		
//		try {
//			ArticleManager artmger = new ArticleManager();
//			ArticleVendu a = artmger.getArticleById(43);
//			System.out.println(a);
//			a.setEtatVente(EtatVente.EN_COURS);
//			System.out.println(a);
//
//			artmger.modifierArticle(a);
//			ArticleVendu a2 = artmger.getArticleById(43);
//			System.out.println("modif faite");
//			System.out.println(a2);
//			
//		} catch (BLLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		LocalDateTime test = LocalDateTime.now();
		System.out.println(test);
		LocalDateTime test1 = LocalDateTime.now().plusMinutes(10);
		System.out.println(test1);
		LocalDateTime test2 = LocalDateTime.now().plusMinutes(10).plusHours(1);
		System.out.println(test2);
	}
}
