/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de oct. de 2017
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

import java.util.Date;

import lombok.Data;

/**
 * Represents the base attributes in the response of the fraud detection service calls.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public class FDBaseResponse {

	/** Date of the response generation. */
	private Date responseDate;

	/** The answer code. */
	private GeneralAnswerCode generalAnswerCode;

	/** The general error code. */
	private GeneralErrorCode generalErrorCode;
	
	/** The answer code value. */
	private Integer generalAnswerCodeValue;
	
	/** The general error code value. */
	private Integer generalErrorCodeValue;

	/** Text describing the happened error. */
	private String generalErrorMessage;
	
	/**
	 * Indicates if there was an error in the process.
	 * 
	 * @return true if there was an error otherwise false.
	 */
	public boolean hasError() {
		return (generalAnswerCode != null && generalAnswerCode == GeneralAnswerCode.FAIL)
				|| generalErrorCode != null ;
	}

}
