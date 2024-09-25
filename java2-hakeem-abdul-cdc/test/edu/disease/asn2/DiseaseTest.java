package edu.disease.asn2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class DiseaseTest {

	private InfectiousDisease infectiousDisease1;
    private InfectiousDisease infectiousDisease2;
    private NonInfectiousDisease nonInfectiousDisease;

    @Before
    public void setUp() {
        infectiousDisease1 = new InfectiousDisease();
        infectiousDisease1.setDiseaseId(UUID.randomUUID());
        infectiousDisease1.setName("InfectiousDisease");

        infectiousDisease2 = new InfectiousDisease();
        infectiousDisease2.setDiseaseId(UUID.randomUUID());
        infectiousDisease2.setName("InfectiousDisease");

        nonInfectiousDisease = new NonInfectiousDisease();
        nonInfectiousDisease.setDiseaseId(UUID.randomUUID());
        nonInfectiousDisease.setName("NonInfectiousDisease");
    }
    
    @Test
    public void testInfectiousDiseaseGetName() {
        assertEquals("InfectiousDisease", infectiousDisease1.getName());
    }

    @Test
    public void testNonInfectiousDiseaseGetName() {
        assertEquals("NonInfectiousDisease", nonInfectiousDisease.getName());
    }


    @Test
    public void testInfectiousDiseaseEquality() {
        assertFalse(infectiousDisease1.equals(nonInfectiousDisease));
        assertTrue(infectiousDisease1.equals(infectiousDisease1));
        assertFalse(infectiousDisease1.equals(infectiousDisease2));
    }
    
    

    @Test
    public void testNonInfectiousDiseaseEquality() {
        assertFalse(nonInfectiousDisease.equals(infectiousDisease1));
        assertTrue(nonInfectiousDisease.equals(nonInfectiousDisease));
    }

    @Test
    public void testInfectiousDiseaseHashCode() {
        assertFalse(infectiousDisease1.hashCode() == nonInfectiousDisease.hashCode());
        assertTrue(infectiousDisease1.hashCode() == infectiousDisease1.hashCode());
        assertFalse(infectiousDisease1.hashCode() == infectiousDisease2.hashCode());
    }

    @Test
    public void testNonInfectiousDiseaseHashCode() {
        assertFalse(nonInfectiousDisease.hashCode() == infectiousDisease1.hashCode());
        assertTrue(nonInfectiousDisease.hashCode() == nonInfectiousDisease.hashCode());
    }

    @Test
    public void testInfectiousDiseaseToString() {
        assertEquals("Disease [diseaseId=" + infectiousDisease1.getDiseaseId() + ", name=InfectiousDisease]",
                infectiousDisease1.toString());
    }

    @Test
    public void testNonInfectiousDiseaseToString() {
        assertEquals("Disease [diseaseId=" + nonInfectiousDisease.getDiseaseId() + ", name=NonInfectiousDisease]",
                nonInfectiousDisease.toString());
    }

    
    
    @Test
    public void testGetInfectiousDiseaseExamplesAllNonNull() {
        String[] examples = infectiousDisease1.getExamples();
        assertNotNull("Examples should not be null", examples);
        assertTrue("All examples should be non-null", Arrays.stream(examples).allMatch(Objects::nonNull));
    }

    @Test
    public void testGetNonInfectiousDiseaseExamplesAllNonNull() {
        String[] examples = nonInfectiousDisease.getExamples();
        assertNotNull("Examples should not be null", examples);
        assertTrue("All examples should be non-null", Arrays.stream(examples).allMatch(Objects::nonNull));
    }

    @Test
    public void testGetInfectiousDiseaseExamplesLength() {
        String[] examples = infectiousDisease1.getExamples();
        assertNotNull("Examples should not be null", examples);
        assertEquals("Number of examples should be 4", 4, examples.length);
    }

    @Test
    public void testGetNonInfectiousDiseaseExamplesLength() {
        String[] examples = nonInfectiousDisease.getExamples();
        assertNotNull("Examples should not be null", examples);
        assertEquals("Number of examples should be 4", 4, examples.length);
    }

    @Test
    public void testGetInfectiousDiseaseExamplesContent() {
        String[] examples = infectiousDisease1.getExamples();
        assertNotNull("Examples should not be null", examples);
        assertTrue(Arrays.asList(examples).contains("Influenza"));
        assertTrue(Arrays.asList(examples).contains("Tuberculosis"));
        assertTrue(Arrays.asList(examples).contains("COVID-19"));
        assertTrue(Arrays.asList(examples).contains("Malaria"));
    }

    @Test
    public void testGetNonInfectiousDiseaseExamplesContent() {
        String[] examples = nonInfectiousDisease.getExamples();
        assertNotNull("Examples should not be null", examples);
        assertTrue(Arrays.asList(examples).contains("Diabetes"));
        assertTrue(Arrays.asList(examples).contains("Heart Disease"));
        assertTrue(Arrays.asList(examples).contains("Asthma"));
        assertTrue(Arrays.asList(examples).contains("Cancer"));
    }

}
