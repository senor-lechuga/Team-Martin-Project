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

	public void addPatient (Patients p) throws SQLException {
		PreparedStatement statement;
		String add = "INSERT INTO patients (title,firstName,lastName,dob,phone,)"
					+ "VALUES (?,?,?,?,?)";

		statement = con.prepareStatement(add);
		statement.setString (1, p.getTitle());
		statement.setString (2, p.getFirstName());
		statement.setString (3, p.getLastName());
		statement.setString (4, p.dateToString());
		statement.setString (5, p.getPhone());
		statement.execute();
	}

	public void closeConnection () throws SQLException
	{
		if(con != null)
			con.close();
	}
}