/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 17 de oct. de 2017
 */
package com.payulatam.fraudvault.client.retrofit.model.soap.request;

import org.simpleframework.xml.Element;

import lombok.Data;

/**
 * Encapsulates the base parameters of the Fraudvault service request.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
@Element
@Data
public class RequestBodyBaseData {

	@Element(name = "arg0")
	private Long clientId;
	
	@Element(name = "arg1")
	private String login;
	
	@Element(name = "arg2")
	private String password;

}
