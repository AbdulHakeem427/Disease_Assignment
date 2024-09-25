package edu.disease.asn3;

/**
 * The Class InfectiousDisease.
 */
public class InfectiousDisease extends Disease{
	private static final long serialVersionUID = 1L;
	@Override
	String[] getExamples() {
		// TODO Auto-generated method stub
		return new String[]{
			"Influenza",
			"Tuberculosis",
            "COVID-19",
            "Malaria",
            };
	}

}
