/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
 */
package com.payulatam.fraudvault.model.response.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Data;

/**
 * Represents the location data of an IP address.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root(name = "ubicacion")
@Data
public class IpAddressLocation {

	@Element(name = "codigo-iso-pais", required = false)
	private String ipCountryCode;

	@Element(name = "nombre-pais", required = false)
	private String ipCountry;

	@Element(name = "region", required = false)
	private String ipRegion;

	@Element(name = "ciudad", required = false)
	private String ipCity;

	@Element(name = "codigo-postal", required = false)
	private String ipZipCode;

	@Element(name = "latitud", required = false)
	private Double ipLatitude;

	@Element(name = "longitud", required = false)
	private Double ipLongitude;

	@Element(name = "codigo-area-metropolitano", required = false)
	private Integer ipMetroAreaCode;

	@Element(name = "codigo-area-telefono", required = false)
	private String ipTelephoneAreaCode;

}
