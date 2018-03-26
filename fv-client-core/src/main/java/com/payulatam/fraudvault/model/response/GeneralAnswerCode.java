/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 21 de mar. de 2018
 */
package com.payulatam.fraudvault.model.response;

import java.util.HashMap;
import java.util.Map;

/**
 * The general answer codes in the response of a specific calls to Fraudvault indicating if
 * the request was successful or not.
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
