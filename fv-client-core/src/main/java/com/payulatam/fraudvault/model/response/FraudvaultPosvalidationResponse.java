/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 25 de ene. de 2018
 */
package com.payulatam.fraudvault.model.response;

import java.util.List;

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
public class FraudvaultPosvalidationResponse extends FraudvaultBaseResponse{
	
	/**
	 * The decision made by Fraudvault. **/
	private PosvalidationDecision decision;
	
	/** The decision id value. */
	private Integer decisionIdValue;

	/** The identifier of the transaction. */
	private String transactionId;

	/**
	 * They are the actions triggered in the execution of rules. The actions triggered are according
	 * to transaction data that match with the rules profiles configuration in Fraudvault.
	 */
	private List<Action> actions;
	
	/** A list with the keys of the actions triggered in the execution of rules.*/
	private List<String> actionsKeysValues;

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

	/** The error code if there is an error when evaluating a transaction. */
	private ErrorCode errorCode;
	
	/** The error code identifier value if there is an error when evaluating a transaction. */
	private Integer errorCodeIdValue;

	/** Error message associated with the evaluation of the transaction. */
	private String errorMessage;
	
	/**
	 * Indicates if there was an error in the posvalidation process.
	 * 
	 * @return true if there was an error otherwise false.
	 */
	@Override
	public boolean hasError() {
		return super.hasError() || errorCode != null;
	}

}
