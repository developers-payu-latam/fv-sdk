/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 18 de oct. de 2017
 */
package com.payulatam.fraudvault.api.client;

import com.payulatam.fraudvault.api.client.exception.FraudvaultException;
import com.payulatam.fraudvault.model.request.Transaction;
import com.payulatam.fraudvault.model.response.FraudvaultPosvalidationResponse;
import com.payulatam.fraudvault.model.response.FraudvaultPrevalidationResponse;
import com.payulatam.fraudvault.model.response.FraudvaultQueryStateResponse;
import com.payulatam.fraudvault.model.response.FraudvaultUpdateStateResponse;

import lombok.Data;

/**
 * Interface that define the methods to implement for a Fraudvault service client.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public abstract class FraudvaultClient {

	/** The configuration for the Fraudvault client */
	private FraudvaultClientConfiguration clientConfiguration;

	/** Constructor of the Fraudvault client. */
	public FraudvaultClient(FraudvaultClientConfiguration clientConfiguration) {
		this.clientConfiguration = clientConfiguration;
	}

	/**
	 * Performs the fraud evaluation of a transaction in prevalidation.
	 * 
	 * @param transaction the transaction to be prevalidated by Fraudvault.
	 * @return the response with the result of the Fraudvault prevalidation.
	 * @throws FraudvaultException if an error occurs in the prevalidation process.
	 */
	public abstract FraudvaultPrevalidationResponse prevalidate(Transaction transaction) throws FraudvaultException;

	/**
	 * Performs the fraud evaluation of a transaction in prevalidation.
	 * 
	 * @param transaction the transaction to be evaluated by Fraudvault.
	 * @throws FraudvaultException if an error occurs in the evaluation process.
	 */
	public abstract FraudvaultPrevalidationResponse evaluate(Transaction transaction) throws FraudvaultException;

	/**
	 * Performs the fraud posvalidation of a transaction.
	 * 
	 * @param transaction the transaction to be posvalidated by Fraudvault.
	 * @return the response with the result of the Fraudvault posvalidation.
	 * @throws FraudvaultException if an error occurs in the posvalidation process.
	 */
	public abstract FraudvaultPosvalidationResponse posvalidate(String transactionId) throws FraudvaultException;

	/**
	 * Performs a query in Fraudvault to get the current state of a transaction.
	 * 
	 * @param transactionId the identifier of the transaction to be queried in Fraudvault.
	 * @return the response with the result of the query operation.
	 * @throws FraudvaultException if an error occurs in the query operation.
	 */
	public abstract FraudvaultQueryStateResponse queryTransactionState(String transactionId) throws FraudvaultException;

	/**
	 * Updates the state of a transaction in Fraudvault.
	 * 
	 * @param transactionId the identifier of the transaction in Fraudvault.
	 * @return the response with the result of the update state operation.
	 * @throws FraudvaultException if an error occurs in the update operation.
	 */
	public abstract FraudvaultUpdateStateResponse updateTransactionState(String transactionId, Long stateId) throws FraudvaultException;

}
