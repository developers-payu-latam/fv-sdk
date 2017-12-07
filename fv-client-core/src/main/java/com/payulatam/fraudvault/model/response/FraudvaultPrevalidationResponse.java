/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de oct. de 2017
 */
package com.payulatam.fraudvault.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the response with the Fraudvault prevalidation result.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root
@Data
@EqualsAndHashCode(callSuper = false)
public class FraudvaultPrevalidationResponse extends FraudvaultBaseResponse {

	@Element(name = "respuesta-maf-prevalidacion", required = false)
	private FraudvaultEvaluation evaluation;

	/**
	 * Read the content of the XML document and convert it into a {@code FraudvaultPrevalidationResponse} object.
	 * 
	 * @param xml the XML to be deserialized.
	 * @return the object deserialized from the XML .
	 * @throws Exception if the object cannot be deserialized.
	 */
	public static FraudvaultPrevalidationResponse fromXml(String xml) throws Exception {

		Strategy strategy = new AnnotationStrategy();
		Serializer serializer = new Persister(strategy);
		return serializer.read(FraudvaultPrevalidationResponse.class, xml);
	}

}
