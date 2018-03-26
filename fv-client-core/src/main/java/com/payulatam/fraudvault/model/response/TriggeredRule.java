/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
 */
package com.payulatam.fraudvault.model.response;

import org.simpleframework.xml.Element;

import lombok.Data;

/**
 * Encapsulates the data of a triggered rule associated with a rules profile in Fraudvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public class TriggeredRule {

	/**
	 * The name of the rule that has been triggered.
	 */
	@Element(name = "nombre-regla", required = false)
	private String ruleName;
	
	/**
	 * The name of the field/attribute in the transaction data that produced the trigger of the rule.
	 */
	@Element(name = "nombre", required = false)
	private String transactionFieldName;
	
	/**
	 * The value of the field in the transaction data that produced the trigger of the rule.
	 */
	@Element(name = "valor", required = false)
	private String transactionFieldValue;
	
	/**
	 * The configured value in the rule definition.
	 */
	@Element(name = "valor-configuracion", required = false)
	private String ruleConfiguredValue;
	
	/**
	 * The configured operator in the rule definition, the operator is applied to the configured 
	 * value in the rule definition and the specific transaction field value.
	 */
	@Element(name = "operador", required = false)
	private String operator;

}
