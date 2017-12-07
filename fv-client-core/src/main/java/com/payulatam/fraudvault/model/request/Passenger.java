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

	@Element(name = "id", required = false)
	private String id;

	@Element(name = "pais", required = false)
	private String country;

	@Element(name = "nivel", required = false)
	private String level;

	@Element(name = "nombres", required = false)
	private String firstName;

	@Element(name = "apellidos", required = false)
	private String lastName;

	@Element(name = "tipo-documento", required = false)
	private Integer documentType;

	@Element(name = "numero-documento", required = false)
	private String documentNumber;

	@Element(name = "correo-electronico", required = false)
	private String email;

	@Element(name = "telefono-oficina", required = false)
	private String officePhone;

	@Element(name = "telefono-residencia", required = false)
	private String homePhone;

	@Element(name = "telefono-movil", required = false)
	private String mobilePhone;

	@Element(name = "direccion", required = false)
	private Address address;

}
