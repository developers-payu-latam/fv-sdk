/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 20 de oct. de 2017
 */
package com.payulatam.fraudvault.api.client;

import com.payulatam.fraudvault.model.request.Credentials;

import lombok.Data;

/**
 * Defines the configuration attributes for a Fraudvault client.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
@Data
public class FraudvaultClientConfiguration {

	/** URL of the Fraudvault service. */
	private String fraudvaultServiceBaseUrl;

	/** Connect timeout for the Fraudvault service connection in milliseconds. */
	private Integer connectionTimeout;

	/** Read timeout for Fraudvault service connection in milliseconds. */
	private Integer readTimeout;

	/** The Fraudvault authentication credentials. */
	private Credentials credentials;

	/** Default milliseconds for the connection timeout in case there is none provided. */
	private static final int DEFAULT_CONNECTION_TIMEOUT = 5000;

	/** Default milliseconds for the read timeout in case there is none provided. */
	private static final int DEFAULT_READ_TIMEOUT = 10000;

	/**
	 * Create a {@code FraudvaultClientConfiguration} with the attributes of the class Builder. If
	 * the timeouts attributes are not provided in the builder then the object will have the
	 * {@link #DEFAULT_CONNECTION_TIMEOUT} and the {@link #DEFAULT_READ_TIMEOUT}
	 * 
	 * @param builder the Builder of the class.
	 */
	private FraudvaultClientConfiguration(Builder builder) {
		this.fraudvaultServiceBaseUrl = builder.fraudvaultServiceBaseUrl;
		this.credentials = builder.credentials;

		Integer builderConnectionTimeout = builder.connectionTimeout;
		if (builderConnectionTimeout != null) {
			this.connectionTimeout = builderConnectionTimeout;
		}
		else {
			this.connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
		}

		Integer builderReadTimeout = builder.readTimeout;
		if (builderReadTimeout != null) {
			this.readTimeout = builderReadTimeout;
		}
		else {
			this.readTimeout = DEFAULT_READ_TIMEOUT;
		}
	}

	public static Builder builder(Credentials credentials, String fraudvaultServiceBaseUrl) {

		return new Builder(credentials, fraudvaultServiceBaseUrl);
	}

	public static class Builder {

		private String fraudvaultServiceBaseUrl;
		private Integer connectionTimeout;
		private Integer readTimeout;
		private Credentials credentials;

		public Builder(Credentials credentials, String fraudvaultServiceBaseUrl) {
			this.credentials = credentials;
			this.fraudvaultServiceBaseUrl = fraudvaultServiceBaseUrl;
		}

		public Builder connectionTimeout(Integer connectionTimeout) {

			this.connectionTimeout = connectionTimeout;
			return this;
		}

		public Builder readTimeout(Integer readTimeout) {

			this.readTimeout = readTimeout;
			return this;
		}

		public FraudvaultClientConfiguration build() {

			return new FraudvaultClientConfiguration(this);
		}
	}

}
