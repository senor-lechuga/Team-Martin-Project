package team017;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Main{

	public static void main (String [] args){

		Address a = new Address("17b", "eggstreet", "14a", "14a", "14a");

		Connection con = null;       //   EXAMPLE BELOW
		SqlHandler h; 
		try{
			SqlHandler h = new SqlHandler();
			h.addAddress (a);
		}
		catch(SQLException ex){
		      ex.printStackTrace();
		//	con.close();
		}catch(ClassNotFoundException e){
			System.out.println("Could not find connection.");
		}finally{
			h.closeConnection();
		}
// END EXAMPLE

		//MAKE THE GUI FOR MAIN WINDOW.....

		/*try {
		//	Class.forName("com.mysql.jdbc.Driver");
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
		}*/	
	}
}
