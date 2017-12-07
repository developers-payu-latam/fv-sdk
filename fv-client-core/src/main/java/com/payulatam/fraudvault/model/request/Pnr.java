package com.payulatam.fraudvault.model.request;

import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import lombok.Builder;
import lombok.Data;

/**
 * Encapsulates the data of the Passenger Name Records and other data related to airline tickets.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "pnr")
@Data
@Builder
public class Pnr {

	@Element(name = "id", required = false)
	private String bookingCode;

	@Element(name = "agente-reservador", required = false)
	private BookingAgent bookingAgent;

	@Element(name = "oficina-reserva", required = false)
	private Office bookingOffice;

	@Element(name = "oficina-venta", required = false)
	private Office sellerOffice;

	@Path("pasajeros")
	@ElementList(inline=true, required = false)
	private List<Passenger> passengers;

	@Path("trayectos")
	@ElementList(inline=true, required = true)
	private List<FlightPath> flightPaths;

	@Element(name = "tipo-viaje", required = false)
	private String tripType;

}
