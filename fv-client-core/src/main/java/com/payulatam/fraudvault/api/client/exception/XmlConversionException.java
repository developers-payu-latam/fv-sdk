/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de dic. de 2017
 */
package com.payulatam.fraudvault.api.client.exception;


/**
 * Represents an exception related with the process of serialization/deserialization of XML data.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public class XmlConversionException extends Exception {

	private static final long serialVersionUID = -9096596739135898377L;

	public XmlConversionException(String msg) {
		super(msg);
	}

	public XmlConversionException(String msg, Throwable e) {
		super(msg, e);
	}

	public XmlConversionException(Throwable e) {
		super(e);
	}

}
