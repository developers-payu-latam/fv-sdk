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
	
	/**
	 * A code indicating the decision made by Fraudvault, this is:
	 * 4: If the transaction must be reversed.
	 * 5: If the transaction must be validated.
	 * 6: If the transaction must be released.
	 * 8: If the transaction must be monitored in posvalidation
	 **/
	private Integer decision;

	/** The identifier of the transaction. */
	private String transactionId;

	/**
	 * They are the actions triggered in the execution of rules. The actions triggered are according
	 * to transaction data that match with the rules profiles configuration in Fraudvault.
	 */
	private List<String> actions;

	/** Number of fraudulent transactions that are similar to the current transaction. */
	private Integer similarTransactionsNumber;

	/**
	 * A list with the detail of the triggered rules by Fraudvault. The detail contains among others:
	 * the name of the rule, the field of the transaction that triggers the rule, the value
	 * configured in the rule.
	 */
	private List<TriggeredRule> triggeredRules;

	/** Time in milliseconds that Fraudvault takes to evaluate the transaction. */
	private Integer evaluationTime;

	/** A value from 2001 to 2999 returned if there is an error when evaluating a transaction. */
	private Integer errorCode;

	/** Error message associated with the evaluation of the transaction. */
	private String errorMessage;

}
