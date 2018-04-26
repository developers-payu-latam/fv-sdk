/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 17 de nov. de 2017
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

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Builder;
import lombok.Data;

/**
 * Encapsulates the data of an address.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root
@Data
@Builder
public class Address {

	/** The ISO 3166 code of the country associate with the address. */
	@Element(name = "pais", required = false)
	private String countryIso;

	/** The name of the city  associate with the address. */
	@Element(name = "ciudad", required = false)
	private String city;

	/** The full address. */
	@Element(name = "calle", required = false)
	private String street;

	/** Postal code associated with the address. */
	@Element(name = "codigo-postal", required = false)
	private String zipCode;

}
