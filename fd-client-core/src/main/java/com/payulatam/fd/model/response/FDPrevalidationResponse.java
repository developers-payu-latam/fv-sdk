/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 25 de ene. de 2018
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.payulatam.fd.model.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the prevalidation result from the fraud detection service.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class FDPrevalidationResponse extends FDBaseResponse {

	/** The decision made by the fraud detection service. */
	private PrevalidationDecision decision;
	
	/** The decision id value. */
	private Integer decisionIdValue;

	/** The identifier of the transaction. */
	private String transactionId;

	/**
	 * A list of alerts that can be thrown by the fraud detection service. For example:
	 * INTERNATIONAL_CARD: if the credit card is international.
	 * DESCRIPTION_HAS_SUSPICIOUS_WORD: if the description sent in the transaction data contains
	 * any suspicious word.
	 */
	private List<Alert> alerts;
	
	/** A list with the alerts keys that can be thrown by the fraud detection service. */
	private List<String> alertsKeysValues;

	/**
	 * The actions triggered in the execution of rules. The actions triggered are according
	 * to transaction data that match with the rules profiles configuration in the fraud detection service.
	 */
	private List<Action> actions;
	
	/** A list with the keys of the actions triggered in the execution of rules.*/
	private List<String> actionsKeysValues;

	/** Number of fraudulent transactions that are similar to the current transaction. */
	private Integer similarTransactionsNumber;

	/**
	 * A list with the detail of the triggered rules by the fraud detection service. The detail contains among others:
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

	/** Indicates if any transaction data match with the data contained in white lists database 
	 *  of the fraud detection system */
	private ListMatch whiteListsMatching;

	/** Indicates if any transaction data match with the data contained in black lists database 
	 *  of the fraud detection system */
	private ListMatch blackListsMatching;

	/** Indicates if any transaction data match with the data contained in temporary white lists database 
	 *  of the fraud detection system */
	private ListMatch temporaryListsMatching;

	/** Information of the card issuer bank. */
	private IssuerBank issuerBank;

	/** Indicates if there is a high risk that the IP address is a proxy. */
	private boolean ipProxy;

	/** The name of the detected Internet service provider. */
	private String ispName;

	/** Information of the IP address location. */
	private IpAddressLocation ipAddressLocation;

	/** Time in milliseconds that the fraud detection service takes to evaluate the transaction. */
	private Integer evaluationTime;

	/** The error code if there is an error when evaluating a transaction. */
	private ErrorCode errorCode;
	
	/** The error code identifier value if there is an error when evaluating a transaction. */
	private Integer errorCodeIdValue;

	/** Error message associated with the evaluation of the transaction. */
	private String errorMessage;

	private boolean validatedWithCreditBureau;
	
	/**
	 * Indicates if there was an error in the prevalidation process.
	 * 
	 * @return true if there was an error otherwise false.
	 */
	@Override
	public boolean hasError() {
		return super.hasError() || errorCode != null;
	}
	
	/**
	 * Indicates if some field in the transaction is contained in black lists.
	 * 
	 * @return true if some field in the transaction is contained in black lists otherwise false. 
	 */
	public boolean matchWithBlackList(){
		return blackListsMatching != null && blackListsMatching.isMatch();
	}
	
	/**
	 * Indicates if some field in the transaction is contained in white lists.
	 * 
	 * @return true if some field in the transaction is contained in white lists otherwise false. 
	 */
	public boolean matchWithWhiteList(){
		return whiteListsMatching != null && whiteListsMatching.isMatch();
	}
	
	/**
	 * Indicates if some field in the transaction is contained in temporary white lists.
	 * 
	 * @return true if some field in the transaction is contained in temporary white lists otherwise false. 
	 */
	public boolean matchWithTemporaryList(){
		return temporaryListsMatching != null && temporaryListsMatching.isMatch();
	}
	
}
