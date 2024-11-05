package hospital_system;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
	
	
//	Method to register a user
	public boolean registerUser(User user) {
		Connection connection = DatabaseConnection.getConnection();
		if(connection != null) {
			System.out.println("Database connected successfully.");
		}else {
			System.out.println("Failed to connect to database");
		}
//		Check if user is already registered
		if(isUserRegistered(user.getPhoneNumber())) {
			
			return false;
		}
		else {
			String query = "INSERT INTO USERS (FIRST_NAME, LAST_NAME, USERNAME, PHONE_NUMBER, ROLE, PASSWORD) VALUES(?, ?, ?, ?, ?, ?)";
			try(PreparedStatement pstmt = connection.prepareStatement(query)){
				pstmt.setString(1, user.getFirstName());
				pstmt.setString(2, user.getLastName());
				pstmt.setString(3, user.getUsername());
				pstmt.setInt(4, user.getPhoneNumber());
				pstmt.setString(5, user.getRole());
				pstmt.setString(6, user.getPassword());
				
				pstmt.executeUpdate();
		
				return true; //User added successfully
				
			}catch(SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error registering user!","Database Error",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	}
	
//	Method to check if a user is already registered
	public boolean isUserRegistered(int phoneNumber) {
		Connection connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM USERS WHERE PHONE_NUMBER = ?";
		
		try(PreparedStatement pstmt = connection.prepareStatement(query)){
			
			pstmt.setInt(1, phoneNumber);
			
			ResultSet resultSet =pstmt.executeQuery();
			return resultSet.next(); // Returns true if user exists
			
		} catch(SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,  "Error checking user!", "Database Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
//	Method to authenticate a user during logging in
	public User authenticateUser(String username, String password) {
		Connection connection = DatabaseConnection.getConnection();
		String query = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
		
		try(PreparedStatement pstmt = connection.prepareStatement(query)){
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String role = rs.getString("role"); //Fetch role for access control
				return new User(username, role, password); //Instantiate a User object with relevant data
			}
		}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Login failed:"+ e.getMessage());
		}
		return null; 
	}

}
