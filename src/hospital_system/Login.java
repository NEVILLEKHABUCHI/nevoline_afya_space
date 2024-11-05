package hospital_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame{
	
	private UserManager userManager;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	
	public Login(UserManager userManager) {
		this.userManager = userManager;
		createLoginWindow();
	}
	private void createLoginWindow() {
		setTitle("Nevoline Afya Space");
		setSize(400,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); //Setting a BorderLayout for the frame
		
//		Creating a header panel that will have the login window welcome message
		JPanel headerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints headerCon = new GridBagConstraints();
		headerPanel.setBackground(new Color(135, 206, 235));
		headerPanel.setPreferredSize(new Dimension(400,150));
		
//		Creating a JLabel with the welcome message
		JLabel welcomeMessage = new JLabel("We Value Your Well Being.");
		welcomeMessage.setFont(new Font("Arial",Font.BOLD,16));
		
		
//		Setting GridBagConstraints to center the welcome in the header panel
		headerCon.weightx = 1.0;
		headerCon.weighty = 1.0;
		headerCon.anchor = GridBagConstraints.CENTER; 
		headerPanel.add(welcomeMessage,headerCon);
		
//		Creating a bodyPanel that will have the login fields
		JPanel bodyPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		bodyPanel.setBackground(Color.decode("#F5F5F5"));
		bodyPanel.setPreferredSize(new Dimension(400,550));
		
//		Creating a username label and field for logging in
		JLabel username = new JLabel("Enter your Username: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 0, 1, 10);
		bodyPanel.add(username,gbc);
		
		usernameField = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx =0.0;
		gbc.fill = GridBagConstraints.NONE;
		bodyPanel.add(usernameField,gbc);
		
//		Creating a password label and field for logging in
		JLabel password = new JLabel("Enter your Password: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 0, 1, 10);
		bodyPanel.add(password, gbc);
		
		passwordField = new JPasswordField(15);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		bodyPanel.add(passwordField,gbc);
		
//		Creating a button for logging in
		loginButton = new JButton("LOG IN");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.CENTER;
		bodyPanel.add(loginButton,gbc);
		
//		Creating a JLabel for signup redirection
		JLabel signupLink = new JLabel("<HTML>Don't have an account? <U>Sign Up</U></HTML>");
		gbc.gridx = 0;
		gbc.gridy = 5;
		bodyPanel.add(signupLink,gbc);
		
//		Action for redirecting to login window when the loginLink is clicked
		signupLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				dispose();
				new Signup(userManager);
			}
		});
		
//		Login action
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				
				User existingUser = userManager.authenticateUser(username, password);
				if(existingUser != null) {//user authenticated successfully
					String role = existingUser.getRole();
					
					PatientManager patientManager = new PatientManager();
					switch (role.toLowerCase()) {
					
					case "doctor":
						new Doctor(patientManager);
						System.out.println("You are now on the doctor's window");
						break;
					case "patient":
						System.out.println("You are now on the patient's window");
						break;
					case "admin":
						new Admin(patientManager);
						System.out.println("You are now on the admin's window");
						break;
						default: 
							JOptionPane.showMessageDialog(null, "Unknown role");
					}
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Login Failed: Invalid Credentials", "Credentials error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		add(headerPanel, BorderLayout.NORTH);
		add(bodyPanel, BorderLayout.SOUTH);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
