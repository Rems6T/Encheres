package fr.eni.ENI_Encheres.dal;




import java.sql.Date;
import java.util.List;

import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.jdbcImpl.ArticleVenduJdbcImpl;


public class testDAL {

	
	public static void main(String[] args) {
		ArticleVenduJdbcImpl artImpl = new ArticleVenduJdbcImpl();
		ArticleVendu artVendu = new ArticleVendu("chaussure", "chaussure bleu",new Date(2022, 10, 10), new Date(2022, 10, 10), 10, 0, 2, 3);
		
		
		try {
			artImpl.insert(artVendu);
			List<ArticleVendu> liste = artImpl.selectAll();
			afficherAll(liste);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void afficherAll(List<ArticleVendu> articleVendus){
		StringBuffer sb = new StringBuffer();
		for(ArticleVendu articleVendu: articleVendus){
			sb.append(articleVendu.toString());
			sb.append("\n");
		}
		System.out.println(sb.toString());
}
	}
