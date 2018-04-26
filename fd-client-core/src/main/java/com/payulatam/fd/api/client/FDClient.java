/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 18 de oct. de 2017
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

package com.payulatam.fd.api.client;

import com.payulatam.fd.api.client.exception.FDException;
import com.payulatam.fd.model.request.Transaction;
import com.payulatam.fd.model.response.FDPosvalidationResponse;
import com.payulatam.fd.model.response.FDPrevalidationResponse;
import com.payulatam.fd.model.response.FDStateQueryResponse;
import com.payulatam.fd.model.response.FDStateUpdateResponse;

import lombok.Data;

/**
 * Interface that define the methods to implement for a client of the fraud detection service.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public abstract class FDClient {

	/** The configuration for the client of the fraud detection service. */
	private FDClientConfiguration clientConfiguration;

	/** Constructor of the client. */
	public FDClient(FDClientConfiguration clientConfiguration) {
		this.clientConfiguration = clientConfiguration;
	}

	/**
	 * Performs the fraud evaluation of a transaction in prevalidation.
	 * 
	 * @param transaction the transaction to be prevalidated by the fraud detection service.
	 * @return the response with the result of the prevalidation.
	 * @throws FDException if an error occurs in the prevalidation process.
	 */
	public abstract FDPrevalidationResponse prevalidate(Transaction transaction) throws FDException;

	/**
	 * Performs the fraud evaluation of a transaction in prevalidation.
	 * 
	 * @param transaction the transaction to be evaluated by the fraud detection service.
	 * @return the response with the result of the prevalidation.
	 * @throws FDException if an error occurs in the evaluation process.
	 */
	public abstract FDPrevalidationResponse evaluate(Transaction transaction) throws FDException;

	/**
	 * Performs the fraud posvalidation of a transaction.
	 * 
	 * @param transactionId the identifier of the transaction to be posvalidated by the fraud detection service.
	 * @return the response with the result of the posvalidation.
	 * @throws FDException if an error occurs in the posvalidation process.
	 */
	public abstract FDPosvalidationResponse posvalidate(String transactionId) throws FDException;

	/**
	 * Performs a query to get the current state of a transaction.
	 * 
	 * @param transactionId the identifier of the transaction to be queried.
	 * @return the response with the result of the query operation.
	 * @throws FDException if an error occurs in the query operation.
	 */
	public abstract FDStateQueryResponse queryTransactionState(String transactionId) throws FDException;

	/**
	 * Updates the state of a transaction.
	 * 
	 * @param transactionId the identifier of the transaction to be queried.
	 * @return the response with the result of the update state operation.
	 * @throws FDException if an error occurs in the update operation.
	 */
	public abstract FDStateUpdateResponse updateTransactionState(String transactionId, Long stateId) throws FDException;

}
