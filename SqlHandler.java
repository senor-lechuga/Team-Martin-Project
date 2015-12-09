import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;

public class SqlHandler {

	private Connection con = null;

	public SqlHandler () throws SQLException
	{
		con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team017?user=team017&password=33b55883");
	}

	/**
	 * Attempts to get an instance of a treatment type from the database, given a treatment type name.
	 * @return TreatmentType	the wanted treatment type, or null if not found.
	*/
	public TreatmentTypes getTreatmentType (String name) throws SQLException
	{
		// Construct SQL command below to get a list of all treatment types of that name (should be one):
		String getData = "SELECT (type, cost) FROM treatmentTypes WHERE type = ?";
		PreparedStatement statement = con.prepareStatement(getData);
		statement.setString(1, name);
		ResultSet res = statement.executeQuery();
		if(res.getFetchSize() == 0)
			return null;
		else{
			return (new TreatmentTypes(res.getString("type"), res.getDouble("cost")));
		}
	}
	public void setTreatmentType (TreatmentTypes t) throws SQLException{
		PreparedStatement statement;
		if (t.getTreatmentType() == null)
		{
			statement = con.prepareStatement("INSERT INTO treatmentTypes (type, cost) VALUES (?,?)");
			statement.setString(1, t.getTreatmentType());
			statement.setDouble(2, t.getCost());
		}else{
			statement = con.prepareStatement("UPDATE TABLE treatmentTypes (cost) VALUES (?) WHERE type = ?");
			statement.setDouble(1, t.getCost());
			statement.setString(2, t.getTreatmentType());
		}
		statement.execute();
	}

	public void addAddress (Address a) throws SQLException {
		PreparedStatement statement;
		String add = "INSERT INTO addresses (number,street,district,city,postCode)"
					+ "VALUES (?,?,?,?,?)";

		statement = con.prepareStatement(add);
		statement.setString (1, a.getHouseNumber());
		statement.setString (2, a.getStreetName());
		statement.setString (3, a.getDistrict());
		statement.setString (4, a.getCity());
		statement.setString (5, a.getPostCode());
		statement.execute();
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

	public void createAppointment(Appointments a) throws SQLException{
		PreparedStatement statement;
		String add = "INSERT INTO appointments (patientId,date,startTime,endTime,partner,paid)"
					+ "VALUES (?,?,?,?,?,?)";

		statement = con.prepareStatement(add);
		statement.setInt (1, a.getPatientID());
		statement.setString (2, a.dateToString());
		statement.setString(3, a.timeToString(a.getStartTime()));
		statement.setString (4, a.timeToString(a.getEndTime()));
		statement.setString (5, a.getPartner());
		statement.setBoolean (6, a.isPaid());
		statement.execute();
	}
	
	public void removeAppointment(Appointments a) throws SQLException{
		String removeApp = "DELETE FROM appointments WHERE type = (?,?,?)";
		
		PreparedStatement statement = con.prepareStatement(removeApp);
		statment.setString (1, a.dateToString());
		statement.setString(2, a.timeToString(a.getStartTime()));
		statement.setString(3, a.getPartner());
		statement.execute();
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



	public void closeConnection () throws SQLException
	{
		if(con != null)
			con.close();
	}
}
