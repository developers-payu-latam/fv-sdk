/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 28 de nov. de 2017
 */
package com.payulatam.fraudvault.client.retrofit.model.soap.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Encapsulates the parameters data of a soap message envelope for the fraudvault prevalidation request.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
@Root
@Data
@EqualsAndHashCode(callSuper = true)
public class PrevalidationBodyData extends RequestBodyBaseData {

	@Element(name = "arg3", data = true)
	private String transaction;

}