/**
 * PayU Latam - Copyright (c) 2013 - 2018
 * http://www.payu.com.co
 * Date: 26 de ene. de 2018
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

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the response with the result of a transaction state query operation.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class FDStateQueryResponse extends FDBaseResponse{
	
	/** The identifier of the transaction. */
	private String transactionId;

	/** The transaction state. */
	private TransactionState state;
	
	/** The identifier of the transaction state. */
	private Integer stateIdValue;

	/** The answer code. */
	private GeneralAnswerCode answerCode;
	
	/**  Message associated with the error, if an error occurs */
	private String errorMessage;
	
	/**
	 * Indicates if there was an error in the state query process.
	 * 
	 * @return true if there was an error otherwise false.
	 */
	@Override
	public boolean hasError() {
		return super.hasError() || (answerCode != null && answerCode == GeneralAnswerCode.FAIL);
	}

}
