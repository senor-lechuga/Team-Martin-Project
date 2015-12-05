import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
//import Address;

public class Main{
	public static void main (String [] args){


		SqlHandler h = new SqlHandler();
//               EXAMPLE BELOW
		try{
		h.addAddress("15a", "eggstreet",
		catch(){

		}
// END EXAMPLE

		//MAKE THE GUI FOR MAIN WINDOW.....

		Connection con = null;
		try {
			//Class.forName("org.gjt.mm.mysql.Driver");
			String dB="jdbc:mysql://stusql.dcs.shef.ac.uk/team017?user=team017&password=33b55883";
			con = DriverManager.getConnection(dB);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}finally{
			con.close();
		}
	}
}
