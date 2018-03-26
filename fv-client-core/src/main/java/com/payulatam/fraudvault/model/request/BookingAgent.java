package com.payulatam.fraudvault.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Builder;
import lombok.Data;

/**
 * Data of the agent who performs booking of the travel tickets.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root
@Data
@Builder
public class BookingAgent {

	@Element(name = "id", required = false)
	private String id;

	@Element(name = "nombres", required = false)
	private String firstName;

	@Element(name = "apellidos", required = false)
	private String lastName;

	@Element(name = "correo-electronico", required = false)
	private String email;

	@Element(name = "telefono-oficina", required = false)
	private String officePhone;	

}
