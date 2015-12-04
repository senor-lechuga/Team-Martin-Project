import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main{
	public static void main (String [] args){
		Connection con = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			String dB="jdbc:mysql://stusql.dcs.shef.ac.uk/team017?user=team017&password=33b55883";
			con = DriverManager.getConnection(dB);	
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}