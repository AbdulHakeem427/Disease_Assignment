package edu.disease.asn3;

import java.util.UUID;



/**
 * The Interface DiseaseControlManager.
 */
public interface DiseaseControlManager {
	public Disease addDisease(String name, Boolean isInfectious);
	public Disease getDisease(UUID diseaseId);
	public Patient addPatient(String firstName, String lastName, int maxDiseases, int maxExposures);
	public Patient getPatientById(UUID patientId);
	public void addDiseaseToPatient(UUID patientID, UUID diseaseId);
	public void addExposureToPatient(UUID patientId, Exposure exposure);
	;
	
}
