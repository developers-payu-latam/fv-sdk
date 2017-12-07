package com.payulatam.fraudvault.model.request;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import lombok.Builder;
import lombok.Data;

/**
 * Represents the retail data of the purchase order.
 * 
 * @author <a href="fredy.dorado@payulatam.com">Fredy Dorado</a>
 *
 */
@Root(name = "retail")
@Data
@Builder
public class Retail {

	@ElementList(name = "item", inline = true)
	private List<RetailItem> items;

}
