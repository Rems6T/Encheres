package fr.eni.ENI_Encheres.controleurs;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Connexion")

public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupération des données de connexion envoyées par le formulaire de connexion
        String username = request.getParameter("pseudo");
        String password = request.getParameter("motDePasse");

        // Vérification des informations de connexion
        if (username.equals("admin") && password.equals("admin")) {
            // Si les informations de connexion sont correctes, création d'une session utilisateur
            HttpSession session = request.getSession();
            session.setAttribute("pseudo", username);

            // Redirection vers la page d'accueil
            response.sendRedirect("Accueil.jsp");
        } else {
            // Si les informations de connexion sont incorrectes, affichage d'un message d'erreur
            request.setAttribute("errorMessage", "Nom d'utilisateur ou mot de passe incorrect");
            request.getRequestDispatcher("Connexion.jsp").forward(request, response);
        }
    }
}
