/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 18 de oct. de 2017
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

package com.payulatam.fraudvault.model.request;

import lombok.Data;

/**
 * Encapsulates the data necessary for a client authentication with the Fraudvault service.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
@Data
public class Credentials {

	/** The client identifier, it's provided by PayU Latam. */
	private Long clientId;

	/** The login user assigned to the client, it's provided by PayU Latam. */
	private String login;

	/** The password assigned to the client, it's provided by PayU Latam. */
	private String password;
	
	public Credentials(Long clientId, String login, String password) {
		if (clientId == null || login == null || password == null) {
			throw new IllegalArgumentException("The authentication credentials must not be null");
		}
		this.clientId = clientId;
		this.login = login;
		this.password = password;
	}
}
