/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 19 de oct. de 2017
 */
package com.payulatam.fraudvault.client.retrofit.model.soap.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import lombok.Data;

/**
 * Encapsulates a soap message envelope of the fraudvault posvalidation response.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
@Data
@Root(name = "S:Envelope")
@NamespaceList({
		@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "S") })
public class PosvalidationResponseSoapEnvelope {

	@Element(name = "postValidarResponse")
	@Path("Body")
	@NamespaceList({ @Namespace(reference = "http://ws.maf.pagosonline.com", prefix = "ns2"),
			@Namespace(reference = "http://ws.maf.pagosonline.com/", prefix = "ns3") })
	private ResponseBodyData postvalidationResponseBodyData;

}
