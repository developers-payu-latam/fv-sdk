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
 * The detailed error codes in the response of a specific calls to the fraud detection service.
 * Values from 2001 to 2999 are used for the error identifier.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public enum ErrorCode {

	LOADING_RULES_PROFILES_ERROR(2001),
	EXECUTING_RULES_ERROR(2002),
	EXECUTING_EVALUATION_ERROR(2003),
	VALIDATION_QUEUE_NOT_FOUND_ERROR(2004),
	SEARCHING_SIMILAR_TRANSACTIONS_ERROR(2005),
	UNKNOWN(-1);

	/** The identifier of the error code. */
	Integer id;

	/** Map with the (id, ErrorCode) pairs. */
	private static Map<Integer, ErrorCode> errorCodeMap = new HashMap<>();

	/** Initializes the map with the (id, ErrorCode) pairs. */
	static {
		for (ErrorCode error : ErrorCode.values()) {
			errorCodeMap.put(error.getId(), error);
		}
	}

	/** Constructor. */
	ErrorCode(Integer id) {
		this.id = id;
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
	 * Gets the ErrorCode by id, if there isn't a ErrorCode with the sent Id, so the UNKNOWN value
	 * is returned
	 */
	public static ErrorCode getById(Integer id) {

		ErrorCode error = null;
		if (id != null) {
			error = errorCodeMap.get(id);
			if (error == null) {
				error = UNKNOWN;
			}
		}
		return error;
	}

}
