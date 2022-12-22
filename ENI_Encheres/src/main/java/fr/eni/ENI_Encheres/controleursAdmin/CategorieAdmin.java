package fr.eni.ENI_Encheres.controleursAdmin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import fr.eni.ENI_Encheres.bo.Categorie;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.jdbcImpl.CategorieJdbcImpl;

/**
 * Servlet implementation class CategorieAdmin
 */
@WebServlet("/CategorieAdmin")
public class CategorieAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("utilisateur", session.getAttribute("utilisateur"));
		CategorieJdbcImpl mger = new CategorieJdbcImpl();;
		
		List<Categorie> categorieList = null;
		try {
			categorieList = mger.selectAll();
		} catch ( DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("categorieList", categorieList);

		request.getRequestDispatcher("CategorieAdmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
