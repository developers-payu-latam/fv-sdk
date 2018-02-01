package com.payulatam.fraudvault.model.response.xml;

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

	@Element(name = "puntaje", required = false)
	private Double generalScore;

	@Element(name = "puntaje-email", required = false)
	private Double emailScore;

}
