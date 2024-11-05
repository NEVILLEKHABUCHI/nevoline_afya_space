package hospital_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signup extends JFrame{
	
	private UserManager userManager;
	
	private JTextField firstNameField, lastNameField, usernameField, phoneNumberField;
	private JComboBox<String> roleDropdown;
	private JPasswordField passwordField;
	private JButton signupButton;
	
	public Signup(UserManager userManager) {
		this.userManager = userManager;
		createSignupWindow();
	}
	private void createSignupWindow() {
		setTitle("Nevoline Afya Space");
		setSize(400,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout()); //Setting a BorderLayout for the frame
		
//		Creating a headerPanel that will have the hospital's system welcome message.
		JPanel headerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints headerCon = new GridBagConstraints();
		headerPanel.setBackground(new Color(135, 206, 235));
		headerPanel.setPreferredSize(new Dimension(400,150));
		
//		Creating a JLabel with the welcome message
		JLabel welcomeMessage = new JLabel("WELCOME TO NEVOLINE AFYA SPACE.");
		welcomeMessage.setFont(new Font("Arial",Font.BOLD,16));
		
//		Setting Grid Bag Constraints to center the welcomeMessage in the headerPanel
		headerCon.weightx = 1.0;
		headerCon.weighty = 1.0;
		headerCon.anchor = GridBagConstraints.CENTER; 
		headerPanel.add(welcomeMessage,headerCon);
		
		
		
//		Creating a bodyPanel that will have the user registration fields
		JPanel bodyPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		bodyPanel.setBackground(Color.decode("#F5F5F5"));
		bodyPanel.setPreferredSize(new Dimension(400,550));
		
//		Creating a first name label and field for registration
		JLabel firstName = new JLabel("Enter First Name: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 0, 1, 10);
		bodyPanel.add(firstName,gbc);
		
		firstNameField = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx =0.0;
		gbc.fill = GridBagConstraints.NONE;
//		gbc.insets = new Insets(10, 10, 10, 10); 
		bodyPanel.add(firstNameField,gbc);
		
//		Creating a second name label and field for registration
		JLabel lastName = new JLabel("Enter Last Name: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 0, 1, 10);
		bodyPanel.add(lastName,gbc);
		
		lastNameField = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		bodyPanel.add(lastNameField,gbc);
		
//		Creating a username label and field for registration
		JLabel username = new JLabel("Enter Username: ");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 0, 1,10);
		bodyPanel.add(username,gbc);
		
		usernameField = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		bodyPanel.add(usernameField,gbc);
		
//		Creating a phone number label for registration
		JLabel phoneNumber = new JLabel("Enter Phone Number: ");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 0, 1, 10);
		bodyPanel.add(phoneNumber,gbc);
		
		phoneNumberField = new JTextField(15);
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		bodyPanel.add(phoneNumberField,gbc);
		
//		Creating a JLabel for role selection
		JLabel roleLabel = new JLabel("Select your role: ");
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 0, 1, 10);
		bodyPanel.add(roleLabel,gbc);
		
//		Creating a JCombobox(dropdown) for selecting user role
		String[] roles = {"Patient","Admin","Doctor"};
		roleDropdown = new JComboBox<>(roles);
//		Set default selection to "Patient"
		roleDropdown.setSelectedItem("Patient");
		roleDropdown.setPreferredSize(new Dimension(168,19));
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		bodyPanel.add(roleDropdown,gbc);
		
//		Creating a JLabel and password Field for registration
		JLabel passwordLabel = new JLabel("Enter your password: ");
		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 0, 1, 10);
		bodyPanel.add(passwordLabel,gbc);
		
		passwordField = new JPasswordField(15);
		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.gridwidth = 1;
		gbc.weightx = 0.0;
		gbc.fill = GridBagConstraints.NONE;
		bodyPanel.add(passwordField,gbc);
		
		signupButton = new JButton("REGISTER");
		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.anchor = GridBagConstraints.CENTER;
		bodyPanel.add(signupButton,gbc);
		
//		JLabel for login redirection
		JLabel loginLink = new JLabel("<HTML>Already have an account? <U>Login</U></HTML>");
		gbc.gridx = 0;
		gbc.gridy = 13;
		bodyPanel.add(loginLink,gbc);
		
		
		add(headerPanel,BorderLayout.NORTH);
		add(bodyPanel,BorderLayout.SOUTH);
		
//		Action for redirecting to login window when the loginLink is clicked
		loginLink.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				dispose();
				new Login(userManager);
			}
		});
		
		
//		Signup action
		signupButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String username = usernameField.getText();
//				Parse phone number from JTextField
				int phoneNumber;
				try {
					phoneNumber =Integer.parseInt(phoneNumberField.getText());
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter a valid integer.","Error",JOptionPane.ERROR_MESSAGE);
					return; //Exit the action if the phone number is invalid
				}
				String role = (String) roleDropdown.getSelectedItem();
				String password = new String(passwordField.getPassword());
				
//				Creating a new User object
				User newUser = new User(firstName, lastName, username, phoneNumber, role, password);
				if(userManager.registerUser(newUser)) {
					JOptionPane.showMessageDialog(null, "Signup successful!");
					dispose(); //Close signup window
					new Login(userManager);
				}else {
					JOptionPane.showMessageDialog(null, "Signup failed: Username already exists.","Already registered",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		setLocationRelativeTo(null);// Center the window
		setVisible(true);
	}
	

}
