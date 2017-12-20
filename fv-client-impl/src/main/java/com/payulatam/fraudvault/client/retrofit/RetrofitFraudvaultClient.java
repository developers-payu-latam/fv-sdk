/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 13 de oct. de 2017
 */
package com.payulatam.fraudvault.client.retrofit;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import com.payulatam.fraudvault.api.client.FraudvaultClient;
import com.payulatam.fraudvault.api.client.FraudvaultClientConfiguration;
import com.payulatam.fraudvault.api.client.exception.FraudvaultException;
import com.payulatam.fraudvault.api.client.exception.XmlConversionException;
import com.payulatam.fraudvault.client.retrofit.converter.SoapEnvelopeConverter;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.*;
import com.payulatam.fraudvault.client.retrofit.model.soap.response.*;
import com.payulatam.fraudvault.model.request.Transaction;
import com.payulatam.fraudvault.model.response.FraudvaultPosvalidationResponse;
import com.payulatam.fraudvault.model.response.FraudvaultPrevalidationResponse;
import com.payulatam.fraudvault.model.response.FraudvaultQueryStateResponse;
import com.payulatam.fraudvault.model.response.FraudvaultUpdateStateResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Retrofit implementation of the Fraudvault client.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public class RetrofitFraudvaultClient extends FraudvaultClient {
	
	/** Class logger. */
	private final Logger logger = Logger.getLogger(getClass());

	/** Fraudvault service API end point implementation. */
	private IFraudvaultService fraudvaultService;

	/** Create a Retrofit Fraudvault client with the sent configuration. */
	public RetrofitFraudvaultClient(FraudvaultClientConfiguration clientConfiguration) {
		super(clientConfiguration);
		fraudvaultService = createService();
	}

	/**
	 * Create an implementation of the Fraudvault API endpoints.
	 */
	private IFraudvaultService createService() {

		OkHttpClient httpClient = new OkHttpClient.Builder()
				.readTimeout(getClientConfiguration().getReadTimeout(), TimeUnit.MILLISECONDS)
				.connectTimeout(getClientConfiguration().getConnectionTimeout(), TimeUnit.MILLISECONDS)
				.build();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(verifyBaseUrl())
				.client(httpClient)
				.addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
				.build();

		return retrofit.create(IFraudvaultService.class);
	}
	
	/** Verify the API base URL for retrofit and ensure it ends with / because the Base URLs should always end in / 
	 * thus the endpoints values which are relative paths will correctly append themselves to the base. */
	private String verifyBaseUrl(){
		
		String baseUrl = getClientConfiguration().getFraudvaultServiceBaseUrl();
		if(!baseUrl.endsWith("/")){
			baseUrl = baseUrl.concat("/");			
		}
		return baseUrl;		
	}

	/**
	 * @see com.payulatam.fraudvault.api.client.FraudvaultClient#prevalidate(com.payulatam.fraudvault.model.request.Transaction)
	 */
	@Override
	public FraudvaultPrevalidationResponse prevalidate(Transaction transaction) throws FraudvaultException {

		if (transaction == null) {
			throw new IllegalArgumentException("The transaction object to prevalidate can not be null");
		}
		try {
			PrevalidationRequestSoapEnvelope prevalidationSoapEnvelope = SoapEnvelopeConverter
					.getPrevalidationSoapEnvelope(transaction, getClientConfiguration().getCredentials());
			 Call<PrevalidationResponseSoapEnvelope> prevalidationCall = fraudvaultService.prevalidate(prevalidationSoapEnvelope);
			Response<PrevalidationResponseSoapEnvelope> response = prevalidationCall.execute();
			if (response.isSuccessful()) {
				if(response.body() != null) {
					return FraudvaultPrevalidationResponse.fromXml(response.body().getPrevalidationResponseBodyData().getResponseContent());
				} else {
					throw getUnsuccessfulResponseException("The body of the response is null", response);
				}
			} else {
				throw getUnsuccessfulResponseException("Unsuccessful response of prevalidation", response);
			}
		} catch (XmlConversionException e) {
			throw getException("Error converting the data to SOAP envelope objects in prevalidation"
					+ transaction.getTransactionId(), e);
		} catch (Exception e) {
			throw getException("Unexpected error calling the prevalidation Fraudvault service for transaction: "
							+ transaction.getTransactionId(), e);
		}
	}

	/**
	 * @see com.payulatam.fraudvault.api.client.FraudvaultClient#evaluate(com.payulatam.fraudvault.model.request.Transaction)
	 */
	@Override
	public FraudvaultPrevalidationResponse evaluate(Transaction transaction) throws FraudvaultException {

		if (transaction == null) {
			throw new IllegalArgumentException("The transaction object to evaluate can not be null");
		}
		try {
			EvaluationRequestSoapEnvelope evaluationSoapEnvelope = SoapEnvelopeConverter
					.getEvaluationSoapEnvelope(transaction,getClientConfiguration().getCredentials());
			Call<EvaluationResponseSoapEnvelope> evaluationCall = fraudvaultService.evaluate(evaluationSoapEnvelope);
			Response<EvaluationResponseSoapEnvelope> response = evaluationCall.execute();
			if (response.isSuccessful()) {
				if(response.body() != null) {
					return FraudvaultPrevalidationResponse.fromXml(response.body().getPrevalidationResponseBodyData().getResponseContent());
				} else {
					throw getUnsuccessfulResponseException("The body of the response is null", response);
				}
			} else {
				throw getUnsuccessfulResponseException("Unsuccessful response of evaluation", response);
			}
		} catch (XmlConversionException e) {
			throw getException("Error converting the data to SOAP envelope objects in evaluation"
					+ transaction.getTransactionId(), e);
		} catch (Exception e) {
			throw getException("Unexpected error calling the evaluation Fraudvault service for transaction: "
					+ transaction.getTransactionId(), e);
		}
	}

	/**
	 * @see com.payulatam.fraudvault.api.client.FraudvaultClient#posvalidate(java.lang.String)
	 */

	@Override
	public FraudvaultPosvalidationResponse posvalidate(String transactionId) throws FraudvaultException {

		if (transactionId == null) {
			throw new IllegalArgumentException("The transaction ID to posvalidate can not be null");
		}
		try {
			PosvalidationRequestSoapEnvelope postvalidationSoapEnvelope = SoapEnvelopeConverter
					.getPostvalidationSoapEnvelope(transactionId, getClientConfiguration().getCredentials());
			Call<PosvalidationResponseSoapEnvelope> postvalidationCall = fraudvaultService.posvalidate(postvalidationSoapEnvelope);
			Response<PosvalidationResponseSoapEnvelope> response = postvalidationCall.execute();
			if (response.isSuccessful()) {
				return FraudvaultPosvalidationResponse.fromXml(response.body().getPostvalidationResponseBodyData().getResponseContent());
			}
			else {
				throw getUnsuccessfulResponseException("Unsuccessful response postvalidating the transaction: " + transactionId, response);
			}
		} catch (Exception e) {
			throw getException("Unexpected error calling the posvalidation Fraudvault service for transaction: "+ transactionId, e);
		}
	}

	/**	
	 * @see com.payulatam.fraudvault.api.client.FraudvaultClient#queryTransactionState(java.lang.String)
	 */
	@Override
	public FraudvaultQueryStateResponse queryTransactionState(String transactionId) throws FraudvaultException {

		if (transactionId == null) {
			throw new IllegalArgumentException("The transaction ID to query can not be null");
		}
		try {
			QueryStateRequestSoapEnvelope queryStateSoapEnvelope = SoapEnvelopeConverter
					.getQueryStateSoapEnvelope(transactionId, getClientConfiguration().getCredentials());
			Call<QueryStateResponseSoapEnvelope> queryStateCall = fraudvaultService.queryState(queryStateSoapEnvelope);
			Response<QueryStateResponseSoapEnvelope> response = queryStateCall.execute();
			if (response.isSuccessful()) {
				return FraudvaultQueryStateResponse.fromXml(response.body().getQueryStateResponseBodyData().getResponseContent());
			}
			else {
				throw getUnsuccessfulResponseException("Unsuccessful response querying the state of transaction: " + transactionId, response);
			}
		} catch (Exception e) {
			throw getException("Unexpected error calling the query state Fraudvault service for transaction: "+ transactionId, e);
		}
	}

	/**
	 * @see com.payulatam.fraudvault.api.client.FraudvaultClient#updateTransactionState(java.lang.String, java.lang.Long)
	 */
	@Override
	public FraudvaultUpdateStateResponse updateTransactionState(String transactionId, Long stateId) throws FraudvaultException{

		if (transactionId == null || stateId == null) {
			throw new IllegalArgumentException("The transaction ID and the state ID can not be null");
		}
		try {
			UpdateStateRequestSoapEnvelope updateStateRequestSoapEnvelope = SoapEnvelopeConverter
					.getUpdateStateSoapEnvelope(transactionId, stateId, getClientConfiguration().getCredentials());
			Call<UpdateStateResponseSoapEnvelope> updateStateCall = fraudvaultService.updateState(updateStateRequestSoapEnvelope);
			Response<UpdateStateResponseSoapEnvelope> response = updateStateCall.execute();
			if (response.isSuccessful()) {
				return FraudvaultUpdateStateResponse.fromXml(response.body().getUpdateStateResponseBodyData().getResponseContent());
			}
			else {
				throw getUnsuccessfulResponseException("Unsuccessful response updating the state of transaction: "+transactionId, response);
			}
		} catch (Exception e) {
			throw getException("Unexpected error calling the update state Fraudvault service for transaction: "+ transactionId, e);
		}
	}

	/**
	 * Gets a {@code FraudvaultException} with the detail for an unsuccessful response.
	 * 
	 * @param message The message to add in the exception message.
	 * @param response the response of the service call.
	 * @return the {@code FraudvaultException}
	 */
	private <T> FraudvaultException getUnsuccessfulResponseException(String message, Response<T> response) {

		StringBuilder msg = new StringBuilder("Error calling the Fraudvault Service: ")
				.append(message).append(" -Code: ").append(response.code()).append(" -Message: ")
				.append(response.message()).append(" -The raw response was: ").append(response.raw());
		logger.debug(msg.toString());
		return new FraudvaultException(msg.toString());
	}
	
	/**
	 * Gets a {@code FraudvaultException} with the sent data.
	 * 
	 * @param message the message to add in the exception message.
	 * @param response the original exception.
	 * @return the {@code FraudvaultException}
	 */
	private FraudvaultException getException(String message, Exception e){
		logger.debug(message, e);
		return new FraudvaultException(message, e);
	}

}
