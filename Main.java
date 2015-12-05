import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
//import Address;

public class Main{
	public static void main (String [] args){
		Connection con = null;;
		try {
			//Class.forName("org.gjt.mm.mysql.Driver");
			String dB="jdbc:mysql://stusql.dcs.shef.ac.uk/team017?user=team017&password=33b55883";
			con = DriverManager.getConnection(dB);

			String addAddress = "INSERT INTO addresses (number,street,district,city,postCode)"
						+ "VALUES (?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(addAddress);
  		        preparedStmt.setString (1, "15a");
			preparedStmt.setString (2, "Edward Street");
			preparedStmt.setString (3, "Sheffield");
			preparedStmt.setString (4, "Sheffield");
			preparedStmt.setString (5, "S3 7Gb");
		        preparedStmt.execute();

		        con.close();
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
