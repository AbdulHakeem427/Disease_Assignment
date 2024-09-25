package edu.disease.asn3;

public class DiseaseAndPatient {
	private Disease[] diseases;
	private Patient[] patients;

	public DiseaseAndPatient(Disease[] diseases, Patient[] patients) {
		this.diseases = diseases;
		this.patients = patients;
	}
	public Disease[] getDiseases() {
		return diseases;
	}
	public void setDiseases(Disease[] diseases) {
		this.diseases = diseases;
	}

	public Patient[] getPatients() {
		return patients;
	}
	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}


}