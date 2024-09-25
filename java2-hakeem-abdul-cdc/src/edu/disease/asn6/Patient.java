package edu.disease.asn6;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import custom_exception.InvalidMaxValueException;

public class Patient implements Comparable<Patient>, Serializable {
    private static final long serialVersionUID = 1L;
    private UUID patientId;
    private String firstName;
    private String lastName;
    private List<Exposure> exposures;
    private List<UUID> diseaseIds;

    

    public Patient(int maxDiseases, int maxExposures) {
        if (maxDiseases <= 0 || maxExposures <= 0) {
            throw new InvalidMaxValueException("maxDiseases and maxExposure must be positive values");
        }
        this.exposures = new ArrayList<>(maxExposures);
        this.diseaseIds = new ArrayList<>(maxDiseases);
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

    public void addDiseaseId(UUID diseaseId) {
        diseaseIds.add(diseaseId);
    }

    public void addExposure(Exposure exposure) {
        exposures.add(exposure);
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

	public List<Exposure> getExposures() {
		return exposures;
	}

	public void setExposures(List<Exposure> exposures) {
		this.exposures = exposures;
	}

	public List<UUID> getDiseaseIds() {
		return diseaseIds;
	}

	public void setDiseaseIds(List<UUID> diseaseIds) {
		this.diseaseIds = diseaseIds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
                + ", exposures=" + exposures + ", diseaseIds=" + diseaseIds + "]";
    }
}
