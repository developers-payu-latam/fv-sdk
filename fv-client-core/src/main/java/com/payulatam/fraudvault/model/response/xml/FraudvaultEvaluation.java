package com.payulatam.fraudvault.model.response.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Data;

/**
 * Represents the Fraudvault evaluation result.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root
@Data
public class FraudvaultEvaluation {

	@Element(name = "evaluacion", required = false)
	private FraudvaultEvaluationDetail detail;

	@Element(name = "decision", required = false)
	private Integer decision;

}
