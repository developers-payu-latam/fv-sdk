/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 23 de mar. de 2018
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

import java.util.HashMap;
import java.util.Map;

/**
 * The transaction state in Frausvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public enum TransactionState {

	NEW(1),
	STOPPED(2),
	AUTHORIZED_BY_FRAUDVAULT(3),
	REJECTED_BY_FRAUDVAULT(4),
	APPROVED_BY_FINANCIAL_ENTITY(5),
	NOT_APPROVED_BY_FINANCIAL_ENTITY(6),
	IN_AUTORIZATION(7),
	LIBERATED(8),
	IN_VALIDATION(9),
	CANCELED(10),
	FRAUD(11),
	FRAUD_INTENT(12),
	REJECTED_BY_ANALYST(13),
	AUTHORIZED_BY_ANALYST_TO_SEND_FINANCIAL_ENTITY(14),
	UNKNOWN(-1);

	private Integer id;

	/** Constructor. */
	TransactionState(Integer id) {
		this.id = id;
	}

	/** Map with the (id, TransactionState) pairs. */
	private static Map<Integer, TransactionState> statesMap = new HashMap<>();

	/** Initializes the map with the (id, TransactionState) pairs. */
	static {
		for (TransactionState state : TransactionState.values()) {
			statesMap.put(state.getId(), state);
		}
	}

	/**
	 * Returns the id
	 * 
	 * @return the id
	 */
	public Integer getId() {

		return id;
	}

	/**
	 * Gets the TransactionState by id, if there isn't a TransactionState with the sent key,
	 * so the UNKNOWN value is returned
	 * 
	 * @param id the id.
	 * @return the TransactionState.
	 */
	public static TransactionState getById(Integer id) {

		TransactionState state = null;
		if (id != null) {
			state = statesMap.get(id);
			if (state == null) {
				state = TransactionState.UNKNOWN;
			}
		}
		return state;
	}

}
