package edu.disease.asn2;

import java.util.Objects;
import java.util.UUID;


abstract public class Disease {
	private UUID diseaseId;
	private String name;
	
	abstract String[] getExamples();

	
	public UUID getDiseaseId() {
		return diseaseId;
	}
	
	public void setDiseaseId(UUID diseaseId) {
		this.diseaseId = diseaseId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(diseaseId);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) {
	        return true;
	    }
	    if (obj == null || getClass() != obj.getClass()) {
	        return false;
	    }
	    Disease other = (Disease) obj;
	    return Objects.equals(diseaseId, other.diseaseId);
	}
	@Override
	public String toString() {
		return "Disease [diseaseId=" + diseaseId + ", name=" + name + "]";
	}

}
