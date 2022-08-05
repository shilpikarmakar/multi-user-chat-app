package com.abc.chatapp.dao;
import java.security.NoSuchAlgorithmException;
//User CRUD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.abc.chatapp.dto.UserDTO;
import com.abc.chatapp.utils.Encryption;

//USER CRUD
public class UserDAO {
//Receive the dto
	public static boolean isLogin(UserDTO userDTO) throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		final String sql = "select userid from users where userid=? and password=?"; //? is for runtime
		try {
			con = CommonDAO.createConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userDTO.getUserid()); //get the value from dto
			String encryptedPwd = Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2,encryptedPwd);
			rs = pstmt.executeQuery();
			return rs.next();
//			if(rs.next())
//			{
//				return true; // returns if there is an entry
//			}
		}
		finally {
			if(rs != null)
			{
				rs.close();
			}
			if(pstmt != null)
			{
				pstmt.close();
			}
			if(con != null)
			{
				con.close();
			}
		}
	}
	
//	public int add(String userid, String password){
//		
//	}
	
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		System.out.println("Rec " + userDTO.getUserid()+ " " + userDTO.getPassword());
		Connection connection = null;
		Statement stmt = null; //query
		try { //Guarded region
		connection = CommonDAO.createConnection(); //Step 1 - Connection create
		//Step 2 - We do a query
		stmt = connection.createStatement();
		//insert into users (userid, password) values('ram','ram123');
		int record = stmt.executeUpdate("insert into users(userid,password)values('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')"); //Insert, Delete, Update
		return record;
		}
		finally { //Always exit apart from System.exit(Resource clean)
			if(stmt != null) {
				connection.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
//		stmt.close(); //it has bugs
//		connection.close();//it has bugs
		
	}

}
