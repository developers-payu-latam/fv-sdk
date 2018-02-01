/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
 */
package com.payulatam.fraudvault.model.response.xml;

import org.simpleframework.xml.Element;

import lombok.Data;

/**
 * Encapsulates the data for the matching of a control list.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public class ListMatch {

	@Element(name = "coincide", required = false)
	private boolean match;

	@Element(name = "parametro", required = false)
	private String parameter;

}
