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

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import com.payulatam.fd.util.DateConverter;

import lombok.Builder;
import lombok.Data;

/**
 * Data of the holder of the account or card involved in the payment.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root
@Data
@Builder
public class AccountHolder {

	/** The full name of the account/card holder. */
	@Element(name = "nombre-completo", required = false)
	private String name;
	
	/** The birthdate of the account/card holder. */
	@Element(name = "fecha-nacimiento-titular", required = false)
	@Convert(DateConverter.class)
	private Date birthDate;
	
	/** The number of ID of the account/card holder. */
	@Element(name = "numero-documento", required = false)
	private String idDocumentNumber;

	@Element(name = "telefono-oficina", required = false)
	private String officePhoneNumber;

	@Element(name = "telefono-residencia", required = false)
	private String homePhoneNumber;

	@Element(name = "telefono-movil", required = false)
	private String mobilePhoneNumber;

	/** The gender(F or M) of the account/card holder. */
	@Element(name = "genero", required = false)
	private String gender;

	/** The billing address of the account/card holder. */
	@Element(name = "direccion-correspondencia", required = false)
	private Address billingAddress;
	
}
