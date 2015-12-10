import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class SqlHandler {

	private Connection con = null;

	public SqlHandler () throws SQLException
	{
		//Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team017?user=team017&password=33b55883");
	}

	/**
	 * Attempts to get an instance of a treatment type from the database, given a treatment type name.
	 * @return TreatmentType	the wanted treatment type, or null if not found.
	*/
	public Treatment getTreatmentType (String name) throws SQLException
	{
		// Construct SQL command below to get a list of all treatment types of that name (should be one):
		String getData = "SELECT (type, cost) FROM treatmentTypes WHERE type = ?";
		PreparedStatement statement = con.prepareStatement(getData);
		statement.setString(1, name);
		ResultSet res = statement.executeQuery();
		if(res.getFetchSize() == 0)
			return null;
		else{
			return (new Treatment(res.getString("type"), res.getDouble("cost")));
		}
	}
	public void setTreatmentType (Treatment t) throws SQLException{
		PreparedStatement statement;
		if (t.getTreatmentType() == null)
		{
			statement = con.prepareStatement("INSERT INTO treatmentTypes (type, cost) VALUES (?,?)");
			statement.setString(1, t.getTreatmentType());
			statement.setDouble(2, t.getCost());
		}else{
			statement = con.prepareStatement("UPDATE treatmentTypes SET (cost) = ? WHERE type = ?");
			statement.setDouble(1, t.getCost());
			statement.setString(2, t.getTreatmentType());
		}
		statement.execute();
	}
	
	public Address getAddress(Address a) throws SQLException {
		PreparedStatement statement;
		String getData = "SELECT (number,street,district,city,postCode) FROM addresses WHERE number = ? AND postCode = ?";
		statement = con.prepareStatement(getData);
		statement.setString (1, a.getHouseNumber());
		statement.setString (2, a.getPostCode());
		ResultSet res = statement.executeQuery();
		if(res.getFetchSize() == 0){
			return null;
		}else{
			return (new Address(res.getString("number"), res.getString("street"),res.getString("district"),res.getString("city"),res.getString("postCode")));
		}	
	}

	public void setAddress (Address a) throws SQLException {
		PreparedStatement statement;
		if (getAddress(a) == null)
		{
			String add = "INSERT INTO addresses (number,street,district,city,postCode)"
					+ "VALUES (?,?,?,?,?)";
			statement = con.prepareStatement(add);
			statement.setString (1, a.getHouseNumber());
			statement.setString (2, a.getStreetName());
			statement.setString (3, a.getDistrict());
			statement.setString (4, a.getCity());
			statement.setString (5, a.getPostCode());
			statement.execute();
		}else{
			String update = "UPDATE`addresses` SET number = ?, street = ?, district = ?, city = ?,  postCode = ? WHERE name = ? AND postCode = ?";
			statement = con.prepareStatement(update);
			statement.setString (1, a.getHouseNumber());
			statement.setString (2, a.getStreetName());
			statement.setString (3, a.getDistrict());
			statement.setString (4, a.getCity());
			statement.setString (5, a.getPostCode());
			statement.setString (6, a.getHouseNumber());
			statement.setString (7, a.getPostCode());
		}
	}

	public void addPatient (Patient p) throws SQLException {
		PreparedStatement statement;
		String add = "INSERT INTO patients (title,firstName,lastName,birthDate,phone)"
					+ "VALUES (?,?,?,?,?)";

		statement = con.prepareStatement(add);
		statement.setString (1, p.getTitle());
		statement.setString (2, p.getFirstName());
		statement.setString (3, p.getLastName());
		statement.setString (4, p.dateToString());
		statement.setString (5, p.getPhone());
		statement.execute();
	}

	public void addAppointment(Appointment a) throws SQLException {
		PreparedStatement statement;
		String add = "INSERT INTO appointments (patientID,date,startTime,endTime,partner,paid)" 
					+ "VALUES (?,?,?,?,?,?)";

		statement = con.prepareStatement(add);
		statement.setInt (1, a.getPatient().getPatientID());
		statement.setString (2, a.dateToString());
		statement.setString(3, a.timeToString(a.getStartTime()));
		statement.setString (4, a.timeToString(a.getEndTime()));
		statement.setString (5, a.getPartner());
		statement.setBoolean (6, a.isPaid());
		statement.execute();
	}
	
	public void removeAppointment(Appointment a) throws SQLException{
		String removeApp = "DELETE FROM appointments WHERE type = (?,?,?)";
		
		PreparedStatement statement = con.prepareStatement(removeApp);
		statement.setString (1, a.dateToString());
		statement.setString(2, a.timeToString(a.getStartTime()));
		statement.setString(3, a.getPartner());
		statement.execute();
	}
	
	/*public ArrayList<Appointment> getAppointments (String patientName) throws SQLException
	{
		String getPID = "SELECT (patientID) FROM patient Where type = ?";
		PreparedStatement statement1 = con.prepareStatement(getPID);
		statement1.setString(1,patientName);
		Result resID = statement.executeQuery();
		if(resID.getFetchSize() == 0)
			return null;
		else{
			String getData = "SELECT (patientID, date, startTime, endTime, partner, paid,) FROM appointments NATURAL JOIN treatments NATURAL JOIN treatmentTypes  WHERE type = (?)";
			PreparedStatement statement = con.prepareStatement(getData);
			statement.setString(1, resID.getInt("patientID"));
			ResultSet resData = statement.executeQuery();
			if(resData.getFetchSize() == 0)
				return null;
			else{
			ArrayList <String> result = new ArrayList<String>();
				while (rs.next()){
					result.add( new Appointment(res.getDate("date"), res.getDate("startTime"),res.getDate("endTime"),res.getString("partner"),res.getBool("paid"),res.get));
				}
			return result;
			}	
		}
	}
		*/
		
	public HealthcarePlan getHealthcarePlan(HealthcarePlan hp) throws SQLException{
		
	}
		
	public void addHealthcarePlan(HealthcarePlan hp) throws SQLException{
		PreparedStatement statement;
		String add = "INSERT INTO healthcarePlan (name,checkups,hygiene,repairs,monthlyCost)"
					+ "VALUES (?,?,?,?,?)";

		statement = con.prepareStatement(add);
		statement.setString (1, hp.getName());
		statement.setInt (2, hp.getCheckups());
		statement.setInt (3, hp.getHygiene());
		statement.setInt (4, hp.getRepair());
		statement.setDouble (5,hp.getMonthlyCost());
		statement.execute();
	}



	public void closeConnection ()
	{
		if(con != null)
		{
			try{
				con.close();
				System.out.println("SQL database connection closed.");
			}catch(SQLException e){
				System.out.println("Fatal error:");
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
}
