/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de oct. de 2017
 */
package com.payulatam.fraudvault.api.client.exception;

/**
 * Represents a Fraudvault exception.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public class FraudvaultException extends Exception {

	private static final long serialVersionUID = -9096596739135898377L;

	public FraudvaultException(String msg) {
		super(msg);
	}

	public FraudvaultException(String msg, Throwable e) {
		super(msg, e);
	}

	public FraudvaultException(Throwable e) {
		super(e);
	}

}
