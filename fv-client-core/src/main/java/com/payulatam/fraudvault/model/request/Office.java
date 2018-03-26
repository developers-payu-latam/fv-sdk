package com.payulatam.fraudvault.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Builder;
import lombok.Data;

/**
 * Encapsulates the data of an office that book or sell airlines tickets.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root
@Data
@Builder
public class Office {

	@Element(name = "id", required = false)
	private String id;

	@Element(name = "pais", required = false)
	private String country;

}
