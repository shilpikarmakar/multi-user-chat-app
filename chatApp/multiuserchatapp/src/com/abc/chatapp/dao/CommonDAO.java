package com.abc.chatapp.dao;
//Contain the DB CRUD Operations
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.abc.chatapp.utils.ConfigReader.getValue;

//Throw Early and Catch Later - It means always throw early but catch later at user end so that the user knows the fault nd gets to know that there is a bug
public interface CommonDAO {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		//Step 1 - Load a driver
		Class.forName(getValue("DRIVER"));	
		//Step 2 - Making a connection
		final String CONNECTION_STRING = getValue("CONNECTION_URL"); //because its fixed it is final
		final String USER_ID = getValue("USERID");
		final String PASSWORD = getValue("PASSWORD");
		Connection con = DriverManager.getConnection(CONNECTION_STRING, USER_ID, PASSWORD);
		if(con != null) {
			System.out.println("Connection Created.......");
//			con.close();
		}
		return con;
		}
}
