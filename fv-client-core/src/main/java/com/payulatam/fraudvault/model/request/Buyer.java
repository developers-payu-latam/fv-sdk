/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 17 de nov. de 2017
 */
package com.payulatam.fraudvault.model.request;

import org.simpleframework.xml.Element;

import lombok.Builder;
import lombok.Data;

/**
 * Data of the person who performs the transaction. It can be the same card/account holder or
 * other different person.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
@Builder
public class Buyer {

	@Element(name = "id", required = false)
	private String id;

	@Element(name = "nombres", required = false)
	private String name;

	@Element(name = "apellidos", required = false)
	private String surname;

	@Element(name = "correo-electronico", required = false)
	private String email;

	@Element(name = "huella", required = false)
	private Footprint footprint;
}
