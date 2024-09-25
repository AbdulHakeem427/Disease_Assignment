package edu.disease.asn6;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import custom_exception.FirstOrLastNameNullException;
import custom_exception.PatientNotFoundException;

public class DiseaseControlManagerImpl implements DiseaseControlManager {
    private List<Disease> diseases;
    private List<Patient> patients;

    public List<Disease> getDiseases() {
        return diseases;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public DiseaseControlManagerImpl(int maxDiseases,  int maxPatients) {
        if (maxDiseases <= 0 || maxPatients <= 0) {
            throw new IllegalArgumentException("Supplied Integer cannot be used to initialize the lists");
        }
        this.patients = new ArrayList<>(maxPatients);
        this.diseases = new ArrayList<>(maxDiseases);
    }

   
    

    @Override
    public Disease addDisease(String name, Boolean isInfectious) {
    if (name == null) {
        throw new IllegalArgumentException("Provide a valid name");
    }

    Disease newDisease;
    if (isInfectious) {
        newDisease = new InfectiousDisease();
    } else {
        newDisease = new NonInfectiousDisease();
    }

    newDisease.setName(name);
    newDisease.setDiseaseId(UUID.randomUUID());

    // Add the new disease to the list
    diseases.add(newDisease);

    return newDisease;

    }
   

    @Override
    public List<Disease> getDisease(UUID diseaseId) {
        List<Disease> matchingDiseases = new ArrayList<>();

        for (Disease disease : diseases) {
            if (disease.getDiseaseId().equals(diseaseId)) {
                matchingDiseases.add(disease);
            }
        }

        return matchingDiseases.isEmpty() ? null : matchingDiseases;
    }

    @Override
    public Patient addPatient(String firstName, String lastName, int maxDiseases, int maxExposures) {
        if (firstName == null) {
            throw new FirstOrLastNameNullException(" FirstName attribute  is Null ");
        }
        if (lastName == null) {
            throw new FirstOrLastNameNullException("LastName attribute  is Null ");
        }
       
       Patient patient = new Patient(maxDiseases, maxExposures);
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPatientId(UUID.randomUUID());

        patients.add(patient);

        return patient;
    }

    
    @Override
    public List<Patient> getPatientById(UUID patientId) {
        List<Patient> matchingPatients = new ArrayList<>();

        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                matchingPatients.add(patient);
            }
        }

        return matchingPatients.isEmpty() ? null : matchingPatients;
    }
    @Override
    public  void addDiseaseToPatient(UUID patientId, UUID diseaseId) {
    Patient patient = findPatientById(patientId);
    Disease disease = findDiseaseById(diseaseId);

    System.out.println("Patient ID: " + patientId);
    System.out.println("Disease ID: " + diseaseId);

    if (patient != null) {
        System.out.println("Patient found: " + patient);
    } else {
        System.out.println("Patient not found");
    }

    if (disease != null) {
        System.out.println("Disease found: " + disease);
    } else {
        System.out.println("Disease not found");
    }

    if (patient != null && disease != null) {
        patient.addDiseaseId(disease.getDiseaseId());
    } else {
        throw new IllegalArgumentException("PatientId and DiseaseId not found");
    }
    }

    private Patient findPatientById(UUID patientId) {
        return patients.stream()
                .filter(patient -> patient.getPatientId().equals(patientId))
                .findFirst()
                .orElse(null);
    }

    private Disease findDiseaseById(UUID diseaseId) {
        return diseases.stream()
                .filter(disease -> disease.getDiseaseId().equals(diseaseId))
                .findFirst()
                .orElse(null);
    }
    

    @Override
    public void addExposureToPatient(UUID patientId, Exposure exposure) {
        for (Patient patient : patients) {
            if (patient.getPatientId().equals(patientId)) {
                patient.addExposure(exposure);
                System.out.println("Exposure added to patient successfully.");
                return;
            }
        }
        throw new IllegalArgumentException("PatientId is not associated with any patient in the list");
    }
    

}
