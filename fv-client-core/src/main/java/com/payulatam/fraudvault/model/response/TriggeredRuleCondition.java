/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.payulatam.fraudvault.model.response;

import lombok.Builder;
import lombok.Data;

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
