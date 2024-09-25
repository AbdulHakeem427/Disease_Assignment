package edu.disease.asn6;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import custom_exception.UnknowExposureTypeException;
import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExposureTest {
	private Exposure exposure;

    @Before
    public void setUp() {
        LocalDateTime dateTime = LocalDateTime.now();
        UUID patientId = UUID.randomUUID();
        String exposureType = ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure();
        exposure = new Exposure(dateTime, patientId, exposureType);
    }

    @Test
    public void testGetDateTime() {
        assertNotNull(exposure.getDateTime());
    }

    @Test
    public void testSetDateTime() {
        LocalDateTime newDateTime = LocalDateTime.now().plusDays(1);
        exposure.setDateTime(newDateTime);
        assertEquals(newDateTime, exposure.getDateTime());
    }

    @Test
    public void testGetPatientId() {
        assertNotNull(exposure.getPatientId());
    }

    @Test
    public void testSetPatientId() {
        UUID newPatientId = UUID.randomUUID();
        exposure.setPatientId(newPatientId);
        assertEquals(newPatientId, exposure.getPatientId());
    }

    @Test
    public void testGetExposureType() {
        assertEquals(ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure(), exposure.getExposureType());
    }

    @Test
    public void testSetExposureTypeValid() {
        String newExposureType = ExposureConstantUsingEnum.INDIRECT_EXPOSURE.getExposure();
        exposure.setExposureType(newExposureType);
        assertEquals(newExposureType, exposure.getExposureType());
    }

    @Test(expected = UnknowExposureTypeException.class)
    public void testSetExposureTypeInvalid() {
        String invalidExposureType = "P";
        exposure.setExposureType(invalidExposureType);
    }

    @Test
    public void testHashCode() {
        LocalDateTime dateTime = LocalDateTime.now();
        UUID patientId = UUID.randomUUID();
        Exposure anotherExposure = new Exposure(dateTime, patientId, ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure());

        assertNotEquals(exposure.hashCode(), anotherExposure.hashCode());
    }

    @Test
    public void testEquals() {
        LocalDateTime dateTime = LocalDateTime.now();
        UUID patientId = UUID.randomUUID();
        Exposure anotherExposure = new Exposure(dateTime, patientId, ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure());

        assertFalse(exposure.equals(anotherExposure));
    }

    @Test
    public void testToString() {
        assertNotNull(exposure.toString());
    }

}
