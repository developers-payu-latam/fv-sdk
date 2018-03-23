/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
 */
package com.payulatam.fraudvault.model.response;

import org.simpleframework.xml.Element;

import lombok.Data;

/**
 * Encapsulates the data for the matching of a control list.
 * The lists in Fraudvault are used to save data bases of reliable/good data(also named white lists)
 * and unreliable/bad data(also named black lists).
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public class ListMatch {

	/** Indicates if some transaction field matchs with some value contained in a Fraudvault list. */
	@Element(name = "coincide", required = false)
	private boolean match;

	/** The name of the transaction field matching with some value contained in a Fraudvault list. */
	@Element(name = "parametro", required = false)
	private String transactionFieldName;

}
