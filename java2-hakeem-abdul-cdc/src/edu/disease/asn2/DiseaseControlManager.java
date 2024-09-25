package edu.disease.asn2;

import java.util.UUID;



/**
 * The Interface DiseaseControlManager.
 */
public interface DiseaseControlManager {
	public Disease addDisease(String name, Boolean isInfectious);
	public Disease getDisease(UUID diseaseId);
	public Patient addPatient(String firstName, String lastName, int maxDiseases, int maxExposures);
	public Patient getPatient(UUID patientId);
	public void addDiseaseToPatient(UUID patientID, UUID diseaseId);
	public void addExposureToPatient(UUID patientId, Exposure exposure);
}
