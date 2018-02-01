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
 * Represents the response with the result of a transaction state query operation in Fraudvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class FraudvaultStateQuery extends FraudvaultBaseResponse{
	
	private String transactionId;

	private Integer state;

	/**
	 * A value of 1: it represents a successful response code; in case there were no errors,
	 * otherwise 2: it represents an error response code; in case there were errors.
	 */
	private Integer answerCode;

	private String errorMessage;

}
