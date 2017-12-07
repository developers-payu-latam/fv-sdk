package com.payulatam.fraudvault.model.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import lombok.Builder;
import lombok.Data;

/**
 * Encapsulates the data of the payment.
 * 
 * @author <a href="mailto:claudia.rodriguez@payulatam.com">Claudia Jimena Rodriguez</a>
 */
@Root
@Data
@Builder
public class PaymentInformation {

	@Element(name = "tipo-medio-pago", required = false)
	private Integer paymentMethodType;

	@Element(name = "medio-pago", required = false)
	private Integer paymentMethod;

	@Element(name = "pan", required = false)
	private String pan;

	@Element(name = "bin", required = false)
	private String bin;

	@Element(name = "numero", required = false)
	private String number;

	@Element(name = "numero-visible", required = false)
	private String maskedNumber;

	@Element(name = "banco", required = false)
	private String issuerBank;

	@Element(name = "moneda", required = false)
	private String currencyIso;

	@Element(name = "fecha-expiracion", required = false)
	private String expirationDate;

	@Element(name = "codigo-seguridad", required = false)
	private String securityCode;

	@Element(name = "cuotas", required = false)
	private Integer installmentsNumber;

	@Element(name = "titular", required = false)
	private AccountHolder holder;

}
