/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de oct. de 2017
 */
package com.payulatam.fraudvault.model.response.soap;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.convert.Convert;

import com.payulatam.fraudvault.util.DateConverter;

import lombok.Data;

/**
 * Represents the base attributes in the response of the Fraudvault calls.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public class FraudvaultBaseResponseSoapWrapper {

	/** Date of the response generation. */
	@Element(name = "fecha", required = false)
	@Convert(DateConverter.class)
	private Date responseDate;

	/** The answer code. The values are: 1 for a successful answer or 2 for a failed answer. */
	@Element(name = "codigo-respuesta", required = false)
	private Integer generalAnswerCode;

	/** A value from 1001 to 1999 identifying the error, if an error occurs. */
	@Element(name = "codigo-error", required = false)
	private Integer generalErrorCode;

	/** Text describing the happened error. */
	@Element(name = "mensaje-error", required = false)
	private String generalErrorMessage;

}
