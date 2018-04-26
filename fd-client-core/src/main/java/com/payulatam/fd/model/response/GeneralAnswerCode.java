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
 * The general answer codes in the response of a specific calls to the fraud detection service
 *  indicating if the request was successful or not.
 * A value of 1: it represents a successful response code; in case there were no errors,
 * otherwise 2: it represents an error response code; in case there were errors.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public enum GeneralAnswerCode {

	OK(1), FAIL(2), UNKNOWN(-1);

	Integer id;

	/** Map with the (id, GeneralAnswerCode) pairs. */
	private static Map<Integer, GeneralAnswerCode> answerCodesMap = new HashMap<>();

	/** Initializes the map with the (id, GeneralAnswerCode) pairs. */
	static {
		for (GeneralAnswerCode error : GeneralAnswerCode.values()) {
			answerCodesMap.put(error.getId(), error);
		}
	}

	/** Constructor. */
	GeneralAnswerCode(Integer id) {
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
	 * Gets the GeneralAnswerCode by id, if there isn't a GeneralAnswerCode with the sent Id, so the
	 * UNKNOWN value is returned
	 */
	public static GeneralAnswerCode getById(Integer id) {

		GeneralAnswerCode error = null;
		if (id != null) {
			error = answerCodesMap.get(id);
			if (error == null) {
				error = UNKNOWN;
			}
		}
		return error;
	}

}
