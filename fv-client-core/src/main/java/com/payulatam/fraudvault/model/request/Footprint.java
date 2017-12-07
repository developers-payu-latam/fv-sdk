package com.payulatam.fraudvault.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Builder;
import lombok.Data;

/**
 * Encapsulates the data of a digital footprint of the buyer device.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root
@Data
@Builder
public class Footprint {

	@Element(name = "direccion-ip", required = false)
	private String ipAddress;

	@Element(name = "firma-dispositivo", required = false)
	private String deviceSignature;

	@Element(name = "user-agent", required = false)
	private String userAgent;

}
