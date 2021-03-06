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

	/** The booking ID. */
	@Element(name = "id", required = false)
	private String bookingCode;

	/** The identifier of sales agent(the person) making the booking. */
	@Element(name = "agente-reservador", required = false)
	private BookingAgent bookingAgent;

	/** The data of the office where the booking was made. */
	@Element(name = "oficina-reserva", required = false)
	private Office bookingOffice;

	/** The data of the office where the reservation was sold. */
	@Element(name = "oficina-venta", required = false)
	private Office sellerOffice;

	/** List of passengers of the booking. */
	@Path("pasajeros")
	@ElementList(inline=true, required = false)
	private List<Passenger> passengers;

	/** Data of the flights in the booking. */
	@Path("trayectos")
	@ElementList(inline=true, required = true)
	private List<FlightPath> flightPaths;
	
	/**
	 * The code of the trip type:
	 * OW = One Way
	 * RT = Round Trip
	 * MC = Multicity
	 */
	@Element(name = "tipo-viaje", required = false)
	private TripType tripType;

}
