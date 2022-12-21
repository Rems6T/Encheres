package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;

import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/CreerCompte")
public class CreerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//envoie a la page CreerCompte.jsp
		request.getRequestDispatcher("/CreerCompte.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// gerer une nouvelle inscription
		
		// recuperation des saisies utilisateurs
		String pseudo=request.getParameter("pseudo").trim();
		String nom=request.getParameter("nom").trim();
		String prenom=request.getParameter("prenom").trim();
		String mail=request.getParameter("mail").trim();
		String telephone=request.getParameter("telephone").trim();
		String rue=request.getParameter("rue").trim();
		String codePostal=request.getParameter("codePostal").trim();
		String ville=request.getParameter("ville").trim();
		String motDePasse=request.getParameter("motDePasse").trim();
		
		int credit = 100;
		Boolean administrateur = false;
		
		Utilisateur u = new Utilisateur(pseudo, nom, prenom, mail, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
		UtilisateurManager mgerUtilisateur = null;
		try {
			mgerUtilisateur = new UtilisateurManager();
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


            try {
				mgerUtilisateur.ajoutUtilisateur(u);
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		// verification
    		boolean pseudoUniqueOK = false;
    		boolean emailUniqueOK = false;
    		try {
				pseudoUniqueOK = mgerUtilisateur.verifUniquePseudo(pseudo);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				emailUniqueOK = mgerUtilisateur.verifUniqueMail(mail);
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(!pseudoUniqueOK || !emailUniqueOK) {
				request.setAttribute("pseudoUniqueOK", pseudoUniqueOK);
				request.setAttribute("emailUniqueOK", emailUniqueOK);
				request.getRequestDispatcher("/CreerCompte.jsp").forward(request, response);
			} else {
				try {
					mgerUtilisateur.ajoutUtilisateur(u);
					System.out.print("Votre compte a été crée");
					response.sendRedirect(request.getContextPath() + "/Connexion.jsp");
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



		
	}
			
		
	} }

