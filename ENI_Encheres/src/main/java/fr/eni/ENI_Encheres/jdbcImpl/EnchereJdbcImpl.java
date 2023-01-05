package fr.eni.ENI_Encheres.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENI_Encheres.bll.BLLException;
import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.bo.Encheres;
import fr.eni.ENI_Encheres.bo.Utilisateur;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.dal.DAO;
import fr.eni.ENI_Encheres.dal.JdbcTools;

public class EnchereJdbcImpl implements DAO<Encheres> {
	private static final String sqlSelectById = "select * from encheres where no_article=?";
	private static final String sqlSelectByUtil = "select * from encheres where no_utilisateur=?";
	private static final String sqlSelectAll = "select * from encheres";
	private static final String sqlSelectAllByArticle = "select * from encheres where no_article=? ORDER BY montant_enchere";
	private static final String sqlSelectAllByUtil = "select * from encheres where no_utilisateur=? ORDER BY montant_enchere";
	private static final String sqlUpdate = "update  encheres set no_utilisateur=?,date_enchere=?,montant_enchere=?   where no_article=?";
	private static final String sqlInsert = "insert into encheres(no_utilisateur,no_article,date_enchere,montant_enchere) values(?,?,?,?)";
	private static final String sqlDelete = "delete from encheres where no_article=?";
	private static final String GET_ALL_BY_ARTICLE = "SELECT * FROM ENCHERES WHERE no_article=?";
	   private static final String IS_MEILLEUR_ENCHERISSEUR = "SELECT e.no_utilisateur, e.montant_enchere, u.pseudo " +
			    "FROM Encheres e " +
			    "INNER JOIN Articles a on a.no_article = e.no_article " +
			    "INNER JOIN Utilisateurs u on e.no_utilisateur = u.no_utilisateur " +
			    "WHERE e.no_article = ? AND e.no_utilisateur = ? AND a.date_fin_encheres > GETDATE() AND e.montant_enchere = (SELECT MAX(montant_enchere) "+
			    "FROM ENCHERES e2 "+
			    "WHERE e2.no_article = a.no_article)";
	   private static final String SELECT_MEILLEUR_ENCHERISSEUR = "SELECT u.no_utilisateur, u.pseudo, u.nom, u.prenom, u.email, u.telephone, u.rue, u.code_postal, u.ville, u.mot_de_passe, u.credit " +
	            "FROM Encheres e " +
	            "INNER JOIN Articles a on a.no_article = e.no_article " +
	            "INNER JOIN Utilisateurs u on e.no_utilisateur = u.no_utilisateur " +
	            "WHERE e.no_article = ? AND a.date_fin_encheres > GETDATE() AND e.montant_enchere = (SELECT MAX(montant_enchere) "+
	                                                                                                "FROM ENCHERES e2 "+
	                                                                                                "WHERE e2.no_article = a.no_article)";
	@Override
	public Encheres selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Encheres encheres = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			if (rs.next()) {

				encheres = new Encheres(rs.getInt("no_utilisateur"), rs.getInt("no_article"),  rs.getTimestamp("date_enchere").toLocalDateTime(),
						rs.getInt("montant_enchere"));

			}

		} catch (SQLException e) {
			throw new DALException("selectByIdEnchere failed - id = " + id, e);
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
		return encheres;
	}
	public Encheres selectByUtil(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Encheres encheres = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectByUtil);
			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			if (rs.next()) {

				encheres = new Encheres(rs.getInt("no_utilisateur"), rs.getInt("no_article"),  rs.getTimestamp("date_enchere").toLocalDateTime(),
						rs.getInt("montant_enchere"));

			}

		} catch (SQLException e) {
			throw new DALException("selectByIdEnchere failed - id = " + id, e);
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
		return encheres;
	}
	@Override
	public List<Encheres> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Encheres> liste = new ArrayList<Encheres>();
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Encheres encheres = null;

			while (rs.next()) {

				encheres = new Encheres(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getTimestamp("date_enchere").toLocalDateTime(),
						rs.getInt("montant_enchere"));
				liste.add(encheres);
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
	public List<Encheres> selectAllByArticle(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Encheres> liste = new ArrayList<Encheres>();
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectAllByArticle);
			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			Encheres encheres = null;

			while (rs.next()) {

				encheres = new Encheres(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getTimestamp("date_enchere").toLocalDateTime(),
						rs.getInt("montant_enchere"));
				liste.add(encheres);
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
	public List<Encheres> selectAllByUtil(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		List<Encheres> liste = new ArrayList<Encheres>();
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectAllByUtil);
			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			Encheres encheres = null;

			while (rs.next()) {

				encheres = new Encheres(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getTimestamp("date_enchere").toLocalDateTime(),
						rs.getInt("montant_enchere"));
				liste.add(encheres);
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
	public void update(Encheres data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setInt(1, data.getNoUtilisateur());
			rqt.setTimestamp(2,  Timestamp.valueOf(data.getDateEnchere()));

		//	rqt.setDate(2, data.getDateEnchere());
			rqt.setInt(3, data.getMontantEnchere());
			rqt.setInt(4, data.getNoArticle());

			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update encheres failed - " + data, e);
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
	public void insert(Encheres data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlInsert);
			
			rqt.setInt(1, data.getNoUtilisateur());
			rqt.setInt(2, data.getNoArticle());
			rqt.setTimestamp(3,  Timestamp.valueOf(data.getDateEnchere()));


		//	rqt.setDate(3, data.getDateEnchere());
			rqt.setInt(4, data.getMontantEnchere());
			
			rqt.executeUpdate();
			

			

		} catch (SQLException e) {
			throw new DALException("Insert encheres failed - " + data, e);
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
	public void delete(Encheres data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();

			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, data.getNoArticle());
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete encheres failed - id=" + data.getNoArticle(), e);
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
	
public List<Encheres> getAllByArticle(int id) throws SQLException  {
		

		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Encheres> Listeencherespararticle = new ArrayList<Encheres>();


			cnx = JdbcTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Encheres enchere = null;

			while (rs.next()) {

				enchere = new Encheres(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getTimestamp("date_enchere").toLocalDateTime(),
						rs.getInt("montant_enchere"));
				Listeencherespararticle.add(enchere);
			}
		

				if (rqt != null) {
					rqt.close();
				}


			JdbcTools.closeConnection();
		
		return Listeencherespararticle;
	}
public boolean isMeilleurEncherisseur(int noArticle, int noUtilisateur) throws SQLException {
    boolean isMeilleurEncherisseur = false;
    
	Connection cnx = null;
PreparedStatement pstmt = cnx.prepareStatement(IS_MEILLEUR_ENCHERISSEUR);
        pstmt.setInt(1, noArticle);
        pstmt.setInt(2, noUtilisateur);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            isMeilleurEncherisseur = true;
        }

        pstmt.close();
        rs.close();

    return isMeilleurEncherisseur;
}
	 public Utilisateur selectMeilleurEncherisseur(ArticleVendu article) throws BLLException, SQLException {
	        Utilisateur utilisateur = null;
	    	Connection cnx = null;	            
	    	PreparedStatement pstmt = cnx.prepareStatement(SELECT_MEILLEUR_ENCHERISSEUR);
	            pstmt.setInt(1, article.getNoArticle());
	            ResultSet rs = pstmt.executeQuery();

	            if(rs.next()) {
	                utilisateur = new Utilisateur(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
	                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
	                        rs.getInt(11), false);
	                utilisateur.setNo_utilisateur(rs.getInt(1));
	            }

	            rs.close();
	            pstmt.close();

	        return utilisateur;
	    }

}


