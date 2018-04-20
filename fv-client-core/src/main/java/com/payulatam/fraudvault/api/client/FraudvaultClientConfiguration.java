/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 20 de oct. de 2017
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

package com.payulatam.fraudvault.api.client;

import com.payulatam.fraudvault.model.request.Credentials;
import lombok.Data;
import lombok.Builder;

/**
 * Defines the configuration attributes for a Fraudvault client.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
@Data
@Builder
public class FraudvaultClientConfiguration {

	/** Indicates if the client should ignore invalid certificates to call .
	 * WARNING: This feature should be used only for testing porpoises!!!
	 * */
	@Builder.Default
	private boolean ignoreInvalidCertificates = false;

	/** URL of the Fraudvault service. */
	private String fraudvaultServiceBaseUrl;

	/** Connect timeout for the Fraudvault service connection in milliseconds. */
	@Builder.Default
	private Integer connectionTimeoutInMillis = 5000;

	/** Read timeout for Fraudvault service connection in milliseconds. */
	@Builder.Default
	private Integer readTimeoutInMillis  = 10000;

	@Builder.Default
	private boolean logHttpRequest = false;

	/** The Fraudvault authentication credentials. */
	private Credentials credentials;

}
