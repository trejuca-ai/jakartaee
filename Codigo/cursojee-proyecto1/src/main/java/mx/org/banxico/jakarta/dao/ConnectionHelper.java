package mx.org.banxico.jakarta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

	private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=sakila;encrypt=false";
	private static final String USUARIO = "sa";
	private static final String PASSWORD = "hola123";
	private static Connection conexion = null;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		getConnection();
	}
	
	public static Connection getConnection() 
			throws ClassNotFoundException, SQLException {
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
		
		return conexion;
	}
	
	public static void closeConnection() throws SQLException {
		if (conexion != null) {
			conexion.close();
		}
	}
}
