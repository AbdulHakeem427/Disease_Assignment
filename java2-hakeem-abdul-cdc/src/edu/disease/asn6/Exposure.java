package edu.disease.asn6;



import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import custom_exception.UnknowExposureTypeException;
import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;


public class Exposure implements Serializable {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The date time. */
	private LocalDateTime dateTime;
	
	/** The patient id. */
	private UUID patientId;
	
	/** The exposure type. */
	private String exposureType;

	/**
	 * Instantiates a new exposure.
	 *
	 * @param dateTime the date time
	 * @param patientId the patient id
	 * @param exposureType the exposure type
	 */
	
	public Exposure(LocalDateTime dateTime, UUID patientId, String exposureType) {
		super();
		this.dateTime = dateTime;
		this.patientId = patientId;
		this.exposureType = exposureType;
	}

	/**
	 * Gets the date time.
	 *
	 * @return the dateTime
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	/**
	 * Sets the date time.
	 *
	 * @param dateTime the dateTime to set
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Gets the patient id.
	 *
	 * @return the patientId
	 */
	public UUID getPatientId() {
		return patientId;
	}

	/**
	 * Sets the patient id.
	 *
	 * @param patientId the patientId to set
	 */
	public void setPatientId(UUID patientId) {
		this.patientId = patientId;
	}

	/**
	 * Gets the exposure type.
	 *
	 * @return the exposureType
	 */
	public String getExposureType() {
		return exposureType;
	}

	
	public void setExposureType(String exposureType) {
		if (exposureType.equalsIgnoreCase(ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure()) || exposureType.equalsIgnoreCase(ExposureConstantUsingEnum.INDIRECT_EXPOSURE.getExposure())) {
			this.exposureType = exposureType;
		} else {
			throw new UnknowExposureTypeException(
					"UnknowExposureTypeException: Unknown exposure type = " + exposureType + "'");
		}
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dateTime, patientId);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exposure other = (Exposure) obj;
		return Objects.equals(dateTime, other.dateTime) && Objects.equals(patientId, other.patientId);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Exposure [dateTime=" + dateTime + ", patientId=" + patientId + ", exposureType=" + exposureType + "]";
	}
	


}
