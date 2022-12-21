package fr.eni.ENI_Encheres.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.ENI_Encheres.bo.Retrait;
import fr.eni.ENI_Encheres.dal.DALException;
import fr.eni.ENI_Encheres.dal.DAO;
import fr.eni.ENI_Encheres.dal.JdbcTools;

public class RetraitJdbcImpl implements DAO<Retrait> {
	private static final String sqlSelectById = "select * from retraits where no_article=?";
	private static final String sqlSelectAll = "select * from retraits";
	private static final String sqlUpdate = "update  retraits set rue=?,code_postal=?,ville=?   where no_article=?";
	private static final String sqlInsert = "insert into retraits(no_article,rue,code_postal,ville) values(?,?,?,?)";
	private static final String sqlDelete = "delete from retraits where no_article=?";
	@Override
	public Retrait selectById(int id) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		Retrait retrait = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlSelectById);
			rqt.setInt(1, id);

			rs = rqt.executeQuery();
			if (rs.next()) {

				retrait = new Retrait(rs.getInt("no_article"), rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"));

			}

		} catch (SQLException e) {
			throw new DALException("selectByIdRetrait failed - id = " + id, e);
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
		return retrait;
	}
	@Override
	public List<Retrait> selectAll() throws DALException {
		Connection cnx = null;
		Statement rqt = null;
		ResultSet rs = null;
		List<Retrait> liste = new ArrayList<Retrait>();
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.createStatement();
			rs = rqt.executeQuery(sqlSelectAll);
			Retrait retrait = null;

			while (rs.next()) {

				retrait = new Retrait(rs.getInt("no_article"), rs.getString("rue"), rs.getString("code_postal"),
						rs.getString("ville"));
				liste.add(retrait);
			}
		} catch (SQLException e) {
			throw new DALException("selectAllRetrait failed - ", e);
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
	public void update(Retrait data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlUpdate);
			rqt.setString(1, data.getRue());
			rqt.setString(2, data.getCode_postal());
			rqt.setString(3, data.getVille());
		

			rqt.executeUpdate();

		} catch (SQLException e) {
			throw new DALException("Update retrait failed - " + data, e);
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
	public void insert(Retrait data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();
			rqt = cnx.prepareStatement(sqlInsert);
			rqt.setInt(1, data.getNo_article());
			rqt.setString(2, data.getRue());
			rqt.setString(3, data.getCode_postal());
			rqt.setString(4, data.getVille());
			

			

			

		} catch (SQLException e) {
			throw new DALException("Insert retrait failed - " + data, e);
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
	public void delete(Retrait data) throws DALException {
		Connection cnx = null;
		PreparedStatement rqt = null;
		try {
			cnx = JdbcTools.getConnection();

			rqt = cnx.prepareStatement(sqlDelete);
			rqt.setInt(1, data.getNo_article());
			rqt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Delete retrait failed - id=" + data.getNo_article(), e);
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
