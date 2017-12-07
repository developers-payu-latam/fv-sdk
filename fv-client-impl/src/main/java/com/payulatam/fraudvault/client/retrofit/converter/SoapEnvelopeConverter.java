/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 18 de oct. de 2017
 */
package com.payulatam.fraudvault.client.retrofit.converter;

import com.payulatam.fraudvault.client.retrofit.model.soap.request.EvaluationRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.PosvalidationRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.PrevalidationBodyData;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.PrevalidationRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.QueryStateRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.UpdateStateRequestSoapEnvelope;
import com.payulatam.fraudvault.model.request.Credentials;
import com.payulatam.fraudvault.model.request.Transaction;

/**
 * Utility class for convert the transaction data to be sent to Fraudvault in objects of the soap message envelope model.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
public class SoapEnvelopeConverter {

	private SoapEnvelopeConverter() {

	}

	/**
	 * Converts the transaction data into a {@code PrevalidationRequestSoapEnvelope} object.
	 * 
	 * @param transaction transaction data.
	 * @param credentials authentication data.
	 * @return the {@code PrevalidationRequestSoapEnvelope} object.
	 * @throws Exception If an error happens getting the XML data of the transaction.
	 */
	public static PrevalidationRequestSoapEnvelope getPrevalidationSoapEnvelope(Transaction transaction,
			Credentials credentials) throws Exception {

		PrevalidationBodyData prevalidationBodyData = new PrevalidationBodyData();
		prevalidationBodyData.setTransaction(transaction.toXml());
		prevalidationBodyData.setClientId(credentials.getClientId());
		prevalidationBodyData.setLogin(credentials.getLogin());
		prevalidationBodyData.setPassword(credentials.getPassword());

		PrevalidationRequestSoapEnvelope soapEnvelope = new PrevalidationRequestSoapEnvelope();
		soapEnvelope.setRequestSoapEnvelopeBody(prevalidationBodyData);
		return soapEnvelope;
	}

	/**
	 * Converts the transaction data into a {@code EvaluationRequestSoapEnvelope} object.
	 * 
	 * @param transaction transaction data.
	 * @param credentials authentication data.
	 * @return the {@code EvaluationRequestSoapEnvelope} object.
	 * @throws Exception If an error happens getting the XML data of the transaction.
	 */
	public static EvaluationRequestSoapEnvelope getEvaluationSoapEnvelope(Transaction transaction,
			Credentials credentials) throws Exception {

		PrevalidationBodyData prevalidationBodyData = new PrevalidationBodyData();
		prevalidationBodyData.setTransaction(transaction.toXml());
		prevalidationBodyData.setClientId(credentials.getClientId());
		prevalidationBodyData.setLogin(credentials.getLogin());
		prevalidationBodyData.setPassword(credentials.getPassword());

		EvaluationRequestSoapEnvelope soapEnvelope = new EvaluationRequestSoapEnvelope();
		soapEnvelope.setRequestSoapEnvelopeBody(prevalidationBodyData);
		return soapEnvelope;
	}

	/**
	 * Converts the transaction data into a {@code PosvalidationRequestSoapEnvelope} object.
	 * 
	 * @param transaction transaction data.
	 * @param credentials authentication data.
	 * @return the {@code PosvalidationRequestSoapEnvelope} object.
	 * @throws Exception If an error happens getting the XML data of the transaction.
	 */
	public static PosvalidationRequestSoapEnvelope getPostvalidationSoapEnvelope(String transactionId, Credentials credentials) {

		PosvalidationRequestSoapEnvelope.PosvalidationBodyData postvalidationBodyData = new PosvalidationRequestSoapEnvelope.PosvalidationBodyData();
		postvalidationBodyData.setTransactionId(transactionId);
		postvalidationBodyData.setClientId(credentials.getClientId());
		postvalidationBodyData.setLogin(credentials.getLogin());
		postvalidationBodyData.setPassword(credentials.getPassword());

		PosvalidationRequestSoapEnvelope soapEnvelope = new PosvalidationRequestSoapEnvelope();
		soapEnvelope.setRequestSoapEnvelopeBody(postvalidationBodyData);
		return soapEnvelope;
	}

	/**
	 * Converts the transaction data into a {@code QueryStateRequestSoapEnvelope} object.
	 * 
	 * @param transactionId identifier of the transaction to query the state.
	 * @param credentials the authentication data.
	 * @return the {@code QueryStateRequestSoapEnvelope} object.
	 */
	public static QueryStateRequestSoapEnvelope getQueryStateSoapEnvelope(String transactionId, Credentials credentials) {

		QueryStateRequestSoapEnvelope.QueryStateBodyData queryStateBodyData = new QueryStateRequestSoapEnvelope.QueryStateBodyData();
		queryStateBodyData.setClientId(credentials.getClientId());
		queryStateBodyData.setLogin(credentials.getLogin());
		queryStateBodyData.setPassword(credentials.getPassword());
		queryStateBodyData.setTransactionId(transactionId);

		QueryStateRequestSoapEnvelope queryStateSoapEnvelope = new QueryStateRequestSoapEnvelope();
		queryStateSoapEnvelope.setQueryStateBodyData(queryStateBodyData);
		return queryStateSoapEnvelope;
	}

	/**
	 * Converts the transaction data into a {@code UpdateStateRequestSoapEnvelope} object.
	 * @param transactionId identifier of the transaction to update the state.
	 * @param newStateId identifier of the state to be setted to the transaction.
	 * @param credentials the authentication data.
	 * @return the {@code QueryStateRequestSoapEnvelope} object.
	 */
	public static UpdateStateRequestSoapEnvelope getUpdateStateSoapEnvelope(String transactionId, Long newStateId, Credentials credentials) {

		UpdateStateRequestSoapEnvelope.UpdateStateBodyData updateStateBodyData = new UpdateStateRequestSoapEnvelope.UpdateStateBodyData();
		updateStateBodyData.setClientId(credentials.getClientId());
		updateStateBodyData.setLogin(credentials.getLogin());
		updateStateBodyData.setPassword(credentials.getPassword());
		updateStateBodyData.setTransactionId(transactionId);
		updateStateBodyData.setNewStateId(newStateId);

		UpdateStateRequestSoapEnvelope updateStateSoapEnvelope = new UpdateStateRequestSoapEnvelope();
		updateStateSoapEnvelope.setUpdateStateBodyData(updateStateBodyData);
		return updateStateSoapEnvelope;
	}

}
