/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 18 de oct. de 2017
 */
package com.payulatam.fraudvault.model.request;

import lombok.Data;

/**
 * Encapsulates the data necessary for a client authentication to the Fraudvault service.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
@Data
public class Credentials {

	/** The client identifier, it's provided by PayU Latam. */
	private Long clientId;

	/** The login user assigned to the client, it's provided by PayU Latam. */
	private String login;

	/** The password assigned to the client, it's provided by PayU Latam. */
	private String password;
	
	private Credentials(Builder builder) {
		this.clientId = builder.clientId;
		this.login = builder.login;
		this.password = builder.password;
	}

	public static Builder builder(Long clientId, String login, String password) {

		return new Builder(clientId, login, password);
	}

	public static class Builder {

		private Long clientId;
		private String login;
		private String password;

		public Builder(Long clientId, String login, String password) {

			if (clientId == null || login == null || password == null) {
				throw new IllegalArgumentException("The authentication credentials must not be null");
			}
			this.clientId = clientId;
			this.login = login;
			this.password = password;
		}

		public Credentials build() {

			return new Credentials(this);
		}
	}
}
