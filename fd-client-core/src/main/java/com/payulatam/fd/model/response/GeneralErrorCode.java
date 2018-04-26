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
 * The general error codes in the response of a specific call to the fraud detection service.
 * Values from 1001 to 1999 are used to the error identifier.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public enum GeneralErrorCode {

	INVALID_CREDENTIALS_ERROR(1001),
	INVALID_PARAMETERS_ERROR(1002),
	READING_RESPONSE_ERROR(1003),
	COMMUNICATION_ERROR(1004),
	READING_XML_ERROR(1005),
	EVALUATING_TRANSACTION_ERROR(1006),
	QUERY_STATE_ERROR(1007),
	UPDATE_STATE_ERROR(1008),
	INVALID_STATE_ERROR(1010),
	UNAVAILABLE_SERVICE_ERROR(1016),
	CALLING_EVALUATION_SERVICE_ERROR(1029),
	FD_GENERAL_ERROR(1020),
	UNKNOWN(-1);

	Integer id;

	/** Map with the (id, GeneralErrorCode) pairs. */
	private static Map<Integer, GeneralErrorCode> generalErrorCodesMap = new HashMap<>();

	/** Initializes the map with the (id, GeneralErrorCode) pairs. */
	static {
		for (GeneralErrorCode error : GeneralErrorCode.values()) {
			generalErrorCodesMap.put(error.getId(), error);
		}
	}

	/** Constructor. */
	GeneralErrorCode(Integer id) {
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
	 * Gets the GeneralErrorCode by id, if there isn't a GeneralErrorCode with the sent Id, so the
	 * UNKNOWN value is returned
	 */
	public static GeneralErrorCode getById(Integer id) {

		GeneralErrorCode error = null;
		if (id != null) {
			error = generalErrorCodesMap.get(id);
			if (error == null) {
				error = UNKNOWN;
			}
		}
		return error;
	}

}