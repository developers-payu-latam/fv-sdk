/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 16 de nov. de 2017
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

package com.payulatam.fd.model.response;

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

	/** The two letter ISO 3166-1 alpha-2 country code associated with the country of the IP address. */
	@Element(name = "codigo-iso-pais", required = false)
	private String ipCountryCode;

	/** The country name of the IP address. */
	@Element(name = "nombre-pais", required = false)
	private String ipCountry;

	/** A two character ISO-3166-2 or FIPS 10-4 code for the state/region associated with the IP address.*/
	@Element(name = "region", required = false)
	private String ipRegion;

	/** The city or town name associated with the IP address. */
	@Element(name = "ciudad", required = false)
	private String ipCity;

	/** The postal code associated with the IP address(only available for some countries).*/
	@Element(name = "codigo-postal", required = false)
	private String ipZipCode;

	/** The latitude of the IP address location. */
	@Element(name = "latitud", required = false)
	private Double ipLatitude;
	
	/** The longitude of the IP address location. */
	@Element(name = "longitud", required = false)
	private Double ipLongitude;

	/** The metro code associated with the IP address(only available for IP addresses in the US).*/
	@Element(name = "codigo-area-metropolitano", required = false)
	private Integer ipMetroAreaCode;

	/** The telephone area code associated with the IP address(only available for IP addresses in the US).*/
	@Element(name = "codigo-area-telefono", required = false)
	private String ipTelephoneAreaCode;

}
