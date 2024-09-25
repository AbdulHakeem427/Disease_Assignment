package edu.disease.asn1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ExposureTest {

    private static final LocalDateTime BASE_DATE_TIME = LocalDateTime.of(2023, 1, 1, 12, 0);
    private static final UUID BASE_PATIENT_ID = UUID.randomUUID();
    private static final String BASE_EXPOSURE_TYPE = "Direct";

    @Test
    void testConstructorAndAccessors() {
        Exposure exposure = new Exposure(BASE_DATE_TIME, BASE_PATIENT_ID, BASE_EXPOSURE_TYPE);

        assertEquals(BASE_DATE_TIME, exposure.getDateTime());
        assertEquals(BASE_PATIENT_ID, exposure.getPatientId());
        assertEquals(BASE_EXPOSURE_TYPE, exposure.getExposureType());
    }

    @Test
    void testSetDateTime() {
        Exposure exposure = new Exposure(null, null, null);

        LocalDateTime newDateTime = LocalDateTime.now();
        exposure.setDateTime(newDateTime);

        assertEquals(newDateTime, exposure.getDateTime());
    }

    @Test
    void testSetPatientId() {
        Exposure exposure = new Exposure(null, null, null);

        UUID newPatientId = UUID.randomUUID();
        exposure.setPatientId(newPatientId);

        assertEquals(newPatientId, exposure.getPatientId());
    }

    @Test
    void testSetExposureTypeValid() throws IllegalAccessException {
        Exposure exposure = new Exposure(null, null, null);

        String validExposureType = ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure();
        exposure.setExposureType(validExposureType);

        assertEquals(validExposureType, exposure.getExposureType());
    }

    @Test
    void testSetExposureTypeInvalid() {
        Exposure exposure = new Exposure(null, null, null);

        String invalidExposureType = "InvalidType";

        assertThrows(IllegalAccessException.class, () -> exposure.setExposureType(invalidExposureType));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Direct", "Indirect"})
    void testHashCodeAndEquals(String exposureType) {
        Exposure exposure1 = new Exposure(BASE_DATE_TIME, BASE_PATIENT_ID, exposureType);
        Exposure exposure2 = new Exposure(BASE_DATE_TIME, BASE_PATIENT_ID, exposureType);

        assertEquals(exposure1.hashCode(), exposure2.hashCode());
        assertEquals(exposure1, exposure2);
    }

    @Test
    void testToString() {
        Exposure exposure = new Exposure(BASE_DATE_TIME, BASE_PATIENT_ID, BASE_EXPOSURE_TYPE);

        String expectedToString = "Exposure [dateTime=" + BASE_DATE_TIME + ", patientId=" + BASE_PATIENT_ID + ", exposureType=" + BASE_EXPOSURE_TYPE + "]";
        assertEquals(expectedToString, exposure.toString());
    }
}
