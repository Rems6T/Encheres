package fr.eni.ENI_Encheres.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.dal.DAO;
import fr.eni.ENI_Encheres.dal.JdbcTools;

public class UtilisateurDAOJdbcImpl implements DAO<Utilisateur> {
	/*
	 * 
	 */

	private static final String sqlSelectById = "select * from utilisateurs where no_utilisateur=?";
	private static final String sqlSelectAll = "select * from utilisateurs";
	private static final String sqlUpdate = "update  Utilisateurs set pseudo=?,nom=?,prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?,credit=?,administrateur=?   where no_utilisateur=?";
	private static final String sqlInsert = "insert into Utilisateurs(pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String sqlDelete = "delete from Utilisateurs where no_utilisateur=?";
	private final static String SELECT_BY_MAIL = "select * from utilisateurs where email=?;";
	private final static String SELECT_BY_PSEUDO = "select * from utilisateurs where pseudo = ?;";

	@Override
	public Utilisateur selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			if (rs.next()) {

				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));

			}

		} catch (SQLException e) {
			throw new DALException("selectByIdUtilisateur failed - id = " + id, e);
		} finally {
			try {

				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcTools.closeConnection();

		}
		return utilisateur;
	}

	@Override
	public List<Utilisateur> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Utilisateur> liste = new ArrayList<Utilisateur>();
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Utilisateur utilisateur = null;

			while (rs.next()) {

				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
				liste.add(utilisateur);
			}
		} catch (SQLException e) {
			throw new DALException("selectAllUtilisateur failed - ", e);
		} finally {
			try {

				if (rqt != null) {
					rqt.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
			JdbcTools.closeConnection();
		}
		return liste;

	}

	@Override
	public void update(Utilisateur data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, data.getPseudo());
			rqt.setString(2, data.getNom());
			rqt.setString(3, data.getPrenom());
			rqt.setString(4, data.getEmail());
			rqt.setString(5, data.getTelephone());
			rqt.setString(6, data.getRue());
			rqt.setString(7, data.getCodePostal());
			rqt.setString(8, data.getVille());
			rqt.setString(9, data.getMotDePasse());
			rqt.setInt(10, data.getCredit());
			rqt.setBoolean(11, data.isAdministrateur());

			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update utilisateur failed - " + data, e);
		} finally {
			try {
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcTools.closeConnection();

		}

	}

	@Override
	public void insert(Utilisateur data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			rqt.setString(1, data.getPseudo());
			rqt.setString(2, data.getNom());
			rqt.setString(3, data.getPrenom());
			rqt.setString(4, data.getEmail());
			rqt.setString(5, data.getTelephone());
			rqt.setString(6, data.getRue());
			rqt.setString(7, data.getCodePostal());
			rqt.setString(8, data.getVille());
			rqt.setString(9, data.getMotDePasse());
			rqt.setInt(10, data.getCredit());
			rqt.setBoolean(11, data.isAdministrateur());

			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					data.setNo_utilisateur(rs.getInt(1));
				}

			}

		} catch (SQLException e) {
			throw new DALException("Insert utilisateur failed - " + data, e);
		} finally {
			try {
				if (rqt != null) {
					rqt.close();
				}

			} catch (SQLException e) {
				throw new DALException("close failed - ", e);
			}
			JdbcTools.closeConnection();

		}

	}

	@Override
	public void delete(Utilisateur data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();

			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, data.getNo_utilisateur());
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete utilisateur failed - id=" + data.getNo_utilisateur(), e);
		} finally {
			try {
				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				throw new DALException("close failed ", e);
			}
			JdbcTools.closeConnection();

		}

	}

	public boolean selectUniquePseudo(String pseudo) throws DALException {

		Connection cnx;
		PreparedStatement stmt = null;
		ResultSet rs;
		boolean ok = true;

		try {
			cnx = JdbcTools.getConnection();
			stmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			stmt.setString(1, pseudo);
			rs = stmt.executeQuery();

			if (rs.next()) {
				ok = false;
			}

		} catch (SQLException e) {
			throw new DALException("probleme avec la methode selectUniquePseudo", e);
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcTools.closeConnection();

		}

		return ok;
	}

	public boolean selectUniqueMail(String mail) throws DALException {

		Connection cnx;
		PreparedStatement stmt = null;
		ResultSet rs;
		boolean ok = true;
		System.out.println(mail);
		try {
			cnx = JdbcTools.getConnection();
			stmt = cnx.prepareStatement(SELECT_BY_MAIL);
			stmt.setString(1, mail);
			rs = stmt.executeQuery();

			if (rs.next()) {
				ok = false;
			}

		} catch (SQLException e) {
			throw new DALException("probleme avec la methode selectUniqueMail", e);
		} finally {
			try {

				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcTools.closeConnection();

		}

		return ok;
	}

	public static Utilisateur selectByPseudo(String pseudo) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Utilisateur utilisateur = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			rqt.setString(1, pseudo);

			rs = rqt.executeQuery();
			if (rs.next()) {

				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"), rs.getString("pseudo"), rs.getString("nom"),
						rs.getString("prenom"), rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));

			}

		} catch (SQLException e) {
			throw new DALException("selectByIPseudo failed - pseudo = " + pseudo, e);
		} finally {
			try {

				if (rqt != null) {
					rqt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JdbcTools.closeConnection();

		}
		return utilisateur;
	}
}
