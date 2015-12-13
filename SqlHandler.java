import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
		String getData = "SELECT * FROM addresses WHERE number = ? AND postCode = ?";
		statement = con.prepareStatement(getData);
		statement.setString (1, a.getHouseNumber());
		statement.setString (2, a.getPostCode());
		ResultSet res = statement.executeQuery();
		String getCount = "SELECT COUNT(*) AS count FROM addresses WHERE number = ? AND postCode = ?";
		PreparedStatement countState;
		countState = con.prepareStatement(getCount);
		countState.setString (1, a.getHouseNumber());
		countState.setString (2, a.getPostCode());
		ResultSet count = countState.executeQuery();
		count.first();
		if(count.getInt("count") == 0){
			return null;
		}else{
			res.first();
			return (new Address(res.getString("number"), res.getString("street"),res.getString("district"),res.getString("city"),res.getString("postCode")));
		}
	}

	public Address[] getAllAddresses() throws SQLException {
		PreparedStatement statement = con.prepareStatement("SELECT * FROM addresses");
		ResultSet rs = statement.executeQuery();
		ArrayList<Address> result = new ArrayList<Address>();
		while(rs.next())
		{
			result.add(new Address(rs.getString("number"), rs.getString("street"), rs.getString("district"), rs.getString("city"), rs.getString("postCode")));
		}
		return result.toArray(new Address[result.size()]);
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
	
	public Address getAddressNumPC(String num, String postC) throws SQLException{
		PreparedStatement statement;
		String getData = "SELECT * FROM addresses WHERE number = ? AND postCode = ?";
		statement = con.prepareStatement(getData);
		statement.setString(1,num);
		statement.setString(2,postC);
		String getCount = "SELECT COUNT(*) AS count FROM addresses WHERE number = ? AND postCode = ?";
		ResultSet res = statement.executeQuery();
		PreparedStatement countState;
		countState = con.prepareStatement(getCount);
		countState.setString (1, num);
		countState.setString (2, postC);
		ResultSet count = countState.executeQuery();
		count.first();
		if(count.getInt("count") == 0){
			return null;
		}else{
			res.first();
			return (new Address(res.getString("number"), res.getString("street"),res.getString("district"),res.getString("city"),res.getString("postCode")));
		}
	}

	public void addPatient (Patient p) throws SQLException {
		PreparedStatement statement;
		String add = "INSERT INTO patients (title,firstName,lastName,birthDate,phone,houseNumber,postCode,healthPlan)"
					+ "VALUES (?,?,?,?,?,?,?,?)";
		statement = con.prepareStatement(add);
		statement.setString (1, p.getTitle());
		statement.setString (2, p.getFirstName());
		statement.setString (3, p.getLastName());
		statement.setDate (4, p.getBirthDate());
		statement.setLong (5, p.getPhone());
		statement.setString (6, p.getAddress().getHouseNumber());
		statement.setString	(7, p.getAddress().getPostCode());
		statement.setString (8, p.getHealthcarePlan().getName());
	
		statement.execute();
	}
	
	public Patient getPatientById(int pId) throws SQLException {
		PreparedStatement statement;
		String data = "SELECT * FROM patients WHERE patientID = ?";
		statement = con.prepareStatement(data);
		statement.setInt(1,pId);
		ResultSet res = statement.executeQuery();
		PreparedStatement countState;
		String getCount = "SELECT COUNT(*) AS count FROM patients WHERE patientID = ?";
		countState = con.prepareStatement(getCount);
		countState.setInt(1,pId);
		ResultSet count = countState.executeQuery();
		count.first();
		if(count.getInt("count") == 0){
			return null;
		}else{
				res.first();
				return( new Patient(res.getString("title"),res.getString("firstname"),res.getString("lastname"),res.getDate("birthDate"),res.getLong("phone"),getHealthcarePlan(res.getString("healthPlan")),getAddressNumPC(res.getString("houseNumber"),res.getString("postCode")),res.getInt("patientID")));	
		}
	}

	/*public getPatient (String patientName) throws SQLException {
		PreparedStatement statement;
		String getDate = "SElECT * FROM patient WHERE 
	}*/
	
	
	//add can't overlap apppointments
	public void addAppointment(Appointment a) throws SQLException {
		PreparedStatement statement;
		String add = "INSERT INTO appointments (patientID,date,startTime,endTime,partner,paid)"
					+ "VALUES (?,?,?,?,?,?)";

		statement = con.prepareStatement(add);
		statement.setInt (1, a.getPatient().getPatientID());
		statement.setDate (2, a.getDate());
		statement.setTime(3, a.getStartTime());
		statement.setTime (4, a.getEndTime());
		statement.setString (5, a.getPartner());
		statement.setBoolean (6, a.isPaid());
		statement.execute();
	}

	public void removeAppointment(Appointment a) throws SQLException{
		String removeApp = "DELETE FROM appointments WHERE type = (?,?,?)";

		PreparedStatement statement = con.prepareStatement(removeApp);
		statement.setDate (1, a.getDate());
		statement.setTime(2, a.getStartTime());
		statement.setString(3, a.getPartner());
		statement.execute();
	}
	
	public Appointment[] getAppointmentsByDay(java.sql.Date date) throws SQLException {
		PreparedStatement statement;
		String getAppointments = "SELECT * FROM appointments WHERE date = ?";
		statement = con.prepareStatement(getAppointments);
		System.out.print("date " +date);
		java.sql.Date formatDate = new java.sql.Date (date.getYear()-1900, date.getMonth()-1, date.getDate());
		statement.setDate(1, formatDate);
		System.out.println("date "+formatDate);
		ResultSet res = statement.executeQuery();
		ArrayList<Appointment> result = new ArrayList<Appointment>();
		System.out.println("result " +result);
		while(res.next())
		{
			System.out.println("treatment: " + getTreatmentByTimeDatePartner(res.getTime("startTime"),res.getDate("date"),res.getString("partner")));
			
			result.add(new Appointment(getPatientById(res.getInt("patientID")),res.getDate("date"),res.getTime("startTime"),res.getTime("endTime"),res.getString("partner"),res.getBoolean("paid"),getTreatmentByTimeDatePartner(res.getTime("startTime"),res.getDate("date"),res.getString("partner"))));
		}
		return result.toArray(new Appointment[result.size()]);
	}
	
	public ArrayList<Treatment> getTreatmentByTimeDatePartner(java.sql.Time time,java.sql.Date date, String partner) throws SQLException {
		PreparedStatement statement;
		String getTreatments = "SELECT * FROM treatments NATURAL JOIN treatmentTypes WHERE date = ? AND startTime = ? AND partner = ?";
		statement = con.prepareStatement(getTreatments);
		statement.setDate(1, date);
		statement.setTime(2, time);
		statement.setString(3, partner);
		ResultSet res = statement.executeQuery();
		ArrayList<Treatment> result = new ArrayList<Treatment>();
		while(res.next())
		{
			result.add(new Treatment(res.getString("type"),res.getDouble("cost")));
		}
		return result;
	}
		
		//Treatment (String treatment,double c)
		
	//(Patient p, java.sql.Date dates,java.sql.Time sTime,java.sql.Time eTime, String partners, boolean paid, ArrayList<Treatment> ts)

	//Appointment (Patient p, java.sql.Date dates,java.sql.Time sTime,java.sql.Time eTime, String partners, boolean paid, ArrayList<Treatment> ts)
	
	
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

	public HealthcarePlan[] getAllHealthcarePlans() throws SQLException {
		PreparedStatement statement = con.prepareStatement("SELECT * FROM healthcarePlans");
		ResultSet rs = statement.executeQuery();
		ArrayList<HealthcarePlan> result = new ArrayList<HealthcarePlan>();
		while(rs.next())
		{
			result.add(new HealthcarePlan(rs.getString("name"), rs.getInt("checkups"), rs.getInt("hygiene"), rs.getInt("repairs"), rs.getDouble("monthlyCost")));
		}
		return result.toArray(new HealthcarePlan[result.size()]);
	}

	public HealthcarePlan getHealthcarePlan(String planName) throws SQLException{
		PreparedStatement statement;
		String getData = "SELECT name,checkups,hygiene,repairs,monthlyCost FROM healthcarePlans WHERE name = ? ";
		statement = con.prepareStatement(getData);
		statement.setString(1, planName);
		ResultSet res = statement.executeQuery();

		PreparedStatement countState;
		String getCount = "SELECT COUNT(*) AS count FROM healthcarePlans WHERE name = ?";
		countState = con.prepareStatement(getCount);
		countState.setString(1, planName);
		ResultSet count = countState.executeQuery();

		count.first();
		if(count.getInt("count") == 0){
			return null;
		}else{
		res.first();
			return (new HealthcarePlan(res.getString("name"),res.getInt("checkups"),res.getInt("hygiene"),res.getInt("repairs"),res.getFloat("monthlyCost")));
		}
	}


		// get appointments pass patient - and returns list of associated appointments
		//function to change healthCareplan and update amount of check ups had etc...

		// get appointments pass patient - and returns list of associated appointments

	public void addHealthcarePlan(HealthcarePlan hp) throws SQLException{
		PreparedStatement statement;
		if (getHealthcarePlan(hp.getName()) == null)
		{
			String add = "INSERT INTO healthcarePlans (name,checkups,hygiene,repairs,monthlyCost)"
					+ "VALUES (?,?,?,?,?)";
			statement = con.prepareStatement(add);
			statement.setString (1, hp.getName());
			statement.setInt (2, hp.getCheckups());
			statement.setInt (3, hp.getHygienes());
			statement.setInt (4, hp.getRepairs());
			statement.setDouble (5,hp.getMonthlyCost());
			statement.execute();
		}else{
			String update = "UPDATE healthcarePlans SET name = ?, checkups = ?, hygiene = ?, repairs = ?, monthlyCost = ? WHERE name = ?";
			statement = con.prepareStatement(update);
			statement.setString (1, hp.getName());
			statement.setInt (2, hp.getCheckups());
			statement.setInt (3, hp.getHygienes());
			statement.setInt (4, hp.getRepairs());
			statement.setDouble (5,hp.getMonthlyCost());
			statement.setString(6, hp.getName());
			statement.execute();

		}
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

	public static void main (String[]args){

	//Address address = new Address("egg","poop","eggs","poop","eggs");

	//HealthcarePlan plan = new HealthcarePlan("NHS",6,5,6,50.00);
	//java.sql.Date date = new java.sql.Date(1994,06,05);
	java.sql.Date date1 = new java.sql.Date(2015,12,01);
	System.out.println(date1);
	
	//(String name,int checks,int hygienes,int repairs, double cost)
	//Patient patient = new Patient("Miss","Piggy","Frog",date,87881402011L,plan,address);


	try{
	//HealthcarePlan test = (new SqlHandler().getHealthcarePlan("NHS"));
	//new SqlHandler().addHealthcarePlan(plan);
	//new SqlHandler().addPatient(patient);
	Appointment[] a = new SqlHandler().getAppointmentsByDay(date1);
	System.out.println(a[0]);
	System.out.println(a[1]);
	//System.out.println(test);
	}catch (SQLException ex){
	ex.printStackTrace();
	System.out.println("error"+ ex);
	}
	}
}
