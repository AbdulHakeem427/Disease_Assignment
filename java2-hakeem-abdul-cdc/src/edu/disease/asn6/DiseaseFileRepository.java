package edu.disease.asn6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List; 



/**
 * Manages serialization and deserialization of disease and patient data to files.
 */
public class DiseaseFileRepository implements Serializable {

	private static final long serialVersionUID = 1L;
    private String folderPath;
    private Container<Disease> diseaseContainer;
    private Container<Patient> patientContainer;

    public DiseaseFileRepository(Container<Disease> diseaseContainer, Container<Patient> patientContainer) {
        this.diseaseContainer = diseaseContainer;
        this.patientContainer = patientContainer;
    }

   
    public void init(String folderPath) {
        this.folderPath = folderPath;
    }

    public void save(List<Disease> diseases, List<Patient> patients) {
    	if (!diseases.isEmpty()) {
        //    diseaseContainer.setItems(diseases.toArray(new Disease[0]));//
        }
        if (!patients.isEmpty()) {
         //   patientContainer.setItems(patients.toArray(new Patient[0]));//
        }
        serializeAndSave(diseaseContainer, "diseases.dat");
        serializeAndSave(patientContainer, "patients.dat");
    }

    private void serializeAndSave(Container<?> container, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(folderPath + File.separator + fileName))) {
            oos.writeObject(container);
            System.out.println(container.getClass().getSimpleName() + " data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving " + container.getClass().getSimpleName() + " data: " + e.getMessage());
        }
    }

    public DiseaseAndPatient deserialize() {
        Container<Disease> diseasesContainer = deserialize("diseases.dat");
        Container<Patient> patientsContainer = deserialize("patients.dat");

        List<Disease> diseases = diseasesContainer.getItems();
        List<Patient> patients = patientsContainer.getItems();

        return new DiseaseAndPatient(diseases, patients);
    }

    
	private <T> Container<T> deserialize(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(folderPath + File.separator + fileName))) {
            return (Container<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error deserializing " + fileName + " data: " + e.getMessage());
            return new Container<>(); // Or handle it according to your application's logic
        }
    }
}