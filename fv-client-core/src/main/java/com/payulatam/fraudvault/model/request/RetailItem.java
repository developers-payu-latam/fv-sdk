package com.payulatam.fraudvault.model.request;

import java.math.BigDecimal;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Builder;
import lombok.Data;

/**
 * Represents the purchase order item.
 *
 * @author <a href="fredy.dorado@payulatam.com">Fredy Dorado</a>
 *
 */
@Root(name = "item")
@Data
@Builder
public class RetailItem {

	/** The product of the order. */
	@Element(name = "producto", required = false)
	private RetailProduct product;
	
	/** Number of units of the product to buy. */
	@Element(name = "cantidad", required = false)
	private Integer quantity;
	
	/** Price per product. */
	@Element(name = "valor-unitario", required = false)
	private BigDecimal unitPrice;

}
