package edu.disease.asn6;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;

public class DiseaseFileRepositoryTest {

    private DiseaseControlManagerImpl controlManager;
    private FileRepositoryCommand fileRepository;

    @Test
    public void testSaveAndLoadData() {
    
        controlManager = new DiseaseControlManagerImpl(10, 10);
        fileRepository = new FileRepositoryCommand(
                new Container<>(new Disease[5]),
                new Container<>(new Patient[2])
        );
        fileRepository.init("C:/Users/aabdu/OneDrive/Desktop/hakeem1");
    

    
        Disease flu = controlManager.addDisease("Flu", true);
        Disease cancer = controlManager.addDisease("Cancer", false);

        Patient johnDoe = controlManager.addPatient("John", "Doe", 5, 5);
        Patient janeDoe = controlManager.addPatient("Jane", "Doe", 5, 5);

        controlManager.addDiseaseToPatient(johnDoe.getPatientId(), flu.getDiseaseId());
        controlManager.addDiseaseToPatient(janeDoe.getPatientId(), cancer.getDiseaseId());

        // Create some sample exposures
        Exposure directExposure = new Exposure(LocalDateTime.now(), johnDoe.getPatientId(), ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure());
        Exposure indirectExposure = new Exposure(LocalDateTime.now(), janeDoe.getPatientId(), ExposureConstantUsingEnum.INDIRECT_EXPOSURE.getExposure());

        // Add exposures to patients
        controlManager.addExposureToPatient(johnDoe.getPatientId(), directExposure);
        controlManager.addExposureToPatient(janeDoe.getPatientId(), indirectExposure);

        // Save diseases and patients to files
        fileRepository.save(controlManager.getDiseases(), controlManager.getPatients());

        // Deserialize and retrieve diseases and patients
        DiseaseAndPatient data = fileRepository.deserialize();

        // Assert that the loaded data matches the saved data
        assertNotNull(data);
        assertEquals(2, data.getDiseases().size());
        assertEquals(2, data.getPatients().size());
    }

}
