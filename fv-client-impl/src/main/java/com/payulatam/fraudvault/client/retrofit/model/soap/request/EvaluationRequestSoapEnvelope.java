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

/**
 * Encapsulates a soap message envelope for the fraudvault evaluation request.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
@Root(name = "soapenv:Envelope")
@NamespaceList({
		@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soapenv"),
		@Namespace(reference = "http://ws.maf.pagosonline.com/", prefix = "ws")
})
@Data
public class EvaluationRequestSoapEnvelope {

	@Element(name = "ws:evaluar")
	@Path("soapenv:Body")
	private PrevalidationBodyData requestSoapEnvelopeBody;

}
