package fr.eni.ENI_Encheres.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENI_Encheres.bo.Encheres;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.dal.DAO;
import fr.eni.ENI_Encheres.dal.JdbcTools;

public class EnchereJdbcImpl implements DAO<Encheres> {
	private static final String sqlSelectById = "select * from encheres where no_article=?";
	private static final String sqlSelectAll = "select * from encheres";
	private static final String sqlUpdate = "update  encheres set no_utilisateur=?,date_enchere=?,montant_enchere=?   where no_article=?";
	private static final String sqlInsert = "insert into encheres(no_utilisateur,no_article,date_enchere,montant_enchere) values(?,?,?)";
	private static final String sqlDelete = "delete from encheres where no_article=?";
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

				encheres = new Encheres(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getDate("date_enchere"),
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

				encheres = new Encheres(rs.getInt("no_utilisateur"), rs.getInt("no_article"), rs.getDate("date_enchere"),
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
			rqt.setDate(2, data.getDateEnchere());
			rqt.setInt(3, data.getMontantEnchere());
		

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
			rqt.setDate(3, data.getDateEnchere());
			rqt.setInt(4, data.getMontantEnchere());
			

			

			

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
	
	
	
}
