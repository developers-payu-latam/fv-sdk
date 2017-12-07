/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 13 de oct. de 2017
 */
package com.payulatam.fraudvault.client.retrofit;

import com.payulatam.fraudvault.client.retrofit.model.soap.request.EvaluationRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.PosvalidationRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.PrevalidationRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.QueryStateRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.request.UpdateStateRequestSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.response.EvaluationResponseSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.response.PosvalidationResponseSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.response.PrevalidationResponseSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.response.QueryStateResponseSoapEnvelope;
import com.payulatam.fraudvault.client.retrofit.model.soap.response.UpdateStateResponseSoapEnvelope;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Fraudvault service API end points definitions.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public interface IFraudvaultService {

	@POST("ws/maf")
	@Headers({ "Content-Type: text/xml" })
	Call<PrevalidationResponseSoapEnvelope> prevalidate(@Body PrevalidationRequestSoapEnvelope body);

	@POST("ws/maf")
	@Headers({ "Content-Type: text/xml" })
	Call<PosvalidationResponseSoapEnvelope> posvalidate(@Body PosvalidationRequestSoapEnvelope body);

	@POST("ws/maf")
	@Headers({ "Content-Type: text/xml" })
	Call<EvaluationResponseSoapEnvelope> evaluate(@Body EvaluationRequestSoapEnvelope body);

	@POST("ws/maf")
	@Headers({ "Content-Type: text/xml" })
	Call<QueryStateResponseSoapEnvelope> queryState(@Body QueryStateRequestSoapEnvelope body);

	@POST("ws/maf")
	@Headers({ "Content-Type: text/xml" })
	Call<UpdateStateResponseSoapEnvelope> updateState(@Body UpdateStateRequestSoapEnvelope body);

}
