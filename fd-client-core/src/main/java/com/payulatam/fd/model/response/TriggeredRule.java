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

package com.payulatam.fd.model.response;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Encapsulates the data of the triggered rules associated with a rules profile in the fraud detection system.
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
     * The names of the rules.
     */
    @ElementList(inline = true, entry = "nombre", required = false)
    private List<String> names;

    /**
     * The values of the rules.
     */
    @ElementList(inline = true, entry = "valor", required = false)
    private List<String> values;

    /**
     * The configuration values of the rules.
     */
    @ElementList(inline = true, entry = "valor-configuracion", required = false)
    private List<String> configurationValues;

    /**
     * The operators of the rules.
     */
    @ElementList(inline = true, entry = "operador", required = false)
    private List<String> operators;

    /**
     * <p>
     * Gets {@link TriggeredRuleCondition} using list of names, values, operators, and configuration values contained in
     * the current rule.
     * </p>
     * <p>
     * For example the index <code>0</code> obtains a {@link TriggeredRuleCondition} that contains the data of names [0],
     * values[0],configurationValues[0], and operators[0].
     * </p>
     *
     * @param index the position of a rule condition in the list of rule conditions.
     * @return the rule condition located in the given position.
     * @throws IllegalArgumentException when the given index is invalid.
     */
    public TriggeredRuleCondition getTriggeredRuleCondition(Integer index) {
        TriggeredRuleCondition triggeredRuleCondition;
        try {
            triggeredRuleCondition = TriggeredRuleCondition.builder().transactionFieldName(names.get(index)).
                    transactionFieldValue(values.get(index)).ruleConfiguredValue(configurationValues.get(index)).operator(operators.get(index)).build();
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid index", ex);
        }
        return triggeredRuleCondition;
    }

    /**
     * Obtains the size of the triggered rule conditions.
     *
     * @return size of the triggered rule conditions .
     */
    public Integer getSizeOfTriggeredRuleConditions() {
        return (names != null ? names.size() : 0);
    }

}
