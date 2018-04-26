/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de oct. de 2017
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.payulatam.fd.model.response.soap;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payulatam.fd.api.client.exception.XmlConversionException;

/**
 * Represents the response with the prevalidation result that can be mapped with the XML response structure.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root
@Data
@EqualsAndHashCode(callSuper = true)
public class PrevalidationResponseSoapWrapper extends FDBaseResponseSoapWrapper {

	/** Class logger. */
	private static final Logger logger = LoggerFactory.getLogger(PrevalidationResponseSoapWrapper.class);

	@Element(name = "respuesta-maf-prevalidacion", required = false)
	private EvaluationSoapWrapper evaluation;
	
	private static Serializer serializer = new Persister(new AnnotationStrategy());

	/**
	 * Read the content of the XML document and convert it into a {@code PrevalidationResponseSoapWrapper} object.
	 * 
	 * @param xml the XML to be deserialized.
	 * @return the object deserialized from the XML .
	 * @throws XmlConversionException if the object cannot be deserialized.
	 */
	public static PrevalidationResponseSoapWrapper fromXml(String xml) throws XmlConversionException {

		try {
			logger.debug(xml);
			return serializer.read(PrevalidationResponseSoapWrapper.class, xml);
		} catch (Exception e) {
			throw new XmlConversionException(
					"Exception deserializing the XML string to PrevalidationResponseSoapWrapper object", e);
		}
	}

}
