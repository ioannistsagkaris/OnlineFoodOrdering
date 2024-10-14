import java.sql.*;


public class DB_Connection {

	//declarations
	protected static Connection con;
	protected static Statement st;
	protected static ResultSet rs;

	//constructor
	DB_Connection() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/iFood", "root", "3213");
			//con.close();
		} catch (Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
}
