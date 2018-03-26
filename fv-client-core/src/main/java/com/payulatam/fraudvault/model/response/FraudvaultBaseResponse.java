/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de oct. de 2017
 */
package com.payulatam.fraudvault.model.response;

import java.util.Date;

import lombok.Data;

/**
 * Represents the base attributes in the response of the Fraudvault calls.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public class FraudvaultBaseResponse {

	/** Date of the response generation. */
	private Date responseDate;

	/** The answer code. */
	private GeneralAnswerCode generalAnswerCode;

	/** The general error code. */
	private GeneralErrorCode generalErrorCode;
	
	/** The answer code value. */
	private Integer generalAnswerCodeValue;
	
	/** The general error code value. */
	private Integer generalErrorCodeValue;

	/** Text describing the happened error. */
	private String generalErrorMessage;
	
	/**
	 * Indicates if there was an error in the process.
	 * 
	 * @return true if there was an error otherwise false.
	 */
	public boolean hasError() {
		return (generalAnswerCode != null && generalAnswerCode == GeneralAnswerCode.FAIL)
				|| generalErrorCode != null ;
	}

}
