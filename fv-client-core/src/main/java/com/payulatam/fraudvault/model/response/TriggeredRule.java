/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
 */
package com.payulatam.fraudvault.model.response;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Encapsulates the data of the triggered rules associated with a rules profile in Fraudvault.
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
