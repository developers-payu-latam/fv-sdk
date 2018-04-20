/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
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

import org.simpleframework.xml.Element;

import lombok.Data;

/**
 * Encapsulates the data for the matching of a control list.
 * The lists in Fraudvault are used to save data bases of reliable/good data(also named white lists)
 * and unreliable/bad data(also named black lists).
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
public class ListMatch {

	/** Indicates if some transaction field matchs with some value contained in a Fraudvault list. */
	@Element(name = "coincide", required = false)
	private boolean match;

	/** The name of the transaction field matching with some value contained in a Fraudvault list. */
	@Element(name = "parametro", required = false)
	private String transactionFieldName;

}
