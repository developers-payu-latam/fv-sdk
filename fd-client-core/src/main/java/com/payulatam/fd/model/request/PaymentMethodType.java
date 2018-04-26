/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 20 de mar. de 2018
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

package com.payulatam.fd.model.request;

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
