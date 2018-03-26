package com.payulatam.fraudvault.model.response.soap;

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
public class EvaluationSoapWrapper {

	/** The detail of the Fraudvault evaluation result.*/
	@Element(name = "evaluacion", required = false)
	private EvaluationDetailSoapWrapper detail;

	/** The identifier of the decision made by Fraudvault. */
	@Element(name = "decision", required = false)
	private Integer decision;

}
