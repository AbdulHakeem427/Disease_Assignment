package edu.disease.asn6;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainApplication {
    public static void main(String[] args) {
    	
        // Create instances of Disease and Patient
        InfectiousDisease flu = new InfectiousDisease();
        flu.setDiseaseId(UUID.randomUUID());
        flu.setName("Flu");

        Patient johnDoe = new Patient(1, 1);
        johnDoe.setPatientId(UUID.randomUUID());
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");
        johnDoe.addDiseaseId(flu.getDiseaseId());

        // Initialize containers with specific instances
        Container<Disease> diseaseContainer = new Container<>(flu);
        Container<Patient> patientContainer = new Container<>(johnDoe);
        DiseaseFileRepository fileRepository = new DiseaseFileRepository(diseaseContainer, patientContainer);

        // Initialize the folder path
        fileRepository.init("C:/Users/aabdu/OneDrive/Desktop/hakeem1/");

        // Save the data
        List<Disease> diseases = new ArrayList<>();
        diseases.add(flu);

        List<Patient> patients = new ArrayList<>();
        patients.add(johnDoe);

        fileRepository.save(diseases, patients);

        // Deserialize and print the deserialized data
        DiseaseAndPatient deserializedData = fileRepository.deserialize();
        System.out.println("Deserialized Diseases: " + deserializedData.getDiseases());
        System.out.println("Deserialized Patients: " + deserializedData.getPatients());
    	
    }
}
