/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 13 de oct. de 2017
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

package com.payulatam.fd.client.retrofit;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payulatam.fd.client.retrofit.converter.ResponseConverter;
import com.payulatam.fd.client.retrofit.converter.SoapEnvelopeConverter;
import com.payulatam.fd.client.retrofit.model.soap.request.*;
import com.payulatam.fd.client.retrofit.model.soap.response.*;
import com.payulatam.fd.api.client.FDClient;
import com.payulatam.fd.api.client.FDClientConfiguration;
import com.payulatam.fd.api.client.exception.FDException;
import com.payulatam.fd.api.client.exception.XmlConversionException;
import com.payulatam.fd.model.request.Transaction;
import com.payulatam.fd.model.response.FDPosvalidationResponse;
import com.payulatam.fd.model.response.FDPrevalidationResponse;
import com.payulatam.fd.model.response.FDStateQueryResponse;
import com.payulatam.fd.model.response.FDStateUpdateResponse;
import com.payulatam.fd.model.response.soap.PosvalidationResponsesSoapWrapper;
import com.payulatam.fd.model.response.soap.PrevalidationResponseSoapWrapper;
import com.payulatam.fd.model.response.soap.QueryStateResponseSoapWrapper;
import com.payulatam.fd.model.response.soap.UpdateStateResponseSoapWrapper;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import javax.net.ssl.*;

