/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 17 de oct. de 2017
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.payulatam.fd.client.retrofit.model.soap.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import lombok.Data;

/**
 * Encapsulates a soap message envelope for the prevalidation request.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com" >Claudia Jimena Rodriguez</a>
 */
@Root(name = "soapenv:Envelope")
@NamespaceList({
		@Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soapenv"),
		@Namespace(reference = "http://ws.maf.pagosonline.com/", prefix = "ws")
})
@Data
public class PrevalidationRequestSoapEnvelope {

	@Element(name = "ws:preValidar")
	@Path("soapenv:Body")
	private PrevalidationBodyData requestSoapEnvelopeBody;

}
