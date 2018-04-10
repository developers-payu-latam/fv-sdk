/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
 */
package com.payulatam.fraudvault.model.response;

import lombok.Builder;
import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Encapsulates the data of a triggered rule associated with a rules profile in Fraudvault.
 * 
 * @author <a href="mailto:fredy.dorado@payulatam.com">Fredy Dorado</a>
 */
@Data
@Builder
public class TriggeredRuleCondition {

	/**
	 * The name of the field/attribute in the transaction data that produced the trigger of the rule.
	 */
	private String transactionFieldName;

	/**
	 * The value of the field in the transaction data that produced the trigger of the rule.
	 */
	private String transactionFieldValue;
	
	/**
	 * The configured value in the rule definition.
	 */
	private String ruleConfiguredValue;
	
	/**
	 * The configured operator in the rule definition, the operator is applied to the configured 
	 * value in the rule definition and the specific transaction field value.
	 */
	private String operator;

}
