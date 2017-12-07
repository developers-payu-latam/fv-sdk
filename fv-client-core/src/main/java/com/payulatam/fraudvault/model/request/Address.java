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

	@Element(name = "pais", required = false)
	private String countryIso;

	@Element(name = "ciudad", required = false)
	private String city;

	@Element(name = "calle", required = false)
	private String street;

	@Element(name = "codigo-postal", required = false)
	private String zipCode;

}
