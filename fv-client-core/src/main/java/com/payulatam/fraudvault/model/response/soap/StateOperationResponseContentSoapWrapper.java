/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 9 de nov. de 2017
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

package com.payulatam.fraudvault.model.response.soap;

import org.simpleframework.xml.Element;

import lombok.Data;

/**
 * Encapsulates the attributes of the response content for an operation on the transaction state in
 * Fraudvault.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public class StateOperationResponseContentSoapWrapper {

	/** The identifier of the transaction. */
	@Element(name = "transaccion-id", required = false)
	private String transactionId;

	/** The identifier of the transaction state. */
	@Element(name = "estado", required = false)
	private Integer state;

	/**
	 * A value of 1: it represents a successful response code; in case there were no errors,
	 * otherwise 2: it represents an error response code; in case there were errors.
	 */
	@Element(name = "codigo-respuesta", required = false)
	private Integer answerCode;

	/**  Message associated with the error, if an error occurs */
	@Element(name = "mensaje-error", required = false)
	private String errorMessage;

}
