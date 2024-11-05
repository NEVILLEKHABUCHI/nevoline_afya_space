package hospital_system;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Admin extends JFrame{
	
	private PatientManager patientManager;
	
	private JButton patientsRegistrationTab, recordsTab, appointmentsTab, billingTab;
	private JTextField patientsFirstNameField, patientsLastNameField, patientsPhoneNumberField, patientsDateOfBirthField, patientsResidenceField, kinsNameField, kinsPhoneNumberField;
	private JButton submitButton;
	private JTextField searchField;
	private JButton searchButton;
	private JTable resultsTable;
	private  DefaultTableModel resultsTableModel; 
	private List<Patient> allPatients;
	
	public Admin(PatientManager patientManager) {
		this.patientManager = patientManager;
		allPatients = patientManager.getAllPatients();
		createAdminsWindow();
	}
	private void createAdminsWindow() {
		setTitle("Nevoline Afya Space");
		setSize(700, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
//		Creating a header panel that will have the navigation buttons
		 JPanel headerPanel = new JPanel(new GridBagLayout());
		 GridBagConstraints headerCon = new GridBagConstraints();
		 headerPanel.setBackground(new Color(135, 206, 235));
		 headerPanel.setPreferredSize(new Dimension(700,100));
		 
//		 Creating a JLabel for the admin's name
		 JLabel adminsName = new JLabel("ADMIN PAGE");
		 adminsName.setForeground(new Color(1, 11, 19));
		 headerCon.gridx = 0;
		 headerCon.gridy = 0;
		 headerCon.gridwidth = 1;
		 headerCon.fill = GridBagConstraints.NONE;
//		 headerCon.anchor = GridBagConstraints.CENTER;
		 headerCon.insets = new Insets(0, 0, 1, 30);
		 headerPanel.add(adminsName, headerCon);
		 
//		 Creating a navigation button; registrationTab
		 patientsRegistrationTab = new JButton("PATIENT REGISTRATION");
		 patientsRegistrationTab.setContentAreaFilled(false); //Makes the button blend into the background
		 patientsRegistrationTab.setBorderPainted(false); //Keeps the border but does not paint it
		 patientsRegistrationTab.setBorder(null);// Removes the button's border entirely
		 headerCon.gridx = 1;
		 headerCon.gridy = 0;
		 headerCon.gridwidth = 1;
		 headerCon.fill = GridBagConstraints.NONE;
		 headerCon.insets = new Insets(0, 10, 1, 10);
		 headerPanel.add(patientsRegistrationTab, headerCon);
		 
//		 Creating a navigation button; recordsTab
		 recordsTab = new JButton("PATIENT RECORDS");
		 recordsTab.setContentAreaFilled(false);
		 recordsTab.setBorderPainted(false);
		 headerCon.gridx = 2;
		 headerCon.gridy = 0;
		 headerCon.gridwidth = 1;
		 headerCon.fill = GridBagConstraints.NONE;
		 headerCon.insets = new Insets(0, 10, 1, 10);
		 headerPanel.add(recordsTab, headerCon);
		 
//		 Creating a navigation button; searchTab
		 billingTab = new JButton("BILLING");
		 billingTab.setContentAreaFilled(false);
		 billingTab.setBorderPainted(false);
		 headerCon.gridx = 3;
		 headerCon.gridy = 0;
		 headerCon.gridwidth = 1;
		 headerCon.fill = GridBagConstraints.NONE;
		 headerCon.insets = new Insets(0, 10, 1, 10);
		 headerPanel.add(billingTab, headerCon);
		 
//		 Creating a navigation button; appointmentsTab
		 appointmentsTab = new JButton("APPOINTMENTS");
		 appointmentsTab.setContentAreaFilled(false);
		 appointmentsTab.setBorderPainted(false);
		 headerCon.gridx = 4;
		 headerCon.gridy = 0;
		 headerCon.gridwidth = 1;
		 headerCon.fill = GridBagConstraints.NONE;
		 headerCon.insets = new Insets(0, 10, 1, 10);
		 headerPanel.add(appointmentsTab, headerCon);
		 
		 JPanel bodyPanel = new JPanel(new CardLayout());
		 bodyPanel.setPreferredSize(new Dimension(700, 800));
		 
//		 Create cards
		 JPanel registrationPanel = new JPanel(new GridBagLayout());
		 GridBagConstraints gbc = new GridBagConstraints();
		 
//		 Creating a JLabel and JTextField for registering a patient's first name
		 JLabel patientsFirstName = new JLabel("Enter Patient's First Name: ");
		 gbc.gridx = 0;
		 gbc.gridy = 0;
		 gbc.gridwidth = 1;
		 gbc.anchor = GridBagConstraints.WEST;
		 gbc.insets = new Insets(10, 0, 1, 10);
		 registrationPanel.add(patientsFirstName, gbc);
		
		 patientsFirstNameField = new JTextField(20);
		 gbc.gridx = 0;
		 gbc.gridy = 1;
		 gbc.gridwidth = 1;
		 gbc.weightx =0.0;
		 gbc.fill = GridBagConstraints.NONE;
		 registrationPanel.add(patientsFirstNameField, gbc);
		 
//		 Creating a JLabel and JTextField for registering a patient's last name
		 JLabel patientsLastName = new JLabel("Enter Patient's Last Name: ");
		 gbc.gridx = 0;
		 gbc.gridy = 2;
		 gbc.gridwidth = 1;
		 gbc.anchor = GridBagConstraints.WEST;
		 gbc.insets = new Insets(10, 0, 1, 10);
		 registrationPanel.add(patientsLastName, gbc);
		 
		 patientsLastNameField = new JTextField(20);
		 gbc.gridx = 0;
		 gbc.gridy = 3;
		 gbc.gridwidth = 1;
		 gbc.weightx = 0.0;
		 gbc.fill = GridBagConstraints.NONE;
		 registrationPanel.add(patientsLastNameField, gbc);
		 
//		 Creating a JLabel and JtextField for registering a patient's data of birth
		 JLabel patientsDateOfBirth = new JLabel("Enter Patient's Date of Birth: ");
		 gbc.gridx = 0;
		 gbc.gridy = 4;
		 gbc.gridwidth = 1;
		 gbc.anchor = GridBagConstraints.WEST;
		 gbc.insets = new Insets(10, 0, 1, 10);
		 registrationPanel.add(patientsDateOfBirth, gbc);
		 
		 patientsDateOfBirthField = new JTextField("yyyy-mm-dd", 20);
		 gbc.gridx = 0;
		 gbc.gridy = 5;
		 gbc.gridwidth = 1;
		 gbc.weightx = 0.0;
		 gbc.fill = GridBagConstraints.NONE;
		 registrationPanel.add(patientsDateOfBirthField, gbc);
		 
//		 Creating a JLabel and JTextField for registering a patient's phone number
		 JLabel patientsPhoneNumber = new JLabel("Enter Patient's Phone Number: ");
		 gbc.gridx = 0;
		 gbc.gridy = 6;
		 gbc.gridwidth = 1;
		 gbc.anchor = GridBagConstraints.WEST;
		 gbc.insets = new Insets(10, 0, 1, 10);
		 registrationPanel.add(patientsPhoneNumber, gbc);
		 
		 patientsPhoneNumberField = new JTextField(20);
		 gbc.gridx = 0;
		 gbc.gridy = 7;
		 gbc.gridwidth = 1;
		 gbc.weightx = 0.0;
		 gbc.fill = GridBagConstraints.NONE;
		 registrationPanel.add(patientsPhoneNumberField, gbc);
		 
//		 Creating a JLabel and JTextField for registering a patient's area of residence
		 JLabel patientsResidence = new JLabel("Enter Patient's Area of Residence: ");
		 gbc.gridx = 0;
		 gbc.gridy = 8;
		 gbc.gridwidth = 1;
		 gbc.anchor = GridBagConstraints.WEST;
		 gbc.insets = new Insets(10, 0, 1, 10);
		 registrationPanel.add(patientsResidence, gbc);
		 
		 patientsResidenceField = new JTextField(20);
		 gbc.gridx = 0;
		 gbc.gridy = 9;
		 gbc.gridwidth = 1;
		 gbc.weightx = 0.0;
		 gbc.fill = GridBagConstraints.NONE;
		 registrationPanel.add(patientsResidenceField, gbc);
		 
//		 Creating a JLabel and JTextField for registering patient's next of kin name
		 JLabel kinsName = new JLabel("Enter Patient's Next of Kin Name: ");
		 gbc.gridx = 0;
		 gbc.gridy = 10;
		 gbc.gridwidth = 1;
		 gbc.weightx = 0.0;
		 gbc.anchor = GridBagConstraints.WEST;
		 gbc.insets = new Insets(10, 0, 1, 10);
		 registrationPanel.add(kinsName, gbc);
		 
		 kinsNameField = new JTextField(20);
		 gbc.gridx = 0;
		 gbc.gridy = 11;
		 gbc.gridwidth = 1;
		 gbc.weightx = 0.0;
		 gbc.fill = GridBagConstraints.NONE;
		 registrationPanel.add(kinsNameField, gbc);
		 
//		 Creating a JPanel and a JTextField for registering the patients next of kin's phone number
		 JLabel kinsPhoneNumber = new JLabel("Enter Next of Kin's Phone Number: ");
		 gbc.gridx = 0;
		 gbc.gridy = 12;
		 gbc.gridwidth = 1;
		 gbc.anchor = GridBagConstraints.WEST;
		 gbc.insets = new Insets(10, 0, 1, 10);
		 registrationPanel.add(kinsPhoneNumber, gbc);
		 
		 kinsPhoneNumberField = new JTextField(20);
		 gbc.gridx = 0;
		 gbc.gridy = 13;
		 gbc.gridwidth = 1;
		 gbc.weightx = 0.0;
		 gbc.fill = GridBagConstraints.NONE;
		 registrationPanel.add(kinsPhoneNumberField, gbc);
		
		 
//		 Creating a button to submit the patient's details
		 submitButton = new JButton("SUBMIT DETAILS");
		 gbc.gridx = 0;
		 gbc.gridy = 14;
		 gbc.anchor = GridBagConstraints.CENTER;
		 registrationPanel.add(submitButton, gbc);
		 
//		 Patient registration action
		 submitButton.addActionListener(new ActionListener() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 String firstName = patientsFirstNameField.getText();
				 String lastName = patientsLastNameField.getText();
				 String dateOfBirth = patientsDateOfBirthField.getText();
				 
//				 Checking whether the date format matches that of the database
				 String datePattern = "^\\d{4}-\\d{2}-\\d{2}$";
				 
				 if(!dateOfBirth.matches(datePattern)) {
//					 Display error message if the format is incorrect
					 JOptionPane.showMessageDialog(null, "Invalid date format. Please enter in YYYY-MM-DD format.", "Format error", JOptionPane.ERROR_MESSAGE);
					 return;
				 }
				 
				 int phoneNumber;
					try {
						phoneNumber =Integer.parseInt(patientsPhoneNumberField.getText());
					}catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter a valid integer.","Error",JOptionPane.ERROR_MESSAGE);
						return; //Exit the action if the phone number is invalid
					}
				 String residence = patientsResidenceField.getText();
				 String kinsName = kinsNameField.getText();
				 int kinsPhoneNumber;
					try {
						kinsPhoneNumber =Integer.parseInt(kinsPhoneNumberField.getText());
					}catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter a valid integer.","Error",JOptionPane.ERROR_MESSAGE);
						return; //Exit the action if the phone number is invalid
					}
				 
//				 Creating a new patient object
				 Patient newPatient = new Patient(firstName, lastName, dateOfBirth, phoneNumber, residence, kinsName, kinsPhoneNumber);
				 if(patientManager.registerPatient(newPatient)) {
					 JOptionPane.showMessageDialog(null, "Patient Registered Successful!","Patient Registered Successful", JOptionPane.INFORMATION_MESSAGE);

//					 Dispose the current window
					 dispose();
//					 Reopen the same window
					 Admin newWindow = new Admin(patientManager);
					 newWindow.setVisible(true);
				 }else {
					 JOptionPane.showMessageDialog(null, "Patient Already Registered","Already registered",JOptionPane.ERROR_MESSAGE);
					 
					 patientsFirstNameField.setText(" ");
					 patientsLastNameField.setText(" ");
					 patientsDateOfBirthField.setText(" ");
					 patientsPhoneNumberField.setText(" ");
					 patientsResidenceField.setText(" ");
					 kinsNameField.setText(" ");
					 kinsPhoneNumberField.setText(" ");
				 }
			 }
		 });
		 
		 
		 JPanel recordsPanel = new JPanel(new GridBagLayout());
		 GridBagConstraints recordsCon = new GridBagConstraints();
		 
