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

	@Element(name = "nombre-completo", required = false)
	private String name;
	
	@Element(name = "fecha-nacimiento-titular", required = false)
	@Convert(DateConverter.class)
	private Date birthDate;
	
	@Element(name = "numero-documento", required = false)
	private String documentNumber;

	@Element(name = "telefono-oficina", required = false)
	private String officePhoneNumber;

	@Element(name = "telefono-residencia", required = false)
	private String homePhoneNumber;

	@Element(name = "telefono-movil", required = false)
	private String mobilePhoneNumber;

	@Element(name = "genero", required = false)
	private String gender;

	@Element(name = "direccion-correspondencia", required = false)
	private Address billingAddress;
	
}
