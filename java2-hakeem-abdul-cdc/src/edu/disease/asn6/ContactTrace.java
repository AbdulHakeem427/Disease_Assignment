package edu.disease.asn6;

import java.util.ArrayList;
import java.util.List;


import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;


public class ContactTrace {
	private final DiseaseControlManager diseaseControlManager;
    private List<PatientZero> patientZeros;

    public ContactTrace(DiseaseControlManager diseaseControlManager) {
        this.diseaseControlManager = diseaseControlManager;
        this.patientZeros = new ArrayList<>();
    }

    public PatientZero findPatientZero(List<Patient> patient) {
        PatientZero patientZero = new PatientZero();
        findEarliestPatient(patient, patientZero);
        return patientZero;
    }
   
    private void findEarliestPatient(List<Patient> patients, PatientZero patientZero) {
    
//        if (patient != null && ((Patient) patient).getExposures() != null) {
//            for (Exposure exposure : ((Patient) patient).getExposures()) {
    	if (patients != null) {
    	for (Patient currentPatient : patients) {
    	    if (currentPatient.getExposures() != null) {
    	        for (Exposure exposure : currentPatient.getExposures()) {
                System.out.println("Checking exposure: " + exposure);
                if (isValidDirectExposure(exposure) &&
                        (isFirstExposure(patientZero) ||
                                isEarlierExposure(exposure, patientZero))) {
                    updatePatientZero(patients, exposure, patientZero);
                    System.out.println("Updated patient zero: " + patientZero.getPatient());
                    findEarliestPatient(diseaseControlManager.getPatientById(exposure.getPatientId()), patientZero);
                }
            }
    	    }
        }
    	}
    }

    private boolean isValidDirectExposure(Exposure exposure) {
        return exposure != null && ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure().equalsIgnoreCase(exposure.getExposureType());
    }

    private boolean isFirstExposure(PatientZero patientZero) {
        return !patientZero.isExposed();
    }

    private boolean isEarlierExposure(Exposure exposure, PatientZero patientZero) {
        return exposure.getDateTime().isBefore(patientZero.getExposureDateTime());
    }

    private void updatePatientZero(List<Patient> patient, Exposure exposure, PatientZero patientZero) {
        patientZero.setPatient(patient);
        patientZero.setExposureDateTime(exposure.getDateTime());
        patientZero.setExposed(true);

        // Add to the list of PatientZeros
        patientZeros.add(patientZero);
    }

    public List<PatientZero> getPatientZeros() {
        return patientZeros;
    }
}