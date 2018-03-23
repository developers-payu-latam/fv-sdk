/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 9 de nov. de 2017
 */
package com.payulatam.fraudvault.model.response.soap;

import org.simpleframework.xml.Element;

import lombok.Data;

/**
 * Encapsulates the attributes of the response content for an operation on the transaction state in
 * Fraudvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public class StateOperationResponseContentSoapWrapper {

	/** The identifier of the transaction. */
	@Element(name = "transaccion-id", required = false)
	private String transactionId;

	/** The identifier of the transaction state. */
	@Element(name = "estado", required = false)
	private Integer state;

	/**
	 * A value of 1: it represents a successful response code; in case there were no errors,
	 * otherwise 2: it represents an error response code; in case there were errors.
	 */
	@Element(name = "codigo-respuesta", required = false)
	private Integer answerCode;

	/**  Message associated with the error, if an error occurs */
	@Element(name = "mensaje-error", required = false)
	private String errorMessage;

}
