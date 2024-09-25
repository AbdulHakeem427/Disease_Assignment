package edu.disease.asn4;

import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;
import edu.disease.asn3.DiseaseControlManager;
import edu.disease.asn3.Exposure;
import edu.disease.asn3.Patient;



public class ContactTrace {
    private final DiseaseControlManager diseaseControlManager;

    public ContactTrace(DiseaseControlManager diseaseControlManager) {
        this.diseaseControlManager = diseaseControlManager;
    }

    public PatientZero findPatientZero(Patient patient) {
        PatientZero patientZero = new PatientZero();
        findEarliestPatient(patient, patientZero);
        return patientZero;
    }
   

    
    private void findEarliestPatient(Patient patient, PatientZero patientZero) {
        if (patient != null && patient.getExposures() != null) {
            for (Exposure exposure : patient.getExposures()) {
                System.out.println("Checking exposure: " + exposure);

                if (isValidDirectExposure(exposure) &&
                        (isFirstExposure(patientZero) ||isEarlierExposure(exposure, patientZero))) {
                    updatePatientZero(patient, exposure, patientZero);
                    System.out.println("Updated patient zero: " + patientZero.getPatient());
                    findEarliestPatient(diseaseControlManager.getPatientById(exposure.getPatientId()), patientZero);
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

    private void updatePatientZero(Patient patient, Exposure exposure, PatientZero patientZero) {
        patientZero.setPatient(patient);
        patientZero.setExposureDateTime(exposure.getDateTime());
        patientZero.setExposed(true);
    }
}
