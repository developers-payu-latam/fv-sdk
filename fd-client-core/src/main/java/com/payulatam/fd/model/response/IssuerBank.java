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

package com.payulatam.fd.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Data;

/**
 * Data of the credit/debit card issuer bank.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "banco-emisor")
@Data
public class IssuerBank {

	/** The two letter ISO 3166-1 alpha-2 country code associated with the country of the bank 
	 * issuing the card. For example: CO for Colombia and BR for Brazil. */
	@Element(name = "codigo-iso-pais", required = false)
	private String country;

	/** The bank name issuing the card. */
	@Element(name = "banco", required = false)
	private String bank;

	/** The bank phone number issuing the card. */
	@Element(name = "telefono", required = false)
	private String phoneNumber;

}
