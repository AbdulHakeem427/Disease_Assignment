package edu.disease.asn6;

import java.util.Arrays;
import java.util.List;


public class NonInfectiousDisease extends Disease {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets the examples.
	 *
	 * @return the examples
	 */
	@Override
	List<String> getExamples() {
		// Provide concrete implementation and return a List<String>
		return Arrays.asList("Diabetes", "Heart Disease", "Asthma", "Cancer");
	}

}
