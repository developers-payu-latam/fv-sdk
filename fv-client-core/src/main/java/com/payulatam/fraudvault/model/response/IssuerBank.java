package com.payulatam.fraudvault.model.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Data;

/**
 * Encapusules the data of the credit card issuer bank.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "banco-emisor")
@Data
public class IssuerBank {

	/** The country ISO code. */
	@Element(name = "codigo-iso-pais", required = false)
	private String country;

	/** The bank name. */
	@Element(name = "banco", required = false)
	private String bank;

	/** The bank phone number. */
	@Element(name = "telefono", required = false)
	private String phoneNumber;

}
