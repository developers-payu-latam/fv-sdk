/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
 */
package com.payulatam.fraudvault.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Data;

/**
 * Encapsulates the data of the control lists matching with the transaction data in Fraudvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "listas-de-control")
@Data
public class ControlListsInformation {

	/** Indicates the matching of any transaction data with the data contained in Fraudvault's 
	 *  white lists database. */
	@Element(name = "lista-blanca")
	private ListMatch whiteListsMatching;

	/** Indicates the matching of any transaction data with the data contained in Fraudvault's 
	 *  black lists database. */
	@Element(name = "lista-negra")
	private ListMatch blackListsMatching;

	/** Indicates the matching of any transaction data with the data contained in Fraudvault's 
	 *  temporary lists database. */
	@Element(name = "autorizacion-temporal")
	private ListMatch temporaryListsMatching;

}
