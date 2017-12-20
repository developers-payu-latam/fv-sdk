/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 9 de nov. de 2017
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
 * Represents the response with the result of a transaction state update operation in Fraudvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FraudvaultUpdateStateResponse extends FraudvaultBaseResponse {

	@Element(name = "respuesta-maf-actualiza-estado", required = false)
	private FraudvaultStateOperationResponseContent updateStateResponseContent;

	/**
	 * Read the content of the XML document and convert it into a {@code FraudvaultUpdateStateResponse} object.
	 * 
	 * @param xml the XML to be deserialized.
	 * @return the object deserialized from the XML .
	 * @throws XmlConversionException if the object cannot be deserialized.
	 */
	public static FraudvaultUpdateStateResponse fromXml(String xml) throws XmlConversionException {

		try {
			Strategy strategy = new AnnotationStrategy();
			Serializer serializer = new Persister(strategy);
			return serializer.read(FraudvaultUpdateStateResponse.class, xml);
		} catch (Exception e) {
			throw new XmlConversionException(
					"Exception deserializing the XML string to FraudvaultUpdateStateResponse object", e);
		}
	}

}
