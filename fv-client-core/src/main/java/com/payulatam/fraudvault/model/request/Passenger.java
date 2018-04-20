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

package com.payulatam.fraudvault.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Builder;
import lombok.Data;

/**
 * Encapsulates the data of a Passenger.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "pasajero")
@Data
@Builder
public class Passenger {

	/** The identifier assigned by the client to the passenger. For example: the traveler number. */
	@Element(name = "id", required = false)
	private String id;

	/** The ISO 3166 code of the passenger's origin country. */
	@Element(name = "pais", required = false)
	private String country;

	/** The level assigned by the client to a passenger. Eg CORP */
	@Element(name = "nivel", required = false)
	private String level;

	@Element(name = "nombres", required = false)
	private String firstName;

	@Element(name = "apellidos", required = false)
	private String lastName;

	/** The type of passenger identification document. */
	@Element(name = "tipo-documento", required = false)
	private Integer idDocumentType;

	/** The passenger's identification document number. */
	@Element(name = "numero-documento", required = false)
	private String idDocumentNumber;

	@Element(name = "correo-electronico", required = false)
	private String email;

	@Element(name = "telefono-oficina", required = false)
	private String officePhone;

	@Element(name = "telefono-residencia", required = false)
	private String homePhone;

	@Element(name = "telefono-movil", required = false)
	private String mobilePhone;

	/** The address associated with the passenger. */
	@Element(name = "direccion", required = false)
	private Address address;

}
