/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 20 de mar. de 2018
 */
package com.payulatam.fraudvault.model.request;

/**
 * The payment method type values.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
public enum PaymentMethodType {

	CREDIT_CARD(1),
	DEBIT_CARD(2),
	ACH(3),
	BANK_TRANSFER(4),
	CASH(5),
	INTERNAL_TRANSFER(6),
	ONLINE_BANKING(7);

	int id;

	PaymentMethodType(int id) {
		this.id = id;
	}

	/**
	 * Returns the id
	 * @return the id
	 */
	public int getId() {
	
		return id;
	}

}
