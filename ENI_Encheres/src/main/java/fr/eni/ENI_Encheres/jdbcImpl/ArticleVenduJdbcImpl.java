package fr.eni.ENI_Encheres.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENI_Encheres.bo.ArticleVendu;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.dal.DAO;
import fr.eni.ENI_Encheres.dal.JdbcTools;

public class ArticleVenduJdbcImpl implements DAO<ArticleVendu> {
	private static final String sqlSelectById = "select * from articles_vendus where no_article=?";
	private static final String sqlSelectAll = "select * from articles_vendus";
	private static final String sqlUpdate = "update  articles_vendus set nom_article=?,description=?,date_debut_encheres=?,date_fin_encheres=?,prix_initial=?,prix_vente=?,no_utilisateur=?,no_categorie=?   where no_article=?";
	private static final String sqlInsert = "insert into articles_vendus(nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie) values(?,?,?,?,?,?,?,?)";
	private static final String sqlDelete = "delete from articles_vendus where no_article=?";
	@Override
	public ArticleVendu selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		ArticleVendu articleVendu = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			if (rs.next()) {

				articleVendu = new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"), rs.getString("description"),
						rs.getTimestamp("date_debut_encheres").toLocalDateTime(),
						rs.getTimestamp("date_fin_encheres").toLocalDateTime(),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), 
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), null);

			}

		} catch (SQLException e) {
			throw new DALException("selectByIdArticle failed - id = " + id, e);
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
		return articleVendu;
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<ArticleVendu> liste = new ArrayList<ArticleVendu>();
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			ArticleVendu articleVendu = null;

			while (rs.next()) {

				articleVendu = new ArticleVendu(rs.getInt("no_article"),rs.getString("nom_article"), rs.getString("description"),
					rs.getTimestamp("date_debut_encheres").toLocalDateTime(),
					rs.getTimestamp("date_fin_encheres").toLocalDateTime(),
					//	((Timestamp) rs.getObject(4)).toLocalDateTime(),
					//	((Timestamp) rs.getObject(5)).toLocalDateTime(),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), 
						rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), null);
				liste.add(articleVendu);
			}
		} catch (SQLException e) {
			throw new DALException("selectAllArticles failed - ", e);
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
	public void update(ArticleVendu data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, data.getNomArticle());
			rqt.setString(2, data.getDescription());
			rqt.setTimestamp(3,  Timestamp.valueOf(data.getDateDebutEncheres()));
			rqt.setTimestamp(4,  Timestamp.valueOf(data.getDateFinEncheres()));
          //  data.setDateDebutEncheres(((Timestamp) ((ResultSet) rqt).getObject(4)).toLocalDateTime());
          //  data.setDateFinEncheres(((Timestamp) ((ResultSet) rqt).getObject(5)).toLocalDateTime());
			//rqt.setDate(3, (Date) data.getDateDebutEncheres());
			//rqt.setDate(4, (Date) data.getDateFinEncheres());
			rqt.setInt(5, data.getMiseAPrix());
			rqt.setInt(6, data.getPrixVente());
			rqt.setInt(7, data.getNoUtilisateur());
			rqt.setInt(8, data.getNoCategorie());
			rqt.setInt(9, data.getNoArticle());

			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update article failed - " + data, e);
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
	public void insert(ArticleVendu data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS);
			
			rqt.setString(1, data.getNomArticle());
			rqt.setString(2, data.getDescription());
			rqt.setTimestamp(3,  Timestamp.valueOf(data.getDateDebutEncheres()));
			rqt.setTimestamp(4,  Timestamp.valueOf(data.getDateFinEncheres()));
			//rqt.setDate(3, (Date) data.getDateDebutEncheres());
			//rqt.setDate(4, (Date) data.getDateFinEncheres());
			rqt.setInt(5, data.getMiseAPrix());
			rqt.setInt(6, data.getPrixVente());
			rqt.setInt(7, data.getNoUtilisateur());
			rqt.setInt(8, data.getNoCategorie());
			
			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					data.setNoArticle(rs.getInt(1));
				}

			}

			

		} catch (SQLException e) {
			throw new DALException("Insert article failed - " + data, e);
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
	public void delete(ArticleVendu data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();

			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, data.getNoArticle());
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete article failed - id=" + data.getNoArticle(), e);
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
	}