//		 Creating a searchField to allow the admin to check whether a patient is registered in the database
		 searchField = new JTextField(28); //28 is the column width
		 searchField.setPreferredSize(new Dimension(300, 30)); //200 is the width while 30 is the height
		 recordsCon.gridx = 0;
		 recordsCon.gridy = 0;
		 recordsCon.gridwidth = 1;
		 recordsCon.anchor = GridBagConstraints.WEST;
		 recordsCon.insets = new Insets(10, 0, 1, 10);
		 recordsPanel.add(searchField, recordsCon);
		 
//		 Creating a search button to allow the admin search for a particular patient in the database
		 searchButton = new JButton("SEARCH");
		 recordsCon.gridx = 1;
		 recordsCon.gridy = 0;
		 recordsCon.gridwidth = 1;
		 recordsPanel.add(searchButton, recordsCon);
		 
//		 Adding an action to the searchButton
		 searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				performSearch();
			}
		 });
		 
//		 Initialize JTable for displaying search results
		 resultsTableModel = new DefaultTableModel(new String[] {"FIRST NAME","LAST NAME","DATE OF BIRTH"},0);
		 resultsTable = new JTable(resultsTableModel);
		 JScrollPane resultsPane = new JScrollPane(resultsTable);
		 resultsPane.setPreferredSize(new Dimension(400, 650));
		 recordsCon.gridx = 0;
		 recordsCon.gridy = 1;
		 recordsCon.gridwidth = 2;
		 recordsCon.fill= GridBagConstraints.NONE;
		 recordsCon.weightx = 0.0;
		 recordsCon.weighty = 0.0;
		 recordsPanel.add(resultsPane, recordsCon);
		 
