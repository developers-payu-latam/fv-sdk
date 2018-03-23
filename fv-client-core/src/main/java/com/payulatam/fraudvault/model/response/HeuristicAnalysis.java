package com.payulatam.fraudvault.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Data;

/**
 * Encapsulates the scores assigned by the neuronal network.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "analisis-heuristico")
@Data
public class HeuristicAnalysis {

	/**
	 * The risk score assigned by the neuronal network. The score is in a range of 0.0 to 1.0, 0.0
	 * being the lowest risk and 1.0 the highest risk.
	 */
	@Element(name = "puntaje", required = false)
	private Double generalScore;

	/**
	 * The risk score assigned by the neural network to the email structure of the buyer user, the
	 * score is in a range of 0.0 to 1.0, 0.0 being the lowest risk and 1.0 the highest risk.
	 */
	@Element(name = "puntaje-email", required = false)
	private Double emailScore;

}
