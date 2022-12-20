package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;

import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/CreerCompte")
public class CreerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		getServletContext().getRequestDispatcher("/CreerCompte.jsp").forward(request, response);
	}

	 /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 		if(request.getServletPath().equals("/creer"))
	{
		String pseudo,nom,prenom,email, telephone, rue, codePostal, ville, motDePasse;
		int credit = 0;
		Utilisateur u;
		pseudo=request.getParameter("pseudo");
		nom=request.getParameter("nom");
		prenom=request.getParameter("prenom");
		email=request.getParameter("email");
		telephone=request.getParameter("telephone");
		rue=request.getParameter("rue");
		codePostal=request.getParameter("cp");
		ville=request.getParameter("ville");
		motDePasse=request.getParameter("motDePasse");
		credit+=100;
		u=new Utilisateur(pseudo, nom, prenom, email,telephone,rue, codePostal, ville, motDePasse, credit, false);
		UtilisateurManager mgerUtilisateur;
        try {
            mgerUtilisateur = new UtilisateurManager();
            mgerUtilisateur.ajoutUtilisateur(u);
        } catch (BLLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}
	
		
		
		else if(request.getServletPath().equals("/annuler")) {
			response.sendRedirect("inde.jsp");
		}
		
		
	}
		
	}
