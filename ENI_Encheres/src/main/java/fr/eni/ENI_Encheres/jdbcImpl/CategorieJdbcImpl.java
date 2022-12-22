package fr.eni.ENI_Encheres.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENI_Encheres.bo.Categorie;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.dal.DAO;
import fr.eni.ENI_Encheres.dal.JdbcTools;

public class CategorieJdbcImpl implements DAO<Categorie> {
	private static final String sqlSelectById = "select * from categories where no_categorie=?";
	private static final String sqlSelectAll = "select * from categories";
	private static final String sqlUpdate = "update  categories set libelle=?  where no_categorie=?";
	private static final String sqlInsert = "insert into categories(libelle) values(?)";
	private static final String sqlDelete = "delete from categories where no_categorie=?";

	@Override
	public Categorie selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Categorie categorie = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			if (rs.next()) {

				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));

			}

		} catch (SQLException e) {
			throw new DALException("selectByIdCategorie failed - id = " + id, e);
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
		return categorie;
	}

	@Override
	public List<Categorie> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Categorie> liste = new ArrayList<Categorie>();
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Categorie categorie = null;

			while (rs.next()) {

				categorie = new Categorie(rs.getInt("no_categorie"), rs.getString("libelle"));
				liste.add(categorie);
			}
		} catch (SQLException e) {
			throw new DALException("selectAllCategorie failed - ", e);
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
	public void update(Categorie data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, data.getLibelle());
			rqt.setInt(2, data.getNoCategorie());
			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update categorie failed - " + data, e);
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
	public void insert(Categorie data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			rqt.setString(1, data.getLibelle());

			int nbRows = rqt.executeUpdate();
			if (nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if (rs.next()) {
					data.setNoCategorie(rs.getInt(1));
				}

			}

		} catch (SQLException e) {
			throw new DALException("Insert categorie failed - " + data, e);
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
	public void delete(Categorie data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();

			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, data.getNoCategorie());
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete categorie failed - id=" + data.getNoCategorie(), e);
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
