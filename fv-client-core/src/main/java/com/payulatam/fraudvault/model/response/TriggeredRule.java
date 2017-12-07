/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
 */
package com.payulatam.fraudvault.model.response;

import org.simpleframework.xml.Element;

import lombok.Data;

/**
 * Encapsulates the data of a triggered rule associated with a profile in Fraudvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public class TriggeredRule {

	@Element(name = "nombre-regla", required = false)
	private String ruleName;
	
	@Element(name = "nombre", required = false)
	private String filteredAttributeName;
	
	@Element(name = "valor", required = false)
	private String filteredValue;
	
	@Element(name = "valor-configuracion", required = false)
	private String ruleConfiguredValue;
	
	@Element(name = "operador", required = false)
	private String operator;

}
