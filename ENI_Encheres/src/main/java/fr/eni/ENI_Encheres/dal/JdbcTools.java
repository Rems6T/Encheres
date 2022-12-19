package fr.eni.ENI_Encheres.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcTools {
	private static Connection connection;
	static {
		// Chargement du driver
		try {
			Class.forName(Settings.getProperties("driver"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			
			connection = DriverManager.getConnection(Settings.getProperties("url"), Settings.getProperties("user"), Settings.getProperties("password"));
		}

		return connection;
	}
	
	public static void closeConnection(){
		if(connection!=null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection=null;
		}
	}
	
}
