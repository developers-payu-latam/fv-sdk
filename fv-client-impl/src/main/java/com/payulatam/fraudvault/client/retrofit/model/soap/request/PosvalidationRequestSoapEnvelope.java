/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 17 de oct. de 2017
 */
package com.payulatam.fraudvault.client.retrofit.model.soap.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Encapsulates a soap message envelope for the fraudvault posvalidation request.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
@Root(name = "soapenv:Envelope")
@NamespaceList({
		@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soapenv"),
		@Namespace(reference = "http://ws.maf.pagosonline.com/", prefix = "ws")
})
@Data
public class PosvalidationRequestSoapEnvelope {

	@Element(name = "ws:postValidar")
	@Path("soapenv:Body")
	private PosvalidationBodyData requestSoapEnvelopeBody;

	/**
	 * Encapsulates the parameters data of a soap envelope for the fraudvault posvalidation request.	 
	 */
	@Root
	@Data
	@EqualsAndHashCode(callSuper = true)
	public static class PosvalidationBodyData extends RequestBodyBaseData {

		@Element(name = "arg3")
		private String transactionId;
	}
}
