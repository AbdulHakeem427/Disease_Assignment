package edu.disease.asn1;
import org.junit.jupiter.api.Test;

import custom_exception.InvalidMaxValueException;
import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;

import java.time.LocalDateTime;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void testPatientConstructor()  {
        int maxDiseases = 3;
        int maxExposures = 2;

        Patient patientObj = new Patient(maxDiseases, maxExposures);

        assertEquals(maxDiseases, patientObj.getDiseaseIds().length);
        assertEquals(maxExposures, patientObj.getExposures().length);
    }
    @Test
    void testPatientConstructorWithNegativeValues() {
        int invalidMaxDiseases = 0;
        int invalidMaxExposures = -1;

       InvalidMaxValueException exception = assertThrows( InvalidMaxValueException.class, 
        		() -> new Patient(invalidMaxDiseases, invalidMaxExposures));
        assertEquals("maxDiseases and maxExposure must be positive values", exception.getMessage());
    }
    
    @Test
    void testGetFirstName()  {
        Patient patient = new Patient(3, 2);
        patient.setFirstName("John");
        assertEquals("John", patient.getFirstName());
    }

    @Test
    void testSetFirstName() {
        Patient patient = new Patient(3, 2);
        patient.setFirstName("Alice");
        assertEquals("Alice", patient.getFirstName());
    }

    @Test
    void testGetLastName()  {
        Patient patient = new Patient(3, 2);
        patient.setLastName("Doe");
        assertEquals("Doe", patient.getLastName());
    }

    @Test
    void testToString() {
        UUID patientId = UUID.randomUUID();
        Patient patient = new Patient(3, 2);
        patient.setPatientId(patientId);
        patient.setFirstName("John");
        patient.setLastName("Doe");

        String expected = "Patient [patientId=" + patientId +
                ", firstName=John, lastName=Doe, exposures=[null, null], diseaseIds=[null, null, null]]";

        assertEquals(expected, patient.toString());
    }

    @Test
    void testSetLastName(){
        Patient patient = new Patient(3, 2);
        patient.setLastName("Sam");
        assertEquals("Sam", patient.getLastName());
    }

    @Test
    void testAddDiseaseId() {
        Patient patient = new Patient(3, 2);

        UUID diseaseId1 = UUID.randomUUID();
        UUID diseaseId2 = UUID.randomUUID();
        UUID diseaseId3 = UUID.randomUUID();

        patient.addDiseaseId(diseaseId1);
        patient.addDiseaseId(diseaseId2);
        patient.addDiseaseId(diseaseId3);

        UUID[] diseaseIds = patient.getDiseaseIds();

        assertArrayEquals(new UUID[]{diseaseId1, diseaseId2, diseaseId3}, diseaseIds);
    }

    @Test
    void testIndexOutOfBoundsExceptionForAddDiseaseId(){
        Patient patient = new Patient(2, 2);

        UUID diseaseId1 = UUID.randomUUID();
        UUID diseaseId2 = UUID.randomUUID();
        UUID diseaseId3 = UUID.randomUUID();

        patient.addDiseaseId(diseaseId1);
        patient.addDiseaseId(diseaseId2);

        assertThrows(IndexOutOfBoundsException.class, () -> patient.addDiseaseId(diseaseId3));
    }

    @Test
    void testAddExposure() {
        Patient patient = new Patient(2, 3);

        LocalDateTime datetime1 = LocalDateTime.now();
        UUID patientId1 = UUID.randomUUID();
        String exposureType1 = ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure();
        Exposure exposure1 = new Exposure(datetime1, patientId1, exposureType1);

        LocalDateTime datetime2 = LocalDateTime.now();
        UUID patientId2 = UUID.randomUUID();
        String exposureType2 = ExposureConstantUsingEnum.INDIRECT_EXPOSURE.getExposure();
        Exposure exposure2 = new Exposure(datetime2, patientId2, exposureType2);

        LocalDateTime datetime3 = LocalDateTime.now();
        UUID patientId3 = UUID.randomUUID();
        String exposureType3 = ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure();
        Exposure exposure3 = new Exposure(datetime3, patientId3, exposureType3);

        patient.addExposure(exposure1);
        patient.addExposure(exposure2);
        patient.addExposure(exposure3);

        Exposure[] exposures = patient.getExposures();

        assertArrayEquals(new Exposure[]{exposure1, exposure2, exposure3}, exposures);
    }

    @Test
    void testIndexOutOfBoundsExceptionForAddExposure() {
        Patient patient = new Patient(2, 2);

        LocalDateTime datetime1 = LocalDateTime.now();
        UUID patientId1 = UUID.randomUUID();
        String exposureType1 = ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure();
        Exposure exposure1 = new Exposure(datetime1, patientId1, exposureType1);

        LocalDateTime datetime2 = LocalDateTime.now();
        UUID patientId2 = UUID.randomUUID();
        String exposureType2 = ExposureConstantUsingEnum.INDIRECT_EXPOSURE.getExposure();
        Exposure exposure2 = new Exposure(datetime2, patientId2, exposureType2);

        LocalDateTime datetime3 = LocalDateTime.now();
        UUID patientId3 = UUID.randomUUID();
        String exposureType3 = ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure();
        Exposure exposure3 = new Exposure(datetime3, patientId3, exposureType3);

        patient.addExposure(exposure1);
        patient.addExposure(exposure2);

        assertThrows(IndexOutOfBoundsException.class, () -> patient.addExposure(exposure3));
    }

    @Test
    void testEqualsAndHashMethod()  {
        UUID patientId1 = UUID.randomUUID();
        UUID patientId2 = UUID.randomUUID();

        Patient patient1a = new Patient(3, 2);
        patient1a.setPatientId(patientId1);

        Patient patient1b = new Patient(3, 2);
        patient1b.setPatientId(patientId1);

        Patient patient2 = new Patient(4, 6);
        patient2.setPatientId(patientId2);

        assertTrue(patient1a.equals(patient1a));
        assertTrue(patient1a.equals(patient1b));
        assertTrue(patient1b.equals(patient1a));
        assertFalse(patient1a.equals(patient2));

        assertEquals(patient1a.hashCode(), patient1b.hashCode());
    }
}