//		 Method to load all the patients into the table on startup
		 displayResults(allPatients);

//		 Add mouse listener to detect clicks on patient names
		 resultsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = resultsTable.rowAtPoint(e.getPoint());
				int col = resultsTable.columnAtPoint(e.getPoint());
				
//				Check if the "Patient Name" column was clicked
				if(col == 0 || col == 1) { //Assuming "Patient Name" is in the first column
					String patientFirstName = resultsTableModel.getValueAt(row, 0).toString();
					String patientLastName = resultsTableModel.getValueAt(row, 1).toString();
					String patientDateOfBirth = resultsTableModel.getValueAt(row, 2).toString();
					
//					Retrieve patient ID from patientCche using the clicked row
					Patient patient = patientManager.getAllPatients().get(row);
					int patientId = patient.getPatientId();
					
//					Creating an instance of the patient's class
					Patient pendingPatient = new Patient(patientFirstName, patientLastName, patientDateOfBirth,patientId);
					
//					Call method to send patient details to the database
					boolean success = patientManager.sendPatientDetailsToDatabase(pendingPatient);
					
//					Feedback on success or failure
					if(success) {
						JOptionPane.showMessageDialog(null,patientLastName+ " has been added to the pending list successfully", "Added to pending list successfully", JOptionPane.INFORMATION_MESSAGE);
						 displayResults(allPatients);
					}else {
						JOptionPane.showMessageDialog(null, patientLastName+ " has already been added to the pending list", "Exists in the pending list", JOptionPane.ERROR_MESSAGE);
						 displayResults(allPatients);
					}
				}
			}
		 });
		 
		 
		 
		 
		 JPanel searchPanel = new JPanel();
		 searchPanel.add(new JLabel("This is the search panel"));
		 
		 JPanel appointmentsPanel = new JPanel();
		 appointmentsPanel.add(new JLabel("This is the appointments panel"));
		 
		 bodyPanel.add(registrationPanel, "Registration");
		 bodyPanel.add(recordsPanel, "Records");
		 bodyPanel.add(searchPanel, "Search");
		 bodyPanel.add(appointmentsPanel, "Appointments");
		 
		 
		 add(headerPanel, BorderLayout.NORTH);
		 add(bodyPanel, BorderLayout.CENTER);
		 
		 CardLayout cardLayout = (CardLayout) (bodyPanel.getLayout());
	     cardLayout.show(bodyPanel, "Card1"); // Ensures first card displays

	     patientsRegistrationTab.addActionListener(e -> cardLayout.show(bodyPanel, "Registration"));
