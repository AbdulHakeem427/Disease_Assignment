package edu.disease.asn1;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

import custom_exception.InvalidMaxValueException;

public class Patient {
	private UUID patientId;
	private String firstName;
	private String lastName;
	private Exposure[] exposures;
	private UUID[] diseaseIds;

	public Patient(int maxDiseases, int maxExposures) {
		if (maxDiseases <= 0 || maxExposures <= 0){
			throw new InvalidMaxValueException("maxDiseases and maxExposure must be positive values");
		}
		this.exposures = new Exposure[maxExposures];
		this.diseaseIds = new UUID[maxDiseases];
		
	}
	

	public void addDiseaseId(UUID dieaseId) throws  IndexOutOfBoundsException{		 
		if (isDiseaseIdsFull()) {
			throw new IndexOutOfBoundsException("Disease Array is full");
		}
		for (int i = 0; i < diseaseIds.length; i++) {
			if (diseaseIds[i] == null) {
				diseaseIds[i] = dieaseId;
				return;
			}
		}
	}

	public boolean isDiseaseIdsFull() {
		for (UUID diseaseId : diseaseIds) {
			if (diseaseId == null) {
				return false;
			}
		}
		return true;
	}
	public void addExposure(Exposure exposure) {
		if (isExposureArrayFull()) {
			throw new IndexOutOfBoundsException("Exposure Array is full");
		}
		for (int i = 0; i < exposures.length; i++) {
			if (exposures[i] == null) {
				exposures[i] = exposure;
				return;
			}
		}
	}
	private boolean isExposureArrayFull() {
		for (Exposure exposure : exposures) {
			if (exposure == null) {
				return false;
			}
		}
		return true;
	}
	
	public UUID getPatientId() {
		return patientId;
	}

	public void setPatientId(UUID patientId) {
		this.patientId = patientId;
	}

	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Exposure[] getExposures() {
		return exposures;
	}

	
	public UUID[] getDiseaseIds() {
		return diseaseIds;
	}
	@Override
	public int hashCode() {
		return Objects.hash(patientId);
	}
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Patient other = (Patient) obj;
	    return Objects.equals(patientId, other.patientId);
	}
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", exposures=" + Arrays.toString(exposures) + ", diseaseIds=" + Arrays.toString(diseaseIds) + "]";
	}

}
