/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 17 de nov. de 2017
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

package com.payulatam.fd.model.request;

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