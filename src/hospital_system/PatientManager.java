package hospital_system;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class PatientManager {
	private Connection connection;
	private List<Patient> patientCache, pendingPatients;
	private int currentIndex;
	
	public PatientManager() {
		connection = new DatabaseConnection().getConnection();
		patientCache = new ArrayList<>();
		pendingPatients = new ArrayList<>();
		currentIndex = 0;
		loadPatients();
		loadPendingPatients();
	}
//	Method to register a patient
	public boolean registerPatient(Patient patient) {
		
//		Check if user is already registered
		if(isPatientRegistered(patient.getPhoneNumber())) {
			return false;
		}
		else {
			String query = "INSERT INTO PATIENTS (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, PHONE_NUMBER, RESIDENCE, KINS_NAME, KINS_PHONE_NUMBER) VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			try(PreparedStatement pstmt = connection.prepareStatement(query)){
				pstmt.setString(1, patient.getFirstName());
				pstmt.setString(2, patient.getLastName());
				pstmt.setString(3, patient.getDateOfBirth().toString());
				pstmt.setInt(4, patient.getPhoneNumber());
				pstmt.setString(5, patient.getResidence());
				pstmt.setString(6, patient.getKinsName());
				pstmt.setInt(7, patient.getKinsPhoneNumber());
				
				pstmt.executeUpdate();
				
				return true; //Patient added successfully
			}catch(SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error registering user!","Database Error",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	}
	
//		Method to check if a user is already registered
		public boolean isPatientRegistered(int phoneNumber) {
		
		String query = "SELECT * FROM PATIENTS WHERE PHONE_NUMBER = ?";
		
		try(PreparedStatement pstmt = connection.prepareStatement(query)){
			pstmt.setInt(1, phoneNumber);
			
			
			ResultSet resultSet =pstmt.executeQuery();
			return resultSet.next(); // Returns true if user exists
		} catch(SQLException e) {
			
		}
		return false;
	}
	
//		Method to load data from the database and store it in the cache
		private void loadPatients() {
			Connection connection = DatabaseConnection.getConnection();
			
			String query = "SELECT * FROM PATIENTS";
			
			try(PreparedStatement pstmt = connection.prepareStatement(query);
					ResultSet rs = pstmt.executeQuery()){
				
				patientCache.clear(); //Clear existing data if reloading
				
				while (rs.next()) {
					Patient patient = new Patient();
					patient.setFirstName(rs.getString("FIRST_NAME"));
					patient.setLastName(rs.getString("LAST_NAME"));
					patient.setDateOfBirth(rs.getString("DATE_OF_BIRTH"));
					patient.setPatientId(rs.getInt("PATIENT_ID"));
					
					patientCache.add(patient);;
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
//		Method to get all the patients in the patientCache
		public List<Patient> getAllPatients(){
			return patientCache;
		}
		
//		Method to search in the cached data
		public List<Patient> searchPatients(String keyword){
			List<Patient> results = new ArrayList<>();
			for (Patient patient : patientCache) {
				if(patient.getFirstName().toLowerCase().contains(keyword.toLowerCase()) || patient.getLastName().toLowerCase().contains(keyword.toLowerCase())) {
					results.add(patient);
				}
			}
			return results;
		}
		
//		Method to send patient details into the waiting list awaiting to see the doctor
		public Boolean sendPatientDetailsToDatabase(Patient patient) {
			
//			Check if patient has already been sent to the pending list
			if(isPatientAddedToPendingList(patient.getLastName())) {
				return false;
			}
			else {
			String query = "INSERT INTO PENDING_PATIENTS (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, PATIENT_ID) VALUES(?,?,?,?)";
			
			try(PreparedStatement pstmt = connection.prepareStatement(query)){
				pstmt.setString(1, patient.getFirstName());
				pstmt.setString(2, patient.getLastName());
				pstmt.setString(3, patient.getDateOfBirth());
				pstmt.setInt(4,  patient.getPatientId());
				pstmt.executeUpdate();
				
				return true; //Success
				
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
			}
		}
		
//		Method to check whether a patient has already been added to the pending list
		public boolean isPatientAddedToPendingList(String lastName) {
			
			String query = "SELECT * FROM PENDING_PATIENTS WHERE LAST_NAME = ?";
			
			try(PreparedStatement pstmt = connection.prepareStatement(query)){
				pstmt.setString(1, lastName);
				
				
				ResultSet resultSet =pstmt.executeQuery();
				return resultSet.next(); // Returns true if user exists
			} catch(SQLException e) {
				
			}
			return false;
		}
		
//		Method to get all patients from the PENDING_PATIENTS tables
		private void loadPendingPatients() {
			
			String query = "SELECT * FROM PENDING_PATIENTS";
			
			try {
				PreparedStatement pstmt = connection.prepareStatement(query);
				
				ResultSet resultSet = pstmt.executeQuery();
				
				while(resultSet.next()) {
					String firstName = resultSet.getString("FIRST_NAME");
					String lastName = resultSet.getString("LAST_NAME");
					String dateOfBirth = resultSet.getString("DATE_OF_BIRTH");
					int patientId = resultSet.getInt("PATIENT_ID");
					pendingPatients.add(new Patient(firstName, lastName, dateOfBirth, patientId));
				}
			}catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
		
//		Method to get a patient's current index
		public Patient getCurrentPatient() {
			if(currentIndex < pendingPatients.size()) {
				return pendingPatients.get(currentIndex);
			}else {
				return null; //No more patients
			}
		}
		
//		Method to handle the next patient's details
		public Patient getNextPatient() {
			if(currentIndex +1 < pendingPatients.size()) {
				currentIndex++;
				return pendingPatients.get(currentIndex);
			}
			return null; //No more patients
		}
		
		public boolean hasNext() {
			return currentIndex +1< pendingPatients.size();
		}
		
//		Method to remove the current patient after successful submission
		public void removeCurrentPatient() {
			if(currentIndex > 0) {
				pendingPatients.remove(currentIndex - 1);
				currentIndex--; //Adjust index after removal
			}
		}
		
//		Method to update patient's details in the patients_records table in the database
		public boolean updatePatientsRecords(Patient patient) {
			String query ="INSERT INTO PATIENTS_RECORDS(PATIENT_ID, DIAGNOSIS, TREATMENT) VALUES(?,?,?)";
			
			try(PreparedStatement pstmt = connection.prepareStatement(query)) {
				
				pstmt.setInt(1, patient.getPatientId());
				pstmt.setString(2, patient.getDiagnosis());
				pstmt.setString(3, patient.getTreatment());
				pstmt.executeUpdate();
				
				return true; //Updated successfully
				
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
//		Method to delete patient's details in the pending_patients table after the patient's details being successfully sent to patients_history table
		public boolean deletePatientRecords(Patient patient) {
			String query = "DELETE FROM PENDING_PATIENTS WHERE PATIENT_ID = ?";
			
			try(PreparedStatement pstmt = connection.prepareStatement(query)){
				
				pstmt .setInt(1, patient.getPatientId());
				pstmt.executeUpdate();
				
				return true;  //Record deleted successfully
			}catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
//		Method to reset to start at the first patient in the list
		public void resetQueue() {
			currentIndex = 0;
		}
//		

}
