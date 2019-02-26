package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnect 
{
	static Properties dbProperties = null;
	static Connection jdbcConnection = null;
	static FileInputStream fis = null;
	
      //loading the properties
	public static void jdbcProperties() throws IOException {
		dbProperties = new Properties();
		
		fis = new FileInputStream("C:\\eclipse\\workspace\\AirlineReservation\\src\\utility\\JDBC1.properties");
		dbProperties.load(fis);
		fis.close();
	}
      //Establishing the connection
	public static Connection connect() throws ClassNotFoundException, SQLException, IOException {
		jdbcProperties();
		String driver = dbProperties.getProperty("myJDBC.driver");
		String url = dbProperties.getProperty("myJDBC.url");
		String username = dbProperties.getProperty("myJDBC.username");
		String password = dbProperties.getProperty("myJDBC.password");
		System.out.println("Connection establish");
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				
				Class.forName(driver);
				System.out.println("hello");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}

		}
		return jdbcConnection = DriverManager.getConnection(url, username, password);
	}
}
