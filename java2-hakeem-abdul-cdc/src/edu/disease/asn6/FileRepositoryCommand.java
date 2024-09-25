package edu.disease.asn6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileRepositoryCommand implements Serializable {

	private static final long serialVersionUID = 1L;
    private String folderPath;
    private Container<Disease> diseaseContainer;
    private Container<Patient> patientContainer;

    public FileRepositoryCommand(Container<Disease> diseaseContainer, Container<Patient> patientContainer) {
        this.diseaseContainer = diseaseContainer;
        this.patientContainer = patientContainer;
    }

    public void init(String folderPath) {
        this.folderPath = folderPath;
    }

    public void save(List<Disease> diseases, List<Patient> patients) {
        if (!diseases.isEmpty()) {
            diseaseContainer.setItems(diseases);
        }
        if (!patients.isEmpty()) {
            patientContainer.setItems(patients);
        }

        Command saveDiseaseCommand = new SaveCommand(diseaseContainer, "diseases.dat", folderPath);
        Command savePatientCommand = new SaveCommand(patientContainer, "patients.dat", folderPath);

        saveDiseaseCommand.execute();
        savePatientCommand.execute();
    }

    public DiseaseAndPatient deserialize() {
        Command deserializeDiseaseCommand = new DeserializeCommand("diseases.dat", folderPath, diseaseContainer);
        Command deserializePatientCommand = new DeserializeCommand("patients.dat", folderPath, patientContainer);

        deserializeDiseaseCommand.execute();
        deserializePatientCommand.execute();

        List<Disease> diseases = new ArrayList<>(diseaseContainer.getItems());
        List<Patient> patients = new ArrayList<>(patientContainer.getItems());

        return new DiseaseAndPatient(diseases, patients);
    }
}

interface Command {
    void execute();
}

class SaveCommand implements Command {
    private Container<?> container;
    private String fileName;
    private String folderPath;

    public SaveCommand(Container<?> container, String fileName, String folderPath) {
        this.container = container;
        this.fileName = fileName;
        this.folderPath = folderPath;
    }

    @Override
    public void execute() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(folderPath + File.separator + fileName))) {
            oos.writeObject(container.getItems());
            System.out.println(container.getClass().getSimpleName() + " data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error saving " + container.getClass().getSimpleName() + " data: " + e.getMessage());
        }
    }
}

class DeserializeCommand implements Command {
    private String fileName;
    private String folderPath;
    private Container<?> resultContainer;

    public DeserializeCommand(String fileName, String folderPath, Container<?> resultContainer) {
        this.fileName = fileName;
        this.folderPath = folderPath;
        this.resultContainer = resultContainer;
    }

    @Override
    public void execute() {
    	 try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(folderPath + File.separator + fileName))) {
             List<?> items = (List<?>) ois.readObject();

             // Use a helper method for type conversion
             resultContainer.setItems(convertList(items));
             System.out.println(resultContainer.getClass().getSimpleName() + " data loaded successfully.");
         } catch (IOException | ClassNotFoundException e) {
             e.printStackTrace();
             System.err.println("Error deserializing " + fileName + " data: " + e.getMessage());
         }
     }

     private <T> List<T> convertList(List<?> items) {
         List<T> result = new ArrayList<>();
         for (Object item : items) {
             // Assuming T is the desired type for each item
             result.add((T) item);
         }
         return result;
     }
}