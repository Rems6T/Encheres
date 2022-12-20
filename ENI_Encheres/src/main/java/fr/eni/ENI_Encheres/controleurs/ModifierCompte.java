package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;

import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Modifier
 */
public class ModifierCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 		getServletContext().getRequestDispatcher("/WEB-INF/ModifierCompte.jsp").forward(request, response);

		int id;
		id=Integer.parseInt(request.getParameter("id"));
		UtilisateurManager mgerUtilisateur;
        try {
            mgerUtilisateur = new UtilisateurManager();
            mgerUtilisateur.getUtilisateurById(id);
        } catch (BLLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 			int id;

	id=Integer.parseInt(request.getParameter("id"));
	UtilisateurManager mgerUtilisateur;
    try {
        mgerUtilisateur = new UtilisateurManager();
        Utilisateur user = mgerUtilisateur.getUtilisateurById(id);
    
		if(request.getServletPath().equals("/enregistrer"))
	{
			user.setPseudo(request.getParameter("pseudo"));
			user.setNom(request.getParameter("nom"));
			user.setPrenom(request.getParameter("prenom"));
			user.setEmail(request.getParameter("email"));
			user.setTelephone(request.getParameter("telephone"));
			user.setRue(request.getParameter("rue"));
			user.setCodePostal(request.getParameter("codePostal"));
			user.setVille(request.getParameter("ville"));
			user.setMotDePasse(request.getParameter("motDePasse"));
			mgerUtilisateur.modifierUtilisateur(user) ;
			response.sendRedirect("Accueil"); 
			System.out.print("Votre compte a été modifié");}

		
		else if(request.getServletPath().equals("/supprimer")) {
			mgerUtilisateur.supprimerUtilisateur(user) ;
			response.sendRedirect("Accueil");
			System.out.print("Votre compte est supprimé");
		}
		
    } catch (BLLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
	}}
		
	


