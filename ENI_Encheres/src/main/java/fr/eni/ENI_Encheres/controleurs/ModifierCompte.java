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
import jakarta.servlet.http.HttpSession;

@WebServlet("/ModifierCompte")
public class ModifierCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Utilisateur utilisateur = (Utilisateur) session.getAttribute("ConnectedUser");

		request.setAttribute("utilisateur", utilisateur);

		request.getRequestDispatcher("ModifierCompte.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("ConnectedUser");
		
		UtilisateurManager um = null;
		try {
			um = new UtilisateurManager();
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		utilisateur.setPseudo(request.getParameter("pseudo"));
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setEmail(request.getParameter("email"));
		utilisateur.setTelephone(request.getParameter("telephone"));
		utilisateur.setRue(request.getParameter("rue"));
		utilisateur.setCodePostal(request.getParameter("codePostal"));
		utilisateur.setVille(request.getParameter("ville"));
	//	int credit = Integer.parseInt(request.getParameter("credit").trim());
	//	boolean administrateur = false;

		String NouveaumotDePasse = request.getParameter("NouveaumotDePasse");

			if (NouveaumotDePasse!=null) {
				
				utilisateur.setMotDePasse(NouveaumotDePasse);
			} 
			
				try {
					um.modifierUtilisateur(utilisateur);
					response.sendRedirect("AfficherMonProfil");
					System.out.println("user modifié");
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	
}
