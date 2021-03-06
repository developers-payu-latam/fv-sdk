/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 21 de mar. de 2018
 */
package com.payulatam.fraudvault.model.response;

import java.util.HashMap;
import java.util.Map;

/**
 * The decision made by Fraudvault in a posvalidation. This is:
 * 1: If the transaction must be rejected.
 * 2: If the transaction must be stopped.
 * 3: If the transaction must be approved.
 * 7: If the transaction must be monitored in pre-validation
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public enum PrevalidationDecision {

	REJECT(1),
	STOP(2),
	APPROVE(3),
	MONITOR(7),
	UNKNOWN(-1);

	Integer id;

	/** Map with the (id, PrevalidationDecision) pairs. */
	private static Map<Integer, PrevalidationDecision> prevalidationDecisionMap = new HashMap<>();

	/** Initializes the map with the (id, PrevalidationDecision) pairs. */
	static {
		for (PrevalidationDecision decision : PrevalidationDecision.values()) {
			prevalidationDecisionMap.put(decision.getId(), decision);
		}
	}

	/** Constructor. */
	PrevalidationDecision(Integer id) {
		this.id = id;
	}

	public Integer getId() {

		return id;
	}

	/**
	 * Gets the PrevalidationDecision by id, if there isn't a PrevalidationDecision with the sent
	 * key,
	 * so the UNKNOWN value is returned
	 * 
	 * @param id the id.
	 * @return the PrevalidationDecision.
	 */
	public static PrevalidationDecision getById(Integer id) {

		PrevalidationDecision decision = null;
		if (id != null) {
			decision = prevalidationDecisionMap.get(id);
			if (decision == null) {
				decision = PrevalidationDecision.UNKNOWN;
			}
		}
		return decision;
	}

}
