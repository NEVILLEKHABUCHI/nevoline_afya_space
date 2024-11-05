package hospital_system;

// This class encapsulates users' details
public class User extends Person{
	private String username;
	private String role;
	private String password;
	
	public User(String firstName, String lastName, String username, int phoneNumber, String role, String password) {
		super(firstName, lastName, phoneNumber);
		this.username = username;
		this.role = role;
		this.password = password;
	}
	
//	Overloaded constructor for login
	public User(String username, String role, String password) {
		this.username = username;
		this.role = role;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public String getRole() {
		return role;
	}
	public String getPassword() {
		return password;
	}
	

}
