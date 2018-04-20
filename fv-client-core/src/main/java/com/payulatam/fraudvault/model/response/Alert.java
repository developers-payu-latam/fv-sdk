/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 22 de mar. de 2018
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
 * The alerts generated in the Fraudvault prevalidation/posvalidation process.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public enum Alert {

	EMAIL_HAS_NUMBERS("EMAIL_CONTIENE_NUMEROS"),
	EMAIL_FREE_PROVIDER("EMAIL_PROVEEDOR_GRATUITO"),
	EMAIL_HAS_DICTIONARY_WORD("EMAIL_CONTIENE_PALABRA_DICCIONARIO"),
	INTERNATIONAL_IP("IP_INTERNACIONAL"),
	DESCRIPTION_HAS_SUSPICIOUS_WORD("DESC_CONTIENE_PALABRA_SOSPECHOSA"),
	INTERNATIONAL_CARD("TC_INTERNACIONAL"),
	HAS_SIMILAR_FRAUDULENT("TIENE_SIMILAR_FRAUDULENTA"),
	EMAIL_DOESNT_MATCH_NAME("EMAIL_NO_COINCIDE_CON_NOMBRE"),
	EMAIL_SUSPICIOUS("EMAIL_SOSPECHOSO"),
	UNKNOWN("UNKNOWN");

	/** The key for the Alert. */
	String alertKey;

	/** Map with the (alertKey, Alert) pairs. */
	private static Map<String, Alert> alertsMap = new HashMap<>();

	/** Constructor. */
	Alert(String key) {
		alertKey = key;
	}

	/** Initializes the map with the (alertKey, Alert) pairs. */
	static {
		for (Alert alert : Alert.values()) {
			alertsMap.put(alert.getAlertKey(), alert);
		}
	}

	/**
	 * Returns the alertKey
	 * 
	 * @return the alertKey
	 */
	public String getAlertKey() {

		return alertKey;
	}

	/**
	 * Gets the Alert by key, if there isn't a Alert with the sent key, so the UNKNOWN value
	 * is returned
	 */
	public static Alert getByKey(String key) {

		Alert alert = null;
		if (key != null && !key.isEmpty()) {
			alert = alertsMap.get(key);
			if (alert == null) {
				alert = UNKNOWN;
			}
		}
		return alert;
	}

}
