package edu.disease.asn6;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DiseaseTest {
	
	@Test
    public void testGetDiseaseId() {
        UUID diseaseId = UUID.randomUUID();
        Disease disease = new InfectiousDisease();
        disease.setDiseaseId(diseaseId);

        assertEquals(diseaseId, disease.getDiseaseId());
    }

    @Test
    public void testGetName() {
        Disease disease = new NonInfectiousDisease();
        disease.setName("NonInfectiousDisease");

        assertEquals("NonInfectiousDisease", disease.getName());
    }

    @Test
    public void testEquality() {
        UUID diseaseId = UUID.randomUUID();

        Disease disease1 = new InfectiousDisease();
        disease1.setDiseaseId(diseaseId);
        disease1.setName("InfectiousDisease");

        Disease disease2 = new InfectiousDisease();
        disease2.setDiseaseId(diseaseId);
        disease2.setName("InfectiousDisease");

        Disease disease3 = new NonInfectiousDisease();
        disease3.setDiseaseId(UUID.randomUUID());
        disease3.setName("NonInfectiousDisease");

        assertEquals(disease1, disease2); // Same diseaseId and name
        assertNotEquals(disease1, disease3); // Different diseaseId
    }

    @Test
    public void testDiseaseEquality() {
        UUID diseaseId1 = UUID.randomUUID();
        UUID diseaseId2 = UUID.randomUUID();

        Disease disease1 = new InfectiousDisease();
        disease1.setDiseaseId(diseaseId1);
        disease1.setName("InfectiousDisease");

        Disease disease2 = new NonInfectiousDisease();
        disease2.setDiseaseId(diseaseId1);
        disease2.setName("NonInfectiousDisease");

        Disease disease3 = new NonInfectiousDisease();
        disease3.setDiseaseId(diseaseId2);
        disease3.setName("NonInfectiousDisease");

        assertNotEquals(disease1, disease2); // Same diseaseId
        assertNotEquals(disease1, disease3); // Different diseaseId
    }

    @Test
    public void testDiseaseHashcode() {
        UUID diseaseId1 = UUID.randomUUID();
        UUID diseaseId2 = UUID.randomUUID();

        Disease disease1 = new InfectiousDisease();
        disease1.setDiseaseId(diseaseId1);

        Disease disease2 = new NonInfectiousDisease();
        disease2.setDiseaseId(diseaseId1);

        Disease disease3 = new NonInfectiousDisease();
        disease3.setDiseaseId(diseaseId2);

        assertEquals(disease1.hashCode(), disease2.hashCode()); // Same diseaseId
        assertNotEquals(disease1.hashCode(), disease3.hashCode()); // Different diseaseId
    }

    @Test
    public void testInfectiousDiseaseExamples() {
        Disease infectiousDisease = new InfectiousDisease();
        List<String> examples = infectiousDisease.getExamples();
        List<String> expectedExamples = Arrays.asList("Influenza", "Tuberculosis", "COVID-19", "Malaria");

        assertEquals(expectedExamples, examples);
    }

    @Test
    public void testNonInfectiousDiseaseExamples() {
        Disease nonInfectiousDisease = new NonInfectiousDisease();
        List<String> examples = nonInfectiousDisease.getExamples();
        List<String> expectedExamples = Arrays.asList("Diabetes", "Heart Disease", "Asthma", "Cancer");

        assertEquals(expectedExamples, examples);
    }
    @Test
    public void testToString() {
        UUID diseaseId = UUID.randomUUID();
        Disease disease = new InfectiousDisease();
        disease.setDiseaseId(diseaseId);
        disease.setName("InfectiousDisease");

        String expectedToString = "Disease [diseaseId=" + diseaseId + ", name=InfectiousDisease]";
        assertEquals(expectedToString, disease.toString());
    }
}
