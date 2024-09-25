package edu.disease.asn6;

import java.util.List;
import java.util.UUID;



public interface DiseaseControlManager {
	 Disease addDisease(String name, Boolean isInfectious);
	 List<Disease> getDisease(UUID diseaseId);
	 Patient addPatient(String firstName, String lastName, int maxDiseases, int maxExposures);
	 List<Patient> getPatientById(UUID patientId);
	 void addDiseaseToPatient(UUID patientID, UUID diseaseId);
	 void addExposureToPatient(UUID patientId, Exposure exposure);
	
	
}
