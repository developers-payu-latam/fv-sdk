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
