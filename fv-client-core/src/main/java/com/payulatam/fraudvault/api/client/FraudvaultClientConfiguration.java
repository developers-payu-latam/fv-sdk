/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 20 de oct. de 2017
 */
package com.payulatam.fraudvault.api.client;

import com.payulatam.fraudvault.model.request.Credentials;
import lombok.Data;
import lombok.Builder;

/**
 * Defines the configuration attributes for a Fraudvault client.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
@Data
@Builder
public class FraudvaultClientConfiguration {

	/** Indicates if the client should ignore invalid certificates to call .
	 * WARNING: This feature should be used only for testing porpoises!!!
	 * */
	@Builder.Default
	private boolean ignoreInvalidCertificates = false;

	/** URL of the Fraudvault service. */
	private String fraudvaultServiceBaseUrl;

	/** Connect timeout for the Fraudvault service connection in milliseconds. */
	@Builder.Default
	private Integer connectionTimeoutInMillis = 5000;

	/** Read timeout for Fraudvault service connection in milliseconds. */
	@Builder.Default
	private Integer readTimeoutInMillis  = 10000;

	@Builder.Default
	private boolean logHttpRequest = false;

	/** The Fraudvault authentication credentials. */
	private Credentials credentials;

}
