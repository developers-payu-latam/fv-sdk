/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de oct. de 2017
 */
package com.payulatam.fraudvault.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import com.payulatam.fraudvault.api.client.exception.XmlConversionException;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the response with the Fraudvault posvalidation result.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FraudvaultPosvalidationResponse extends FraudvaultBaseResponse {

	@Element(name="respuesta-maf-postvalidacion", required=false)
	private FraudvaultEvaluation evaluation;
	
	/**
	 * Read the content of the XML document and convert it into a {@code FraudvaultPosvalidationResponse} object.
	 * 
	 * @param xml the XML to be deserialized.
	 * @return the object deserialized from the XML .
	 * @throws XmlConversionException if the object cannot be deserialized.
	 */
	public static FraudvaultPosvalidationResponse fromXml(String xml) throws XmlConversionException{

		try {
			Strategy strategy = new AnnotationStrategy();
			Serializer serializer = new Persister(strategy);
			return serializer.read(FraudvaultPosvalidationResponse.class, xml);
		} catch (Exception e) {
			throw new XmlConversionException(
					"Exception deserializing the XML string to FraudvaultPosvalidationResponse object", e);
		}
	}	

}
