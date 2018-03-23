/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 9 de nov. de 2017
 */
package com.payulatam.fraudvault.model.response.soap;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import com.payulatam.fraudvault.api.client.exception.XmlConversionException;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the response with the result of a transaction state update operation in Fraudvault 
 * that can be mapped with the XML response structure.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UpdateStateResponseSoapWrapper extends FraudvaultBaseResponseSoapWrapper {

	@Element(name = "respuesta-maf-actualiza-estado", required = false)
	private StateOperationResponseContentSoapWrapper updateStateResponseContent;
	
	private static Serializer serializer = new Persister(new AnnotationStrategy());

	/**
	 * Read the content of the XML document and convert it into a {@code UpdateStateResponseSoapWrapper} object.
	 * 
	 * @param xml the XML to be deserialized.
	 * @return the object deserialized from the XML .
	 * @throws XmlConversionException if the object cannot be deserialized.
	 */
	public static UpdateStateResponseSoapWrapper fromXml(String xml) throws XmlConversionException {

		try {
			return serializer.read(UpdateStateResponseSoapWrapper.class, xml);
		} catch (Exception e) {
			throw new XmlConversionException(
					"Exception deserializing the XML string to UpdateStateResponseSoapWrapper object", e);
		}
	}

}
