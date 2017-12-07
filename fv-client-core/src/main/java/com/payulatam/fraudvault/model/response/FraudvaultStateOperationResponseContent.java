/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 9 de nov. de 2017
 */
package com.payulatam.fraudvault.model.response;

import org.simpleframework.xml.Element;

import lombok.Data;

/**
 * Encapsulates the attributes of the response content for an operation on the transaction state in
 * Fraudvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public class FraudvaultStateOperationResponseContent {

	@Element(name = "transaccion-id", required = false)
	private String transactionId;

	@Element(name = "estado", required = false)
	private Integer state;

	@Element(name = "codigo-respuesta", required = false)
	private Integer answerCode;

	@Element(name = "mensaje-error", required = false)
	private String errorMessage;

}
