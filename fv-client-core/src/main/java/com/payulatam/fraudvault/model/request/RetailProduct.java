package com.payulatam.fraudvault.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Builder;
import lombok.Data;

/**
 * Represents the product of a order.
 *
 * @author <a href="fredy.dorado@payulatam.com">Fredy Dorado</a>
 *
 */
@Root(name = "producto")
@Data
@Builder
public class RetailProduct {

	/** Code of the product. */
	@Element(name = "codigo", required = false)
	private String code;

	/** Name of the product. */
	@Element(name = "nombre", required = false)
	private String name;

	/** Description of the product. */
	@Element(name = "descripcion", required = false)
	private String description;

	/** Brand of the product. */
	@Element(name = "marca", required = false)
	private String brand;

	/** Category of the product. */
	@Element(name = "categoria", required = false)
	private String category;

	/** Product risk category. */
	@Element(name = "riesgo", required = false)
	private String risk;

	/** Additional product information. */
	@Element(name = "extra", required = false)
	private String extra;

}
