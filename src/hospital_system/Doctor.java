package hospital_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Doctor extends JFrame{
	
	private PatientManager patientManager;
	private Patient currentPatient;
	
	private JButton patientsTab, appointmentsTab, historyButton, submitButton, nextButton; 
	private JLabel patientsName;
	private JTextArea patientsDiagnosisArea, patientsTreatmentArea;
	
	public Doctor(PatientManager patientManager) {
		this.patientManager = patientManager;
		createDoctorsWindow();
	}
	private void createDoctorsWindow() {
		setTitle("Nevoline Afya Space");
		setSize(400, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
//		Creating a header panel that will hold the navigation buttons
		JPanel headerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints headerCon = new GridBagConstraints();
		headerPanel.setBackground(new Color(135, 206, 235));
		headerPanel.setPreferredSize(new Dimension(400,100));
		
//		Creating a JLabel for the Doctor's name
		JLabel doctorsName = new JLabel("Doctor Neville Khabuchi.");
		headerCon.gridx = 0;
		headerCon.gridy = 0;
		headerCon.gridwidth = 2;
		headerCon.fill = GridBagConstraints.NONE;
		headerCon.insets = new Insets(0, 0, 10, 30);
		headerPanel.add(doctorsName, headerCon);
		
//		Creating a navigation button for patientsTab
		patientsTab = new JButton("PATIENTS");
		patientsTab.setContentAreaFilled(false); //Makes the button blend into the background
		patientsTab.setBorderPainted(false); //Keeps the border but does not paint it
		patientsTab.setBorder(null);// Removes the button's border entirely
		headerCon.gridx = 0;
		headerCon.gridy = 1;
		headerCon.gridwidth = 1;
		headerCon.fill = GridBagConstraints.NONE;
		headerCon.insets = new Insets(0, 10, 1, 10);
		headerPanel.add(patientsTab, headerCon);
		
//		Creating a navigation button for the appointmentsTab
		appointmentsTab = new JButton("APPOINTMENTS");
		appointmentsTab.setContentAreaFilled(false); //Makes the button blend into the background
		appointmentsTab.setBorderPainted(false); //Keeps the border but does not paint it
		appointmentsTab.setBorder(null);// Removes the button's border entirely
		headerCon.gridx = 1;
		headerCon.gridy = 1;
		headerCon.gridwidth = 1;
		headerCon.fill = GridBagConstraints.NONE;
		headerCon.insets = new Insets(0, 10, 1, 10);
		headerPanel.add(appointmentsTab, headerCon);
		
//		Creating a body panel to hold the windows main content
		JPanel bodyPanel = new JPanel(new CardLayout());
		bodyPanel.setPreferredSize(new Dimension(400, 600));
		
		JPanel patientsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
//		Creating a JLabel for the patient's Name
		patientsName = new JLabel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 0, 1, 10);
		patientsPanel.add(patientsName, gbc);
		
//		Creating a JButton for the patient's history
		historyButton = new JButton("HISTORY");
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(10, 0, 1, 10);
		patientsPanel.add(historyButton, gbc);
		
//		Creating a JLabel and JTextArea for paient's diagnosis
		JLabel patientsDiagnosis = new JLabel("Enter the Patient's Diagnosis: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 0, 1, 10);
		patientsPanel.add(patientsDiagnosis, gbc);
		
		patientsDiagnosisArea = new JTextArea(8, 24);
		patientsDiagnosisArea.setLineWrap(true); //Wrap lines to keep text within the area
		patientsDiagnosisArea.setWrapStyleWord(true); //Wrap at word boundaries
//		Add the JTextArea to a JScrollPane (to allow scrolling if needed)
		
		JScrollPane diagnosisPane = new JScrollPane(patientsDiagnosisArea);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.0;
		patientsPanel.add(diagnosisPane, gbc);
		
//		Creating a JLabel and JTextArea for patient's treatment
		JLabel patientsTreatment = new JLabel("Enter the Patient's treatment: ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 0, 1, 10);
		patientsPanel.add(patientsTreatment, gbc);
		
		patientsTreatmentArea = new JTextArea(8, 24);
		patientsTreatmentArea.setLineWrap(true);
		patientsTreatmentArea.setWrapStyleWord(true);
//		Add the JTextArea to a JScrollPane (to allow scrolling if needed)
		
		JScrollPane treatmentPane = new JScrollPane(patientsTreatmentArea);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weightx = 0.0;
		patientsPanel.add(treatmentPane, gbc);
		
//		Creating a JButton for submitting the patient's diagnosis and treatment
		submitButton = new JButton("SUBMIT");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(10, 0, 1, 10);
		patientsPanel.add(submitButton, gbc);
		
//		Creating a JButton for navigating to the next patient
		nextButton = new JButton("NEXT");
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		gbc.insets = (new Insets(10, 0, 1, 10));
		patientsPanel.add(nextButton, gbc);
		
//		Load the first patient
		currentPatient = patientManager.getCurrentPatient();
		if(currentPatient != null) {
			patientsName.setText(currentPatient.getFirstName()+" "+currentPatient.getLastName());
		}else {
			patientsName.setText("No patients in the queue.");
		}
		
		
//		Adding action to the next button
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Patient nextPatient = patientManager.getNextPatient();
				
				if(nextPatient != null) {
					patientsName.setText(nextPatient.getFirstName()+" "+nextPatient.getLastName());
				}else {
					patientsName.setText("No more patients.");
				}
			}
		});
		
//		Adding action to the submit button
		submitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String firstName = currentPatient.getFirstName();
				String lastName = currentPatient.getLastName();
				String dateOfBirth = currentPatient.getDateOfBirth();
				int patientId = currentPatient.getPatientId();
				String patientsDiagnosis = patientsDiagnosisArea.getText();
				String patientsTreatment = patientsTreatmentArea.getText();
				
//				Creating a new patient object for updating patient's details in their records
				Patient newPatient = new Patient(patientId, patientsDiagnosis, patientsTreatment);
				
//				Creating a new patient object for deleting a patient's details in the pending_patients table
				Patient deletePatient = new Patient(firstName, lastName, dateOfBirth, patientId);
				
				if(patientManager.updatePatientsRecords(newPatient)) {
					if(patientManager.deletePatientRecords(deletePatient)) {
						JOptionPane.showMessageDialog(null, "Details updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						if(currentPatient != null) {
							patientsName.setText(currentPatient.getFirstName()+" "+currentPatient.getLastName());
						}else {
							patientsName.setText("No patients in the queue.");
						}
		
						new Doctor(patientManager);
					}
			
					
				}else {
					JOptionPane.showMessageDialog(null, "An error occurred while sending the details to the database", "Error while updating the records", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		 JPanel appointmentsPanel = new JPanel();
		 appointmentsPanel.add(new JLabel("This is the appointments JPanel")); 
		 
		 bodyPanel.add(patientsPanel,"Patients");
		 bodyPanel.add(appointmentsPanel, "Appointments");
		
		add(headerPanel, BorderLayout.NORTH);
		add(bodyPanel, BorderLayout.CENTER);
		
		
		 CardLayout cardLayout = (CardLayout) (bodyPanel.getLayout());
//	     cardLayout.show(bodyPanel, "Card1"); // Ensures first card displays

	     patientsTab.addActionListener(e -> cardLayout.show(bodyPanel, "Patients"));
	     appointmentsTab.addActionListener(e -> cardLayout.show(bodyPanel, "Appointments"));
		
		setLocationRelativeTo(null);
		 setVisible(true);
	}

}
