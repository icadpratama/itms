package id.gts.itms.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class CommonDAO {
	protected static enum ManipulationType { Insert, Update, Delete }
	private static String url;
	private static String username;
	private static String password;
	
	static{
		try{
			Properties prop = new Properties();
	    	ClassLoader cl = Thread.currentThread().getContextClassLoader();
	    	prop.load(cl.getResourceAsStream("config.properties"));
	
			setUrl(prop.getProperty("db.url"));
			setUsername(prop.getProperty("db.username"));
			setPassword(prop.getProperty("db.password"));
			String dbDriver = prop.getProperty("db.driver");
			
			Class.forName(dbDriver);
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
	
	public static ResultSet executeQuery(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
	}
	
	public static int executeUpdate(PreparedStatement ps) throws SQLException {
		return ps.executeUpdate();
	}

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		CommonDAO.url = url;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		CommonDAO.password = password;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		CommonDAO.username = username;
	}
}