/**
 * Retrofit client implementation for the fraud detection service.
 *
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public class RetrofitFDClient extends FDClient {

	/** Class logger. */
	private static final Logger logger = LoggerFactory.getLogger(RetrofitFDClient.class);

	/** The service API end point implementation. */
	private IFraudDetectionService fraudDetectionService;

	/** Create a Retrofit client with the sent configuration. */
	public RetrofitFDClient(FDClientConfiguration clientConfiguration) {
		super(clientConfiguration);
		fraudDetectionService = createService();
	}

	/**
	 * Create an implementation of the service API endpoints.
	 */
	private IFraudDetectionService createService() {

		final OkHttpClient.Builder builder = new OkHttpClient.Builder()
				.readTimeout(getClientConfiguration().getReadTimeoutInMillis(), TimeUnit.MILLISECONDS)
				.connectTimeout(getClientConfiguration().getConnectionTimeoutInMillis(), TimeUnit.MILLISECONDS);

        if (super.getClientConfiguration().isIgnoreInvalidCertificates()) {
            configureIgnoringCertificates(builder);
		}

		if (super.getClientConfiguration().isLogHttpRequest()){
        	configureHttpLogging(builder);
		}

		OkHttpClient httpClient = builder.build();
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl(adaptBaseUrl())
				.client(httpClient)
				.addConverterFactory(SimpleXmlConverterFactory.createNonStrict(new Persister(new AnnotationStrategy())))
				.build();

		return retrofit.create(IFraudDetectionService.class);
	}

	private static void configureHttpLogging(OkHttpClient.Builder builder) {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		builder.addInterceptor(logging);
	}

    private static void configureIgnoringCertificates(OkHttpClient.Builder builder) {
        logger.error("Ignoring SSL certificates, this should be used only for testing porpoises!");
        try {
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            final TrustManager[] trustAllCerts = new TrustManager[]{ new X509TrustAllCertificates() };
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    });
        } catch (Exception e) {
            logger.error("Error configuring OkHttpClient ignoring certificates", e);
        }
    }

    private static class X509TrustAllCertificates implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /** Adapt the API base URL for retrofit ensuring it ends with / because the Base URLs should always end in /
	 * thus the endpoints values which are relative paths will correctly append themselves to the base. */
	private String adaptBaseUrl(){
		
		String baseUrl = getClientConfiguration().getFdServiceBaseUrl();
		if(!baseUrl.endsWith("/")){
			baseUrl = baseUrl.concat("/");			
		}
		return baseUrl;		
	}

	/**
	 * @see com.payulatam.fd.api.client.FDClient#prevalidate(com.payulatam.fd.model.request.Transaction)
	 */
	@Override
	public FDPrevalidationResponse prevalidate(Transaction transaction) throws FDException {

		if (transaction == null) {
			throw new IllegalArgumentException("The transaction object to prevalidate can not be null");
		}
		try {
			PrevalidationRequestSoapEnvelope prevalidationSoapEnvelope = SoapEnvelopeConverter
					.getPrevalidationSoapEnvelope(transaction, getClientConfiguration().getCredentials());
			 Call<PrevalidationResponseSoapEnvelope> prevalidationCall = fraudDetectionService.prevalidate(prevalidationSoapEnvelope);
			Response<PrevalidationResponseSoapEnvelope> response = prevalidationCall.execute();
			if (response.isSuccessful()) {
				PrevalidationResponseSoapEnvelope reponseBody = response.body();
				if(reponseBody != null) {
					if (reponseBody.getPrevalidationResponseBodyData() != null) {
						PrevalidationResponseSoapWrapper prevalidationResponse = PrevalidationResponseSoapWrapper
								.fromXml(reponseBody.getPrevalidationResponseBodyData().getResponseContent());
						return ResponseConverter.getFDPrevalidationResponse(prevalidationResponse);
					} else {
						throw getUnsuccessfulResponseException("Unexpected error: the prevalidation response body data "
								+ "is not present in the response body.", response);
					}
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
			throw getException("Unexpected error calling the prevalidation service for transaction: "
							+ transaction.getTransactionId(), e);
		}
	}

	/**
	 * @see com.payulatam.fd.api.client.FDClient#evaluate(com.payulatam.fd.model.request.Transaction)
	 */
	@Override
	public FDPrevalidationResponse evaluate(Transaction transaction) throws FDException {

		if (transaction == null) {
			throw new IllegalArgumentException("The transaction object to evaluate can not be null");
		}
		try {
			EvaluationRequestSoapEnvelope evaluationSoapEnvelope = SoapEnvelopeConverter
					.getEvaluationSoapEnvelope(transaction,getClientConfiguration().getCredentials());
			Call<EvaluationResponseSoapEnvelope> evaluationCall = fraudDetectionService.evaluate(evaluationSoapEnvelope);
			Response<EvaluationResponseSoapEnvelope> response = evaluationCall.execute();
			if (response.isSuccessful()) {
				EvaluationResponseSoapEnvelope reponseBody = response.body();
				if(reponseBody != null) {
					if (reponseBody.getPrevalidationResponseBodyData() != null) {
						PrevalidationResponseSoapWrapper evaluationResponse = PrevalidationResponseSoapWrapper
								.fromXml(reponseBody.getPrevalidationResponseBodyData().getResponseContent());
						return ResponseConverter.getFDPrevalidationResponse(evaluationResponse);
					} else {
						throw new Exception("Unexpected error: the evaluation response body data is not present in the response body.");
					}
				} else {
					throw getUnsuccessfulResponseException("The body of the evaluation response is null", response);
				}
			} else {
				throw getUnsuccessfulResponseException("Unsuccessful response of evaluation", response);
			}
		} catch (XmlConversionException e) {
			throw getException("Error converting the data to SOAP envelope objects in evaluation"
					+ transaction.getTransactionId(), e);
		} catch (Exception e) {
			throw getException("Unexpected error calling the evaluation service for transaction: "
					+ transaction.getTransactionId(), e);
		}
	}

	/**
	 * @see com.payulatam.fd.api.client.FDClient#posvalidate(String)
	 */

	@Override
	public FDPosvalidationResponse posvalidate(String transactionId) throws FDException {

		if (transactionId == null) {
			throw new IllegalArgumentException("The transaction ID to posvalidate can not be null");
		}
		try {
			PosvalidationRequestSoapEnvelope postvalidationSoapEnvelope = SoapEnvelopeConverter
					.getPostvalidationSoapEnvelope(transactionId, getClientConfiguration().getCredentials());
			Call<PosvalidationResponseSoapEnvelope> postvalidationCall = fraudDetectionService.posvalidate(postvalidationSoapEnvelope);
			Response<PosvalidationResponseSoapEnvelope> response = postvalidationCall.execute();
			if (response.isSuccessful()) {
				PosvalidationResponseSoapEnvelope responseBody = response.body();
				if (responseBody != null) {
					if (responseBody.getPostvalidationResponseBodyData() != null) {
						PosvalidationResponsesSoapWrapper posvalidationResponse = PosvalidationResponsesSoapWrapper
								.fromXml(responseBody.getPostvalidationResponseBodyData().getResponseContent());
						return ResponseConverter.getFDPosvalidationResponse(posvalidationResponse);
					} else {
						throw getUnsuccessfulResponseException( "Unexpected error: the posvalidation "
								+ "response body data is not present in the response body.", response);
					}
				} else {
					throw getUnsuccessfulResponseException("The body of the posvalidate response is null", response);
				}
			} else {
				throw getUnsuccessfulResponseException("Unsuccessful response postvalidating the transaction: " + transactionId, response);
			}
		} catch (Exception e) {
			throw getException("Unexpected error calling the posvalidation service for transaction: "+ transactionId, e);
		}
	}

	/**	
	 * @see com.payulatam.fd.api.client.FDClient#queryTransactionState(java.lang.String)
	 */
	@Override
	public FDStateQueryResponse queryTransactionState(String transactionId) throws FDException {

		if (transactionId == null) {
			throw new IllegalArgumentException("The transaction ID to query can not be null");
		}
		try {
			QueryStateRequestSoapEnvelope queryStateSoapEnvelope = SoapEnvelopeConverter
					.getQueryStateSoapEnvelope(transactionId, getClientConfiguration().getCredentials());
			Call<QueryStateResponseSoapEnvelope> queryStateCall = fraudDetectionService.queryState(queryStateSoapEnvelope);
			Response<QueryStateResponseSoapEnvelope> response = queryStateCall.execute();
			if (response.isSuccessful()) {
				QueryStateResponseSoapEnvelope responseBody = response.body();
				if(responseBody != null){
					if(responseBody.getQueryStateResponseBodyData() != null){
						QueryStateResponseSoapWrapper queryStateResponse = QueryStateResponseSoapWrapper
								.fromXml(responseBody.getQueryStateResponseBodyData().getResponseContent());
						return ResponseConverter.getStateQueryResponse(queryStateResponse);
					} else {
						throw getUnsuccessfulResponseException( "Unexpected error: the query "
								+ "response body data is not present in the response body.", response);
					}
				} else {
					throw getUnsuccessfulResponseException("The body of the state query response is null", response);
				}
			} else {
				throw getUnsuccessfulResponseException("Unsuccessful response querying the state of transaction: " + transactionId, response);
			}
		} catch (Exception e) {
			throw getException("Unexpected error calling the query state service for transaction: "+ transactionId, e);
		}
	}

	/**
	 * @see com.payulatam.fd.api.client.FDClient#updateTransactionState(java.lang.String, java.lang.Long)
	 */
	@Override
	public FDStateUpdateResponse updateTransactionState(String transactionId, Long stateId) throws FDException{

		if (transactionId == null || stateId == null) {
			throw new IllegalArgumentException("The transaction ID and the state ID can not be null");
		}
		try {
			UpdateStateRequestSoapEnvelope updateStateRequestSoapEnvelope = SoapEnvelopeConverter
					.getUpdateStateSoapEnvelope(transactionId, stateId, getClientConfiguration().getCredentials());
			Call<UpdateStateResponseSoapEnvelope> updateStateCall = fraudDetectionService.updateState(updateStateRequestSoapEnvelope);
			Response<UpdateStateResponseSoapEnvelope> response = updateStateCall.execute();
			if (response.isSuccessful()) {
				UpdateStateResponseSoapEnvelope responseBody = response.body();
				if(responseBody != null){
					if(responseBody.getUpdateStateResponseBodyData() != null) {
						UpdateStateResponseSoapWrapper updateStateResponse = UpdateStateResponseSoapWrapper.
								fromXml(responseBody.getUpdateStateResponseBodyData().getResponseContent());
						return ResponseConverter.getStateUpdateResponse(updateStateResponse);
					} else {
						throw getUnsuccessfulResponseException( "Unexpected error: the update "
								+ "response body data is not present in the response body.", response);
					}
				} else {
					throw getUnsuccessfulResponseException("The body of the state update response is null", response);
				}
			} else {
				throw getUnsuccessfulResponseException("Unsuccessful response updating the state of transaction: "+transactionId, response);
			}
		} catch (Exception e) {
			throw getException("Unexpected error calling the update state service for transaction: "+ transactionId, e);
		}
	}

	/**
	 * Gets a {@code FDException} with the detail for an unsuccessful response.
	 * 
	 * @param message The message to add in the exception message.
	 * @param response the response of the service call.
	 * @return the {@code FDException}
	 */
	private <T> FDException getUnsuccessfulResponseException(String message, Response<T> response) {

		StringBuilder msg = new StringBuilder("Error calling the fraud detection service: ")
				.append(message).append(" -Code: ").append(response.code()).append(" -Message: ")
				.append(response.message()).append(" -The raw response was: ").append(response.raw());
		logger.debug(msg.toString());
		return new FDException(msg.toString());
	}
	
	/**
	 * Gets a {@code FDException} with the sent data.
	 * 
	 * @param message the message to add in the exception message.
	 * @param response the original exception.
	 * @return the {@code FDException}
	 */
	private FDException getException(String message, Exception e){
		logger.debug(message, e);
		return new FDException(message, e);
	}

}
