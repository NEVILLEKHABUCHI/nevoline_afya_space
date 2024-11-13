package hospital_system;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserManager userManager = new UserManager(); //Create an instance of UserManager
		new Signup(userManager); //Start with the signup window
//		new History();

	}

}
