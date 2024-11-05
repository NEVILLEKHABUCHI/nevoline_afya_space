package hospital_system;

public class Patient extends Person{
	private int patientId;
	private String dateOfBirth;
	private String residence;
	private String kinsName;
	private int kinsPhoneNumber;
	private String diagnosis;
	private String treatment;
	
//	Default Constructor
	public Patient() {}
	
//	Creating a constructor for the Patient class
	public Patient(String firstName, String lastName, String dateOfBirth, int phoneNumber, String residence, String kinsName, int kinsPhoneNumber) {
		super(firstName, lastName, phoneNumber);
		this.dateOfBirth = dateOfBirth;
		this.residence = residence;
		this.kinsName = kinsName;
		this.kinsPhoneNumber = kinsPhoneNumber;
	}
	
//	Overriding constructor
	public Patient(String firstName, String lastName, String dateOfBirth, int patientId) {
		super(firstName, lastName);
		this.patientId = patientId;
		this.dateOfBirth = dateOfBirth;
	}
	
//	Overriding constructor
//	public Patient(String firstName, String lastName, int patientId) {
//		super(firstName, lastName);
//		this.patientId = patientId;
//	}
	
//	Overriding constructor
	public Patient(int patientId, String diagnosis, String treatment) {
		this.patientId = patientId;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
	}
	
//	Getter and Setter methods for the Patient class
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public String getResidence() {
		return residence;
	}
	
	public String getKinsName() {
		return kinsName;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public int getKinsPhoneNumber() {
		return kinsPhoneNumber;
	}
	
	public String getDiagnosis() {
		return diagnosis;
	}
	
	public String getTreatment() {
		return treatment;
	}
}
