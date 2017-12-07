/**
 * PayU Latam - Copyright (c) 2013 - 2017
 * http://www.payu.com.co
 * Date: 17 de nov. de 2017
 */
package com.payulatam.fraudvault.model.request;

import org.simpleframework.xml.Element;

import lombok.Builder;
import lombok.Data;

/**
 * Data of a merchant seller.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Data
@Builder
public class Seller {

	@Element(name = "id", required = false)
	protected String id;

	@Element(name = "rubro", required = false)
	protected Integer businessActivity;

	@Element(name = "tipo-vendedor", required = false)
	protected Integer merchantType;

	@Element(name = "codigo-clasificacion-comercio", required = false)
	protected String clasificationCode;

}
