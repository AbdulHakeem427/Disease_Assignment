package edu.disease.asn3;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Manages serialization and deserialization of disease and patient data to files.
 */
public class DiseaseFileRepository implements Serializable {
    private static final long serialVersionUID = 1L;

    private String folderPath;

    /**
     * Initializes the DiseaseFileRepository with a folder path.
     *
     * @param folderPath The folder path where data will be serialized/deserialized.
     * @throws IllegalArgumentException If the supplied folder path is null.
     */
    public void init(String folderPath) {
        if (folderPath == null) {
            throw new IllegalArgumentException("Folder path cannot be null.");
        }
        this.folderPath = folderPath;
    }

    /**
     * Serializes and saves disease and patient data to files.
     *
     * @param diseases The array of diseases to save.
     * @param patients The array of patients to save.
     */
    public void save(Disease[] diseases, Patient[] patients) {
        try {
            serializeAndSave(diseases, "diseases.dat");
            serializeAndSave(patients, "patients.dat");
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    private void serializeAndSave(Serializable data, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(folderPath + File.separator + fileName))) {
            oos.writeObject(data);
        }
    }

    /**
     * Deserializes disease and patient data from files.
     *
     * @return An instance of DiseaseAndPatient with deserialized data.
     */
    public DiseaseAndPatient load() {
        try {
            Disease[] diseases = (Disease[]) deserialize("diseases.dat");
            Patient[] patients = (Patient[]) deserialize("patients.dat");

            return new DiseaseAndPatient(diseases, patients);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
            return null;
        }
    }

    private Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(folderPath + File.separator + fileName))) {
            return ois.readObject();
        }
    }
}
