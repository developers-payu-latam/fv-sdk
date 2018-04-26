/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 21 de mar. de 2018
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

import java.util.HashMap;
import java.util.Map;

/**
 * The decision made by the fraud detection service in a posvalidation. This is:
 * 4: If the transaction must be reversed.
 * 5: If the transaction must be validated.
 * 6: If the transaction must be released.
 * 8: If the transaction must be monitored in posvalidation
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public enum PosvalidationDecision {

	REVERSE(4),
	VALIDATE(5),
	LIBERATE(6),
	MONITOR(8),
	UNKNOWN(-1);

	Integer id;

	/** Map with the (id, PosvalidationDecision) pairs. */
	private static Map<Integer, PosvalidationDecision> posvalidationDecisionMap = new HashMap<>();

	/** Initializes the map with the (id, PosvalidationDecision) pairs. */
	static {
		for (PosvalidationDecision decision : PosvalidationDecision.values()) {
			posvalidationDecisionMap.put(decision.getId(), decision);
		}
	}

	/** Constructor. */
	PosvalidationDecision(Integer id) {
		this.id = id;
	}

	public Integer getId() {

		return id;
	}

	/**
	 * Gets the PosvalidationDecision by id, if there isn't a PosvalidationDecision with the sent
	 * key, so the UNKNOWN value is returned
	 * 
	 * @param id the id.
	 * @return the PosvalidationDecision
	 */
	public static PosvalidationDecision getById(Integer id) {

		PosvalidationDecision decision = null;
		if (id != null) {
			decision = posvalidationDecisionMap.get(id);
			if (decision == null) {
				decision = PosvalidationDecision.UNKNOWN;
			}
		}
		return decision;
	}

}
