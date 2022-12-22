package fr.eni.ENI_Encheres.controleursAdmin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import fr.eni.ENI_Encheres.bo.Categorie;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.CategorieJdbcImpl;

/**
 * Servlet implementation class CreerCategorie
 */
@WebServlet("/CreerCategorie")
public class CreerCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategorieJdbcImpl mger = new CategorieJdbcImpl();
		Categorie categorie = new Categorie(request.getParameter("libelle"));
		try {
			mger.insert(categorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("CategorieAdmin");
	}

}
