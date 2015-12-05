import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

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
	public TreatmentType getTreatmentType (String name) throws SQLException
	{
		// Construct SQL command below to get a list of all treatment types of that name (should be one):
		String getData = "SELECT (type, cost) FROM treatmentTypes WHERE type = ?";
		PreparedStatement statement = con.prepareStatement(getData);
		statement.setString(1, name);
		ResultSet res = statement.executeQuery();
		if(res.getFetchSize() == 0)
			return null;
		else{
			return (new TreatmentType(res.getString("type"), res.getDouble("cost")));
		}
	}
	public void setTreatmentType (TreatmentType t) throws SQLException
	{
		PreparedStatement statement;
		if (getTreatmentType(name) == null)
		{
			statement = con.prepareStatement("INSERT INTO treatmentTypes (type, cost) VALUES (?,?)");
			statement.setString(1, t.getName());
			statement.setString(2, t.getCost);
		}else{
			statement = con.prepareStatement("UPDATE TABLE treatmentTypes (cost) VALUES (?) WHERE type = ?");
			statement.setString(1, t.getCost());
			statement.setString(2, t.getName());
		}
		statement.execute();
	}
	public void closeConnection()
	{
		if(con != null)
			con.close();
	}
}
