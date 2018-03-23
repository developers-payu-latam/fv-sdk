/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 26 de ene. de 2018
 */
package com.payulatam.fraudvault.model.response;

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
public class FraudvaultStateQueryResponse extends FraudvaultBaseResponse{
	
	/** The identifier of the transaction. */
	private String transactionId;

	/** The identifier of the transaction state. */
	private TransactionState state;

	/** The answer code. */
	private GeneralAnswerCode answerCode;
	
	/**  Message associated with the error, if an error occurs */
	private String errorMessage;
	
	/**
	 * Indicates if there was an error in the state query process.
	 * 
	 * @return true if there was an error otherwise false.
	 */
	@Override
	public boolean hasError() {
		return super.hasError() || (answerCode != null && answerCode == GeneralAnswerCode.FAIL);
	}

}
