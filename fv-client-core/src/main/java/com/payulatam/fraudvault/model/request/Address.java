package com.payulatam.fraudvault.model.request;

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
