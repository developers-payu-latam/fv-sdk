/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 25 de ene. de 2018
 */
package com.payulatam.fraudvault.model.response;

import java.util.List;

import com.payulatam.fraudvault.model.response.xml.FraudvaultBaseResponse;
import com.payulatam.fraudvault.model.response.xml.TriggeredRule;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the Fraudvault posvalidation result.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class FraudvaultPosvalidation extends FraudvaultBaseResponse{
	
	private Integer decision;

	private String transactionId;

	private List<String> actions;

	private Integer similarTransactionsNumber;

	private List<TriggeredRule> triggeredRules;

	private Integer evaluationTime;

	private Integer errorCode;

	private String errorMessage;

}
