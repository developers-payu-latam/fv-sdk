/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 22 de mar. de 2018
 */
package com.payulatam.fraudvault.model.response;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

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
		if (StringUtils.isNotBlank(key)) {
			alert = alertsMap.get(key);
			if (alert == null) {
				alert = UNKNOWN;
			}
		}
		return alert;
	}

}
