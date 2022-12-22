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
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
		
		UtilisateurManager um = null;
		try {
			um = new UtilisateurManager();
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//TODO Faire un test pour check que le mail et le pseudo ne sont pas utiulisé(par un autre que lui)
	
			int id = Integer.parseInt(request.getParameter("id").trim());
			String pseudo = request.getParameter("pseudo").trim();
			String nom = request.getParameter("nom").trim();
			String prenom = request.getParameter("prenom").trim();
			String email = request.getParameter("mail").trim();
			String telephone = request.getParameter("telephone").trim();
			String rue = request.getParameter("rue").trim();
			String codePostal = request.getParameter("codePostal").trim();
			String ville = request.getParameter("ville").trim();
			String motDePasse = request.getParameter("motDePasse").trim();
			int credit = Integer.parseInt(request.getParameter("credit").trim());
			boolean administrateur = false;
			boolean pseudoUniqueOK = true;
			boolean emailUniqueOK = true;
			Utilisateur u = new Utilisateur(id,pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse,
					credit, administrateur);
			// verification si le pseudo est correspond
			if (!utilisateur.getPseudo().equals(pseudo)) {
					try {
						pseudoUniqueOK = um.verifUniquePseudo(pseudo);
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
			

			// verification si le l'email est correspond
			if (!utilisateur.getEmail().equals(email)) {
					try {
						emailUniqueOK = um.verifUniqueMail(email);
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}

			// verification si un ou des parametres sont faux
			if (!pseudoUniqueOK || !emailUniqueOK) {

				request.setAttribute("pseudoUniqueOK", pseudoUniqueOK);
				request.setAttribute("emailUniqueOK", emailUniqueOK);
				request.getRequestDispatcher("/ModifierCompte.jsp").forward(request, response);
				
			}

			
				try {
					um.modifierUtilisateur(u);;
					response.sendRedirect(request.getContextPath() + "/PageProfil");
					System.out.println("user modifié");
				} catch (BLLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
	
}
