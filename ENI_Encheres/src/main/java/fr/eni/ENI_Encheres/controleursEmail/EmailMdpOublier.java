package fr.eni.ENI_Encheres.controleursEmail;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bll.UtilisateurManager;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;

/**
 * Servlet implementation class EmailMdpOublier
 */
@WebServlet("/EmailMdpOublier")
public class EmailMdpOublier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/MotDePasse.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On recupere le mail
		String emailDestinataire = request.getParameter("nom").trim();
		//on teste si il est dans la base de donné
		UtilisateurManager mger;
		Utilisateur utilisateur;
		try {
			 mger = new UtilisateurManager();
			 utilisateur = mger.selectByMail(emailDestinataire);
		} catch (BLLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//provide recipient's email ID
	      String to = "jakartato@example.com";
	      //provide sender's email ID
	      String from = "jakartafrom@example.com";
	      //provide Mailtrap's username
	      final String username = "a094ccae2cfdb3";
	      //provide Mailtrap's password
	      final String password = "82a851fcf4aa33";
	      //provide Mailtrap's host address
	      String host = "smtp.mailtrap.io";
	      //configure Mailtrap's SMTP server details
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");
	      //create the Session object
	      Session session = Session.getInstance(props,
	         new jakarta.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	    }
	         });
	      try {
	    //create a MimeMessage object
	    Message message = new MimeMessage(session);
	    //set From email field
	    message.setFrom(new InternetAddress(from));
	    //set To email field
	    message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(to));
	    //set email subject field
	    message.setSubject("Here comes Jakarta Mail!");
	    //set the content of the email message
	    message.setText("Just discovered that Jakarta Mail is fun and easy to use");
	    //send the email message
	    Transport.send(message);
	    System.out.println("Email Message Sent Successfully");
	      } catch (MessagingException e) {
	         throw new RuntimeException(e);
	      }
	      	//redirection vers page de connexion
	request.getRequestDispatcher("/MotDePasse.jsp").forward(request, response);
	}
	
}
