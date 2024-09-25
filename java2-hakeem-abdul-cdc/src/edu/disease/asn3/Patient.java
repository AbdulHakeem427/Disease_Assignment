package edu.disease.asn3;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;

import custom_exception.InvalidMaxValueException;

public class Patient implements Comparable<Patient> , Serializable{
	private static final long serialVersionUID = 1L;
	private UUID patientId;
	private String firstName;
	private String lastName;
	private Exposure[] exposures;
	private UUID[] diseaseIds;
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public Patient(int maxDiseases, int maxExposures){
		if (maxDiseases <= 0 || maxExposures <= 0) {
			throw new InvalidMaxValueException("maxDiseases and maxExposure must be positive values");
		}
		this.exposures = new Exposure[maxExposures];
		this.diseaseIds = new UUID[maxDiseases];
		
	}
	
	@Override
	public int compareTo(Patient patient) {
		int lastNameComparison = compareStringsIgnoreCase(this.lastName, patient.lastName);
		if (lastNameComparison != 0) {
			return lastNameComparison;   
		}
	        return compareStringsIgnoreCase(this.firstName, patient.firstName);
	}
	   
	private int compareStringsIgnoreCase(String str1, String str2) {
	    return Objects.compare(str1, str2, Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER));
	}

	public void addDiseaseId(UUID dieaseId) {		 
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