//	     Adding actionListener to the recordsTab
	     recordsTab.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		displayResults(allPatients);
	    		cardLayout.show(bodyPanel, "Records");
	    	} 
	     });
	     billingTab.addActionListener(e -> cardLayout.show(bodyPanel, "Search"));
	     appointmentsTab.addActionListener(e -> cardLayout.show(bodyPanel, "Appointments"));
	     
			
		 setLocationRelativeTo(null);
		 setVisible(true);
			
	}
	
//	 Method to perform the search
	 private void performSearch() {
		 String keyword = searchField.getText();
		 List<Patient> results = patientManager.searchPatients(keyword);
		 
		 if(results.isEmpty()) {
//			 Show message dialog if no patients are found
			 JOptionPane.showMessageDialog(null, "No Search Patient, Please Register", "Patient Doesn't exist!", JOptionPane.ERROR_MESSAGE);
//			 resultsTableModel.setRowCount(0); //Clear any previous search results from the table
			 searchField.setText("");
		 }else {
			 displayResults(results);
		 }
	
	 }
	 
//	 Method to display results
	 private void displayResults(List<Patient> patients) {
		 resultsTableModel.setRowCount(0); //Clear previous results
		 
		 for (Patient patient : patients) {
			 resultsTableModel.addRow(new Object[] {
				patient.getFirstName(),
				patient.getLastName(),
				patient.getDateOfBirth()
			 });
			 searchField.setText("");
		 }
	 }

}

