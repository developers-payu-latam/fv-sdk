/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 22 de mar. de 2018
 */
package com.payulatam.fraudvault.model.response;

import java.util.HashMap;
import java.util.Map;

/**
 * They are the actions triggered in the execution of rules. The actions triggered are according
 * to transaction data that match with the rules profiles configuration in Fraudvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public enum Action {

	REJECT("RECHAZAR"),
	STOP("DETENER"),
	APPROVE("APROBAR"),
	REVERSE("REVERSAR"),
	VALIDATE("VALIDAR"),
	LIBERATE("LIBERAR"),
	MONITOR("MONITOREAR"),
	UNKNOWN("UNKNOWN");

	/** The key for the Action. */
	String actionKey;

	/** Map with the (actionKey, Action) pairs. */
	private static Map<String, Action> actionsMap = new HashMap<>();

	/** Constructor. */
	Action(String key) {
		actionKey = key;
	}

	/** Initializes the map with the (actionKey, Action) pairs. */
	static {
		for (Action action : Action.values()) {
			actionsMap.put(action.getActionKey(), action);
		}
	}

	/**
	 * Returns the actionKey
	 * 
	 * @return the actionKey
	 */
	public String getActionKey() {

		return actionKey;
	}

	/**
	 * Gets the Action by key, if there isn't a Action with the sent key, so the UNKNOWN value
	 * is returned
	 */
	public static Action getByKey(String key) {

		Action action = null;
		if (key != null && !key.isEmpty()) {
			action = actionsMap.get(key);
			if (action == null) {
				action = UNKNOWN;
			}
		}
		return action;
	}

}
