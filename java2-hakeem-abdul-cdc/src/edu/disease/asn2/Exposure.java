package edu.disease.asn2;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import custom_exception.UnknowExposureTypeException;
import edu.disease.asn1.exposure_constant.ExposureConstantUsingEnum;

public class Exposure {
	private LocalDateTime dateTime;
	private UUID patientId;
	private String exposureType;
	
	Exposure(LocalDateTime dateTime, UUID patientId, String exposureType) {
		
		this.dateTime = dateTime;
		this.patientId = patientId;
		this.exposureType = exposureType;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}	
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public UUID getPatientId() {
		return patientId;
	}
	public void setPatientId(UUID patientId) {
		this.patientId = patientId;
	}
	public String getExposureType() {
		return exposureType;
	}
	public void setExposureType(String exposureType)  {
		if (exposureType.equalsIgnoreCase(ExposureConstantUsingEnum.DIRECT_EXPOSURE.getExposure()) ||
				exposureType.equalsIgnoreCase(ExposureConstantUsingEnum.INDIRECT_EXPOSURE.getExposure())) {
			this.exposureType = exposureType;
		} else {
			throw new UnknowExposureTypeException(
					"UnknowExposureTypeException: Unknown exposure type = " + exposureType + "'");
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(dateTime, patientId);
	}

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

	@Override
	public String toString() {
		return "Exposure [dateTime=" + dateTime + ", patientId=" + patientId + ", exposureType=" + exposureType + "]";
	}

}
