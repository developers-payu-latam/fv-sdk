package com.payulatam.fraudvault.model.request;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import com.payulatam.fraudvault.util.DateConverter;

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
