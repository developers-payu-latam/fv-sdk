package com.payulatam.fraudvault.model.request;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import com.payulatam.fraudvault.util.DateConverter;

import lombok.Builder;
import lombok.Data;

/**
 * Represents a flight path.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "trayecto")
@Data
@Builder
public class FlightPath {

	@Element(name = "fecha-salida", required = true)
	@Convert(DateConverter.class)
	private Date departureDate;

	@Element(name = "fecha-llegada", required = true)
	@Convert(DateConverter.class)
	private Date arrivalDate;

	@Element(name = "vuelo", required = true)
	private String flightNumber;

	@Element(name = "origen", required = true)
	private String origin;

	@Element(name = "destino", required = true)
	private String destination;

	@Element(name = "clase", required = true)
	private String travelClass;

}
