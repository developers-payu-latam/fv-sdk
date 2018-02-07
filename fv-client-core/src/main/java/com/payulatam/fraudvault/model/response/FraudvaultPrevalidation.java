/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 25 de ene. de 2018
 */
package com.payulatam.fraudvault.model.response;

import java.util.List;

import com.payulatam.fraudvault.model.response.xml.FraudvaultBaseResponse;
import com.payulatam.fraudvault.model.response.xml.IpAddressLocation;
import com.payulatam.fraudvault.model.response.xml.IssuerBank;
import com.payulatam.fraudvault.model.response.xml.ListMatch;
import com.payulatam.fraudvault.model.response.xml.TriggeredRule;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the Fraudvault prevalidation result.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class FraudvaultPrevalidation extends FraudvaultBaseResponse {

	/**
	 * A code indicating the decision made by Fraudvault, this is:
	 * 1: If the transaction must be rejected.
	 * 2: If the transaction must be stopped.
	 * 3: If the transaction must be approved.
	 * 7: If the transaction must be monitored in pre-validation
	 **/
	private Integer decision;

	/** The identifier of the transaction. */
	private String transactionId;

	/**
	 * A list of alerts that can be thrown by Fraudvault. For example:
	 * TC_INTERNACIONAL: if the credit card is international.
	 * DESC_CONTIENE_PALABRA_SOSPECHOSA: if the description sent in the transaction data contains
	 * any suspicious word in the Fraudvault database.
	 */
	private List<String> alerts;

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

	/**
	 * The risk score assigned by the neuronal network. The score is in a range of 0.0 to 1.0, 0.0
	 * being the lowest risk and 1.0 the highest risk.
	 */
	private Double neuronalNetworkGeneralScore;

	/**
	 * The risk score assigned by the neural network to the email structure of the buyer user, the
	 * score is in a range of 0.0 to 1.0, 0.0 being the lowest risk and 1.0 the highest risk.
	 */
	private Double neuronalNetworkEmailScore;

	/** Indicates if any transaction data match with the data of the Fraudvault's white lists database. */
	private ListMatch whiteListsMatching;

	/** Indicates if any transaction data match with the data of the Fraudvault's black lists database. */
	private ListMatch blackListsMatching;

	/** Indicates if any transaction data match with the data of the Fraudvault's temporary lists database. */
	private ListMatch temporaryListsMatching;

	/** Information of the card issuer bank. */
	private IssuerBank issuerBank;

	/** Indicates if there is a high risk that the IP address is a proxy. */
	private boolean ipProxy;

	/** The name of the detected Internet service provider. */
	private String ispName;

	/** Information of the IP address location. */
	private IpAddressLocation ipAddressLocation;

	/** Time in milliseconds that Fraudvault takes to evaluate the transaction. */
	private Integer evaluationTime;

	/** A value from 2001 to 2999 returned if there is an error when evaluating a transaction. */
	private Integer errorCode;

	/** Error message associated with the evaluation of the transaction. */
	private String errorMessage;

	private boolean validateWithCreditBureau;
	
}
