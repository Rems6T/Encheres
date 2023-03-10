package fr.eni.ENI_Encheres.controleursAdmin;

import java.io.IOException;

import fr.eni.ENI_Encheres.bo.Categorie;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.CategorieJdbcImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SupprimerCategorie
 */
@WebServlet("/SupprimerCategorie")
public class SupprimerCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		Categorie categorie = null;
		id=Integer.parseInt(request.getParameter("id"));
		CategorieJdbcImpl mger= new CategorieJdbcImpl();
		try {
			categorie = mger.selectById(id);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mger.delete(categorie);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("CategorieAdmin");
	}


}
