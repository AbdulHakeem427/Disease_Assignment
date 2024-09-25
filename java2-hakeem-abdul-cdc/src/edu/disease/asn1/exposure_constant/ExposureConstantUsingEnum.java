package edu.disease.asn1.exposure_constant;



public enum ExposureConstantUsingEnum {
	DIRECT_EXPOSURE("D"),
	INDIRECT_EXPOSURE("I");
	
	private final String exposure;
	
	ExposureConstantUsingEnum(String exposure) {
		this.exposure = exposure;   	
	}
	public String getExposure() {
		return exposure;    
	}
	
}


