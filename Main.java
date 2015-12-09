import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
//import Address;

public class Main{

	public static void main (String [] args){

		Address a = new Address("15a", "eggstreet", "14a", "14a", "14a");

		Connection con = null;       //   EXAMPLE BELOW
		try{
		SqlHandler h = new SqlHandler();
			h.addAddress (a);}
		catch(SQLException ex){
      ex.printStackTrace();
		//	con.close();
		}
// END EXAMPLE

		//MAKE THE GUI FOR MAIN WINDOW.....

		try {
			//Class.forName("org.gjt.mm.mysql.Driver");
			String dB="jdbc:mysql://stusql.dcs.shef.ac.uk/team017?user=team017&password=33b55883";
			con = DriverManager.getConnection(dB);
		}
		catch (SQLException ex) {
			ex.printStackTrace();
		}try{
			con.close();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}
