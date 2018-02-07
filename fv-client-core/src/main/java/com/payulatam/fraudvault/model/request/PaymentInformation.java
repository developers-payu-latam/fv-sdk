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

	/** Identifier of the payment method type. For example: 1 corresponds to Credit card, 
	 * 2 corresponds to Debit card. */
	@Element(name = "tipo-medio-pago", required = false)
	private Integer paymentMethodType;

	/** Identifier of the specific payment method. It may vary depend on the country. */
	@Element(name = "medio-pago", required = false)
	private Integer paymentMethod;
	
	/** Number of the debit/credit card used by the buyer. */
	@Element(name = "pan", required = false)
	private String pan;

	/**
	 * The BIN-Bank Identification Number or IIN-Issuer Identification Number of the card used
	 * by the buyer. It is the 6 first digits of the card number.
	 */
	@Element(name = "bin", required = false)
	private String bin;

	/** Number of the card used by the buyer encrypted in SHA-256. */
	@Element(name = "numero", required = false)
	private String number;

	/** The last 4 digits of the card number used by the buyer. */
	@Element(name = "numero-visible", required = false)
	private String maskedNumber;

	/** Name of the issuing bank of the card used by the buyer. */
	@Element(name = "banco", required = false)
	private String issuerBank;

	/** ISO code 4217 of the currency used in the transaction. For example: USD for US dollar.*/
	@Element(name = "moneda", required = false)
	private String currencyIso;

	/**
	 * Expiration date of the card used in the transaction with MM-YY format. For example:10-25 for
	 * October 2025
	 */
	@Element(name = "fecha-expiracion", required = false)
	private String expirationDate;

	/** Security code of the credit card used by the buyer. */
	@Element(name = "codigo-seguridad", required = false)
	private String securityCode;

	/** Number of payment installments. */
	@Element(name = "cuotas", required = false)
	private Integer installmentsNumber;

	/** The account/card holder data. */
	@Element(name = "titular", required = false)
	private AccountHolder holder;

}
