import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "perpus";
	private final String HOST = "localhost:3306";
	private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	private Connection conn;
	private Statement st;
	private static Connect connn;

	public ResultSet res;
	public ResultSetMetaData resM;
	
	// show data
	public ResultSet execQuery(String q) {
		try {
			res = st.executeQuery(q);
			resM = res.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	// Add Data
	public void execUpdate(String q) {
		try {
			st.executeUpdate(q);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connect getInstance() {
		if(connn == null) return new Connect();
		return connn;
	}
	
	private Connect() {
		
		try {
			conn = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			st = conn.createStatement();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
