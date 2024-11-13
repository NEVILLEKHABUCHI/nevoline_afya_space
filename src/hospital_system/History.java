package hospital_system;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class History extends JFrame{
	
	private PatientManager patientManager;
	private String patientName;
	private JButton backButton;
	private JTable historyTable;
	private DefaultTableModel historyTableModel;
	private List<Patient> allRecords;
	
	public History(String patientName, PatientManager patientManager){
		this.patientName = patientName;
		this.patientManager = patientManager;
		allRecords = patientManager.getAllRecords();
		createHistoryWindow();
	}
	
	private void createHistoryWindow() {
		setTitle("Nevoline Afya Space");
		setSize(400,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
//		Creating a header panel that will hold the history window welcome message
		JPanel headerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints headerCon = new GridBagConstraints();
		headerPanel.setBackground(new Color(135, 206, 235));
		headerPanel.setPreferredSize(new Dimension(400,100));
		
		JLabel welcomeMessage = new JLabel("PATIENT'S HISTORY WINDOW");
		welcomeMessage.setFont(new Font("Arial",Font.BOLD,16));
		
//		Setting up GridBagConstraints to center the welcome message in the headerPanel
		headerCon.weightx = 1.0;
		headerCon.weighty = 1.0;
		headerCon.anchor = GridBagConstraints.CENTER; 
		headerPanel.add(welcomeMessage,headerCon);
		
//		Creating a bodyPanel that will accommodate the main components
		JPanel bodyPanel = new JPanel(new GridBagLayout());
		bodyPanel.setPreferredSize(new Dimension(400, 600));
		GridBagConstraints gbc = new GridBagConstraints();
		
//		Creating a JLabel for the patient's name
		JLabel patientsName = new JLabel("PATIENT'S NAME"+patientName);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(10, 10, 1, 0);
		bodyPanel.add(patientsName, gbc);
		
//		Creating a JButton for the back button
		backButton = new JButton("BACK");
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(10, 0, 1, 10);
		bodyPanel.add(backButton, gbc);
		
//		Adding an action to the backButton
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
//		Initializing JTable for displaying the patient's history
		historyTableModel = new DefaultTableModel(new String[] {"DATE OF BIRTH", "DIAGNOSIS", "TREATMENT", "DATE CREATED"}, 0);
		historyTable = new JTable(historyTableModel);
		JScrollPane historyPane = new JScrollPane(historyTable);
		historyPane.setPreferredSize(new Dimension(400, 650));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(10, 10, 10, 10);
		bodyPanel.add(historyPane, gbc);
		
//		Method to load all the records into the table on startup
		displayHistory(allRecords);
		
		add(headerPanel, BorderLayout.NORTH);
		add(bodyPanel, BorderLayout.CENTER);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	private void displayHistory(List<Patient> records) {
		historyTableModel.setRowCount(0); //Clear previous results
		
		if(records.isEmpty()) {
			JOptionPane.showMessageDialog(null, "No history for this patient. It's their first.", "No History", JOptionPane.INFORMATION_MESSAGE);
		}else {
			for (Patient record: records) {
				historyTableModel.addRow(new Object[] {
						record.getDateOfBirth(),
						record.getDiagnosis(),
						record.getTreatment(),
						record.getDateCreated()
				});
			}
		}
	
	}
}
