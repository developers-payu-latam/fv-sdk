/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 26 de ene. de 2018
 */
package com.payulatam.fraudvault.model.response;

import com.payulatam.fraudvault.model.response.xml.FraudvaultBaseResponse;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the response with the result of a transaction state update operation in Fraudvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class FraudvaultStateUpdate extends FraudvaultBaseResponse{
	
	/** The identifier of the transaction. */
	private String transactionId;

	/**
	 * A value of 1: it represents a successful response code; in case there were no errors,
	 * otherwise 2: it represents an error response code; in case there were errors.
	 */
	private Integer answerCode;

	/**  Message associated with the error, if an error occurs */
	private String errorMessage;

}
