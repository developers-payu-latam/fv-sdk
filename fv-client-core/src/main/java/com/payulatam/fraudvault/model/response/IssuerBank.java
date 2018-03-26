package com.payulatam.fraudvault.model.response;

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
