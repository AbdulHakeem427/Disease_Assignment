package edu.disease.asn6;

import java.time.LocalDateTime;
import java.util.List;



public class PatientZero{
	private List<Patient> patient;
	private LocalDateTime exposureDateTime;
	private boolean exposed;
	
	public List<Patient> getPatient() {
		return patient;
	}
	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}
	public LocalDateTime getExposureDateTime() {
		return exposureDateTime;
	}
	public void setExposureDateTime(LocalDateTime exposureDateTime) {
		this.exposureDateTime = exposureDateTime;
	}
	public boolean isExposed() {
		return exposed;
	}
	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}
	
}
